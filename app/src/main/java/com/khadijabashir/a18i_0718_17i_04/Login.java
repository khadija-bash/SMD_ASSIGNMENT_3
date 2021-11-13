package com.khadijabashir.a18i_0718_17i_04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button btnlogin, Register, SignInMobile;
    DBHelper DB;
    FirebaseAuth auth;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.loginbutton);
        SignInMobile = (Button) findViewById(R.id.loginwithphone);
        Register = (Button) findViewById(R.id.register) ;
        auth = FirebaseAuth.getInstance();
        DB = new DBHelper(this);

        SignInMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignInWithMobile.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("t3", "log working ");
                String user = email.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                        editor.putString("name", user);
                        editor.putString("pass", pass);
                        editor.apply();
                        Toast.makeText(Login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(Login.this, chatActivity.class);
                        startActivity(intent);
//                        SharedPreferences.Editor e=sp.edit();
//                        e.putString("email",user);
//                        e.putString("password",pass);
//                        e.commit();
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignupScreen.class);
                startActivity(intent);
            }
        });
    }
}