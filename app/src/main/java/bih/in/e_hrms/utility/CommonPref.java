package bih.in.e_hrms.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import bih.in.e_hrms.entity.UserDetails;

public class CommonPref {

    static Context context;

    CommonPref() {

    }

    CommonPref(Context context) {
        CommonPref.context = context;
    }



    public static void setUserDetails(Context context, UserDetails userInfo) {

        String key = "_USER_DETAILS";

        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("_FName", userInfo.get_FName());
        editor.putString("EmpId", userInfo.getEmpId());
        editor.putString("EmpCode", userInfo.getEmpCode());
        editor.putString("Name", userInfo.getName());

        editor.putString("FName", userInfo.getFName());
        editor.putString("ActJoinDate", userInfo.getActJoinDate());
        editor.putString("ActReleaveDate", userInfo.getActReleaveDate());
        editor.putString("Address", userInfo.getAddress());
        editor.putString("HQualification", userInfo.getHQualification());
        editor.putString("ContactNo", userInfo.getContactNo());

        editor.putString("EmailId", userInfo.getEmailId());
        editor.putString("IsActive", userInfo.getIsActive());
        editor.putString("Designation", userInfo.getDesignation());
        editor.putString("DistName", userInfo.getDistName());
        editor.putString("UserID", userInfo.getUserID());
        editor.putString("UserName", userInfo.getUserName());
        editor.putString("Userrole", userInfo.getUserrole());

        editor.commit();

    }

    public static UserDetails getUserDetails(Context context) {

        String key = "_USER_DETAILS";
        UserDetails userInfo = new UserDetails();
        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        userInfo.set_FName(prefs.getString("_FName", ""));
        userInfo.setEmpId(prefs.getString("EmpId", ""));
        userInfo.setEmpCode(prefs.getString("EmpCode", ""));
        userInfo.setName(prefs.getString("Name", ""));

        userInfo.setFName(prefs.getString("FName", ""));
        userInfo.setActJoinDate(prefs.getString("ActJoinDate", ""));
        userInfo.setActReleaveDate(prefs.getString("ActReleaveDate", ""));
        userInfo.setAddress(prefs.getString("Address", ""));
        userInfo.setHQualification(prefs.getString("HQualification", ""));
        userInfo.setContactNo(prefs.getString("ContactNo", ""));

        userInfo.setEmailId(prefs.getString("EmailId", ""));
        userInfo.setIsActive(prefs.getString("IsActive", ""));
        userInfo.setDesignation(prefs.getString("Designation", ""));
        userInfo.setDistName(prefs.getString("DistName", ""));
        userInfo.setUserID(prefs.getString("UserID", ""));
        userInfo.setUserName(prefs.getString("UserName", ""));
        userInfo.setUserrole(prefs.getString("Userrole", ""));

        return userInfo;
    }



    public static void setCheckUpdate(Context context, long dateTime) {

        String key = "_CheckUpdate";

        SharedPreferences prefs = context.getSharedPreferences(key,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();


        dateTime=dateTime+1*3600000;
        editor.putLong("LastVisitedDate", dateTime);

        editor.commit();

    }

    public static int getCheckUpdate(Context context) {

        String key = "_CheckUpdate";

        SharedPreferences prefs = context.getSharedPreferences(key,
                Context.MODE_PRIVATE);

        long a = prefs.getLong("LastVisitedDate", 0);


        if(System.currentTimeMillis()>a)
            return 1;
        else
            return 0;
    }

    public static void setAwcId(Activity activity, String awcid){
        String key = "_Awcid";
        SharedPreferences prefs = activity.getSharedPreferences(key,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("code2", awcid);
        editor.commit();
    }

    public static String getAwcId(Activity activity){
        String key = "_Awcid";
        UserDetails userInfo = new UserDetails();
        SharedPreferences prefs = activity.getSharedPreferences(key,
                Context.MODE_PRIVATE);
        String code2=prefs.getString("code2","");
        return code2;
    }
}
