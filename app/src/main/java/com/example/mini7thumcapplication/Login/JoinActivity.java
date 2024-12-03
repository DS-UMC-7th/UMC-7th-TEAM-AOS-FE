package com.example.mini7thumcapplication.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mini7thumcapplication.R;

public class JoinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_join); // 회원가입 레이아웃 연결

        Button joinMembershipButton = findViewById(R.id.JoinMembershipButton);
        joinMembershipButton.setOnClickListener(v -> {
            // 필드 연결
            EditText usernameField = findViewById(R.id.registerUsername);
            EditText passwordField = findViewById(R.id.registerPassword);
            EditText confirmPasswordField = findViewById(R.id.passwordcheck);
            EditText nicknameField = findViewById(R.id.registerNickname);

            TextView usernameErrorMessage = findViewById(R.id.usernameErrorMessage);
            TextView passwordErrorMessage = findViewById(R.id.PasswordErrorMessage);
            TextView passwordcheckErrorMessage = findViewById(R.id.passwordcheckErrorMessage);
            TextView nicknameErrorMessage = findViewById(R.id.nicknameErrorMessage);

            // 입력값 가져오기
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();
            String nickname = nicknameField.getText().toString().trim();

            // 모든 검사를 한 번에 실행
            boolean isValid = true;

            // 1. 아이디 유효성 검사
            if (!isUsernameValid(username)) {
                usernameField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                usernameErrorMessage.setText("아이디는 4~12자, 영어와 숫자만 가능합니다.");
                usernameErrorMessage.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                usernameField.setBackgroundResource(R.drawable.button2); // 기본 배경 복원
                usernameErrorMessage.setVisibility(View.GONE);
            }

            // 2. 비밀번호 유효성 검사
            if (!isPasswordValid(password)) {
                passwordField.setBackgroundResource(R.drawable.error_background);
                passwordErrorMessage.setText("비밀번호는 6~20자, 영어, 숫자, 특수문자를 포함해야 합니다.");
                passwordErrorMessage.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                passwordField.setBackgroundResource(R.drawable.button2); // 기본 배경 복원
                passwordErrorMessage.setVisibility(View.GONE);
            }

            // 3. 비밀번호 확인 검사
            if (!password.equals(confirmPassword)) {
                confirmPasswordField.setBackgroundResource(R.drawable.error_background);
                passwordcheckErrorMessage.setText("비밀번호가 일치하지 않습니다.");
                passwordcheckErrorMessage.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                confirmPasswordField.setBackgroundResource(R.drawable.button2); // 기본 배경 복원
                passwordcheckErrorMessage.setVisibility(View.GONE);
            }

            // 4. 닉네임 유효성 검사
            if (nickname.isEmpty() || !isNicknameValid(nickname)) {
                nicknameField.setBackgroundResource(R.drawable.error_background);
                nicknameErrorMessage.setText("닉네임은 12자 이하로 입력해주세요.");
                nicknameErrorMessage.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                nicknameField.setBackgroundResource(R.drawable.button2); // 기본 배경 복원
                nicknameErrorMessage.setVisibility(View.GONE);
            }

            // 유효성 검사 실패 시 종료
            if (!isValid) {
                return;
            }

            // 회원가입 성공
            Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); // 현재 회원가입 화면 종료
        });
    }

    // 아이디 유효성 검사
    private boolean isUsernameValid(String username) {
        String usernamePattern = "^[A-Za-z0-9]{4,12}$"; // 영어와 숫자만 허용, 4~12자
        return username.matches(usernamePattern);
    }

    // 비밀번호 유효성 검사
    private boolean isPasswordValid(String password) {
        // 영어, 숫자, 특수문자를 포함하며, 6~20자
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$";
        return password.matches(passwordPattern);
    }

    // 닉네임 유효성 검사
    private boolean isNicknameValid(String nickname) {
        return nickname.length() <= 12;
    }
}
