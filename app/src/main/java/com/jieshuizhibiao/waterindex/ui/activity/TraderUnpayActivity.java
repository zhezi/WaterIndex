package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.ListOrder;
import com.jieshuizhibiao.waterindex.beans.unpay.BaseOrderInfo;
import com.jieshuizhibiao.waterindex.beans.unpay.BuyerOrderInfo;
import com.jieshuizhibiao.waterindex.beans.unpay.BuyerUnpayResponse;
import com.jieshuizhibiao.waterindex.beans.unpay.PayInfo;
import com.jieshuizhibiao.waterindex.beans.unpay.SellerOrderInfo;
import com.jieshuizhibiao.waterindex.beans.unpay.SellerUnpayResponse;
import com.jieshuizhibiao.waterindex.contract.model.CancelOrderModel;
import com.jieshuizhibiao.waterindex.contract.model.TraderUnpayModel;
import com.jieshuizhibiao.waterindex.contract.presenter.CancelOrderPresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.TraderUnpayPresenter;
import com.jieshuizhibiao.waterindex.contract.view.CancelOrderViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;
import com.jieshuizhibiao.waterindex.utils.TimeUtils;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;
import com.jieshuizhibiao.waterindex.utils.image.GlidImageManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

public class TraderUnpayActivity extends BaseActivity implements CommonViewImpl,CancelOrderViewImpl {

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.divider_under_card)
    LinearLayout dividerUnderCard;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    @BindView(R.id.divider_under_alipay)
    LinearLayout dividerUnderAlipay;
    @BindView(R.id.ll_wechat)
    LinearLayout llWechat;
    @BindView(R.id.img_right_bank)
    ImageView imgRightBank;
    @BindView(R.id.img_right_alipay)
    ImageView imgRightAlipay;
    @BindView(R.id.img_right_wechat)
    ImageView imgRightWechat;

    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_expire_time)
    TextView tvExpireTime;
    @BindView(R.id.tv_bank_name_detail)
    TextView tvBankNameDetail;
    @BindView(R.id.tv_rmb)
    TextView tvRmb;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_pay_code)
    TextView tvPayCode;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.tv_trader)
    TextView tvTrader;
    @BindView(R.id.img_trader_avatar)
    ImageView imgTraderAvatar;
    @BindView(R.id.tv_trader_nickname)
    TextView tvTraderNickname;
    @BindView(R.id.ll_btns_buyer)
    LinearLayout llBtnsBuyer;
    @BindView(R.id.ll_btns_seller)
    LinearLayout llBtnsSeller;

    private Unbinder unbinder;

    private ListOrder order;
    private String my_action;
    private String next_step;
    private long order_id;
    private TraderUnpayPresenter traderUnpayPresenter;
    private CancelOrderPresenter cancelOrderPresenter;
    private CountDownTimer TimeCount;
    private int pay_type = 0;//1选择卡   2选择支付宝    3选择微信
    private AlertChainDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        getIntentExtra();
        initView();
        traderUnpayPresenter = new TraderUnpayPresenter(new TraderUnpayModel());
        traderUnpayPresenter.attachView(this);
        doRequest(next_step);
        cancelOrderPresenter=new CancelOrderPresenter(new CancelOrderModel());
        cancelOrderPresenter.attachView(this);
    }

    private void getIntentExtra() {
        order = getIntent().getParcelableExtra("ListOrder");
        if (order != null) {
            my_action = order.getMy_action();
            next_step = order.getNext_step();
            order_id = order.getOrder_id();
        }
    }

    private void initView() {
        if (order != null) {
            final String my_action = order.getMy_action();
            final String next_step = order.getNext_step();
            if (my_action.equals("购买")) {
                tvTitleCenter.setText("购买节水指标");
                tvTradeType.setText("购买");
                tvTrader.setText("卖家");
                llBtnsBuyer.setVisibility(View.VISIBLE);
                llBtnsSeller.setVisibility(View.GONE);
            } else if (my_action.equals("出售")) {
                tvTitleCenter.setText("出售节水指标");
                tvTradeType.setText("出售");
                tvTrader.setText("买家");
                llBtnsBuyer.setVisibility(View.GONE);
                llBtnsSeller.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_trader_unpay);

    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onRequestSuccess(Object bean) {
        if (bean != null) {
            List<PayInfo> pay_info_list = null;
            BaseOrderInfo baseOrderInfo = null;
            BuyerOrderInfo buyerOrderInfo;
            SellerOrderInfo sellerOrderInfo;
            String avatarUrl = null;
            String nickname = null;
            if ("buyerUnpay".equals(next_step)) {
                BuyerUnpayResponse buyerUnpayResponse = (BuyerUnpayResponse) bean;
                pay_info_list = buyerUnpayResponse.getPay_info_list();
                buyerOrderInfo = buyerUnpayResponse.getOrder_info();
                avatarUrl = buyerOrderInfo.getSeller_avatar();
                nickname = buyerOrderInfo.getSeller_nickname();
                baseOrderInfo = buyerOrderInfo;
            } else if ("sellerUnpay".equals(next_step)) {
                SellerUnpayResponse sellerUnpayResponse = (SellerUnpayResponse) bean;
                pay_info_list = sellerUnpayResponse.getPay_info_list();
                sellerOrderInfo = sellerUnpayResponse.getOrder_info();
                avatarUrl = sellerOrderInfo.getBuyer_avatar();
                nickname = sellerOrderInfo.getBuyer_nickname();
                baseOrderInfo = sellerOrderInfo;
            }
            setViews(baseOrderInfo, pay_info_list, avatarUrl, nickname);
        }
        dismissLoadingDialog();
    }

    @Override
    public void onRequestFailed(String msg) {
        ToastUtils.showCustomToast(msg);
        dismissDialog();
    }

    @Override
    public void onCancelSucc(Object bean) {
        dismissLoadingDialog();
        showDialog("提示","取消成功！","知道了");
        // todo       EventBus.getDefault().post();
    }

    @Override
    public void onCancelFail(String msg) {
        ToastUtils.showCustomToast(msg);
        dismissDialog();
    }

    private void setViews(BaseOrderInfo baseOrderInfo, List<PayInfo> list, String avatarUrl, String nickname) {
        if (baseOrderInfo != null && list != null) {
            String expire_time = baseOrderInfo.getExpire_time();
            if (expire_time.equals("不限制")) {
                tvExpireTime.setText("不限制");
                tvLeft.setVisibility(View.GONE);
                tvRight.setVisibility(View.GONE);
            } else if (isNumeric(expire_time)) {
                long expiretime = Long.valueOf(expire_time);
                countDownPayTime(expiretime);
            } else {
                tvExpireTime.setText("请尽快付款");
                tvLeft.setVisibility(View.GONE);
                tvRight.setVisibility(View.GONE);
            }
            tvRmb.setText(baseOrderInfo.getRmb());
            tvTotal.setText(baseOrderInfo.getTotal());
            tvPrice.setText(baseOrderInfo.getPrice());
            tvOrderSn.setText(baseOrderInfo.getOrder_sn());
            tvPayCode.setText(baseOrderInfo.getPay_code());
            tvCreatetime.setText(baseOrderInfo.getCreatetime());
            GlidImageManager.getInstance().loadCircleImg(this, avatarUrl, imgTraderAvatar, R.mipmap.head, R.mipmap.head);
            tvTraderNickname.setText(nickname);

            for (PayInfo payInfo : list) {
                int type = payInfo.getType();
                if (type == 1) {//card
                    llCard.setVisibility(View.VISIBLE);
                    dividerUnderCard.setVisibility(View.VISIBLE);
                    tvBankNameDetail.setText(new StringBuilder().append(payInfo.getBank_name()).append("   ").append(payInfo.getBank_detail_name()).toString());
                } else if (type == 2) {//alipay
                    llAlipay.setVisibility(View.VISIBLE);
                    dividerUnderAlipay.setVisibility(View.VISIBLE);
                } else if (type == 3) {//wechat
                    llWechat.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    private void countDownPayTime(long expire_time) {
        TimeCount = new CountDownTimer(expire_time * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (!TraderUnpayActivity.this.isFinishing()) {
                    String countDown = TimeUtils.getToMSTime((int) (millisUntilFinished / 1000));
                    tvExpireTime.setText(countDown);
                }
            }

            @Override
            public void onFinish() {
                if (!TraderUnpayActivity.this.isFinishing()) {
                    TimeCount.onFinish();
                }
            }

        };
        TimeCount.start();
    }



    @OnClick({R.id.left_ll, R.id.ll_card, R.id.ll_alipay, R.id.ll_wechat,
            R.id.btn_buyer_cancle, R.id.btn_buyer_pay, R.id.btn_seller_cancle, R.id.btn_seller_pay})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                finish();
                break;
            case R.id.ll_card:
                showOrGoneRightImg(imgRightBank);
                if (imgRightBank.getVisibility() == View.VISIBLE) {
                    imgRightWechat.setVisibility(View.GONE);
                    imgRightAlipay.setVisibility(View.GONE);
                    pay_type = 1;
                }
                break;
            case R.id.ll_alipay:
                showOrGoneRightImg(imgRightAlipay);
                if (imgRightAlipay.getVisibility() == View.VISIBLE) {
                    imgRightWechat.setVisibility(View.GONE);
                    imgRightBank.setVisibility(View.GONE);
                    pay_type = 2;
                }
                break;
            case R.id.ll_wechat:
                showOrGoneRightImg(imgRightWechat);
                if (imgRightWechat.getVisibility() == View.VISIBLE) {
                    imgRightBank.setVisibility(View.GONE);
                    imgRightAlipay.setVisibility(View.GONE);
                    pay_type = 3;
                }
                break;
            case R.id.btn_buyer_cancle:
                //todo 卖家取消订单
                cancelOrder(next_step,order_id);
                break;

            case R.id.btn_buyer_pay:
                //todo 进入银行或微信/支付宝页面进行支付
                break;

            case R.id.btn_seller_cancle:
                //todo 卖家取消订单  无对应接口
                break;
            case R.id.btn_seller_pay:
                //todo 查看卖家是否已经付款
                break;
        }
    }

    private void doRequest(String next_step) {
        showLoadingDialog();
        if (traderUnpayPresenter == null) {
            traderUnpayPresenter = new TraderUnpayPresenter(new TraderUnpayModel());
        }
        if ("buyerUnpay".equals(next_step)) {
            traderUnpayPresenter.buyerUnpay(this, order_id);
        } else if ("sellerUnpay".equals(next_step)) {
            traderUnpayPresenter.sellerUnpay(this, order_id);
        }
    }

    private void cancelOrder(String next_step,long order_id){
        showLoadingDialog();
        if (cancelOrderPresenter == null) {
            cancelOrderPresenter = new CancelOrderPresenter(new CancelOrderModel());
        }
        if ("buyerUnpay".equals(next_step)) {
            cancelOrderPresenter.buyerCancel(this, order_id);
        } else if ("sellerUnpay".equals(next_step)) {
//            traderUnpayPresenter.sellerUnpay(this, order_id);
        }
    }


    private void showOrGoneRightImg(ImageView img) {
        int visibility = img.getVisibility();
        if (visibility == View.VISIBLE) {
            img.setVisibility(View.GONE);
        } else if (visibility == View.GONE || visibility == View.INVISIBLE) {
            img.setVisibility(View.VISIBLE);
        }
    }

    private void showDialog(final String title,String msg,final String positive){
        if (dialog == null) {
            dialog = new AlertChainDialog(this);
        }
        dialog.builder().setCancelable(true);
        dialog.setTitle(title)
                .setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (positive.equals("知道了") || title.equals("提示")) {
                            dialog.dismiss();
                        } /*else if ()) {

                        } else if () {

                        }*/
                    }
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        if (traderUnpayPresenter != null) {
            traderUnpayPresenter.detachView();
        }
        super.onDestroy();
    }


}
