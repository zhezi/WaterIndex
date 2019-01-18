package com.jieshuizhibiao.waterindex.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.beans.UserIndexResponseBean;
import com.jieshuizhibiao.waterindex.contract.presenter.UserIndexPresenter;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.event.LoginStatusChangedEvent;
import com.jieshuizhibiao.waterindex.ui.activity.AboutListActivity;
import com.jieshuizhibiao.waterindex.ui.activity.LoginActivity;
import com.jieshuizhibiao.waterindex.ui.activity.PaymentTypeActivity;
import com.jieshuizhibiao.waterindex.ui.activity.SafeCenterActivity;
import com.jieshuizhibiao.waterindex.ui.activity.SystemMessageActivity;
import com.jieshuizhibiao.waterindex.ui.activity.TransactionDemandActivity;
import com.jieshuizhibiao.waterindex.ui.activity.UserAssetActivity;
import com.jieshuizhibiao.waterindex.ui.activity.UserConfirmActivity;
import com.jieshuizhibiao.waterindex.ui.activity.UserDetailActivity;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;
import com.jieshuizhibiao.waterindex.utils.SPUtil;
import com.jieshuizhibiao.waterindex.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:我的个人中心
 */

public class PersonalFragment extends BaseFragment implements CommonViewImpl {
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private UserIndexPresenter userIndexPresenter;
    /**
     * 是否登录
     * 记录当前是否登录，fargment切换后有登录、退出登录操作后，下次再显示时用该变量与本地SP所存结果比对，不一致时做刷新操作
     */
    private boolean isLogin=false;
    private String user_login;//用户登录手机号，作用同isLogin

    @OnClick({R.id.relative_user_detail, R.id.relative_account_detail, R.id.relative_auth_detail,
            R.id.relative_payment_type, R.id.relative_transaction_demand,
            R.id.relative_sys_msg, R.id.relative_safe_center, R.id.relative_about_us,R.id.tv_order_lists})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.relative_user_detail:
                if (checkLoginStatus()) {
                    jump(UserDetailActivity.class);
                }
                break;
            case R.id.relative_account_detail:
                if (checkLoginStatus()) {
                    jump(UserAssetActivity.class);
                }
                break;
            case R.id.relative_auth_detail:
                if (checkLoginStatus()) {
                    jump(UserConfirmActivity.class);
                }
                break;
            case R.id.relative_payment_type:
                if (checkLoginStatus()) {
                    jump(PaymentTypeActivity.class);
                }
                break;
            case R.id.relative_transaction_demand:
                if (checkLoginStatus()) {
                    jump(TransactionDemandActivity.class);
                }
                break;
            case R.id.relative_sys_msg:
                if (checkLoginStatus()) {
                    jump(SystemMessageActivity.class);
                }
                break;
            case R.id.relative_safe_center:
                if (checkLoginStatus()) {
                    jump(SafeCenterActivity.class);
                }
                break;
            case R.id.relative_about_us:
                    jump(AboutListActivity.class);
                break;
            default:
                break;
        }

    }

    private boolean checkLoginStatus() {
       boolean isLogin_ = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
       if (!isLogin_) {
            if(alertChainDialog!=null){
                alertChainDialog.builder().setCancelable(false);
                alertChainDialog.setTitle("提示消息")
                        .setMsg("您当前未登录，请登录")
                        .setPositiveButton("登录", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                toLogin();
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        }
        return isLogin_;
    }

    private void toLogin() {
        Intent intent=new Intent();
        intent.putExtra("target","main_personal");
        jump(LoginActivity.class,intent);
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(getBaseActivity(), cls));
    }

    private void jump(Class<?>cls,Intent intent){
        intent.setClass(getActivity(),cls);
        startActivity(intent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        userIndexPresenter = new UserIndexPresenter();
        userIndexPresenter.attachView(this);
        doRequest();

        initView();
        isLogin= (boolean) SPUtil.get(getActivity(),SPUtil.IS_LOGIN,false);
        user_login=(String)SPUtil.get(getActivity(),SPUtil.USER_LOGIN,"user_login");
        return rootView;
    }

    private void initView() {
        tvNickname.setText(TextUtils.isEmpty(user_login) ? "******" :Util.hide4Phone(user_login));
        alertChainDialog = new AlertChainDialog(getBaseActivity());

    }

    public void doRequest(){
        userIndexPresenter.userIndex(getBaseActivity());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            doRequest();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            doRequest();
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doRequest();
    }

    public void onEventMainThread(LoginStatusChangedEvent event){
        if(event!=null&&event.getMsg().equals("login_status_changed_main_personal_refresh_nickname")){
            isLogin= (boolean) SPUtil.get(getActivity(),SPUtil.IS_LOGIN,false);
            user_login=(String)SPUtil.get(getActivity(),SPUtil.USER_LOGIN,"user_login");
            tvNickname.setText((String)SPUtil.get(getActivity(), SPUtil.USER_NICKNAME, "********"));
        }
    }


    public String getIsLogin(){
        return new StringBuilder().append(isLogin).append(user_login).toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRequestSuccess(Object bean) {
        if (bean instanceof UserIndexResponseBean){

        }
    }

    @Override
    public void onRequestFailed(String msg) {

    }
}
