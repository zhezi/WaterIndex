package com.jieshuizhibiao.waterindex.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.ui.activity.TransactionDemandActivity;
import com.jieshuizhibiao.waterindex.ui.activity.TranscationReleaseBuyOrSellActivity;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/25.
 * Class Note:全部订单
 */

public class TranscationAllFragment extends BaseFragment {

    private TransactionDemandActivity demandActivity;
    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_transcation_all,container,false);
        ButterKnife.bind(this, rootView);
        alertChainDialog = new AlertChainDialog(getBaseActivity());
        initView();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.demandActivity = (TransactionDemandActivity) context;
        if(context instanceof TransactionDemandActivity){

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.demandActivity = (TransactionDemandActivity) activity;
        if(activity instanceof TransactionDemandActivity){

        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
    }

    @OnClick({R.id.btn_release_transaction_buy,R.id.btn_release_transaction_sell})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_release_transaction_buy:
                jumpActivity("Buy",TranscationReleaseBuyOrSellActivity.class);
                break;
            case R.id.btn_release_transaction_sell:
                jumpActivity("Sell",TranscationReleaseBuyOrSellActivity.class);
                break;
            default:break;
        }
    }

    public void jumpActivity(String action,Class<?> cla){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("action",action);
        intent.putExtras(bundle);
        intent.setClass(this.demandActivity,cla);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
