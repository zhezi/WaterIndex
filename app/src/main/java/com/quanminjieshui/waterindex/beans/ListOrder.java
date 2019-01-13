package com.quanminjieshui.waterindex.beans;
public class ListOrder {
    private String my_action;//方向  购买|出售
    private int other_uid;//对方uid
    private String other_nickname;//对方昵称
    private String other_avatar;//对方头像
    private String createtime;//创建时间
    private String status_text;//订单状态     已完成|进行中|已取消|申诉处理
    private String total;//数量    3.10000T
    private String rmb;//交易金额   9.30000元
    private int order_id;//订单id
    private String next_step;//下一步动作
    public void setMy_action(String my_action) {
        this.my_action = my_action;
    }
    public String getMy_action() {
        return my_action;
    }

    public void setOther_uid(int other_uid) {
        this.other_uid = other_uid;
    }
    public int getOther_uid() {
        return other_uid;
    }

    public void setOther_nickname(String other_nickname) {
        this.other_nickname = other_nickname;
    }
    public String getOther_nickname() {
        return other_nickname;
    }

    public void setOther_avatar(String other_avatar) {
        this.other_avatar = other_avatar;
    }
    public String getOther_avatar() {
        return other_avatar;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getCreatetime() {
        return createtime;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }
    public String getStatus_text() {
        return status_text;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    public String getTotal() {
        return total;
    }

    public void setRmb(String rmb) {
        this.rmb = rmb;
    }
    public String getRmb() {
        return rmb;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getOrder_id() {
        return order_id;
    }

    public void setNext_step(String next_step) {
        this.next_step = next_step;
    }
    public String getNext_step() {
        return next_step;
    }
}
