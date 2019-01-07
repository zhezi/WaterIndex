package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.UserDetailResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface UserDetailViewImpl extends IBaseViewImpl {
    void onUserDetailSuccess(UserDetailResponseBean userDetailResponseBean);
    void onUserDetailFailed(String msg);
}
