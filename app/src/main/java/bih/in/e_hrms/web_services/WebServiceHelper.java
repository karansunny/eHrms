package bih.in.e_hrms.web_services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import bih.in.e_hrms.entity.AllotedSiteEntity;
import bih.in.e_hrms.entity.AttendacneStatus;
import bih.in.e_hrms.entity.District;
import bih.in.e_hrms.entity.EquipmentEntity;
import bih.in.e_hrms.entity.PanchayatEntity;
import bih.in.e_hrms.entity.SignUp;
import bih.in.e_hrms.entity.SurfaceInspectionDetailEntity;
import bih.in.e_hrms.entity.SurfaceInspectionEntity;
import bih.in.e_hrms.entity.SurfaceSchemeEntity;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.entity.Versioninfo;
import bih.in.e_hrms.entity.VillageListEntity;
import bih.in.e_hrms.entity.ward;
import bih.in.e_hrms.utility.AppConstants;

import static org.apache.http.util.EntityUtils.getContentCharSet;

public class WebServiceHelper {

    //public static final String SERVICENAMESPACE = "http://minorirrigation.bihar.gov.in/";
    public static final String SERVICENAMESPACE = "http://ehrms.webglobalinfotech.com/";

    public static final String SERVICEURL1 = "http://ehrms.webglobalinfotech.com/HrmsWebService.asmx";


    public static final String APPVERSION_METHOD = "getAppLatest";
    public static final String AUTHENTICATE_METHOD = "Authenticate";
    public static final String ATTENDANCE_STATUS_METHOD = "AttendanceStatus";
    public static final String GET_ALLOTED_SITE_METHOD = "GetNewSiteAllowtted";

    public static final String ATTENDANCE_MARKIN_METHOD = "AttendanceIn";
    public static final String ATTENDANCE_MARKOUT_METHOD = "AttendanceOut";
    public static final String GET_EQUIPMENTLIST_METHOD = "GetEquipmentList";
    public static final String MARK_EQUIPMENTLIST_METHOD = "MarkEquipmentList";

    static String rest;

    public static Versioninfo CheckVersion(String version) {
        Versioninfo versioninfo;
        SoapObject res1;
        try {

            res1=getServerData(APPVERSION_METHOD, Versioninfo.Versioninfo_CLASS,"IMEI","Ver","0",version);
            SoapObject final_object = (SoapObject) res1.getProperty(0);

            versioninfo = new Versioninfo(final_object);

        } catch (Exception e) {

            return null;
        }
        return versioninfo;

    }

    public static String resizeBase64Image(String base64image){
        byte [] encodeByte= Base64.decode(base64image.getBytes(), Base64.DEFAULT);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPurgeable = true;
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length,options);


        if(image.getHeight() <= 200 && image.getWidth() <= 200){
            return base64image;
        }
        image = Bitmap.createScaledBitmap(image, 100, 100, false);

        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, baos);

        byte [] b=baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public static UserDetails Login(String User_ID, String Pwd) {
        try {
            SoapObject res1;
            res1=getServerData(AUTHENTICATE_METHOD, UserDetails.getUserClass(),"UserID","Password",User_ID,Pwd);
            if (res1 != null) {
                return new UserDetails(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AttendacneStatus getAttendanceStatus(String User_ID) {
        try {
            SoapObject res1;
            res1=getServerData(ATTENDANCE_STATUS_METHOD, UserDetails.getUserClass(),"EmpId",User_ID);
            if (res1 != null) {
                return new AttendacneStatus(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AllotedSiteEntity getAllotedSiteStatus(String empId) {
        try {
            SoapObject res1;
            res1=getServerData(GET_ALLOTED_SITE_METHOD, AllotedSiteEntity.AllotedSiteEntity_CLASS,"EmpId",empId);
            if (res1 != null) {
                return new AllotedSiteEntity(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String markInAttendanceStatus(String userId, String empId, String deviceId, String latitude, String longitude) {
        SoapObject request = new SoapObject(SERVICENAMESPACE, ATTENDANCE_MARKIN_METHOD);

        request.addProperty("EmpId", empId);
        request.addProperty("IsIn", "Y");
        request.addProperty("UserID", userId);
        request.addProperty("SystemInIp", deviceId);
        request.addProperty("InLat", latitude);
        request.addProperty("InLong", longitude);

        Log.e("Location: ", "Lat-"+latitude+", Long-"+longitude);

        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + ATTENDANCE_MARKIN_METHOD,envelope);
            rest = envelope.getResponse().toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "0";
        }
        return rest;
    }

    public static String markOutAttendanceStatus(String userId, String empId, String deviceId, String latitude, String longitude) {
        SoapObject request = new SoapObject(SERVICENAMESPACE, ATTENDANCE_MARKOUT_METHOD);

        request.addProperty("EmpId", empId);
        request.addProperty("IsOut", "Y");
        request.addProperty("OutUserID", userId);
        request.addProperty("SystemOutIp", deviceId);
        request.addProperty("OutLat", latitude);
        request.addProperty("OutLong", longitude);

        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + ATTENDANCE_MARKOUT_METHOD,envelope);
            rest = envelope.getResponse().toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "0";
        }
        return rest;
    }

    public static ArrayList<EquipmentEntity> getEquipmentList(String empId) {

        SoapObject res1;
        res1=getServerData(GET_EQUIPMENTLIST_METHOD, EquipmentEntity.EquipmentEntity_CLASS,  "EmpId",empId);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<EquipmentEntity> fieldList = new ArrayList();

        for (int i = 0; i < TotalProperty; i++) {
            if (res1.getProperty(i) != null) {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    EquipmentEntity info= new EquipmentEntity(final_object);
                    fieldList.add(info);
                }
            } else
                return fieldList;
        }

        return fieldList;
    }

    public static String uploadEquipmentStatus(AllotedSiteEntity data, ArrayList<EquipmentEntity> list){

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;
        try
        {
            docBuilder = dbfac.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0, ParserConfigurationException!!";
        }
        DOMImplementation domImpl = docBuilder.getDOMImplementation();
        Document doc = domImpl.createDocument(SERVICENAMESPACE,    MARK_EQUIPMENTLIST_METHOD, null);
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(true);

        Element poleElement = doc.getDocumentElement();

        poleElement.appendChild(getSoapPropert(doc, "_EmpId",data.getEmpId()));
        poleElement.appendChild(getSoapPropert(doc, "_SiteId",data.getSiteId()));
        poleElement.appendChild(getSoapPropert(doc, "_MoveId",data.getMoveId()));
        poleElement.appendChild(getSoapPropert(doc, "_EquId",data.getEquId()));

        //--------------Array-----------------//
        Element pdlsElement = doc.createElement("UploadPValues");

        for(int x=0;x<list.size();x++)
        {
            Element pdElement = doc.createElement("UploadEquipmentParameterValue");
            Element fid = doc.createElement("MoveId");
            fid.appendChild(doc.createTextNode(data.getMoveId()));
            pdElement.appendChild(fid);

            Element vLebel11 = doc.createElement("EquId");
            vLebel11.appendChild(doc.createTextNode(list.get(x).getId()));
            pdElement.appendChild(vLebel11);

            Element vLebel = doc.createElement("IsApproved");
            vLebel.appendChild(doc.createTextNode("Y"));
            pdElement.appendChild(vLebel);

            pdlsElement.appendChild(pdElement);
        }

        poleElement.appendChild(pdlsElement);

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;
        String res = "0";
        try
        {
            try
            {
                trans = transfac.newTransformer();
            }
            catch (TransformerConfigurationException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "0, TransformerConfigurationException";
            }

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            BasicHttpResponse httpResponse = null;
            try
            {
                trans.transform(source, result);
            }
            catch (TransformerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "0, TransformerException";
            }

            String SOAPRequestXML = sw.toString();

            String startTag = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchem\" "
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" >  "
                    + "<soap:Body > ";
            String endTag = "</soap:Body > " + "</soap:Envelope>";

            try
            {
                HttpPost httppost = new HttpPost(SERVICEURL1);

                StringEntity sEntity = new StringEntity(startTag + SOAPRequestXML+ endTag, HTTP.UTF_8);

                sEntity.setContentType("text/xml;charset=UTF-8");
                httppost.setEntity(sEntity);
                HttpClient httpClient = new DefaultHttpClient();
                httpResponse = (BasicHttpResponse) httpClient.execute(httppost);

                HttpEntity entity = httpResponse.getEntity();

                if (httpResponse.getStatusLine().getStatusCode() == 200|| httpResponse.getStatusLine().getReasonPhrase().toString().equals("OK"))
                {
                    String output = _getResponseBody(entity);

                    res = parseRespnse(output);
                }
                else
                {
                    res = "0, Server no reponse";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "0, Exception Caught";
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0, Exception Caught";
        }
        // response.put("HTTPStatus",httpResponse.getStatusLine().toString());
        return res;
    }

    public static Element getSoapPropert(Document doc, String key, String value){
        Element eid = doc.createElement(key);
        eid.appendChild(doc.createTextNode(value));
        return eid;
    }

    public static String parseRespnse(String xml){
        String result = "Failed to parse";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try
        {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("MarkEquipmentListResult");
            result = list.item(0).getTextContent();
            //System.out.println(list.item(0).getTextContent());
        }
        catch (ParserConfigurationException e)
        {
        }
        catch (SAXException e)
        {
        }
        catch (IOException e)
        {
        }

        return result;
    }

    public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {

        if (entity == null)
        {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }

        InputStream instream = entity.getContent();

        if (instream == null)
        {
            return "";
        }

        if (entity.getContentLength() > Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        }

        String charset = getContentCharSet(entity);

        if (charset == null)
        {
            charset = org.apache.http.protocol.HTTP.DEFAULT_CONTENT_CHARSET;
        }

        Reader reader = new InputStreamReader(instream, charset);

        StringBuilder buffer = new StringBuilder();

        try
        {

            char[] tmp = new char[1024];

            int l;

            while ((l = reader.read(tmp)) != -1)
            {
                buffer.append(tmp, 0, l);
            }

        }
        finally
        {
            reader.close();
        }

        return buffer.toString();

    }


    public static SoapObject getServerData(String methodName, Class bindClass)
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }


    public static SoapObject getServerData(String methodName, Class bindClass, String param, String value )
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param,value);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }



    public static SoapObject getServerData(String methodName, Class bindClass, String param1, String param2, String value1, String value2 )
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static SoapObject getServerData(String methodName, Class bindClass, String param1, String param2, String param3, String value1, String value2, String value3 )
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            request.addProperty(param3,value3);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }
    public static SoapObject getServerData(String methodName, Class bindClass, String param1, String param2, String param3, String param4, String value1, String value2, String value3, String value4 )
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            request.addProperty(param3,value3);
            request.addProperty(param4,value4);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }
    public static SoapObject getServerData(String methodName, Class bindClass, String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8)
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            request.addProperty(param3,value3);
            request.addProperty(param4,value4);
            request.addProperty(param5,value5);
            request.addProperty(param6,value6);
            request.addProperty(param7,value7);
            request.addProperty(param8,value8);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

}
