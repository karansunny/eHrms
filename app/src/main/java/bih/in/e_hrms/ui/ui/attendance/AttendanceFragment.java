package bih.in.e_hrms.ui.ui.attendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.text.DecimalFormat;
import java.util.ArrayList;

import bih.in.e_hrms.R;
import bih.in.e_hrms.entity.AllotedSiteEntity;
import bih.in.e_hrms.entity.AttendacneStatus;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.ui.EquipmentListActivity;
import bih.in.e_hrms.ui.ui.home.HomeFragment;
import bih.in.e_hrms.utility.AppConstants;
import bih.in.e_hrms.utility.CommonPref;
import bih.in.e_hrms.utility.Utiilties;
import bih.in.e_hrms.web_services.WebServiceHelper;

public class AttendanceFragment extends Fragment {

    TextView tv_date,tv_day,tv_note,tv_username,tv_site,tv_other_site_name;
    LinearLayout ll_mark_in,ll_mark_out;
    ImageView iv_mark_in,iv_mark_out;
    Button btn_mark,btn_move;
    CardView cv_alloted_Site;

    UserDetails userInfo;
    AttendacneStatus attendance;
    AllotedSiteEntity allotedSite;
    String deviceId = "NA";

    private ProgressDialog dialog;

    String latitude="",longitud="";

    LocationManager mlocManager = null;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_attendance, container, false);

        initialize(root);
        getUserDetail();

        dialog = new ProgressDialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);

        btn_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Utiilties.isOnline(getActivity())){
                    final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){

                        locationManager();

                    }else{
                        Utiilties.AlertForNoGps(getActivity());
                    }

                }else{
                    Utiilties.showAlet(getActivity());
                }

            }
        });

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Utiilties.isOnline(getActivity())){
                    Intent intent = new Intent(getContext(), EquipmentListActivity.class);
                    intent.putExtra(AppConstants.ALLOTEDSITE, allotedSite);
                    startActivityForResult(intent, AppConstants.EQUIPMENTCODE);

                }else{
                    Utiilties.showAlet(getActivity());
                }

            }
        });

        return root;
    }

    public void initialize(View root){
        tv_username = root.findViewById(R.id.tv_username);
        tv_date = root.findViewById(R.id.tv_date);
        tv_day = root.findViewById(R.id.tv_day);
        tv_note = root.findViewById(R.id.tv_note);
        tv_site = root.findViewById(R.id.tv_site);
        tv_other_site_name = root.findViewById(R.id.tv_other_site_name);

        ll_mark_in = root.findViewById(R.id.ll_mark_in);
        ll_mark_out = root.findViewById(R.id.ll_mark_out);

        iv_mark_in = root.findViewById(R.id.iv_mark_in);
        iv_mark_out = root.findViewById(R.id.iv_mark_out);

        btn_mark = root.findViewById(R.id.btn_mark);
        btn_move = root.findViewById(R.id.btn_move);

        cv_alloted_Site = root.findViewById(R.id.cv_alloted_Site);

        ll_mark_in.setVisibility(View.GONE);
        ll_mark_out.setVisibility(View.GONE);
        btn_mark.setVisibility(View.GONE);
        tv_note.setVisibility(View.GONE);
        cv_alloted_Site.setVisibility(View.GONE);
    }

    public void getUserDetail(){
        userInfo = CommonPref.getUserDetails(getActivity());
        tv_username.setText(userInfo.get_FName());

        tv_date.setText(Utiilties.getDateStringInFormat("dd, MMMM yyyy"));
        tv_day.setText(Utiilties.getDateStringInFormat("EEEE"));
        deviceId = Utiilties.getDeviceIMEI(getContext());

        if(Utiilties.isOnline(getActivity())){
            new AttendanceStatusTask(userInfo.getEmpId()).execute();

            //new SyncAllotedSite(userInfo.getEmpId()).execute();
        }else{
            Utiilties.showAlet(getActivity());
        }
    }

    public void updateAttendaceToServer(){
        if(attendance.getEmpId().equals("anyType{}") && attendance.getIsIn().equals("anyType{}")){
            new MarkAttendanceTask(AppConstants.MARKIN, latitude, longitud).execute();
        }else if (attendance.getIsOut().equals("anyType{}") && attendance.getIsIn().equals("Y")){
            new MarkAttendanceTask(AppConstants.MARKOUT, latitude, longitud).execute();
        }
    }

    public void updateStatus(){
        if (attendance.getSiteName().equals("anyType{}")){
            tv_site.setVisibility(View.GONE);
        }else{
            tv_site.setText(attendance.getSiteName());
        }

        if(attendance.getEmpId().equals("anyType{}")){
            ll_mark_in.setVisibility(View.VISIBLE);
            iv_mark_in.setVisibility(View.GONE);
            btn_mark.setVisibility(View.VISIBLE);
        }

        if (attendance.getIsOut().equals("anyType{}") && attendance.getIsIn().equals("Y")){
            ll_mark_in.setVisibility(View.VISIBLE);
            iv_mark_in.setVisibility(View.VISIBLE);
            ll_mark_out.setVisibility(View.VISIBLE);
            iv_mark_out.setVisibility(View.GONE);
            btn_mark.setVisibility(View.VISIBLE);
        }else  if (attendance.getIsOut().equals("Y") && attendance.getIsIn().equals("Y")){
            tv_note.setVisibility(View.VISIBLE);
            iv_mark_in.setVisibility(View.VISIBLE);
            iv_mark_out.setVisibility(View.VISIBLE);
            btn_mark.setVisibility(View.GONE);

            new SyncAllotedSite(userInfo.getEmpId()).execute();
        }
    }

    private void locationManager() {
        dialog.setMessage("Accessing Location..");
        dialog.show();

            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, (float) 0.01, mlistener);
            mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, (float) 0.01, mlistener);

    }

    private final LocationListener mlistener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            if (location.getLatitude() > 0.0) {

                if (location.getAccuracy() > 0 && location.getAccuracy() < 150) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    mlocManager.removeUpdates(this);
                    latitude = Double.toString(location.getLatitude());
                    longitud = Double.toString(location.getLongitude());

                    updateAttendaceToServer();

                } else {
                    dialog.setMessage("Please wait, Location is not stable.");
                    dialog.show();
                }
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    };

    private class AttendanceStatusTask extends AsyncTask<String, Void, AttendacneStatus> {
        String empId;

        public AttendanceStatusTask(String empId) {
            this.empId = empId;
        }

        private final ProgressDialog dialog = new ProgressDialog(getActivity());

        private final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Syncing Attendance Status...");
            this.dialog.show();
        }

        @Override
        protected AttendacneStatus doInBackground(String... param) {

            return WebServiceHelper.getAttendanceStatus(empId);
        }

        @Override
        protected void onPostExecute(final AttendacneStatus result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result == null){
                alertDialog.setTitle("Connection Error!!");
                alertDialog.setMessage("Failed to connect with server. Try again");
                alertDialog.show();
            }else {
                attendance = result;
                updateStatus();
            }
        }
    }

    private class MarkAttendanceTask extends AsyncTask<String, Void, String> {
        String type, latitude,longitude;

        public MarkAttendanceTask(String type, String latitude, String longitude) {
            this.type = type;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        private final ProgressDialog dialog = new ProgressDialog(getActivity());

        private final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Updating Attendance Status...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {

            if(type.equals(AppConstants.MARKIN)){
                return WebServiceHelper.markInAttendanceStatus(userInfo.getUserID(),userInfo.getEmpId(),deviceId, latitude,longitude);
            }else{
                return WebServiceHelper.markOutAttendanceStatus(userInfo.getUserID(),userInfo.getEmpId(),deviceId, latitude,longitude);
            }

        }

        @Override
        protected void onPostExecute(final String result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result == null){
                alertDialog.setTitle("Connection Error!!");
                alertDialog.setMessage("Failed to connect with server. Try again");
                alertDialog.show();
            }else {
                //attendance = result;
                if(result.equals("1")){
                    if(type.equals(AppConstants.MARKIN)){
                        attendance.setIsIn("Y");
                    }else{
                        attendance.setIsOut("Y");
                    }
                    updateStatus();
                }else{
                    alertDialog.setTitle("Mark Attendance Failed");
                    alertDialog.setMessage("Failed to update your attendace on server.\n Please try again..");
                    alertDialog.show();
                }

            }
        }
    }

    private class SyncAllotedSite extends AsyncTask<String, Void, AllotedSiteEntity> {
        String empId;

        public SyncAllotedSite(String empId) {
            this.empId = empId;
        }

        private final ProgressDialog dialog = new ProgressDialog(getActivity());

        private final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Syncing Site Status...");
            this.dialog.show();
        }

        @Override
        protected AllotedSiteEntity doInBackground(String... param) {

            return WebServiceHelper.getAllotedSiteStatus(empId);
        }

        @Override
        protected void onPostExecute(final AllotedSiteEntity result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result == null){
                alertDialog.setTitle("Connection Error!!");
                alertDialog.setMessage("Failed to connect with server. Try again");
                alertDialog.show();
            }else {
                allotedSite = result;
                tv_other_site_name.setText(result.getSiteName());
                if(allotedSite.getIsApproved().equals("N")){
                    cv_alloted_Site.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
