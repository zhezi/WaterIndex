package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.MoveMoneryBean;
import com.quanminjieshui.waterindex.beans.request.MoveMoneryReqParams;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:移交资产
 */

public class MoveMoneryModel {

    public void moveMonery(BaseActivity activity, MoveMoneryReqParams params,final MoveMoneryCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .moveMonery(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity<MoveMoneryBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<MoveMoneryBean>>io())
                .subscribe(new BaseObserver<MoveMoneryBean>(activity) {
                    @Override
                    protected void onSuccess(MoveMoneryBean moveMoneryBean) throws Exception {
                        callBack.success(moveMoneryBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.failed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.failed(e.getMessage());
                            }
                        } else {
                            callBack.failed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.failed(msg);
                    }
                });
    }

    public interface MoveMoneryCallBack{
        void success(MoveMoneryBean moveMoneryBean);
        void failed(String msg);
    }
}
