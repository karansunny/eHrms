package bih.in.e_hrms.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import bih.in.e_hrms.R;
import bih.in.e_hrms.adapter.EquipmentAdapter;
import bih.in.e_hrms.entity.AllotedSiteEntity;
import bih.in.e_hrms.entity.AttendacneStatus;
import bih.in.e_hrms.entity.EquipmentEntity;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.utility.AppConstants;
import bih.in.e_hrms.utility.CommonPref;
import bih.in.e_hrms.web_services.WebServiceHelper;


public class EquipmentListActivity extends AppCompatActivity implements EquipmentListListner{

    RecyclerView rv_data;
    Button btn_submit;

    ArrayList<EquipmentEntity> equipments = new ArrayList<>();

    UserDetails userInfo;
    AllotedSiteEntity allotedSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Equipments List");

        initialize();
        getUserDetail();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initialize(){
        rv_data = findViewById(R.id.rv_data);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setVisibility(View.GONE);
    }

    public void getUserDetail(){
        userInfo = CommonPref.getUserDetails(this);

        allotedSite = (AllotedSiteEntity) getIntent().getSerializableExtra(AppConstants.ALLOTEDSITE);

        new SyncEquipmentListTask(userInfo.getEmpId()).execute();

    }

    public void onSumitDetail(View view) {
    }

    public void setRecyclerView(){
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        EquipmentAdapter adapter = new EquipmentAdapter(this, equipments, this);
        rv_data.setAdapter(adapter);

        btn_submit.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemChecked(Boolean flag, final int position) {
        EquipmentEntity info = equipments.get(position);
        info.setSelected(flag);
        equipments.set(position, info);
        //rv_data.getAdapter().notifyItemChanged(position);
        rv_data.post(new Runnable() {
            @Override
            public void run() {
                rv_data.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private ArrayList<EquipmentEntity> getMarkedEquiments(ArrayList<EquipmentEntity> list){
        ArrayList<EquipmentEntity> marked = new ArrayList<>();

        for(EquipmentEntity item: list){
            if(item.getSelected()){
                marked.add(item);
            }
        }

        return marked;
    }

    private class SyncEquipmentListTask extends AsyncTask<String, Void, ArrayList<EquipmentEntity>> {
        String empId;

        public SyncEquipmentListTask(String empId) {
            this.empId = empId;
        }

        private final ProgressDialog dialog = new ProgressDialog(EquipmentListActivity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(EquipmentListActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Syncing Equipment List...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<EquipmentEntity> doInBackground(String... param) {

            return WebServiceHelper.getEquipmentList(empId);
        }

        @Override
        protected void onPostExecute(ArrayList<EquipmentEntity> result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result.size() > 0){
                equipments = result;
                setRecyclerView();
            }else {
                alertDialog.setTitle("No Record Found");
                alertDialog.setMessage("No Equiment detail found. Contact Adminstrator.");
                alertDialog.show();
            }
        }
    }

    private class UpdateEquipmentStatus extends AsyncTask<String, Void, String> {
        String empId;


        private final ProgressDialog dialog = new ProgressDialog(EquipmentListActivity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(EquipmentListActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Uploading Equipment Status...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {

            return WebServiceHelper.uploadEquipmentStatus(allotedSite, getMarkedEquiments(equipments));
        }

        @Override
        protected void onPostExecute(String result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result == null){
                alertDialog.setTitle("Connection Error!!");
                alertDialog.setMessage("Failed to connect with server. Try again");
                alertDialog.show();
            }else {
                if(result.equals("1")){
                    finish();
                }else{
                    alertDialog.setTitle("Mark Away Failed");
                    alertDialog.setMessage("Failed to move to another site with equipments.\n Please try again..");
                    alertDialog.show();
                }

            } alertDialog.show();
            }
        }

}
