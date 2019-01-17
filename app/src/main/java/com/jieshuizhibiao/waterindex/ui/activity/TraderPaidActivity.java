package com.jieshuizhibiao.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.ListOrder;
import com.jieshuizhibiao.waterindex.beans.buyerpaid.BuyerPaidOrderInfo;
import com.jieshuizhibiao.waterindex.beans.buyerpaid.BuyerPaidResponse;
import com.jieshuizhibiao.waterindex.beans.sellerpaid.SellerPaidOrderInfo;
import com.jieshuizhibiao.waterindex.beans.sellerpaid.SellerPaidResponse;
import com.jieshuizhibiao.waterindex.beans.unpay.PayInfo;
import com.jieshuizhibiao.waterindex.contract.model.TraderModel;
import com.jieshuizhibiao.waterindex.contract.presenter.TraderPresenter;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.ui.fragment.OrderListsTabFragment;
import com.jieshuizhibiao.waterindex.utils.LogUtils;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.OnClick;

public class TraderPaidActivity extends BaseActivity implements CommonViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.left_ll)
    LinearLayout leftLl;

    @BindView(R.id.tv_big_rmb)
    TextView tvBigRmb;
    @BindView(R.id.tv_buyer_paid)
    TextView tvBuyerPaid;
    @BindView(R.id.ll_time_clock)
    LinearLayout llTimeClock;
    @BindView(R.id.ll_header_rmb)
    LinearLayout llHeaderRmb;

    @BindView(R.id.ll_trade_info)
    LinearLayout llTradeInfo;
    @BindView(R.id.tv_rmb)
    TextView tvRmb;
    @BindView(R.id.ll_rmb)
    LinearLayout llRmb;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.ll_layout_trade_infp)
    LinearLayout llLayoutTradeInfp;
    @BindView(R.id.ll_trade_info_container)
    LinearLayout llTradeInfoContainer;

    @BindView(R.id.ll_pay_info)
    LinearLayout llPayInfo;
    @BindView(R.id.img_pay_type_bank_card)
    ImageView imgPayTypeBankCard;
    @BindView(R.id.img_pay_type_alipay)
    ImageView imgPayTypeAlipay;
    @BindView(R.id.img_pay_type_wechat)
    ImageView imgPayTypeWechat;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.ll_pay_type)
    LinearLayout llPayType;
    @BindView(R.id.tv_user_name_txt)
    TextView tvUserNameTxt;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.ll_user_name)
    LinearLayout llUserName;
    @BindView(R.id.tv_account_name_txt)
    TextView tvAccountNameTxt;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.ll_account_name)
    LinearLayout llAccountName;
    @BindView(R.id.ll_qrcode)
    LinearLayout llQrcode;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_detail_name)
    TextView tvBankDetailName;
    @BindView(R.id.ll_bank_info)
    LinearLayout llBankInfo;
    @BindView(R.id.tv_pay_code)
    TextView tvPayCode;
    @BindView(R.id.ll_pay_code)
    LinearLayout llPayCode;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.ll_createtime)
    LinearLayout llCreatetime;
    @BindView(R.id.tv_paytime)
    TextView tvPaytime;
    @BindView(R.id.ll_paytime)
    LinearLayout llPaytime;
    @BindView(R.id.tv_trader)
    TextView tvTrader;
    @BindView(R.id.img_trader_avatar)
    ImageView imgTraderAvatar;
    @BindView(R.id.tv_trader_nickname)
    TextView tvTraderNickname;
    @BindView(R.id.ll_trader)
    LinearLayout llTrader;
    @BindView(R.id.img_pay_snapshot)
    ImageView imgPaySnapshot;
    @BindView(R.id.ll_pay_snapshot)
    LinearLayout llPaySnapshot;
    @BindView(R.id.ll_seller_part)
    LinearLayout llSellerPart;
    @BindView(R.id.btn_buyer_appeal)
    Button btnBuyerAppeal;
    @BindView(R.id.btn_seller_appeal)
    Button btnSellerAppeal;
    @BindView(R.id.btn_seller_checkout)
    Button btnSellerCheckout;
    @BindView(R.id.ll_btns_seller)
    LinearLayout llBtnsSeller;

    private String current_step;
    private long order_id;
    private PayInfo payInfo;
    private String qrcodeUrl;//该页面要做放大吗？？
    private String pay_code;
    private String createtime;


    private TraderPresenter traderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        getIntentExtra();
        initView();
        traderPresenter = new TraderPresenter(new TraderModel());
        traderPresenter.attachView(this);
        doRequest();
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_trader_paid);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    private void initView() {
        llTimeClock.setVisibility(View.GONE);
        if (current_step.equals(OrderListsTabFragment.BUYER_PAID)) {
            tvTitleCenter.setText("等待买家放行");
            llHeaderRmb.setVisibility(View.GONE);
            tvBuyerPaid.setVisibility(View.GONE);
            llTradeInfoContainer.setVisibility(View.VISIBLE);

            llPaytime.setVisibility(View.VISIBLE);
            llSellerPart.setVisibility(View.GONE);

            btnBuyerAppeal.setVisibility(View.VISIBLE);
            llBtnsSeller.setVisibility(View.GONE);

        } else if (current_step.equals(OrderListsTabFragment.SELLER_PAID)) {
            tvTitleCenter.setText("收款");
            llHeaderRmb.setVisibility(View.VISIBLE);
            tvBuyerPaid.setVisibility(View.VISIBLE);
            llTradeInfoContainer.setVisibility(View.GONE);

            llPaytime.setVisibility(View.GONE);
            llSellerPart.setVisibility(View.VISIBLE);

            btnBuyerAppeal.setVisibility(View.GONE);
            llBtnsSeller.setVisibility(View.VISIBLE);
        }
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        ListOrder order = intent.getParcelableExtra(OrderListsTabFragment.LISTORDER);
        current_step = order.getNext_step();
        order_id = order.getOrder_id();
    }


    @OnClick({R.id.left_ll,})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                finish();
                break;
//            case R.id.left_ll:
//
//                break;
//            case R.id.left_ll:
//
//                break;
//            case R.id.left_ll:
//
//                break;
//            case R.id.left_ll:
//
//                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * perform after getIntentExtra().
     */
    private void doRequest() {
        if (traderPresenter == null) {
            traderPresenter = new TraderPresenter(new TraderModel());
            traderPresenter.attachView(this);
        }
        if (current_step.equals(OrderListsTabFragment.BUYER_PAID)) {
            traderPresenter.buyerPaid(this, order_id);
        } else if (current_step.equals(OrderListsTabFragment.SELLER_PAID)) {
            traderPresenter.sellerPaid(this, order_id);
        }
    }


    @Override
    public void onRequestSuccess(Object bean) {
        if (bean != null) {
            BuyerPaidOrderInfo buyerPaidOrderInfo;
            SellerPaidOrderInfo sellerPaidOrderInfo;

            if (current_step.equals(OrderListsTabFragment.BUYER_PAID)) {
                BuyerPaidResponse buyerPaidResponse = (BuyerPaidResponse) bean;
                buyerPaidOrderInfo = buyerPaidResponse.getOrderinfo();
                if (buyerPaidResponse != null) {
                    payInfo = buyerPaidResponse.getPay_info();
                }
                if (buyerPaidOrderInfo != null) {
                    pay_code = buyerPaidOrderInfo.getPay_code();
                    createtime = buyerPaidOrderInfo.getCreatetime();
                    //买家内容

                    tvRmb.setText(buyerPaidOrderInfo.getRmb());
                    tvTotal.setText(buyerPaidOrderInfo.getTotal());
                    tvPrice.setText(buyerPaidOrderInfo.getPrice());
                    tvPaytime.setText(buyerPaidOrderInfo.getPaytime());
                }
            } else if (current_step.equals(OrderListsTabFragment.SELLER_PAID)) {
                SellerPaidResponse sellerPaidResponse = (SellerPaidResponse) bean;
                sellerPaidOrderInfo = sellerPaidResponse.getOrder_info();
                if (sellerPaidResponse != null) {
                    payInfo = sellerPaidResponse.getPay_info();
                }

                if (sellerPaidOrderInfo != null) {
                    pay_code = sellerPaidOrderInfo.getPay_code();
                    createtime = sellerPaidOrderInfo.getCreatetime();
                    //卖家内容
                    tvBigRmb.setText(sellerPaidOrderInfo.getRmb());
                    tvTrader.setText("买家");
                    GlidImageManager.getInstance().loadCircleImg(this,sellerPaidOrderInfo.getBuyer_avatar(),imgTraderAvatar,R.mipmap.head,R.mipmap.head);
                    tvTraderNickname.setText(sellerPaidOrderInfo.getBuyer_nickname());
                }
            }
            if(payInfo!=null) {
                setCommonView(payInfo);
            }
        }
    }

    private void setCommonView(PayInfo payInfo) {
        final int type = payInfo.getType();
        if (type == 1) {
            imgPayTypeBankCard.setVisibility(View.VISIBLE);
            imgPayTypeAlipay.setVisibility(View.GONE);
            imgPayTypeWechat.setVisibility(View.GONE);
            tvPayType.setText("银行卡");
            tvAccountNameTxt.setText("银行卡号");
            llBankInfo.setVisibility(View.VISIBLE);
            llQrcode.setVisibility(View.GONE);
            tvBankName.setText(payInfo.getBank_name());
            tvBankDetailName.setText(payInfo.getBank_detail_name());
        } else if (type == 2 || type == 3) {
            if (type == 2) {
                imgPayTypeBankCard.setVisibility(View.GONE);
                imgPayTypeAlipay.setVisibility(View.VISIBLE);
                imgPayTypeWechat.setVisibility(View.GONE);
                tvPayType.setText("支付宝");
            } else if (type == 3) {
                imgPayTypeBankCard.setVisibility(View.GONE);
                imgPayTypeAlipay.setVisibility(View.VISIBLE);
                imgPayTypeWechat.setVisibility(View.GONE);
                tvPayType.setText("支付宝");
            }
            tvAccountNameTxt.setText("收款账号");
            llBankInfo.setVisibility(View.GONE);
            llQrcode.setVisibility(View.VISIBLE);
            qrcodeUrl = payInfo.getQrcode();
        }
        tvPayCode.setText(pay_code);
        tvCreatetime.setText(createtime);


    }


    @Override
    public void onRequestFailed(String msg) {

    }

    @Override
    protected void onDestroy() {
        if (traderPresenter != null) {
            traderPresenter.detachView();
        }
        super.onDestroy();
    }
}
