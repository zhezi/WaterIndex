package com.quanminjieshui.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.AccountDetailResponseBean;
import com.quanminjieshui.waterindex.contract.model.AccountDetailModel;
import com.quanminjieshui.waterindex.contract.presenter.AccountDetailPresenter;
import com.quanminjieshui.waterindex.contract.view.AccountDetailViewImpl;
import com.quanminjieshui.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class UserAssetActivity extends BaseActivity implements AccountDetailViewImpl {

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
    private AccountDetailPresenter accountDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountDetailPresenter = new AccountDetailPresenter(new AccountDetailModel());
        accountDetailPresenter.attachView(this);
        accountDetailPresenter.getAccountDetail(this);

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
        if (accountDetailPresenter != null) {
            accountDetailPresenter.getAccountDetail(this);
        }
    }

    @OnClick({R.id.left_ll, R.id.img_title_left, R.id.btn_jsl_transfer, R.id.btn_c2c_transfer})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.img_title_left:
                goBack(view);

                break;
            case R.id.left_ll:
                goBack(view);
                break;

            case R.id.btn_c2c_transfer:
                startActivity(new Intent(UserAssetActivity.this,TransferActivity.class));
                break;
            case R.id.btn_jsl_transfer:
                startActivity(new Intent(UserAssetActivity.this,TransferActivity.class));
                break;
            default:
                break;
        }

    }


    @Override
    public void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean) {
        tvJsl.setText(new StringBuilder("可用：").append(accountDetailResponseBean.getJsl()).toString());
        tvJslFreeze.setText(new StringBuilder("冻结：").append(accountDetailResponseBean.getJsl_freeze()).toString());
        tvJslGyj.setText(new StringBuilder("公益金：").append(accountDetailResponseBean.getJsl_gyj()).toString());

        tvDs.setText(new StringBuilder("可用：").append(accountDetailResponseBean.getDs()).append(" T").toString());
        tvDsFreeze.setText(new StringBuilder("冻结：").append(accountDetailResponseBean.getDs_freeze()).append(" T").toString());
        tvDsTransfer.setText(new StringBuilder("公益金：").append(accountDetailResponseBean.getJsl_gyj()).toString());//TODO
    }

    @Override
    public void onAccountDetailFailed(String msg) {

    }
}
