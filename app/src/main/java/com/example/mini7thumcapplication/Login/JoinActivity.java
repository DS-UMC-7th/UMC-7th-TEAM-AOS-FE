package com.example.mini7thumcapplication.Login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_join); // 회원가입 레이아웃 연결

        Button joinMembershipButton = findViewById(R.id.JoinMembershipButton);
        joinMembershipButton.setOnClickListener(v -> {
            // 유효성 검사 코드 작성
            EditText usernameField = findViewById(R.id.registerUsername);
            EditText passwordField = findViewById(R.id.registerPassword);
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
