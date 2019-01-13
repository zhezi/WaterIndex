package com.quanminjieshui.waterindex.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.ListOrder;
import com.quanminjieshui.waterindex.contract.model.OrderListsModel;
import com.quanminjieshui.waterindex.contract.presenter.OrderListsPresenter;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;
import com.quanminjieshui.waterindex.event.ShouldRefreshEvent;
import com.quanminjieshui.waterindex.ui.adapter.OrderListsAdapter;
import com.quanminjieshui.waterindex.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 全部，进行中，已完成，已取消，申诉处理共用
 */
public class OrderListsTabFragment extends BaseFragment implements CommonViewImpl, OrderListsAdapter.onItemClickedListener, XRecyclerView.LoadingListener {

    Unbinder unbinder;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    @BindView(R.id.relative_hint)
    RelativeLayout rlHint;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    private String title = "全部";
    private String type = "0";//0 全部 1 进行中 2 已完成 3 已取消 4 申诉处理
    private String page = "1";//页码
    private int intPage = 1;//loadMore执行成功时+1    refresh时归位至1
    private String page_size = "5";//每页数量
    private OrderListsAdapter orderListsAdapter;
    private List<ListOrder> list = new ArrayList<>();

    private boolean isLoadMore = false;//如果执行loadMore进行网络请求，该值设置为true,list累加,请求成功后必须归位至false;
    private boolean isRefresh;//数据是否刷新标志 true:表示数据需要刷新，list数据clear   false：加载更多或初次加载等情况
    private boolean isVisiable = false;
    private int isFirstVisable = 0;//懒加载使用  为1时进行网络请求

    private OrderListsPresenter orderListsPresenter = null;
    private BaseActivity baseActivity;//懒加载需要需用

    /**
     * must perform before onCreateView/doRequest
     *
     * @param title
     */
    public void setTitle(String title, BaseActivity activity) {
        this.title = title;
        this.baseActivity = activity;
        selectType();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_lists_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        if (orderListsPresenter == null) {
            orderListsPresenter = new OrderListsPresenter(new OrderListsModel());
            orderListsPresenter.attachView(this);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * viewpager滑动时，仅保留当前Fragment及其左右两侧两个Fragment,共三个Fragment
         *                 最左侧Fragment显示时，其右侧一个Fragment也保留，共2个
         *                 最右侧Fragment显示时，其左侧一个Fragment也保留，共2个
         *                 其他已加载过的Fragment走生命周期onResume
         *
         *
         * 故有以下判断：fragment可见  &&  接收到订单详情页Event
         * 正常滑动情况不执行网络请求
         */
        if (isVisiable && isRefresh) {
            doRequest();
        }
    }

    private void init() {
        orderListsAdapter = new OrderListsAdapter(getActivity(), list, this);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setAdapter(orderListsAdapter);
        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingListener(this);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onItemClicked(ListOrder order) {
        if (order != null) {
            final int order_id = order.getOrder_id();
            //todo jump
        }
    }

    @Override
    public void onRequestSuccess(Object bean) {
        List<ListOrder> datas = (List<ListOrder>) bean;
        if (datas != null) {
            if (list != null && list.size() == 0 && datas.size() == 0) {//第一次执行dorequest
                xrv.setVisibility(View.GONE);
                rlHint.setVisibility(View.VISIBLE);
                tvDetail.setText("您还没有相关订单，赶快下单吧！");
            } else {
                xrv.setVisibility(View.VISIBLE);
                rlHint.setVisibility(View.GONE);
                if (isRefresh) list.clear();
                list.addAll(datas);
                orderListsAdapter.notifyDataSetChanged();
            }
        }
        //如果因为加载更多进行网络请求，请求完毕后isLoadMore归位至false;
        if (isLoadMore) {
            isLoadMore = false;
        }
        isRefresh = false;//每次执行完毕后归位为不刷新
        dismissLoadingDialog();
    }

    @Override
    public void onRequestFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast("msg");
        //如果因为加载更多进行网络请求，请求完毕后isLoadMore归位至false;
        if (isLoadMore) {
            isLoadMore = false;
        }
        isRefresh = false;

    }

    private void selectType() {
        switch (title) {
            case "全部":
                type = "0";
                break;
            case "进行中":
                type = "1";
                break;
            case "已完成":
                type = "2";
                break;
            case "已取消":
                type = "3";
                break;
            case "申诉处理":
                type = "4";
                break;
            default:
                break;
        }
    }


    /**
     * 1、onCreateView创建时请求
     * 2、可见、且收到订单详情页event时请求
     * 3.以防万一，在setUserVisibleHint执行2
     * 4.下拉刷新
     * 5.上垃加载更多
     */
    private void doRequest() {
        if (!isLoadMore) showLoadingDialog();
        if (orderListsPresenter == null) {
            orderListsPresenter = new OrderListsPresenter(new OrderListsModel());
            orderListsPresenter.attachView(this);
        }
        page = String.valueOf(intPage);
//        orderListsPresenter.getOrderList(getBaseActivity(), type, page, page_size);//懒加载时拿到的activity为null
        orderListsPresenter.getOrderList(baseActivity, type, page, page_size);
    }

    private void jump(Class<?> cls, Intent intent) {
        if (intent == null) {
            intent = new Intent(getActivity(), cls);
        }
        startActivity(intent);
    }


    public void onEventMainThread(ShouldRefreshEvent event) {
        if (event != null) {
            String eventMsg = event.getTitle();
            if (eventMsg.equals(this.title)) {
                isRefresh = true;
                doRequest();
            }
        }
    }

    @Override
    public void showLoadingDialog() {
        if (isVisiable) {
//            super.showLoadingDialog();//懒加载时直接使用fragment的showloadingDialog方法报异常。跟生命周期有关
            baseActivity.showLoadingDialog();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (isVisiable) {
            baseActivity.dismissLoadingDialog();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisiable = isVisibleToUser;
        if (isVisiable) {
            isFirstVisable += 1;
            //用户滑动到该fragment显示时，且该fragment接收到刷新event
            if (isRefresh) {
                doRequest();
                return;
            }
            //懒加载
            if (isFirstVisable == 1) {
                doRequest();
            }
        }
    }


    @Override
    public void onRefresh() {
        if (isVisiable) {
            isRefresh = true;
            intPage = 1;
            doRequest();
            xrv.refreshComplete();
        }
    }

    @Override
    public void onLoadMore() {
        if (isVisiable) {
            isLoadMore = true;
            intPage += 1;
            doRequest();
            xrv.loadMoreComplete();
        }
    }

    @Override
    public void onDestroy() {
        if (orderListsPresenter != null) orderListsPresenter.detachView();
        unbinder.unbind();
        super.onDestroy();

    }
}
