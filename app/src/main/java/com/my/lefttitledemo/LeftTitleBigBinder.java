package com.my.lefttitledemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 文琳
 * @time 2020/9/7 12:04
 * @desc
 */
public class LeftTitleBigBinder extends ItemViewBinder<LeftTitleBig, LeftTitleBigBinder.LeftTitleBigHolder> {

    @NonNull
    @Override
    protected LeftTitleBigHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LeftTitleBigHolder(inflater.inflate(R.layout.item_left_title_big, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull LeftTitleBigHolder holder, @NonNull LeftTitleBig bean) {
        if (Checker.notNull(bean.getLast())) holder.tvLast.setText(bean.getLast().getStr());
        if (Checker.notNull(bean.getCurrent())) holder.tvCurrent.setText(bean.getCurrent().getStr());
        if (Checker.notNull(bean.getNext())) holder.tvNext.setText(bean.getNext().getStr());
        holder.tvLast.setOnClickListener(v -> EventBus.getDefault().post(bean.getLast()));
        holder.tvCurrent.setOnClickListener(v -> EventBus.getDefault().post(bean.getCurrent()));
        holder.tvNext.setOnClickListener(v -> EventBus.getDefault().post(bean.getNext()));
    }

    static class LeftTitleBigHolder extends RecyclerView.ViewHolder {
        TextView tvLast, tvCurrent, tvNext;

        public LeftTitleBigHolder(@NonNull View itemView) {
            super(itemView);
            tvLast = itemView.findViewById(R.id.tv_last);
            tvCurrent = itemView.findViewById(R.id.tv_current);
            tvNext = itemView.findViewById(R.id.tv_next);
        }
    }
}
