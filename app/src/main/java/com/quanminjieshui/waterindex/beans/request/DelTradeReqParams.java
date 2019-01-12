package com.quanminjieshui.waterindex.beans.request;

import com.quanminjieshui.waterindex.beans.BaseBean;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class DelTradeReqParams extends BaseBean{
    String id; 	//需求单id 	字符串(string) 	是

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
