package bih.in.e_niwas.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exp.e_niwas.R;

import java.util.ArrayList;

import bih.in.e_niwas.entity.SurfaceSchemeEntity;
import bih.in.e_niwas.entity.UserDetails;
import bih.in.e_niwas.utility.GlobalVariables;
import bih.in.e_niwas.utility.Utiilties;
import bih.in.e_niwas.web_services.WebServiceHelper;


public class SurfaceSchemeAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<SurfaceSchemeEntity> ThrList=new ArrayList<>();
    UserDetails userInfo;
    String panchayatCode,panchayatName="";

    public SurfaceSchemeAdapter(Activity listViewshowedit, ArrayList<SurfaceSchemeEntity> rlist, UserDetails userInfo) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        this.userInfo = userInfo;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ThrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //if (convertView == null) {
        convertView = mInflater.inflate(R.layout.adaptor_surface_scheme, null);

        holder = new ViewHolder();
        holder.tv_scheme_name=(TextView)convertView.findViewById(R.id.tv_scheme_name);
        holder.tv_scheme_id=(TextView)convertView.findViewById(R.id.tv_scheme_id);
        holder.tv_scheme_type=(TextView)convertView.findViewById(R.id.tv_scheme_type);
        holder.tv_water_source=(TextView)convertView.findViewById(R.id.tv_water_source);
        holder.tv_fin_yr=(TextView)convertView.findViewById(R.id.tv_fin_yr);
        holder.tv_fund_type=(TextView)convertView.findViewById(R.id.tv_fund_type);
        holder.tv_nit_no=(TextView)convertView.findViewById(R.id.tv_nit_no);
        holder.tv_district=(TextView)convertView.findViewById(R.id.tv_district);
        holder.tv_block=(TextView)convertView.findViewById(R.id.tv_block);
        holder.tv_panchayat=(TextView)convertView.findViewById(R.id.tv_panchayat);

        holder.btn_view=(Button) convertView.findViewById(R.id.btn_view);
        holder.btn_inspect=(Button) convertView.findViewById(R.id.btn_inspect);

        convertView.setTag(holder);

        holder.tv_scheme_name.setText(ThrList.get(position).getSCHEME_NAME());
        holder.tv_scheme_id.setText(ThrList.get(position).getSCHEME_ID());
        holder.tv_scheme_type.setText(ThrList.get(position).getTYPE_OF_SCHEME());
        holder.tv_water_source.setText(ThrList.get(position).getSOURCE_OF_WATER());
        holder.tv_fin_yr.setText(ThrList.get(position).getFINANCIAL_YEAR());
        holder.tv_fund_type.setText(ThrList.get(position).getFund_Type());
        holder.tv_nit_no.setText(ThrList.get(position).getNIT_No());
        holder.tv_district.setText(ThrList.get(position).getDistrict());
        holder.tv_block.setText(ThrList.get(position).getBlock());
        holder.tv_panchayat.setText(ThrList.get(position).getPanchayat());

        holder.btn_inspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(activity, SurfaceInspectionEntryActivity.class);
//                i.putExtra("data", ThrList.get(position));
//                activity.startActivity(i);
            }
        });

        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utiilties.isOnline(activity) == false) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(activity);
                    ab.setTitle("Internet Connnection Error!!!");
                    ab.setMessage("Please turn on your mobile data or wifi connection");
                    ab.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            GlobalVariables.isOffline = false;
                            Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            activity.startActivity(I);
                        }
                    });
                    ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });

                    ab.show();

                } else {
                    //new SyncSchemeData(ThrList.get(position), userInfo).execute();
                }
            }
        });
        return convertView;
    }


    private class ViewHolder {
        TextView tv_scheme_name,tv_scheme_id,tv_scheme_type,tv_water_source,tv_fin_yr,tv_fund_type,tv_nit_no,tv_district,tv_block,tv_panchayat;
        Button btn_view,btn_inspect;

    }

//    private class SyncSchemeData extends AsyncTask<String, Void, ArrayList<SurfaceInspectionDetailEntity>> {
//        private final ProgressDialog dialog = new ProgressDialog(activity);
//
//        SurfaceSchemeEntity scheme;
//        UserDetails userInfo;
//
//        public SyncSchemeData(SurfaceSchemeEntity scheme, UserDetails userInfo) {
//            this.scheme = scheme;
//            this.userInfo = userInfo;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            this.dialog.setCanceledOnTouchOutside(false);
//            this.dialog.setMessage("Loading Inspection Detail...");
//            this.dialog.show();
//        }
//
//        @Override
//        protected ArrayList<SurfaceInspectionDetailEntity> doInBackground(String...arg) {
//
//            return WebServiceHelper.getSurfaceSchemeInspectionData(userInfo.getUserrole(), userInfo.getUserID(), userInfo.getPassword(),scheme.getSCHEME_ID(),"","","","");
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<SurfaceInspectionDetailEntity> result) {
//            if (this.dialog.isShowing()) {
//                this.dialog.dismiss();
//            }
//
//            if(result.size() > 0 && result.get(0).isAuthenticated()){
//
////                Intent i = new Intent(activity, SurfaceViewInspectionActivity.class);
////                i.putExtra("data", scheme);
////                i.putExtra("user", userInfo);
////                i.putExtra("dataList", result);
////                activity.startActivity(i);
//
//            }else{
//                Toast.makeText(activity, "No Inspection Detail Found!!", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }

}
