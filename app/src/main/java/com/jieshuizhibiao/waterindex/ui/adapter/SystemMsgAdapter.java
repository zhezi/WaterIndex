package com.jieshuizhibiao.waterindex.ui.adapter;/*
package com.jieshuizhibiao.waterindex.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.beans.SystemMsgResponseBean;
import com.jieshuizhibiao.waterindex.beans.TradeListsResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

*/
/**
 * Created by songxiaotao on 2019/1/14.
 * Class Note:
 *//*


public class SystemMsgAdapter extends RecyclerView.Adapter<SystemMsgAdapter.ViewHolder>{

    private Context context;
    private List<SystemMsgResponseBean.SystemMsgList> lists;

    public SystemMsgAdapter(Context context, List<SystemMsgResponseBean.SystemMsgList> list) {
        this.context = context;
        if (list == null) {
            list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public SystemMsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_message_item, parent, false);
        AutoUtils.auto(view);
        return new SystemMsgAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemMsgAdapter.ViewHolder holder, int position) {
        holder.tvMessageTitle.setText(lists.get(position));
        holder.tvMessageTime.setText(tradeEntity.getPrice());
        holder.tvMessageContent.setText(tradeEntity.getOld_total());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.message_title)
        TextView tvMessageTitle;
        @BindView(R.id.message_time)
        TextView tvMessageTime;
        @BindView(R.id.message_coutent)
        TextView tvMessageContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
*/
