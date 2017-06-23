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
    String memberaddress;
    String membercity;
    String memberstate;
    String membercountry;
    String memberpostal;
    String memberprof;
    String memberexpert;
    String memberspl;
    String id;

    public Members(){

    }

    public Members(String id,String membername, String memberspouse, String memberphone, String memberemail, String memberdob, String memberdow,String memberdoj,String memberaddress,String membercity,String membercountry, String memberpostal,String memberprof,String memberexpert,String memberspl,String memberstate) {
        this.membername = membername;
        this.memberspouse = memberspouse;
        this.memberphone = memberphone;
        this.memberemail = memberemail;
        this.memberdob=memberdob;
        this.memberdow=memberdow;
        this.memberdoj=memberdoj;
        this.memberaddress=memberaddress;
        this.membercity=membercity;
        this.membercountry=membercountry;
        this.memberpostal=memberpostal;
        this.memberprof=memberprof;
        this.memberexpert=memberexpert;
        this.memberspl=memberspl;
        this.memberstate=memberstate;

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
    public String getMemberdoj() { return memberdoj; }
    public String getMemberaddress() { return memberaddress; }
    public String getMembercity() { return membercity; }
    public String getMembercountry() { return membercountry; }
    public String getMemberpostal() { return memberpostal; }
    public String getMemberprof() { return memberprof; }
    public String getMemberexpert() { return memberexpert; }
    public String getMemberspl() { return memberspl; }
    public String getMemberstate() { return memberstate; }
}
