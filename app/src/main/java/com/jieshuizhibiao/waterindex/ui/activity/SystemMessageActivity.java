package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.contract.presenter.SystemMsgPresenter;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class SystemMessageActivity extends BaseActivity implements CommonViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.rc_message_list)
    XRecyclerView messageList;

    private SystemMsgPresenter systemMsgPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        systemMsgPresenter = new SystemMsgPresenter();
        systemMsgPresenter.attachView(this);

        doReuqestMessage();
        initView();
    }

    private void initView() {
        tv_title_center.setText("系统消息");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_message);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doReuqestMessage();
    }

    public void doReuqestMessage(){
        systemMsgPresenter.getSystemMsg(SystemMessageActivity.this);
        showLoadingDialog();
    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;

            default:
                break;
        }

    }


    @Override
    public void onRequestSuccess(Object bean) {
        dismissLoadingDialog();
    }

    @Override
    public void onRequestFailed(String msg) {
        dismissLoadingDialog();

    }
}
