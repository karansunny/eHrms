package bih.in.e_hrms.ui.ui.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import bih.in.e_hrms.R;
import bih.in.e_hrms.entity.AttendacneStatus;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.ui.LoginActivity;
import bih.in.e_hrms.ui.ui.attendance.AttendanceFragment;
import bih.in.e_hrms.utility.CommonPref;
import bih.in.e_hrms.utility.Utiilties;
import bih.in.e_hrms.web_services.WebServiceHelper;


public class HomeFragment extends Fragment {

    TextView tv_username,tv_qualification,tv_district,tv_designation,tv_attendance_status;
    LinearLayout ll_attandenace;
    CardView cv_attendance;

    UserDetails userInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        tv_username = root.findViewById(R.id.tv_username);
        tv_qualification = root.findViewById(R.id.tv_qualification);
        tv_district = root.findViewById(R.id.tv_district);
        tv_designation = root.findViewById(R.id.tv_designation);
        tv_attendance_status = root.findViewById(R.id.tv_attendance_status);

        ll_attandenace = root.findViewById(R.id.ll_attandenace);

        cv_attendance = root.findViewById(R.id.cv_attendance);

        cv_attendance.setVisibility(View.GONE);
        setUserDetail();

        ll_attandenace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AttendanceFragment nextFrag= new AttendanceFragment();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(root.getId(), nextFrag, "nav_gallery")
//                        .addToBackStack(null)
//                        .commit();

                //AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                GalleryFragment nextFrag= new GalleryFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(root.getId(), nextFrag).addToBackStack(null).commit();
            }
        });
        return root;
    }

    public void setUserDetail(){
        userInfo = CommonPref.getUserDetails(getActivity());

        tv_username.setText(userInfo.getName());
        tv_qualification.setText(userInfo.getHQualification());
        tv_district.setText(userInfo.getDistName());
        tv_designation.setText(userInfo.getDesignation());

        if(Utiilties.isOnline(getActivity())){
            new AttendanceStatusTask(userInfo.getEmpId()).execute();
        }else{
            Utiilties.showAlet(getActivity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(userInfo != null && Utiilties.isOnline(getActivity())){
            new AttendanceStatusTask(userInfo.getEmpId()).execute();
        }
    }

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

                if(result.getEmpId().equals("anyType{}")){
                    tv_attendance_status.setText("Today attendance is not marked yet.");
                    tv_attendance_status.setTextColor(getResources().getColor(R.color.color_red));
                }else if (result.getIsOut().equals("anyType{}") && result.getIsIn().equals("Y")){
                    tv_attendance_status.setText("Today attendance check-in marked");
                    tv_attendance_status.setTextColor(getResources().getColor(R.color.color_green));
                }else  if (result.getIsOut().equals("Y") && result.getIsIn().equals("Y")){
                    tv_attendance_status.setText("Today attendance check-out marked");
                    tv_attendance_status.setTextColor(getResources().getColor(R.color.color_green));
                }
                cv_attendance.setVisibility(View.VISIBLE);
            }
        }
    }
}
