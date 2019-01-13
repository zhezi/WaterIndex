package com.quanminjieshui.waterindex.event;

public class CreateOrderResultEvent {
    String msg;

    public CreateOrderResultEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
