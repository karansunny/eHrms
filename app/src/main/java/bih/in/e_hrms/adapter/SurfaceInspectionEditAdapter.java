package bih.in.e_hrms.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import bih.in.e_hrms.R;

import java.util.ArrayList;

import bih.in.e_hrms.database.DataBaseHelper;
import bih.in.e_hrms.entity.SurfaceSchemeEntity;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.utility.GlobalVariables;
import bih.in.e_hrms.utility.Utiilties;

public class SurfaceInspectionEditAdapter extends BaseAdapter {

    public DataBaseHelper dataBaseHelper;
    Activity activity;
    LayoutInflater mInflater;
    ArrayList<SurfaceSchemeEntity> ThrList=new ArrayList<>();
    UserDetails userInfo;
    TextView tv_title;
    String panchayatCode,panchayatName="";

    public SurfaceInspectionEditAdapter(Activity listViewshowedit, ArrayList<SurfaceSchemeEntity> rlist, UserDetails userInfo, TextView tv_title) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        this.userInfo = userInfo;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.tv_title = tv_title;
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
        convertView = mInflater.inflate(R.layout.adaptor_surface_inspection_edit, null);

        holder = new ViewHolder();
        holder.tv_scheme_name=(TextView)convertView.findViewById(R.id.tv_scheme_name);
        holder.tv_scheme_Id=(TextView)convertView.findViewById(R.id.tv_scheme_Id);
        holder.tv_scheme_type=(TextView)convertView.findViewById(R.id.tv_scheme_type);
        holder.tv_inspected_by=(TextView)convertView.findViewById(R.id.tv_inspected_by);
        holder.tv_designation=(TextView)convertView.findViewById(R.id.tv_designation);
        holder.tv_work_status=(TextView)convertView.findViewById(R.id.tv_work_status);

        holder.btn_delete=(Button) convertView.findViewById(R.id.btn_delete);
        holder.btn_edit=(Button) convertView.findViewById(R.id.btn_edit);
        holder.btn_upload=(Button) convertView.findViewById(R.id.btn_upload);

        convertView.setTag(holder);

        holder.tv_scheme_name.setText(ThrList.get(position).getSCHEME_NAME());
        holder.tv_scheme_Id.setText(ThrList.get(position).getSCHEME_ID());
        holder.tv_scheme_type.setText(ThrList.get(position).getTYPE_OF_SCHEME());
        holder.tv_inspected_by.setText(ThrList.get(position).getSurveyorName());
        holder.tv_designation.setText(ThrList.get(position).getInspectionBy());
        holder.tv_work_status.setText(ThrList.get(position).getWorkStatus());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(activity)
                        .setIcon(R.drawable.logo)
                        .setTitle("Delete Inspection!!")
                        .setMessage("Are you sure you want to delete this scheme inspection?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dataBaseHelper = new DataBaseHelper(activity);
                                String inspectionid = String.valueOf(ThrList.get(position).getSCHEME_ID());
                                //long c = dataBaseHelper.ResetSchemeInspectionDetail(inspectionid);
                                ThrList.remove(position);
                                notifyDataSetChanged();
                                tv_title.setText("Surface Scheme Inspection List ("+ThrList.size()+")");
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();


            }
        });

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(activity, SurfaceInspectionEntryActivity.class);
//                i.putExtra("data", ThrList.get(position));
//                activity.startActivity(i);
            }
        });

        holder.btn_upload.setOnClickListener(new View.OnClickListener() {
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
                    new AlertDialog.Builder(activity)
                            .setIcon(R.drawable.logo)
                            .setTitle("Upload Inspection")
                            .setMessage("Are you sure you want to upload this inspection record?")
                            .setCancelable(false)
                            .setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //new UploadInspectionDetail(ThrList.get(position), position).execute();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();

                }
            }
        });
        return convertView;
    }


    private class ViewHolder {
        TextView tv_scheme_name,tv_scheme_Id,tv_scheme_type,tv_inspected_by,tv_designation,tv_work_status;
        Button btn_delete,btn_edit,btn_upload;

    }

//    private class UploadInspectionDetail extends AsyncTask<String, Void, SurfaceInspectionResponse> {
//        SurfaceSchemeEntity data;
//        SurfaceSchemeEntity dataImg;
//        String version;
//        int position;
//        private final ProgressDialog dialog = new ProgressDialog(activity);
//
//        UploadInspectionDetail(SurfaceSchemeEntity data, int position, String version) {
//            this.data = data;
//            this.position = position;
//            this.version = version;
//        }
//
//        public UploadInspectionDetail(SurfaceSchemeEntity data, int position) {
//            this.data = data;
//            this.position = position;
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            this.dialog.setCanceledOnTouchOutside(false);
//            this.dialog.setMessage("Uploading Inspection Detail...");
//            this.dialog.show();
//        }
//
//        @Override
//        protected SurfaceInspectionResponse doInBackground(String... param) {
//            Log.e("schemeId", data.getSCHEME_ID());
//            dataBaseHelper=new DataBaseHelper(activity);
//            dataImg=dataBaseHelper.getInspectedSurfaceSchemeDetailForImage(data.getSCHEME_ID());
//            return WebServiceHelper.uploadSurfaceInspectionData(data,dataImg, userInfo);
//            //return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(SurfaceInspectionResponse result) {
//            if (this.dialog.isShowing()) {
//                this.dialog.dismiss();
//            }
//            Log.d("Responsevalue",""+result);
//
//            if (result != null) {
//                if (result.isAuthenticated()) {
//
//                    DataBaseHelper localDBHelper = new DataBaseHelper(activity);
//                    long isDel = localDBHelper.ResetSchemeInspectionDetail(data.getSCHEME_ID());
//
//                    ThrList.remove(position);
//                    notifyDataSetChanged();
//
//                    if (isDel > 0) {
//                        Log.e("messagdelete", "Data deleted !!");
//
//                    } else {
//                        Log.e("message", "data is uploaded but not deleted !!");
//                    }
//
//                    chk_msg("Successfull", "Inspection record has been successfully uploaded.");
//                }
//                else{
//                    Utiilties.ShowMessage(activity,"Upload Failed!!", result.getMessage());
//                }
//
//            }
//            else {
//                Toast.makeText(activity, "Failed to upload record (Null)!!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public void chk_msg(String title, String msg) {
        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        ab.setCancelable(false);
        ab.setIcon(R.drawable.logo);
        ab.setTitle(title);
        ab.setMessage(msg);
        Dialog dialog = new Dialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        // ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
        ab.show();
    }
}
