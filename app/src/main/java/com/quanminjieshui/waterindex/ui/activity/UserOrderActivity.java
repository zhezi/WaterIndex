package com.quanminjieshui.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.TradeIndex;
import com.quanminjieshui.waterindex.utils.StatusBarUtil;
import com.quanminjieshui.waterindex.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserOrderActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_pay_min)
    TextView tvPayMin;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.img_pay_type_bank_card)
    ImageView imgPayTypeBankCard;
    @BindView(R.id.img_pay_type_alipay)
    ImageView imgPayTypeAlipay;
    @BindView(R.id.img_pay_type_wechat)
    ImageView imgPayTypeWechat;
    @BindView(R.id.edt_total)
    EditText edtTotal;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    @BindView(R.id.edt_safe_pw)
    EditText edtSafePw;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.tv_pay_timeout)
    TextView tvPayTimeout;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.trans_container)
    FrameLayout transContainer;

    Unbinder unbinder;
    private TradeIndex tradeIndex;
    private String type;
    private String trade_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        getIntentExtra();
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setImmersionStatus(this, container);
        initView();
        if (tradeIndex != null) {
            trade_id = String.valueOf(tradeIndex.getTrade_id());
        }
    }

    private void setFullScreen() {
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;//设置对话框置顶显示
        win.setAttributes(lp);
    }


    private void initView() {
        if (!TextUtils.isEmpty(type) && tradeIndex != null) {
            if (type.equals("2")) {
                tvTitle.setText("购买节水指标");
                tvPayMin.setText(new StringBuilder("最小购买量：").append(tradeIndex.getPay_min()).toString());
                edtSafePw.setVisibility(View.GONE);
                edtSafePw.setText("");
                tvPayTimeout.setVisibility(View.GONE);

            } else if (type.equals("1")) {
                tvTitle.setText("出售节水指标");
                tvPayMin.setText(new StringBuilder("最小购买量：").append(tradeIndex.getPay_min()).toString());
                edtSafePw.setVisibility(View.VISIBLE);
                tvPayTimeout.setText(tradeIndex.getPay_timeout());
            }
            tvTotal.setText(new StringBuilder("数量：").append(tradeIndex.getTotal()).toString());
            edtTotal.setHint(new StringBuilder("最大可买").append(tradeIndex.getTotal()).toString());
            GlidImageManager.getInstance().loadCircleImg(this, tradeIndex.getAvatar(), imgAvatar, R.mipmap.head, R.mipmap.head);
            tvUserNickname.setText(tradeIndex.getUser_nickname());
            if (tradeIndex.getPay_type_bank_card().equals("1")) {
                imgPayTypeBankCard.setVisibility(View.VISIBLE);
            } else if (tradeIndex.getPay_type_bank_card().equals("0")) {
                imgPayTypeBankCard.setVisibility(View.GONE);
            }
            if (tradeIndex.getPay_type_alipay().equals("1")) {
                imgPayTypeAlipay.setVisibility(View.VISIBLE);
            } else if (tradeIndex.getPay_type_bank_card().equals("0")) {
                imgPayTypeAlipay.setVisibility(View.GONE);
            }
            if (tradeIndex.getPay_type_wechat().equals("1")) {
                imgPayTypeWechat.setVisibility(View.VISIBLE);
            } else if (tradeIndex.getPay_type_bank_card().equals("0")) {
                imgPayTypeWechat.setVisibility(View.GONE);
            }
            //todo tvTotalPrice接口返回并无该字段
            //tvTotalPrice.setText(new StringBuilder("总价：").append("").toString());

        }
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_order);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.img_cancle})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_cancle:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        tradeIndex = intent.getParcelableExtra("trade_index");
        type = intent.getStringExtra("type");
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
