package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.UserDetailResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface UserDetailViewImpl extends IBaseViewImpl {
    void onUserDetailSuccess(UserDetailResponseBean userDetailResponseBean);
    void onUserDetailFailed(String msg);
}
