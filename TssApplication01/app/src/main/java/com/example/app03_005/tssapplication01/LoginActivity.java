package com.example.app03_005.tssapplication01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.example.app03_005.tssapplication01.common.TssUtility;
import com.example.app03_005.tssapplication01.dto.internal.InternalStorageDto;

public class LoginActivity extends AppCompatActivity {

    /** ログ出力時に使用 */
    private final String TAG = this.getClass().getSimpleName();
    /** 内部ファイル用DTO */
    private InternalStorageDto internalStorageDto;

    /** ログインID記憶スウィッチ */
    CompoundButton loginSwitch;
    /** パスワード記憶スウィッチ */
    CompoundButton passwordSwitch;
    /** ログインID入力フォーム */
    EditText loginEditText;
    /** パスワード入力フォーム */
    EditText passwordEditText;
    /** ログインボタン */
    Button loginButton;
    /** ログインアウトプット */
    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG,"start on create");

        //内部ストレージから情報を取得する
        internalStorageDto = new InternalStorageDto();
        internalStorageDto.stringToDto(TssUtility.getInternalStorageText(this.getApplication()));

        //ログインID記憶スウィッチ 前回の記憶スウィッチを反映させる
        loginSwitch = (CompoundButton) findViewById(R.id.login_switch_login);
        loginSwitch.setChecked(internalStorageDto.isMemorizeLoginId());

        //パスワード記憶スウィッチ 前回の記憶スウィッチを反映させる
        passwordSwitch = (CompoundButton) findViewById(R.id.login_switch_password);
        passwordSwitch.setChecked(internalStorageDto.isMemorizePassword());

        //ログインID入力フォーム　ログイン記憶スウィッチがTrueの場合、内部ストレージの情報を初期表示する
        loginEditText = (EditText) findViewById(R.id.login_textField_login);
        if (internalStorageDto.isMemorizeLoginId()) {
            loginEditText.setText(internalStorageDto.getLoginId());
        }

        //パスワード入力フォーム　パスワード記憶スウィッチがTrueの場合、内部ストレージの情報を初期表示する
        passwordEditText = (EditText) findViewById(R.id.login_textField_password);
        if (internalStorageDto.isMemorizePassword()) {
            passwordEditText.setText(internalStorageDto.getPassword());
        }

        //
        infoTextView = (TextView) findViewById(R.id.login_textView_info);
        infoTextView.setText(TssUtility.getInternalStorageText(this.getApplicationContext()));

        //ログインボタン
        loginButton = (Button) findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clickLoginButton();
            }
        });
    }

    /**
     * ログインボタンが押されたとき
     */
    private void clickLoginButton(){
        Log.i(TAG,"click login button");

        String loginId = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isLoginSwitch = loginSwitch.isChecked();
        boolean isPasswordSwitch = passwordSwitch.isChecked();

        internalStorageDto.setLoginId(loginId);
        internalStorageDto.setPassword(password);
        internalStorageDto.setMemorizeLoginId(isLoginSwitch);
        internalStorageDto.setMemorizePassword(isPasswordSwitch);

        TssUtility.writeInternalStorage(this.getApplicationContext(), internalStorageDto.dtoToString());
        Log.d(TAG,"write login id");
        infoTextView.setText(TssUtility.getInternalStorageText(this.getApplicationContext()));
        Log.d(TAG,"read internal starage");

        Intent intent = new Intent(this, AttendanceListActivity.class);
        intent.putExtra("loginID", loginId);
        startActivity(intent);

    }
}
