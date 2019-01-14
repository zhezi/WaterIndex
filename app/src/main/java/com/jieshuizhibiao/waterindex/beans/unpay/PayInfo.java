package com.jieshuizhibiao.waterindex.beans.unpay;

public class PayInfo {
    private int id;
    private int uid;
    private int type;
    private String user_name;
    private String bank_name;
    private String bank_detail_name;
    private String account_name;
    private String qrcode;
    private long add_time;
    private int status;

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public int getType() {
        return type;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getBank_detail_name() {
        return bank_detail_name;
    }

    public String getAccount_name() {
        return account_name;
    }

    public String getQrcode() {
        return qrcode;
    }

    public long getAdd_time() {
        return add_time;
    }

    public int getStatus() {
        return status;
    }
}
