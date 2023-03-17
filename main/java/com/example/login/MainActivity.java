package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText user_name;
    private EditText psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.login);
        user_name = findViewById(R.id.edit_user);
        psw = findViewById(R.id.edit_psw);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = user_name.getText().toString();
        String password = psw.getText().toString();
        if(username.equals("032002525")&&password.equals("123456")){
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(MainActivity.this,"账号密码错误",Toast.LENGTH_LONG).show();
    }
}