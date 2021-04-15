package bih.in.e_niwas.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import bih.in.e_niwas.entity.UserDetails;

public class CommonPref {

    static Context context;

    CommonPref() {

    }

    CommonPref(Context context) {
        CommonPref.context = context;
    }



    public static void setUserDetails(Context context, UserDetails userInfo) {

        String key = "_USER_DETAILS";

        SharedPreferences prefs = context.getSharedPreferences(key,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("UserId", userInfo.getUserID());
        editor.putString("UserName", userInfo.getName());
        editor.putString("UserPassword", userInfo.getPassword());
        editor.putString("Role", userInfo.getUserrole());

        editor.putString("DistCode", userInfo.getDistrictCode());
        editor.putString("DistName", userInfo.getDistName());
        editor.putString("BlockCode", userInfo.getBlockCode());
        editor.putString("BlockName", userInfo.getBlockName());
        editor.putString("Degignation", userInfo.getDegignation());
        editor.putString("MobileNo", userInfo.getMobileNo());


        editor.commit();

    }

    public static UserDetails getUserDetails(Context context) {

        String key = "_USER_DETAILS";
        UserDetails userInfo = new UserDetails();
        SharedPreferences prefs = context.getSharedPreferences(key,
                Context.MODE_PRIVATE);

        userInfo.setUserID(prefs.getString("UserId", ""));
        userInfo.setName(prefs.getString("UserName", ""));
        userInfo.setPassword(prefs.getString("UserPassword", ""));
        userInfo.setUserrole(prefs.getString("Role", ""));

        userInfo.setDistName(prefs.getString("DistName", ""));
        userInfo.setDistrictCode(prefs.getString("DistCode", ""));
        userInfo.setBlockCode(prefs.getString("BlockCode", ""));
        userInfo.setBlockName(prefs.getString("BlockName", ""));
        userInfo.setDegignation(prefs.getString("Degignation", ""));
        userInfo.setMobileNo(prefs.getString("MobileNo", ""));

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
