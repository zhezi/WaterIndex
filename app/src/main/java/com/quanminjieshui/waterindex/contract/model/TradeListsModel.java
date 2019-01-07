/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.model
 * @ClassName: TradeListsModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.TradeListsResponseBean;
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
 *
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.model
 * @ClassName: TradeListsModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeListsModel {

    public void getTradeLists(BaseActivity activity, final TradeListsCallback callback){

        RetrofitFactory.getInstance().createService()
                .tradeList(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<TradeListsResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TradeListsResponseBean>>io())
                .subscribe(new BaseObserver<TradeListsResponseBean>(activity) {
                    @Override
                    protected void onSuccess(TradeListsResponseBean tradeListsResponseBean) throws Exception {
                        callback.onGetTradeListsSuccess(tradeListsResponseBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onGetTradeListsFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onGetTradeListsFailed(e.getMessage());
                            }
                        } else {
                            callback.onGetTradeListsFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onGetTradeListsFailed(msg);
                    }
                });
    }


    public interface TradeListsCallback{
        void onGetTradeListsSuccess(TradeListsResponseBean tradeListsResponseBean);
        void onGetTradeListsFailed(String msg);
    }


}
