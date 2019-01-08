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
package com.quanminjieshui.waterindex.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.InfoListsResponseBean;
import com.quanminjieshui.waterindex.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterindex.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterindex.beans.TradeIndexBase;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRequest = findViewById(R.id.btn_request);
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
                HashMap<String, Object> params = new HashMap<>();
                params.put("type",1);
                params.put("page",1);
                params.put("page_size",2);
                RetrofitFactory.getInstance().createService()
                        .tradeIndex(RequestUtil.getRequestHashBody(params, false))
                        .compose(TestActivity.this.<BaseEntity<TradeIndexBase>>bindToLifecycle())
                        .compose(ObservableTransformerUtils.<BaseEntity<TradeIndexBase>>io())
                        .subscribe(new BaseObserver<TradeIndexBase>(TestActivity.this) {

                            @Override
                            protected void onSuccess(TradeIndexBase bean) throws Exception {
//                                LogUtils.e("tag",
//                                        bean.getLists().get(0).getContent());
                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                            }
                        });


                break;
        }
    }

}