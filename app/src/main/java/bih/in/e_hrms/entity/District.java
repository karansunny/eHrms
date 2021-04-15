package bih.in.e_hrms.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class District implements KvmSerializable, Serializable {
	
	private static final long serialVersionUID = 1L;

	public static Class<District> DISTRICT_CLASS = District.class;
	private String _DistCode = "";
	private String _DistName = "";
	private String _DistNameHN = "";
	private String DistCode3 = "";
	private String Zone = "";
	private String Circle = "";
	private String _StateCode = "";

//	public District(SoapObject obj, int i) {
//		this._DistCode = obj.getProperty("DISTRICTCODE").toString();
//		this._DistName = obj.getProperty("DISTRICTNAME").toString();
//
//	}

	public District(SoapObject obj) {
		
		this._DistCode = obj.getProperty("DistCode").toString();
		this._DistName = obj.getProperty("DistName").toString();
		this.DistCode3 = obj.getProperty("DistCode3").toString();
		this.Zone = obj.getProperty("Zone").toString();
		this.Circle = obj.getProperty("Circle").toString();
		//this._DistNameHN = obj.getProperty("DistNameHN").toString();
		//this._StateCode = obj.getProperty("State_Code").toString();

	}

	public District() {

	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public String getDistCode3() {
		return DistCode3;
	}

	public void setDistCode3(String distCode3) {
		DistCode3 = distCode3;
	}

	public String getZone() {
		return Zone;
	}

	public void setZone(String zone) {
		Zone = zone;
	}

	public String getCircle() {
		return Circle;
	}

	public void setCircle(String circle) {
		Circle = circle;
	}

	public String get_DistNameHN() {
		return _DistNameHN;
	}

	public void set_DistNameHN(String _DistNameHN) {
		this._DistNameHN = _DistNameHN;
	}

	public String get_DistCode() {
		return _DistCode;
	}

	public void set_DistCode(String _DistCode) {
		this._DistCode = _DistCode;
	}

	public String get_DistName() {
		return _DistName;
	}

	public void set_DistName(String _DistName) {
		this._DistName = _DistName;
	}

	public String get_StateCode() {
		return _StateCode;
	}

	public void set_StateCode(String _StateCode) {
		this._StateCode = _StateCode;
	}



	

}
