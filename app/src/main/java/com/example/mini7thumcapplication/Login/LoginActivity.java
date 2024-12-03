package com.example.mini7thumcapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mini7thumcapplication.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


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


        //로그인버튼 연결
       Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
            // 로그인이후 화면으로 이동후 스크립트 여기다쓰시면될거같습니다.
            // NavController를 가져와 MainFragment로 이동
            //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            //navController.navigate(R.id.action_loginFragment_to_mainFragment);

        });
    }
}
