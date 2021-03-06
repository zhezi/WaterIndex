//package com.jieshuizhibiao.waterindex.ui.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.jieshuizhibiao.waterindex.R;
//import com.jieshuizhibiao.waterindex.beans.OrderDetailResponseBean;
//import com.zhy.autolayout.utils.AutoUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class OrderDetailServiceCateAdapter extends RecyclerView.Adapter<OrderDetailServiceCateAdapter.ServiceCateHolder> {
//
//
//    private Context context;
//    private List<OrderDetailResponseBean.ServiceCateEntry> list;
//
//    public OrderDetailServiceCateAdapter(Context context, List<OrderDetailResponseBean.ServiceCateEntry> list) {
//        this.context = context;
//        if (list == null) {
//            this.list = new ArrayList<>();
//        } else {
//            this.list = list;
//        }
//    }
//
//    @NonNull
//    @Override
//    public ServiceCateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_order_detail_service_cate, parent, false);
//        AutoUtils.auto(view);
//        return new ServiceCateHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ServiceCateHolder holder, int position) {
//        final OrderDetailResponseBean.ServiceCateEntry entry = list.get(position);
//        if (entry != null) {
//            holder.tvCNameAndTotal.setText(new StringBuilder()
//                    .append(entry.getC_name())
//                    .append(" * ")
//                    .append(entry.getTotal()).toString());
//            holder.tvPrice.setText(entry.getPrice());
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    class ServiceCateHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tv_c_name_and_total)
//        TextView tvCNameAndTotal;
//        @BindView(R.id.tv_price)
//        TextView tvPrice;
//
//        public ServiceCateHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//}
