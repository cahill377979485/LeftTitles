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
 * @time 2020/9/7 15:20
 * @desc
 */
public class LeftTitleBigTopBinder extends ItemViewBinder<LeftTitleBig, LeftTitleBigTopBinder.LeftTitleBigTopHolder> {


    @NonNull
    @Override
    protected LeftTitleBigTopHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LeftTitleBigTopHolder(inflater.inflate(R.layout.item_left_title_big_top, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull LeftTitleBigTopHolder holder, @NonNull LeftTitleBig bean) {
        if (Checker.notNull(bean.getCurrent())) holder.tvCurrent.setText(bean.getCurrent().getStr());
        if (Checker.notNull(bean.getNext())) holder.tvNext.setText(bean.getNext().getStr());
        holder.tvCurrent.setOnClickListener(v -> EventBus.getDefault().post(bean.getCurrent()));
        holder.tvNext.setOnClickListener(v -> EventBus.getDefault().post(bean.getNext()));
    }

    static class LeftTitleBigTopHolder extends RecyclerView.ViewHolder {
        TextView tvCurrent, tvNext;

        public LeftTitleBigTopHolder(@NonNull View itemView) {
            super(itemView);
            tvCurrent = itemView.findViewById(R.id.tv_current);
            tvNext = itemView.findViewById(R.id.tv_next);
        }
    }
}
