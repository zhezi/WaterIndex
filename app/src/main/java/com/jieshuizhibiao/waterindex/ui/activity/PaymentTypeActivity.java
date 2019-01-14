package com.jieshuizhibiao.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.utils.SPUtil;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.Util;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:收款方式
 */

public class PaymentTypeActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

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

    }

    @OnClick({R.id.left_ll,R.id.zfb_ll,R.id.wx_ll,R.id.bank_ll,R.id.payment_switch_zfb_img,
            R.id.payment_switch_wx_img,R.id.payment_switch_bank_img})
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            case R.id.zfb_ll:
                if (!Util.isFastDoubleClick()) {
                    jumpActivity(AddZFBPaymetActivity.class);
                }
                break;
            case R.id.wx_ll:
                if (!Util.isFastDoubleClick()) {
                    jumpActivity(AddWXPaymetActivity.class);
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
                break;
            case R.id.payment_switch_wx_img:
                if (!Util.isFastDoubleClick() && (Boolean) SPUtil.get(this,"isOpenWX",false)) {
                    wxSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
                    SPUtil.insert(this,"isOpenWX",false);
                }else {
                    wxSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
                    SPUtil.insert(this,"isOpenWX",true);
                }
                break;
            case R.id.payment_switch_bank_img:
                if (!Util.isFastDoubleClick() && (Boolean) SPUtil.get(this,"isOpenBANK",false)) {
                    bankSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_off));
                    SPUtil.insert(this,"isOpenBANK",false);
                }else {
                    bankSwitchImg.setBackground(getResources().getDrawable(R.drawable.switch_on));
                    SPUtil.insert(this,"isOpenBANK",true);
                }
                break;
            default:break;
        }
    }

    public void jumpActivity(Class<?> cla){
        startActivity(new Intent(PaymentTypeActivity.this,cla));
    }

}
