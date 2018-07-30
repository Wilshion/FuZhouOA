package com.wilshion.oa.ui.activity.main;

/**
 * Created by Wilshion on 2018/7/30 15:08.
 * [description : ]
 * [version : 1.0]
 */
public class AuthenticationData {
    private String ip;
    private String userName;
    private String passWord;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public static AuthenticationData newInstance(){
        AuthenticationData ad = new AuthenticationData();
        ad.setIp("10.1.27.245");
        ad.setUserName("zhsclwsu18");
        ad.setPassWord("szscl@2016");
        return ad;
    }
}
