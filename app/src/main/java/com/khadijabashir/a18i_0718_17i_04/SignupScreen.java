package com.khadijabashir.a18i_0718_17i_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupScreen extends AppCompatActivity {

    EditText email, password, repassword;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        repassword = (EditText) findViewById(R.id.confirmpassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnlogin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignupScreen.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            String send_email = email.getText().toString();
                            String sent_pass = password.getText().toString();

                            // Create the Intent object of this class Context() to Second_activity class
                            Intent intent = new Intent(SignupScreen.this, CreateAccount.class);

                            intent.putExtra("email", send_email);
                            intent.putExtra("pass", sent_pass);

                            // start the Intent
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignupScreen.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupScreen.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupScreen.this, Login.class);
                startActivity(intent);
            }
        });

    }

}
