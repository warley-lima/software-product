package com.Util.Model;

public class UserAuthenticated {

    private String na;
    private String at;
    private String rt;

    public UserAuthenticated() {
    }

    public String getNa() {
        return na;
    }

    public void setNa(String na) {
        this.na = na;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    @Override
    public String toString() {
        return "{" +
                "na:" + na +
                ",at:" + at +
                ",rt:" + rt + "}";
    }
}
