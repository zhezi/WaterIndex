package com.jieshuizhibiao.waterindex.beans;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class PayMentResponseBean {


    public TypeList L1;
    public TypeList L2;
    public TypeList L3;

    public static class TypeList{
        String id; 	//收款id 	字符串(string) 		pi_id
        String type; 	//类型 	字符串(string) 		1银行卡 2支付宝 3微信
        String type_text; 	//type文案 	字符串(string)
        String account_name; 	//账号 	字符串(string)
        String isopen; 	//开关 	字符串(string) 		1开 0关
        String link_text; 	//操作文字 	字符串(string) 		添加|编辑

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getIsopen() {
            return isopen;
        }

        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }

        public String getLink_text() {
            return link_text;
        }

        public void setLink_text(String link_text) {
            this.link_text = link_text;
        }
    }

}
