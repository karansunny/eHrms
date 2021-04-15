package bih.in.e_hrms.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import bih.in.e_hrms.R;

import java.util.ArrayList;

import bih.in.e_hrms.entity.SurfaceInspectionDetailEntity;
import bih.in.e_hrms.entity.SurfaceSchemeEntity;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.utility.GlobalVariables;
import bih.in.e_hrms.utility.Utiilties;


public class SurfaceInspectionDetailAdapter extends RecyclerView.Adapter<SurfaceInspectionDetailAdapter.ViewHolder> {

    private ArrayList<SurfaceInspectionDetailEntity> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private UserDetails userInfo;
    private SurfaceSchemeEntity schemeInfo;
    // data is passed into the constructor
    public SurfaceInspectionDetailAdapter(Context context, ArrayList<SurfaceInspectionDetailEntity> data, UserDetails userInfo, SurfaceSchemeEntity schemeInfo) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.userInfo = userInfo;
        this.schemeInfo = schemeInfo;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_surface_inspection, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SurfaceInspectionDetailEntity info = mData.get(position);
        holder.tv_inspection_by.setText(info.getINSPECTION_BY_NAME());
        holder.tv_inspection_id.setText(info.getINSPECTION_ID());
        holder.tv_number.setText(info.getINSPECTION_BY_Phone());
        holder.tv_designation.setText(info.getDESIGNATION());
        holder.tv_observation.setText(info.getObservetion_Category());
        holder.tv_completion.setText(info.getWork_Competion_In_Presentage());
        holder.tv_comment.setText(info.getCOMMENT());
        holder.tv_date.setText(Utiilties.convertDateStringFormet("MM/dd/yyyy HH:mm:ss a", "dd MMM yyyy",info.getINSPECTION_DATE()));
        holder.tv_serial.setText(String.valueOf(position+1)+".");

        holder.sblist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utiilties.isOnline(context) == false) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    ab.setTitle("Internet Connnection Error!!!");
                    ab.setMessage("Please turn on your mobile data or wifi connection");
                    ab.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            GlobalVariables.isOffline = false;
                            Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            context.startActivity(I);
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
//                    Intent i = new Intent(context, SurfaceViewInspectionDetailActivity.class);
//                    i.putExtra("data", schemeInfo);
//                    i.putExtra("id", info.getINSPECTION_ID());
//                    i.putExtra("user", userInfo);
//                    context.startActivity(i);
                    //new SyncSchemeInspectionDetail(info, userInfo).execute();
                }
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_inspection_by,tv_inspection_id,tv_number,tv_designation,tv_observation,tv_completion,tv_comment,tv_date,tv_serial;
        RelativeLayout sblist;

        ViewHolder(View itemView) {
            super(itemView);
            tv_inspection_by = itemView.findViewById(R.id.tv_inspection_by);
            tv_inspection_id = itemView.findViewById(R.id.tv_inspection_id);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_designation = itemView.findViewById(R.id.tv_designation);
            tv_observation = itemView.findViewById(R.id.tv_observation);
            tv_completion = itemView.findViewById(R.id.tv_completion);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_serial = itemView.findViewById(R.id.tv_serial);
            sblist = itemView.findViewById(R.id.sblist);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    SurfaceInspectionDetailEntity getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }



}
