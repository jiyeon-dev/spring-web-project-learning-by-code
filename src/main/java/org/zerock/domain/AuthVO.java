package org.zerock.domain;

import lombok.ToString;

@ToString
public class AuthVO {

    private String userid;
    private String auth;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
