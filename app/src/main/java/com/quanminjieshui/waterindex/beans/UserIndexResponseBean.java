package com.quanminjieshui.waterindex.beans;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class UserIndexResponseBean {
    String avatar; 	//头像 	字符串(string)
    String nick_name; 	//昵称 	字符串(string)
    String create_time; 	//注册时间 	字符串(string)
    String user_login; 	//注册手机号 	字符串(string)
    String is_auth; 	//身份认证 	字符串(string) 		0 未认证|被驳回 1认证审核中 3通过

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(String is_auth) {
        this.is_auth = is_auth;
    }
}
