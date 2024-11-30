package com.example.mini7thumcapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mini7thumcapplication.R;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_join); // 회원가입 레이아웃 연결

        Button joinMembershipButton = findViewById(R.id.JoinMembershipButton);
        joinMembershipButton.setOnClickListener(v -> {
            // 유효성 검사 코드 작성
            EditText passwordField = findViewById(R.id.registerPassword);
            EditText confirmPasswordField = findViewById(R.id.passwordcheck);
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();

            TextView passwordErrorMessage = findViewById(R.id.passwordErrorMessage);

            // 비밀번호 유효성 검사 (영어, 숫자, 특수문자 포함, 6~20자)
            if (!isPasswordValid(password)) {
                passwordField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                passwordErrorMessage.setText("영어와 숫자, 특수문자를 조합하여 6-20자로 입력해주세요.");
                passwordErrorMessage.setVisibility(TextView.VISIBLE); // 오류 메시지 표시
                return;
            } else {
                passwordField.setBackgroundResource(R.drawable.normal_background); // 정상 배경
                passwordErrorMessage.setVisibility(TextView.GONE); // 오류 메시지 숨기기
            }

            // 비밀번호와 비밀번호 확인이 일치하는지 검사
            if (!password.equals(confirmPassword)) {
                confirmPasswordField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                passwordErrorMessage.setText("비밀번호가 일치하지 않습니다.");
                passwordErrorMessage.setVisibility(TextView.VISIBLE); // 오류 메시지 표시
                return;
            } else {
                confirmPasswordField.setBackgroundResource(R.drawable.normal_background); // 정상 배경
                passwordErrorMessage.setVisibility(TextView.GONE); // 오류 메시지 숨기기
            }

            // 회원가입 성공
            Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
        });
    }

    // 비밀번호 유효성 검사 (영어, 숫자, 특수문자 포함, 6~20자)
    private boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,20}$";
        return password.matches(passwordPattern);
    }
}
