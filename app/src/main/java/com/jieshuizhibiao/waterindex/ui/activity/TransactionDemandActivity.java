package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.request.ListTradeReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.ListTradePresenter;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.ui.adapter.TableViewpagerAdapter;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/14.
 * Class Note:买卖需求
 */

public class TransactionDemandActivity extends BaseActivity implements CommonViewImpl {

    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    private TableViewpagerAdapter adapter;
    private String[] titles=new String[]{"全部","购买","出售"};
    private List<Fragment> fragments=new ArrayList<>();
    private ListTradePresenter listTradePresenter;
    private int pageSize = 10, page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);

        listTradePresenter = new ListTradePresenter();
        listTradePresenter.attachView(this);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("订单");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_transaction_demand);
    }

    public void doReuqest(){
        ListTradeReqParams params = new ListTradeReqParams();
        params.setType(HttpConfig.TRANSCATION_TYPE_ALL);
        listTradePresenter.getListTrade(TransactionDemandActivity.this,params);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.left_ll})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.left_ll:
                goBack(view);
                break;
            default:
                break;
        }

    }

    @Override
    public void onRequestSuccess(Object bean) {

    }

    @Override
    public void onRequestFailed(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listTradePresenter!=null){
            listTradePresenter.detachView();
        }
    }
}
