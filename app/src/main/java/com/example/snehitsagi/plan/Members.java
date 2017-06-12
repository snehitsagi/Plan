package com.example.snehitsagi.plan;

/**
 * Created by Snehit Sagi on 08-Jun-17.
 */

public class Members {

    String membername;
    String memberspouse;
    String memberphone;
    String memberemail;
    String memberdob;
    String memberdow;
    String memberdoj;
    String id;

    public Members(){

    }

    public Members(String id,String membername, String memberspouse, String memberphone, String memberemail, String memberdob, String memberdow,String memberdoj) {
        this.membername = membername;
        this.memberspouse = memberspouse;
        this.memberphone = memberphone;
        this.memberemail = memberemail;
        this.memberdob=memberdob;
        this.memberdow=memberdow;
        this.memberdoj=memberdoj;

    }

    public String getMembername() {
        return membername;
    }

    public String getMemberspouse() {
        return memberspouse;
    }

    public String getMemberphone() {
        return memberphone;
    }

    public String getMemberemail() {
        return memberemail;
    }

    public String getMemberdob() {
        return memberdob;
    }
    public String getMemberdow() {
        return memberdow;
    }
    public String getMemberdoj() {
        return memberdoj;
    }
}
