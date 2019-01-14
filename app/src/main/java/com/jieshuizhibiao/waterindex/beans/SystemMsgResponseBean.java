package com.jieshuizhibiao.waterindex.beans;

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
        String title;
        String time;
        String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
