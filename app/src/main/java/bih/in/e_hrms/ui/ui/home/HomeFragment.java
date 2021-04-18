package bih.in.e_hrms.ui.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import bih.in.e_hrms.R;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.ui.ui.gallery.GalleryFragment;
import bih.in.e_hrms.utility.CommonPref;
import bih.in.e_hrms.utility.Utiilties;


public class HomeFragment extends Fragment {

    TextView tv_username,tv_qualification,tv_district,tv_designation;
    LinearLayout ll_attandenace;

    UserDetails userInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        tv_username = root.findViewById(R.id.tv_username);
        tv_qualification = root.findViewById(R.id.tv_qualification);
        tv_district = root.findViewById(R.id.tv_district);
        tv_designation = root.findViewById(R.id.tv_designation);

        ll_attandenace = root.findViewById(R.id.ll_attandenace);

        setUserDetail();

        ll_attandenace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GalleryFragment nextFrag= new GalleryFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(root.getId(), nextFrag, "nav_gallery")
                        .addToBackStack(null)
                        .commit();

                //AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                GalleryFragment nextFrag= new GalleryFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(root.getId(), nextFrag).addToBackStack(null).commit();
            }
        });
        return root;
    }

    public void setUserDetail(){
        userInfo = CommonPref.getUserDetails(getActivity());

        tv_username.setText(userInfo.get_FName());
        tv_qualification.setText(userInfo.getHQualification());
        tv_district.setText(userInfo.getDistName());
        tv_designation.setText(userInfo.getDesignation());
    }
}
