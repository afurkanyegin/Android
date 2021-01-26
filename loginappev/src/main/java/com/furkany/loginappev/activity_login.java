package com.furkany.loginappev;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {
    EditText etusername,etpassword;
    Button btnsign,btnallusers;
    TextView tvregister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername=findViewById(R.id.et_username);
        etpassword=findViewById(R.id.et_password);
        btnsign=findViewById(R.id.btn_sign);
        tvregister=findViewById(R.id.tv_register);
        btnallusers=findViewById(R.id.btn_allusers);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etusername.getText().toString();
                String password=etpassword.getText().toString();
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    databaseHelper= new databaseHelper(activity_login.this);
                    String returnsifre=databaseHelper.findUser(username);
                    if(returnsifre.equals(password)){
                        Intent welcomeIntent= new Intent(getApplicationContext(),welcomeActivity.class);
                        welcomeIntent.putExtra("USERNAME",username);
                        startActivity(welcomeIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Username or password is wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        btnallusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allusersIntent=new Intent(getApplicationContext(),ShowAllUsers.class);
                startActivity(allusersIntent);
            }
        });
    }
}
