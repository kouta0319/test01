package com.example.app03_005.tssapplication01.dto.internal;

/**
 * Created by APP03-005 on 2016/12/25.
 */

public class InternalStorageDto {
    /** 記憶していたログインID */
    private String loginId;
    /** 記憶していたパスワード */
    private String password;
    /** ログインIDを記憶するか　true:記憶する */
    private boolean isMemorizeLoginId;
    /** パスワードを記憶するか　true:記憶する */
    private boolean isMemorizePassword;

    /**
     * DtoからStringへ変換する
     * @return
     */
    public String dtoToString() {
        return loginId + "," + password + "," + isMemorizeLoginId + "," + isMemorizePassword;
    }

    /**
     * StringからDtoへセットする
     */
    public void stringToDto(String text) {
        if (text == null || "".equals(text)){
            return;
        }
       String[] strings = text.split(",");
        if (strings.length != 4){
            return;
        }
        loginId = strings[0];
        password = strings[1];
        isMemorizeLoginId = Boolean.valueOf(strings[2]);
        isMemorizePassword = Boolean.valueOf(strings[3]);
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMemorizeLoginId() {
        return isMemorizeLoginId;
    }

    public void setMemorizeLoginId(boolean memorizeLoginId) {
        isMemorizeLoginId = memorizeLoginId;
    }

    public boolean isMemorizePassword() {
        return isMemorizePassword;
    }

    public void setMemorizePassword(boolean memorizePassword) {
        isMemorizePassword = memorizePassword;
    }
}
