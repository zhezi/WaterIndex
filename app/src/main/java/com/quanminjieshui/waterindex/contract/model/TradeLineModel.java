/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TradeLineModel
 * Author: sxt
 * Date: 2019/1/4 10:53 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterindex.contract.model;

import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.TradeLineResponseBean;
import com.quanminjieshui.waterindex.beans.TradeListsResponseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;

/**
 *
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.model
 * @ClassName: TradeLineModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/4 10:53 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/4 10:53 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeLineModel {

    public void getTradeLine(BaseActivity activity, String type, final TradeLineCallback callback){
        HashMap<String,Object>params=new HashMap<>();
        params.put("type",type);
        RetrofitFactory.getInstance().createService()
                .tradeLine(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity<TradeLineResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TradeLineResponseBean>>io())
                .subscribe(new BaseObserver<TradeLineResponseBean>(activity) {

                    @Override
                    protected void onSuccess(TradeLineResponseBean bean) throws Exception {
                        callback.onTradeLineSuccess(bean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onTradeLineFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onTradeLineFailed(e.getMessage());
                            }
                        } else {
                            callback.onTradeLineFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onTradeLineFailed(msg);
                    }


                });
    }


    public interface TradeLineCallback{
        void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean);
        void onTradeLineFailed(String msg);

    }
}