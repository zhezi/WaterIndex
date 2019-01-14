package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/10.
 * Class Note:
 */

public interface ChangeCapitalPassViewImpl extends IBaseViewImpl {
    void onChangeCapitalPassSuccess();
    void onChangeCapitalPassFailed(String msg);
}
