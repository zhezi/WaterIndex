package com.jieshuizhibiao.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.Money;
import com.jieshuizhibiao.waterindex.beans.UserMoney;
import com.jieshuizhibiao.waterindex.contract.presenter.UserMoneryPresenter;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.utils.SPUtil;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class UserAssetActivity extends BaseActivity implements CommonViewImpl {

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.tv_jsl)
    TextView tvJsl;
    @BindView(R.id.tv_jsl_freeze)
    TextView tvJslFreeze;
    @BindView(R.id.tv_jsl_gyj)
    TextView tvJslGyj;
    @BindView(R.id.tv_ds)
    TextView tvDs;
    @BindView(R.id.tv_ds_freeze)
    TextView tvDsFreeze;
    @BindView(R.id.tv_ds_transfer)
    TextView tvDsTransfer;
    @BindView(R.id.total_all_monery_bb)
    TextView tvDsTransferTotalBb;
    @BindView(R.id.total_all_monery_c2c)
    TextView tvDsTransferTotalC2c;

    private UserMoneryPresenter userMoneryPresenter;
    private Money moneryC2c = new Money();
    private Money moneryBb = new Money();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userMoneryPresenter = new UserMoneryPresenter();
        userMoneryPresenter.attachView(this);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("我的资产");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_asset);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        doRequest();
    }

    public void doRequest(){
        if (userMoneryPresenter != null) {
            userMoneryPresenter.userMonery(this);
            showLoadingDialog();
        }
    }
    @OnClick({R.id.left_ll, R.id.img_title_left, R.id.btn_jsl_transfer, R.id.btn_c2c_transfer})
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.img_title_left:
                goBack(view);

                break;
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_c2c_transfer:
                bundle.putString("ds_c2c",moneryC2c.getDs());
                bundle.putString("ds_Bb",moneryBb.getDs());
                bundle.putString("action","c2c");
                jumpActivity(bundle,intent);
                break;
            case R.id.btn_jsl_transfer:
                bundle.putString("ds_Bb",moneryBb.getDs());
                bundle.putString("ds_c2c",moneryC2c.getDs());
                bundle.putString("action","Bb");
                jumpActivity(bundle,intent);
                break;
            default:
                break;
        }

    }

    public void jumpActivity(Bundle bundle,Intent intent){
        intent.putExtras(bundle);
        intent.setClass(UserAssetActivity.this,TransferActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestSuccess(Object bean) {
        dismissLoadingDialog();
        if(bean instanceof UserMoney){
            moneryBb = ((UserMoney) bean).getBb();
            moneryC2c = ((UserMoney) bean).getC2c();
        }
        DecimalFormat df = new DecimalFormat("###.00");
        Double rmbc2c = Double.valueOf(moneryC2c.getRmb().substring(0,moneryC2c.getRmb().length()-1));
        Double totalc2c = Double.valueOf(moneryC2c.getTotal().substring(0,moneryC2c.getTotal().length()-1));
        Double dsc2c = Double.valueOf(moneryC2c.getDs().substring(0,moneryC2c.getDs().length()-1));
        Double dsFreezec2c = Double.valueOf(moneryC2c.getDs_freeze().substring(0,moneryC2c.getDs_freeze().length()-1));

        tvJsl.setText(new StringBuilder("可用：").append(((dsc2c <= 0.00) ? "0.00":df.format(dsc2c))).toString()+" T");
        tvJslFreeze.setText(new StringBuilder("冻结：").append(((dsFreezec2c <= 0.00) ? "0.00":df.format(dsFreezec2c))).toString()+" T");
        tvJslGyj.setText(new StringBuilder("公益金：").append(SPUtil.get(this,SPUtil.C2C_TRAMSFER,"0.00")).append(" T").toString());
        tvDsTransferTotalC2c.setText("总资产折合："+ ((rmbc2c <= 0.00) ? "0.00":df.format(rmbc2c)) +" 元" +" ≈ "+((totalc2c <= 0.00) ? "0.00":df.format(totalc2c))+" T");

        Double rmbcBb = Double.valueOf(moneryBb.getRmb().substring(0,moneryBb.getRmb().length()-1));
        Double totalBb = Double.valueOf(moneryBb.getTotal().substring(0,moneryBb.getTotal().length()-1));
        Double dscBb = Double.valueOf(moneryBb.getDs().substring(0,moneryBb.getDs().length()-1));
        Double dsFreezeBb = Double.valueOf(moneryBb.getDs_freeze().substring(0,moneryBb.getDs_freeze().length()-1));

        tvDs.setText(new StringBuilder("可用：").append(((dscBb <= 0.00) ? "0.00":df.format(dscBb))).append(" T").toString());
        tvDsFreeze.setText(new StringBuilder("冻结：").append(((dsFreezeBb <= 0.00) ? "0.00":df.format(dsFreezeBb))).append(" T").toString());
        tvDsTransfer.setText(new StringBuilder("公益金：").append(SPUtil.get(this,SPUtil.BB_TRAMSFER,"0.00")).append(" T").toString());
        tvDsTransferTotalBb.setText("总资产折合："+((rmbcBb <= 0.00) ? "0.00" :df.format(rmbcBb)) +" 元" +" ≈ "+((totalBb <= 0.00) ? "0.00":df.format(totalBb))+" T");
    }

    @Override
    public void onRequestFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }
}
