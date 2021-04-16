package bih.in.e_hrms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import bih.in.e_hrms.R;

import bih.in.e_hrms.database.DataBaseHelper;
import bih.in.e_hrms.web_services.WebServiceHelper;
import bih.in.e_hrms.entity.UserDetails;
import bih.in.e_hrms.utility.CommonPref;
import bih.in.e_hrms.utility.GlobalVariables;
import bih.in.e_hrms.utility.Utiilties;


public class LoginActivity extends Activity {

    ConnectivityManager cm;
    public static String UserPhoto;
    //String version;
    TelephonyManager tm;
    private static String imei;
    //TODO setup Database
    //DatabaseHelper1 localDBHelper;
    Context context;
    String uid = "";
    String pass = "";
    EditText userName;
    EditText userPass;
    //String[] param;
    DataBaseHelper localDBHelper;

    UserDetails userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.btn_login);
        TextView signUpBtn = findViewById(R.id.tv_signup);
        userName = findViewById(R.id.et_username);
        userPass = findViewById(R.id.et_password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isDataValidated()){

                    String username = userName.getText().toString();
                    String password = userPass.getText().toString();

                    new LoginTask(username, password).execute();
                }else{
                    Toast.makeText(LoginActivity.this,"Please fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent singUpInt = new Intent(LoginActivity.this, SignUpActivity.class);
//                LoginActivity.this.startActivity(singUpInt);
//            }
//        });

//        try {
//            version = getPackageManager().getPackageInfo(getPackageName(),
//                    0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    private void getUserDetail(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("uid", "user");
        String password = prefs.getString("pass", "password");
        //userInfo = localDBHelper.getUserDetails(username.toLowerCase(), password);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getIMEI();

    }

    public Boolean isDataValidated(){
        View focusView = null;
        boolean validate = true;

        if(userName.getText().toString().isEmpty()){
            userName.setError("Enter Valid UserId");
            focusView = userName;
            validate = false;
        }

        if(userPass.getText().toString().isEmpty()){
            userPass.setError("Enter Valid Password");
            focusView = userPass;
            validate = false;
        }

        return validate;
    }


    private class LoginTask extends AsyncTask<String, Void, UserDetails> {
        String username,password;

        LoginTask(String username, String password){
            this.username = username;
            this.password = password;
        }

        private final ProgressDialog dialog = new ProgressDialog(
                LoginActivity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(
                LoginActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Authenticating, Please Wait...");
            this.dialog.show();
        }

        @Override
        protected UserDetails doInBackground(String... param) {

            if (!Utiilties.isOnline(LoginActivity.this)) {
                UserDetails userDetails = new UserDetails();
                userDetails.setAuthenticated(true);
                return userDetails;
            } else {
                return WebServiceHelper.Login(username, password,"");
            }

        }

        @Override
        protected void onPostExecute(final UserDetails result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result != null && result.isAuthenticated() == false) {

                alertDialog.setTitle(getResources().getString(R.string.failed));
                alertDialog.setMessage(getResources().getString(R.string.authentication_failed));
                alertDialog.show();

            } else if (!(result != null)) {
                AlertDialog.Builder ab = new AlertDialog.Builder(context);
                ab.setTitle(getResources().getString(R.string.server_down_title));
                ab.setMessage(getResources().getString(R.string.server_down_text));
                ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.dismiss();

                    }
                });


                ab.create().getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                ab.show();

            } else {

                //-----------------------------------------Online-------------------------------------
                if (Utiilties.isOnline(LoginActivity.this)) {


                    uid = param[0];
                    pass = param[1];

                    if (result != null && result.isAuthenticated() == true) {
                        uid=result.getUserID();
                        pass = param[1];

                        try {

                            GlobalVariables.LoggedUser = result;
                            GlobalVariables.LoggedUser.setUserID(userName
                                    .getText().toString().trim().toLowerCase());

                            GlobalVariables.LoggedUser.setPassword(userPass
                                    .getText().toString().trim());


                            CommonPref.setUserDetails(getApplicationContext(),
                                    GlobalVariables.LoggedUser);


                            long c = setLoginStatus(GlobalVariables.LoggedUser);

                            if (c > 0) {
                                start();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, getResources().getString(R.string.authentication_failed),
                                        Toast.LENGTH_SHORT).show();
                            }


                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.authentication_failed),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                    // offline -------------------------------------------------------------------------

                } else {

                    if (localDBHelper.getUserCount() > 0) {

                        //GlobalVariables.LoggedUser = localDBHelper.getUserDetails(userName.getText().toString().trim().toLowerCase(),userPass.getText().toString());

                        if (GlobalVariables.LoggedUser != null) {

                            CommonPref.setUserDetails(
                                    getApplicationContext(),
                                    GlobalVariables.LoggedUser);

                            SharedPreferences.Editor editor = SplashActivity.prefs.edit();
                            editor.putBoolean("username", true);
                            editor.putBoolean("password", true);
                            editor.putString("uid", uid);
                            editor.putString("pass", pass);
                            editor.commit();
                            start();

                        } else {

                            Toast.makeText(
                                    getApplicationContext(),
                                    getResources().getString(R.string.username_password_notmatched),
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                getResources().getString(R.string.enable_internet_for_firsttime),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

        }
    }

    private long setLoginStatus(UserDetails details) {
        details.setPassword(pass);
        SharedPreferences.Editor editor = SplashActivity.prefs.edit();
        editor.putBoolean("username", true);
        editor.putBoolean("password", true);
        editor.putString("uid", uid.toLowerCase());
        editor.putString("pass", pass);
        editor.putString("role", details.getUserrole());
        editor.commit();
        //PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("USER_ID", uid).commit();
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("uid", uid).commit();
        localDBHelper = new DataBaseHelper(getApplicationContext());
        //long c = localDBHelper.insertUserDetails(details);

        return 0;
    }

    public void start() {
        //getUserDetail();
        //new SyncPanchayatData().execute("");
        Intent iUserHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(iUserHome);
        finish();
    }

}
