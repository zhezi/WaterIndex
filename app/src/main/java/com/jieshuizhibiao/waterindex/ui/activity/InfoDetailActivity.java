/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InfoDetailActivity
 * Author: sxt
 * Date: 2019/1/2 11:38 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.event.SelectFragmentEvent;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 *
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.ui.activity
 * @ClassName: InfoDetailActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/2 11:38 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/2 11:38 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class InfoDetailActivity extends BaseActivity {
    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_detail)
    TextView tvDetadil;
    @BindView(R.id.relative_hint)
    RelativeLayout rlHint;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("咨询详情");
        rlHint.setVisibility(View.VISIBLE);
        tvDetadil.setText("敬请期待！");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods_lists);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                EventBus.getDefault().post(new SelectFragmentEvent("发现"));
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

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}