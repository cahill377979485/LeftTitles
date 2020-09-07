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
public class LeftTitleBigBottomBinder extends ItemViewBinder<LeftTitleBig, LeftTitleBigBottomBinder.LeftTitleBigBottomHolder> {


    @NonNull
    @Override
    protected LeftTitleBigBottomHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LeftTitleBigBottomHolder(inflater.inflate(R.layout.item_left_title_big_bottom, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull LeftTitleBigBottomHolder holder, @NonNull LeftTitleBig bean) {
        if (Checker.notNull(bean.getLast())) holder.tvLast.setText(bean.getLast().getStr());
        if (Checker.notNull(bean.getCurrent())) holder.tvCurrent.setText(bean.getCurrent().getStr());
        holder.tvLast.setOnClickListener(v -> EventBus.getDefault().post(bean.getLast()));
        holder.tvCurrent.setOnClickListener(v -> EventBus.getDefault().post(bean.getCurrent()));

    }

    static class LeftTitleBigBottomHolder extends RecyclerView.ViewHolder {
        TextView tvLast, tvCurrent;

        public LeftTitleBigBottomHolder(@NonNull View itemView) {
            super(itemView);
            tvLast = itemView.findViewById(R.id.tv_last);
            tvCurrent = itemView.findViewById(R.id.tv_current);
        }
    }
}
