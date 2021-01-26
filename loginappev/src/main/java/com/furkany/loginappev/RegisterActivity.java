package com.furkany.loginappev;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText etregisterusername,etregisterpassword,etregistername,etregistermail,etregisterconfirmpassword;
    Button btnregister;
    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etregisterusername=findViewById(R.id.et_Registerusername);
        etregisterpassword=findViewById(R.id.et_RegisterPassword);
        etregistername=findViewById(R.id.et_RegisterName);
        etregistermail=findViewById(R.id.et_RegisterMail);
        etregisterconfirmpassword=findViewById(R.id.et_ConfirmPassword);
        btnregister=findViewById(R.id.btn_Register);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etregisterusername.getText().toString();
                String password=etregisterpassword.getText().toString();
                String passwordconfirm=etregisterconfirmpassword.getText().toString();
                String name=etregistername.getText().toString();
                String mail= etregistermail.getText().toString();
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password) &&!TextUtils.isEmpty(passwordconfirm)&&!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(mail)){
                    if(password.equals(passwordconfirm)){
                        databaseHelper=new DatabaseHelper(RegisterActivity.this);
                        if(databaseHelper.insertUser(username,password,name,mail)){
                            Toast.makeText(RegisterActivity.this,"Account created succesfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RegisterActivity.this,"ERROR!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
