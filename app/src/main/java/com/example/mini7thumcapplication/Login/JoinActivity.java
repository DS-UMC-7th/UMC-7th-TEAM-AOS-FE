package com.example.mini7thumcapplication.Login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mini7thumcapplication.R;

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
            EditText confirmPasswordField = findViewById(R.id.passwordcheck);
            EditText nicknameField = findViewById(R.id.registerNickname);

            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();
            String nickname = nicknameField.getText().toString().trim();

            TextView usernameErrorMessage = findViewById(R.id.usernameErrorMessage);
            TextView passwordErrorMessage = findViewById(R.id.passwordErrorMessage);
            TextView passwordcheckMessage = findViewById(R.id.passwordcheckMessage);
            TextView nicknameErrorMessage = findViewById(R.id.nicknameErrorMessage);

            // 아이디 유효성 검사 (영어와 숫자 조합, 4-12자)
            if (!isUsernameValid(username)) {
                usernameField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                usernameErrorMessage.setVisibility(TextView.VISIBLE); // 오류 메시지 표시
                return;
            } else {
                usernameField.setBackgroundResource(0); // 기본 배경 (배경 변경 안 함)
                usernameErrorMessage.setVisibility(TextView.GONE); // 오류 메시지 숨기기
            }

            // 비밀번호와 비밀번호 확인 일치 여부 검사
            if (!password.equals(confirmPassword)) {
                passwordErrorMessage.setVisibility(TextView.VISIBLE); // 오류 메시지 표시
                return;
            } else {
                passwordErrorMessage.setVisibility(TextView.GONE); // 오류 메시지 숨기기
            }

            // 닉네임 유효성 검사 (12자 이하)
            if (!isNicknameValid(nickname)) {
                nicknameField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                nicknameErrorMessage.setVisibility(TextView.VISIBLE); // 오류 메시지 표시
                return;
            } else {
                nicknameField.setBackgroundResource(0); // 기본 배경 (배경 변경 안 함)
                nicknameErrorMessage.setVisibility(TextView.GONE); // 오류 메시지 숨기기
            }

            // 회원가입 성공
            Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
        });
    }

    // 아이디 유효성 검사 (영어와 숫자 조합, 4-12자)
    private boolean isUsernameValid(String username) {
        String usernamePattern = "^[A-Za-z0-9]{4,12}$"; // 영어와 숫자만 허용, 4~12자
        return username.matches(usernamePattern);
    }

    // 닉네임 유효성 검사 (12자 이하)
    private boolean isNicknameValid(String nickname) {
        return nickname.length() <= 12;
    }
}
