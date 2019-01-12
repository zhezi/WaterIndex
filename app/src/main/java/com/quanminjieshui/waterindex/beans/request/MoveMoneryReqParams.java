package com.quanminjieshui.waterindex.beans.request;

import com.quanminjieshui.waterindex.beans.BaseBean;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class MoveMoneryReqParams extends BaseBean{
    String type; 	//方向 	字符串(string) 	是 	placeholder="非必填" 	1 划出(主站到c2c) 2划入
    String total; 	//数量 	字符串(string) 	是
    String safe_pw; 	//资金密码 	字符串(string) 	是

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSafe_pw() {
        return safe_pw;
    }

    public void setSafe_pw(String safe_pw) {
        this.safe_pw = safe_pw;
    }
}
