package com.quanminjieshui.waterindex.ui.activity;//package com.quanminjieshui.waterindex.ui.activity;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.quanminjieshui.waterindex.R;
//import com.quanminjieshui.waterindex.base.BaseActivity;
//import com.quanminjieshui.waterindex.ui.adapter.OrderListsViewpagerAdapter;
//import com.quanminjieshui.waterindex.ui.fragment.OrderListsTabFragment;
//import com.quanminjieshui.waterindex.utils.StatusBarUtil;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
//public class OrderListsActivity extends BaseActivity  {
//
//
//    @BindView(R.id.tv_title_center)
//    TextView tvTitleCenter;
//    @BindView(R.id.title_bar)
//    RelativeLayout titleBar;
//
//    @BindView(R.id.container)
//    LinearLayout container;
//    @BindView(R.id.tablayout)
//    TabLayout tabLayout;
//    @BindView(R.id.viewpager)
//    ViewPager viewpager;
//
//
//
//
//    public String[] titles = new String[]{"全部", "待付款", "取件中", "洗涤中", "已完成", "已取消"};
//    private ArrayList<OrderListsTabFragment> fragments = new ArrayList<>();
//    private OrderListsViewpagerAdapter adapter;
//
//    @OnClick({R.id.left_ll})
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.left_ll:
//                finish();
//                break;
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        StatusBarUtil.setImmersionStatus(this, titleBar);
//        initView();
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        initView();
//    }
//
//
//    private void initView() {
//        tvTitleCenter.setText("洗涤订单");
////        tabLayout.addOnTabSelectedListener(this);
//
//        for (int i = 0; i < titles.length; i++) {
//            OrderListsTabFragment fragment = new OrderListsTabFragment();
////            fragment.setTitle(titles[i]);
//            fragments.add(fragment);
//            tabLayout.addTab(tabLayout.newTab());
//        }
//        tabLayout.setupWithViewPager(viewpager, false);
//        adapter = new OrderListsViewpagerAdapter(getSupportFragmentManager(), fragments, titles);
//        viewpager.setAdapter(adapter);
//    }
//
//
//
//    @Override
//    public void initContentView() {
//        setContentView(R.layout.fragment_order_lists);
//    }
//
//    @Override
//    public void onReNetRefreshData(int viewId) {
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }
//}
