package com.quanminjieshui.waterindex.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.ActivityManager;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterindex.event.SelectFragmentEvent;
import com.quanminjieshui.waterindex.ui.fragment.FindFragment;
import com.quanminjieshui.waterindex.ui.fragment.PersonalFragment;
import com.quanminjieshui.waterindex.ui.fragment.TransactionFragment;
import com.quanminjieshui.waterindex.utils.SPUtil;
import com.quanminjieshui.waterindex.utils.StatusBarUtil;
import com.quanminjieshui.waterindex.utils.ToastUtils;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.img_title_center)
    ImageView img_title_center;
    @BindView(R.id.ll)
    public LinearLayout ll;
    @BindView(R.id.rg_main)
    public RadioGroup rg_main;
    @BindView(R.id.rb3)
    public RadioButton rb3;
    @BindView(R.id.rb4)
    public RadioButton rb4;
    @BindView(R.id.rb5)
    public RadioButton rb5;

    private Fragment currentFragment,tab1,tab2,tab3;
    private long mExitTime;

//    RadioButton[] rb = new RadioButton[5];
//    Drawable drawables[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    /**
     * 初始化页面Ui
     */
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }


    /**
     * 网络状况改变情况下 重试刷新数据
     *
     * @param viewId
     */
    @Override
    public void onReNetRefreshData(int viewId) {

    }

    private void createFragments() {
        showTransaction();
        left_ll.setVisibility(View.GONE);
        tv_title_center.setVisibility(View.GONE);
        img_title_center.setImageResource(R.mipmap.logo);
        img_title_center.setVisibility(View.VISIBLE);
    }


    private void initView() {
        createFragments();
        initRbsize();
    }


    private void initRbsize() {
//        rb[0] = rb3;
//        rb[1] = rb4;
//        rb[2] = rb5;
//
//        for (int i = 0; i < rb.length; i++) {
//            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
//            drawables = rb[i].getCompoundDrawables();
//            //获取drawables，2/5表示图片要缩小的比例
//            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 2 / 3, drawables[1].getMinimumHeight() * 2 / 3);
//            //定义一个Rect边界
//            drawables[1].setBounds(r);
//            //给每一个RadioButton设置图片大小
//            rb[i].setCompoundDrawables(null, drawables[1], null, null);
////            rb[i].setTextSize(Util.px2sp(this,24,1334));
//            AutoUtils.auto(rb[i]);
//        }
//        rb3.setChecked(true);
    }

    @OnClick({R.id.rb3, R.id.rb4, R.id.rb5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb3:
                showTransaction();
                break;
            case R.id.rb4:
                showFind();
                break;
            case R.id.rb5:
                showPersonal();
                break;
            default:
                break;
        }
    }

    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.activity_main_ll, targetFragment).commit();
        } else {
            transaction.hide(currentFragment).show(targetFragment).commit();
        }
        currentFragment = targetFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showCustomToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager.AppExit(mContext);
        }
    }


    /*****************by sxt****************/
    public void onEventMainThread(SelectFragmentEvent event) {
        if (event != null) {
            String title = event.getTitle();
            switch (title) {
                case "交易":
                    showTransaction();
                    break;
                case "发现":
                    showFind();
                    break;
                case "我的":
                    showPersonal();
                    break;
                default:
                    showTransaction();
                    break;
            }
        }
    }



    private void showTransaction() {
        rb3.setChecked(true);
        tv_title_center.setText("交易中心");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if(tab1==null)tab1=new TransactionFragment();
        if(currentFragment==null)currentFragment=tab1;
        switchFragment(tab1);
    }

    private void showFind() {
        rb4.setChecked(true);
        tv_title_center.setText("发现");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if(tab2==null)tab2=new FindFragment();
        switchFragment(tab2);
    }

    private void showPersonal() {
        rb5.setChecked(true);
        tv_title_center.setText("我的");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if(tab3==null)tab3=new PersonalFragment();
        switchFragment(tab3);
    }

    private String getSp() {
        boolean spIsLogin = (boolean) SPUtil.get(this, SPUtil.IS_LOGIN, false);
        String spUser_login = (String) SPUtil.get(this, SPUtil.USER_LOGIN, "token");
        return new StringBuilder().append(spIsLogin).append(spUser_login).toString();
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}