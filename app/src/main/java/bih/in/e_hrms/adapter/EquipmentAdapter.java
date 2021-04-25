package bih.in.e_hrms.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import bih.in.e_hrms.R;

import java.util.ArrayList;

import bih.in.e_hrms.entity.EquipmentEntity;
import bih.in.e_hrms.entity.SurfaceInspectionDetailEntity;
import bih.in.e_hrms.entity.SurfaceSchemeEntity;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.ui.EquipmentListListner;
import bih.in.e_hrms.utility.GlobalVariables;
import bih.in.e_hrms.utility.Utiilties;


public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {

    private ArrayList<EquipmentEntity> mData;
    private LayoutInflater mInflater;
    private EquipmentListListner listener;

    public EquipmentAdapter(Context context, ArrayList<EquipmentEntity> data, EquipmentListListner listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.listener = listener;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_equipment, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final EquipmentEntity info = mData.get(position);

        holder.tv_name.setText(info.getName());
        holder.ch_status.setChecked(info.getSelected());
        holder.tv_count.setText(String.valueOf(position+1)+".");

        holder.ch_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listener.onItemChecked(b, position);
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
        TextView tv_name,tv_count;
        CheckBox ch_status;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_count = itemView.findViewById(R.id.tv_count);
            ch_status = itemView.findViewById(R.id.ch_status);
        }


        @Override
        public void onClick(View view) {

        }
    }

}

