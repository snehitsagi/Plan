package com.example.snehitsagi.plan;

/**
 * Created by Snehit Sagi on 08-Jun-17.
 */

public class Members {

    String membername;
    String memberspouse;
    String memberphone;
    String memberemail;
    String memeberdob;
    String memberdow;
    String id;

    public Members(){

    }

    public Members(String id,String membername, String memberspouse, String memberphone, String memberemail, String memberdob) {
        this.membername = membername;
        this.memberspouse = memberspouse;
        this.memberphone = memberphone;
        this.memberemail = memberemail;
        this.memeberdob=memberdob;

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
        return memeberdob;
    }
}
