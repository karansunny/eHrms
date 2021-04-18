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
import bih.in.e_hrms.utility.AppConstants;
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
                    Utiilties.hideKeyboard(LoginActivity.this);
                    String username = userName.getText().toString();
                    String password = userPass.getText().toString();

                    if(Utiilties.isOnline(LoginActivity.this)){
                        new LoginTask(username, password).execute();
                    }else{
                        Utiilties.showAlet(LoginActivity.this);
                    }


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
                return WebServiceHelper.Login(username, password);
            }

        }

        @Override
        protected void onPostExecute(final UserDetails result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result == null){
                alertDialog.setTitle("Connection Error!!");
                alertDialog.setMessage("Failed to connect with server. Try again");
                alertDialog.show();
            }else if (result.isAuthenticated()) {
                setLoginStatus(result);
                start();
            } else {
                alertDialog.setTitle("Authentication Failed!!");
                alertDialog.setMessage("Please enter valid username and password");
                alertDialog.show();

            }
        }
    }

    private void setLoginStatus(UserDetails details) {

        SharedPreferences.Editor editor = SplashActivity.prefs.edit();
        editor.putBoolean(AppConstants.USERNAME, true);
        editor.putBoolean(AppConstants.PASSWORD, true);
        editor.putString("uid", uid.toLowerCase());
        editor.putString("pass", pass);
        editor.commit();

        CommonPref.setUserDetails(this, details);
    }

    public void start() {
       // Intent iUserHome = new Intent(getApplicationContext(), MainActivity.class);
        Intent iUserHome = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(iUserHome);
        finish();
    }

}
