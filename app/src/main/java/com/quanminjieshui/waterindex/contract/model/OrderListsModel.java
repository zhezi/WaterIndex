package com.quanminjieshui.waterindex.contract.model;

import android.text.TextUtils;
import android.util.Log;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.ListOrder;
import com.quanminjieshui.waterindex.contract.model.callback.CommonCallback;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sxt
 *
 */

public class OrderListsModel {

    public void orderList(BaseActivity activity, String type, String page, String page_size, final CommonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(type)) {
            params.put("type", type);
        }
        if (!TextUtils.isEmpty(page)) {
            params.put("page", page);
        }
        if (!TextUtils.isEmpty(page_size)) {
            params.put("page_size", page_size);
        }
        Log.e("TAG","是否为空"+(activity==null));
        RetrofitFactory.getInstance().createService()
                .listOrder(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity<List<ListOrder>>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<List<ListOrder>>>io())
                .subscribe(new BaseObserver<List<ListOrder>>() {

                    @Override
                    protected void onSuccess(List<ListOrder> list) throws Exception {
                        callback.onRequestSuccess(list);
                    }

                    /**
                     * 返回失败
                     *
                     * @param e
                     * @param isNetWorkError 是否是网络错误
                     * @throws Exception
                     */
                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFailed(e.getMessage());
                            }
                        } else {
                            callback.onRequestFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onRequestFailed(msg);
                    }
                });

    }
}
