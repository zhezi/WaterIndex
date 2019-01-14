/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestActivity
 * Author: ccdc_android
 * Date: 2018/12/14 11:06 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.http.BaseObserver;
import com.jieshuizhibiao.waterindex.http.RetrofitFactory;
import com.jieshuizhibiao.waterindex.http.bean.BaseEntity;
import com.jieshuizhibiao.waterindex.http.utils.ObservableTransformerUtils;
import com.jieshuizhibiao.waterindex.http.utils.RequestUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashMap;

import butterknife.OnClick;

/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.test
 * @ClassName: TestActivity
 * @Description: 用于各接口测试
 * @Author: sxt
 * @CreateDate: 2018/12/14 11:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/14 11:06 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TestActivity extends BaseActivity {
    //    @BindView(R.id.btn_request)
    Button btnRequest;

int order_id;
String next_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRequest = findViewById(R.id.btn_request);
        order_id=getIntent().getIntExtra("order_id",0);
        next_step=getIntent().getStringExtra("next_step");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_request})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btn_request:
                ToastUtils.showCustomToast("order_id"+order_id+"  next_step:"+next_step);
                order_id=59;
                HashMap<String, Object> params = new HashMap<>();
                params.put("order_id",order_id);
                RetrofitFactory.getInstance().createService()
                        .buyerCancle(RequestUtil.getRequestHashBody(params, false))
                        .compose(TestActivity.this.<BaseEntity>bindToLifecycle())
                        .compose(ObservableTransformerUtils.<BaseEntity>io())
                        .subscribe(new BaseObserver(TestActivity.this) {

                            @Override
                            protected void onSuccess(Object bean) throws Exception {

                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                            }
                        });


                break;
        }
    }

}