package com.jieshuizhibiao.waterindex.beans.request;

import com.jieshuizhibiao.waterindex.beans.BaseBean;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:收款方式 开启|禁用
 */

public class PayMentTypeSwitchReqParams extends BaseBean{
    String id; 	//收款信息id 	字符串(string) 	是

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}