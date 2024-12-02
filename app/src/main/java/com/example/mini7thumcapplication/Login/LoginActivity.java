package com.example.mini7thumcapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mini7thumcapplication.MainActivity;
import com.example.mini7thumcapplication.R;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login); // 로그인 레이아웃 연결

        //회원가입버튼 연결
        Button joinButton = findViewById(R.id.joinButton);
        joinButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(intent);
        });

        // 로그인 버튼 연결
        Button loginButton = findViewById(R.id.loginButton); // 로그인 버튼 ID 확인
        loginButton.setOnClickListener(v -> {
            // 로그인 성공 후 MainActivity로 이동
            Log.d("login Activity","로그인 성공");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Log.d("login Activity","intent");
            startActivity(intent);
            Log.d("login Activity","액티비티 연결");
            finish(); // LoginActivity 종료
        });
    }
}
