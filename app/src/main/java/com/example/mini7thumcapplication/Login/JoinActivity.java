package com.example.mini7thumcapplication.Login;



import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

        // 뒤로가기 버튼 연결
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            // LoginActivity로 이동
            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // 현재 JoinActivity 종료
        });

        Button joinMembershipButton = findViewById(R.id.JoinMembershipButton);
        joinMembershipButton.setOnClickListener(v -> {
            // 입력 필드 가져오기
            EditText usernameField = findViewById(R.id.registerUsername);
            EditText passwordField = findViewById(R.id.registerPassword);//비밀번호
            EditText confirmPasswordField = findViewById(R.id.passwordcheck);//비밀번호확인
            EditText nicknameField = findViewById(R.id.registerNickname);//닉네임


            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();
            String nickname = nicknameField.getText().toString().trim();


            TextView usernameErrorMessage = findViewById(R.id.usernameErrorMessage);
            TextView passwordErrorMessage = findViewById(R.id.passwordErrorMessage);
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

            // 오류 메시지 출력 영역
            TextView usernameErrorMessage = findViewById(R.id.usernameErrorMessage);
            TextView passwordErrorMessage = findViewById(R.id.PasswordErrorMessage);
            TextView passwordcheckErrorMessage = findViewById(R.id. passwordcheckErrorMessage);
            TextView nicknameErrorMessage = findViewById(R.id.nicknameErrorMessage);

            // 유효성 검사 플래그
            boolean isValid = true;

            // 아이디 유효성 검사 (영어와 숫자 조합, 4~12자)
            if (!isUsernameValid(username)) {
                usernameField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                usernameErrorMessage.setVisibility(TextView.VISIBLE);
                isValid = false;
            } else {
                usernameField.setBackgroundResource(R.drawable.button2); // 기본 배경
                usernameErrorMessage.setVisibility(TextView.GONE);
            }

            // 비밀번호 유효성 검사 (영어, 숫자, 특수문자 조합, 6~20자)
            if (!isPasswordValid(password)) {
                passwordField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                passwordErrorMessage.setVisibility(TextView.VISIBLE);
                isValid = false;
            } else {
                passwordField.setBackgroundResource(R.drawable.button2); // 기본 배경
                passwordErrorMessage.setVisibility(TextView.GONE);
            }

            // 비밀번호 확인 검사
            if (!password.equals(confirmPassword)) {
                confirmPasswordField.setBackgroundResource(R.drawable.error_background); // 오류 배경
                passwordcheckErrorMessage.setVisibility(TextView.VISIBLE);
                isValid = false;
            } else {
                confirmPasswordField.setBackgroundResource(R.drawable.button2); // 기본 배경
                if (isPasswordValid(password)) { // 비밀번호 유효성 검사 통과 시 오류 메시지 숨김
                    passwordcheckErrorMessage.setVisibility(TextView.GONE);
                }

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

                isValid = false;
            } else {
                nicknameField.setBackgroundResource(R.drawable.button2); // 기본 배경
                nicknameErrorMessage.setVisibility(TextView.GONE); // 오류 메시지 숨김
            }

            // 유효성 검사 성공 시 처리
            if (isValid) {
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                // 로그인 화면으로 이동
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                // 현재 액티비티 종료
                finish();
            } else {
                Toast.makeText(this, "입력한 내용을 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
            }

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

    // 비밀번호 유효성 검사 (영어, 숫자, 특수문자 포함, 6-20자)
    private boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,20}$";
        return password.matches(passwordPattern);
    }

    // 닉네임 유효성 검사 (12자 이하)
    private boolean isNicknameValid(String nickname) {
        return nickname.length() > 0 && nickname.length() <= 12;

    }
}
