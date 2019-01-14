package com.jieshuizhibiao.waterindex.event;

public class CreateOrderResultEvent {
    String title;

    String msg;

    public CreateOrderResultEvent(String title, String msg) {
        this.title = title;
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public String getMsg() {
        return msg;
    }
}
