package com.khadijabashir.a18i_0718_17i_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {

    EditText firstname,lastname,gender,bio;
    Button confirm;
    DBHelper DB;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        if(savedInstanceState == null) {

            // Retrieve the Intent with which the Activity was started
            Intent intent = getIntent();
            id = intent.getLongExtra("rowId", -1);
        }

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        gender = (EditText) findViewById(R.id.gender);
        bio = (EditText) findViewById(R.id.bio);
        confirm = (Button) findViewById(R.id.Confirm);
        DB = new DBHelper(this);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String gen = gender.getText().toString();
                String bi = bio.getText().toString();

                if(fname.equals("")||lname.equals("")||gender.equals(""))
                    Toast.makeText(CreateAccount.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                else{
                    // create the get Intent object
                    Intent intent = getIntent();

                    // receive the value by getStringExtra() method
                    // and key must be same which is send by first activity
                    String rec_email = intent.getStringExtra("email");
                    String rec_pass = intent.getStringExtra("pass");

                    boolean insert = DB.insertData(rec_email, rec_pass,fname, lname, gen, bi );
                    if(insert == true){
                        Toast.makeText(CreateAccount.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(CreateAccount.this,setProfile.class);
                        intent1.putExtra("username",firstname + " " + lastname);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(CreateAccount.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
}