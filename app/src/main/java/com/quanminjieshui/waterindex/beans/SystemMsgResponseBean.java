package com.quanminjieshui.waterindex.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class SystemMsgResponseBean {

    private List<SystemMsgList> lists;

    public List<SystemMsgList> getLists() {
        return lists;
    }

    public void setLists(List<SystemMsgList> lists) {
        this.lists = lists;
    }

    private static class SystemMsgList{

    }
}
