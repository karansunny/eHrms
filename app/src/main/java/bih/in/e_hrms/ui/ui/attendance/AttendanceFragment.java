package bih.in.e_hrms.ui.ui.attendance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import bih.in.e_hrms.R;

public class AttendanceFragment extends Fragment {

    TextView tv_date,tv_day,tv_note,tv_username;
    LinearLayout ll_mark_in,ll_mark_out;
    ImageView iv_mark_in,iv_mark_out;
    Button btn_mark;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_attendance, container, false);


        return root;
    }

    public void initialize(View root){
        tv_username = root.findViewById(R.id.tv_username);
        tv_date = root.findViewById(R.id.tv_date);
        tv_day = root.findViewById(R.id.tv_day);
        tv_note = root.findViewById(R.id.tv_note);

        ll_mark_in = root.findViewById(R.id.ll_mark_in);
        ll_mark_out = root.findViewById(R.id.ll_mark_out);

        iv_mark_in = root.findViewById(R.id.iv_mark_in);
        iv_mark_out = root.findViewById(R.id.iv_mark_out);

        btn_mark = root.findViewById(R.id.btn_mark);
    }
}
