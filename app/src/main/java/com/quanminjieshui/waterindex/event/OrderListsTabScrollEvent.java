package com.quanminjieshui.waterindex.event;

import com.quanminjieshui.waterindex.beans.ListOrder;
import com.quanminjieshui.waterindex.beans.OrderListsResponseBean;

import java.util.List;

public class OrderListsTabScrollEvent {
    private String status;
    private List<ListOrder>list;

    public OrderListsTabScrollEvent(String status, List<ListOrder>list) {
        this.status = status;
        this.list=list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListOrder> getList() {
        return list;
    }

    public void setList(List<ListOrder> list) {
        this.list = list;
    }
}
