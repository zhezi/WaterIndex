package com.jieshuizhibiao.waterindex.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.PayMentResponseBean;
import com.jieshuizhibiao.waterindex.beans.SysConfigResponseBean;
import com.jieshuizhibiao.waterindex.beans.request.AddTradeReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.AddTradePresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.CommonSysConfigPresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.PayMentTypePresenter;
import com.jieshuizhibiao.waterindex.contract.view.AddTradeViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.PayMentTypeViewImpl;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;
import com.jieshuizhibiao.waterindex.utils.Util;

import java.util.Map;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/18.
 * Class Note:发布购买需求
 */

public class TranscationReleaseBuyOrSellActivity extends BaseActivity implements AddTradeViewImpl,PayMentTypeViewImpl,CommonViewImpl {

    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.time_limit_fl)
    FrameLayout timeLimitFl;
    @BindView(R.id.iv_bank_state)
    ImageView bankSelect;
    @BindView(R.id.iv_alipay_state)
    ImageView alipaySelect;
    @BindView(R.id.iv_wxpay_state)
    ImageView wxpaySelect;

    @BindView(R.id.edt_transfer_amonut_pwd)
    EditText catitalPass;
    @BindView(R.id.edt_transfer_time_limit)
    EditText timeLimit;
    @BindView(R.id.edt_transfer_min)
    EditText transferMin;
    @BindView(R.id.edt_transfer_max)
    EditText transferMax;
    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edt_border;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edt_border_illegal;
    @BindString(R.string.key_edt_release_capital_pwd)
    String keySafePwd;
    @BindString(R.string.key_edt_release_time_limit)
    String keyTimeLimit;
    @BindString(R.string.key_edt_release_transaction_max)
    String keyTransactionMax;
    @BindString(R.string.key_edt_release_transaction_min)
    String keyTransactionMin;
    @BindView(R.id.transaction_price)
    TextView transPrice;
    @BindView(R.id.bank_ll)
    LinearLayout bankLl;
    @BindView(R.id.wx_ll)
    LinearLayout wechatLl;
    @BindView(R.id.zfb_ll)
    LinearLayout alipayLl;

    private String transfeNumber = "1809";//TODO 暂时未知
    private CommonSysConfigPresenter sysConfigPresenter;
    private AddTradePresenter addTradePresenter;
    private PayMentTypePresenter payMentTypePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        addTradePresenter = new AddTradePresenter();
        addTradePresenter.attachView(this);
        payMentTypePresenter = new PayMentTypePresenter();
        payMentTypePresenter.attachView(this);
        sysConfigPresenter = new CommonSysConfigPresenter();
        sysConfigPresenter.attachView(this);
        initView();
    }

    private void initView() {
        if (getIntent().getStringExtra("action").equals("Buy")){
            tvTitleCenter.setText("发布购买需求");
            timeLimitFl.setVisibility(View.GONE);
        }else {
            tvTitleCenter.setText("发布出售需求");
            timeLimitFl.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.left_ll,R.id.bank_ll,R.id.wx_ll,R.id.zfb_ll,R.id.btn_transaction_buy})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_transaction_buy:
                AddTradeReqParams params = new AddTradeReqParams();
                if (getIntent().getStringExtra("action").equals("Buy")){
                    params.setType(HttpConfig.TRANSCATION_RELEASE_BUY);
                    params.setSafe_pw(catitalPass.getText().toString());
                    params.setPay_min(transferMin.getText().toString());
                    params.setTotal(transferMax.getText().toString());
                }else {
                    params.setType(HttpConfig.TRANSCATION_RELEASE_SELL);
                    params.setSafe_pw(catitalPass.getText().toString());
                    params.setCond_pay_timeout(timeLimit.getText().toString());
                    params.setPay_min(transferMin.getText().toString());
                    params.setTotal(transferMax.getText().toString());
                }
                if (alipaySelect.getVisibility()!=View.VISIBLE && wxpaySelect.getVisibility() !=View.VISIBLE && bankSelect.getVisibility() != View.VISIBLE){
                    ToastUtils.showCustomToast("抱歉发布需求前，请先添加至少一种收款方式！",0);
                    return;
                }
                addTradePresenter.vertify(params,getIntent().getStringExtra("action"),transfeNumber);
                addTradePresenter.addTrade(TranscationReleaseBuyOrSellActivity.this,params,getIntent().getStringExtra("action"));
                break;
            default:
                break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_transaction_release_buy);
    }

    public void doReuqestPrice(){
        sysConfigPresenter.getSysConfig(this);
    }

    public void doRequestPayType(){
        payMentTypePresenter.payMentType(TranscationReleaseBuyOrSellActivity.this);
        showLoadingDialog();
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doRequestPayType();
        doReuqestPrice();
    }

    @Override
    protected void onResume() {
        super.onResume();
        doRequestPayType();
        doReuqestPrice();
    }



    @Override
    public void onEdtContentsLegal() {
        catitalPass.setBackground(edt_border);
        timeLimit.setBackground(edt_border);
        transferMin.setBackground(edt_border);
        transferMax.setBackground(edt_border);
        showLoadingDialog();
    }

    @Override
    public void onEdtContentsIllegalSell(Map<String, Boolean> verify) {
        if (!Util.isEmpty(verify.get(keySafePwd)) && !verify.get(keySafePwd)){
            catitalPass.setBackground(edt_border_illegal);
            catitalPass.setText("");
        } else {
            catitalPass.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyTimeLimit)) && !verify.get(keyTimeLimit)){
            timeLimit.setBackground(edt_border_illegal);
            timeLimit.setText("");
        } else {
            timeLimit.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyTransactionMin)) && !verify.get(keyTransactionMin)){
            transferMin.setBackground(edt_border_illegal);
            transferMin.setText("");
        } else {
            transferMin.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyTransactionMax)) && !verify.get(keyTransactionMax)){
            transferMax.setBackground(edt_border_illegal);
            transferMax.setText("");
        } else {
            transferMax.setBackground(edt_border);
        }
    }

    @Override
    public void onEdtContentsIllegalBuy(Map<String, Boolean> verify) {
        if (!Util.isEmpty(verify.get(keySafePwd)) && !verify.get(keySafePwd)){
            catitalPass.setBackground(edt_border_illegal);
            catitalPass.setText("");
        } else {
            catitalPass.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyTransactionMin)) && !verify.get(keyTransactionMin)){
            transferMin.setBackground(edt_border_illegal);
            transferMin.setText("");
        } else {
            transferMin.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyTransactionMax)) && !verify.get(keyTransactionMax)){
            transferMax.setBackground(edt_border_illegal);
            transferMax.setText("");
        } else {
            transferMax.setBackground(edt_border);
        }
    }

    @Override
    public void onAddTradeSuccess() {
        dismissLoadingDialog();
        ToastUtils.showCustomToast("发布成功",1);
        finish();
    }

    @Override
    public void onAddTradeFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg,0);
    }

    @Override
    public void onPayMentTypeSuccess(PayMentResponseBean payMentResponseBean) {
        dismissLoadingDialog();
        PayMentResponseBean.TypeList bankCardLists = payMentResponseBean.getBank();;
        PayMentResponseBean.TypeList aliPayLists = payMentResponseBean.getAlipay();
        PayMentResponseBean.TypeList wxPayLists = payMentResponseBean.getWechat();
        if (bankCardLists.getLink_text().equals("编辑")){
            bankLl.setVisibility(View.VISIBLE);
            if (bankCardLists.getIsopen() == 1){
                bankSelect.setVisibility(View.VISIBLE);
            }else {
                bankSelect.setVisibility(View.GONE);
            }
        }else{
            bankLl.setVisibility(View.GONE);
        }

        if (aliPayLists.getLink_text().equals("编辑")){
            alipayLl.setVisibility(View.VISIBLE);
            if (aliPayLists.getIsopen() == 1){
                alipaySelect.setVisibility(View.VISIBLE);
            }else {
                alipaySelect.setVisibility(View.GONE);
            }
        }else{
            alipayLl.setVisibility(View.GONE);
        }

        if (wxPayLists.getLink_text().equals("编辑")){
            wechatLl.setVisibility(View.VISIBLE);
            if (wxPayLists.getIsopen() == 1){
                wxpaySelect.setVisibility(View.VISIBLE);
            }else {
                wxpaySelect.setVisibility(View.GONE);
            }
        }else{
            wechatLl.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPayMentTypeFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg,0);
    }

    @Override
    public void onRequestSuccess(Object bean) {
        if (bean instanceof SysConfigResponseBean){
            String price = ((SysConfigResponseBean) bean).getPrice();
            transPrice.setText(price + "元/T");
            transferMax.setHint("交易数量 最大交易数量 "+transfeNumber);//最大交易量即个人资产可用数量
            transferMin.setHint("最小交易量");
        }
    }

    @Override
    public void onRequestFailed(String msg) {
        ToastUtils.showCustomToast(msg,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (addTradePresenter!=null){
            addTradePresenter.detachView();
        }
        if (payMentTypePresenter!=null){
            payMentTypePresenter.detachView();
        }
        if (sysConfigPresenter!=null){
            sysConfigPresenter.detachView();
        }
    }
}
