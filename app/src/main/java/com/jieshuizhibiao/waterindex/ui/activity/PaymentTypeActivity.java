package com.jieshuizhibiao.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.PayMentResponseBean;
import com.jieshuizhibiao.waterindex.beans.request.PayMentTypeSwitchReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.PayMentTypePresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.PayMentTypeSwitchPresenter;
import com.jieshuizhibiao.waterindex.contract.view.PayMentTypeSwitchViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.PayMentTypeViewImpl;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.utils.SPUtil;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;
import com.jieshuizhibiao.waterindex.utils.Util;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:收款方式
 */

public class PaymentTypeActivity extends BaseActivity implements PayMentTypeSwitchViewImpl,PayMentTypeViewImpl {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.payment_switch_zfb_img)
    ImageView zfbSwitchImg;
    @BindView(R.id.payment_switch_wx_img)
    ImageView wxSwitchImg;
    @BindView(R.id.payment_switch_bank_img)
    ImageView bankSwitchImg;
    @BindView(R.id.tv_alipay_state)
    TextView tvAliPayState;
    @BindView(R.id.tv_wxpay_state)
    TextView tvWxpayState;
    @BindView(R.id.tv_bank_state)
    TextView tvBankState;

    private PayMentTypeSwitchPresenter payMentTypeSwitchPresenter;
    private PayMentTypePresenter payMentTypePresenter;
    private PayMentResponseBean.TypeList bankCardLists;
    private PayMentResponseBean.TypeList aliPayLists;
    private PayMentResponseBean.TypeList wxPayLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        payMentTypeSwitchPresenter = new PayMentTypeSwitchPresenter();
        payMentTypeSwitchPresenter.attachView(this);
        payMentTypePresenter = new PayMentTypePresenter();
        payMentTypePresenter.attachView(this);
        initView();
    }

    private void initView() {
        tv_title_center.setText("收款方式");
        if ((Boolean) SPUtil.get(this,"isOpenZFB",false)) {
            zfbSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
        }else {
            zfbSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
        }
        if ((Boolean) SPUtil.get(this,"isOpenWX",false)) {
            wxSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
        }else {
            wxSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
        }
        if ((Boolean) SPUtil.get(this,"isOpenBANK",false)) {
            bankSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
        }else {
            bankSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_payment_type);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doRequestPayType();
    }

    @Override
    protected void onResume() {
        super.onResume();
        doRequestPayType();
    }

    @OnClick({R.id.left_ll,R.id.zfb_ll,R.id.wx_ll,R.id.bank_ll,R.id.payment_switch_zfb_img,
            R.id.payment_switch_wx_img,R.id.payment_switch_bank_img})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            case R.id.zfb_ll:
                if (!Util.isFastDoubleClick()) {
                    bundle.putString("payType", HttpConfig.ZFB_TYPE);
                    jumpActivity(bundle);
                }
                break;
            case R.id.wx_ll:
                if (!Util.isFastDoubleClick()) {
                    bundle.putString("payType", HttpConfig.WX_TYPE);
                    jumpActivity(bundle);
                }
                break;
            case R.id.bank_ll:
                if (!Util.isFastDoubleClick()) {
                    jumpActivity(AddBankPaymetActivity.class);
                }
                break;
            case R.id.payment_switch_zfb_img:
                if (!Util.isFastDoubleClick() && (Boolean) SPUtil.get(this,"isOpenZFB",false)) {
                    zfbSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
                    SPUtil.insert(this,"isOpenZFB",false);
                }else {
                    zfbSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
                    SPUtil.insert(this,"isOpenZFB",true);
                }
                doRequestSwitch(HttpConfig.ZFB_TYPE);
                break;
            case R.id.payment_switch_wx_img:
                if (!Util.isFastDoubleClick() && (Boolean) SPUtil.get(this,"isOpenWX",false)) {
                    wxSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
                    SPUtil.insert(this,"isOpenWX",false);
                }else {
                    wxSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
                    SPUtil.insert(this,"isOpenWX",true);
                }
                doRequestSwitch(HttpConfig.WX_TYPE);
                break;
            case R.id.payment_switch_bank_img:
                if (!Util.isFastDoubleClick() && (Boolean) SPUtil.get(this,"isOpenBANK",false)) {
                    bankSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
                    SPUtil.insert(this,"isOpenBANK",false);
                }else {
                    bankSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
                    SPUtil.insert(this,"isOpenBANK",true);
                }
                doRequestSwitch((String) SPUtil.get(this,SPUtil.ID,"-1"));
                break;
            default:break;
        }
    }

    public void doRequestPayType(){
        payMentTypePresenter.payMentType(PaymentTypeActivity.this);
        showLoadingDialog();
    }

    public void doRequestSwitch(String id){
        PayMentTypeSwitchReqParams params = new PayMentTypeSwitchReqParams();
        params.setId(id);
        payMentTypeSwitchPresenter.setPayMentTypeSwitch(PaymentTypeActivity.this,params);
        showLoadingDialog();
    }

    public void jumpActivity(Class<?> cla){
        startActivity(new Intent(PaymentTypeActivity.this,cla));
    }

    public void jumpActivity(Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(PaymentTypeActivity.this,AddWxOrZfbPaymetActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onPaymentTypeSwitchSuccess() {
        dismissLoadingDialog();
    }

    @Override
    public void onPaymentTypeSwitcFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    @Override
    public void onPayMentTypeSuccess(PayMentResponseBean beanList) {
        dismissLoadingDialog();
        bankCardLists = beanList.L1;
        aliPayLists = beanList.L2;
        wxPayLists = beanList.L3;
        tvAliPayState.setText(aliPayLists.getLink_text());
        tvWxpayState.setText(wxPayLists.getLink_text());
        tvBankState.setText(bankCardLists.getLink_text());
    }

    @Override
    public void onPayMentTypeFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (payMentTypeSwitchPresenter!=null){
            payMentTypeSwitchPresenter.detachView();
        }
        if (payMentTypePresenter !=null){
            payMentTypePresenter.detachView();
        }
    }
}
