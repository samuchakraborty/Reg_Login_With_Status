package com.samu.reg_login_with_status;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity_post_method extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private SeekBar seekBar;
    private EditText ee;



    private Button buttonRegister;

   // private static final String REGISTER_URL = "http://stamasoft.com/android/regloginway2/register_using_post_method.php";
   // private static final String REGISTER_URL = "http://192.168.56.1/android/android_professional/Reg_login_post_get/regloginway2/register_using_post_method.php";

    //private static final String REGISTER_URL = "http://127.0.0.1/android/android_professional/Reg_login_post_get/regloginway2/register_using_post_method.php";
    private static final String REGISTER_URL = "http://10.0.2.2/LifePro/Reg_Login_with_status/register_using_post_method.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post_method);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);


        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
        ee = (EditText) findViewById(R.id.ed1);

        seekBar =(SeekBar) findViewById(R.id.seekBar);
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Write code to perform some action when progress is changed.
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is started.
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                    Integer hh = seekBar.getProgress();
                    String ss = hh.toString();
                    ee.setText(ss);


                    // Write code to perform some action when touch is stopped.
                    Toast.makeText(MainActivity_post_method.this, "Progress is " + seekBar.getProgress() + "%", Toast.LENGTH_SHORT).show();
                }
            });

        }



    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String rating = ee.getText().toString().trim().toLowerCase();

        register(name,username,password,email,rating);
    }

    private void register(String name, String username, String password, String email,String rating) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass_for_post_method ruc = new RegisterUserClass_for_post_method();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity_post_method.this, "Please Wait",null, true, true);
            }


            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);
                data.put("rating",params[4]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,email,rating);
    }
}