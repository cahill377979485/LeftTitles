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
 * @time 2020/9/7 11:05
 * @desc
 */
public class LeftTitleBinder extends ItemViewBinder<LeftTitle, LeftTitleBinder.LeftTitleHolder> {

    @NonNull
    @Override
    protected LeftTitleHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LeftTitleHolder(inflater.inflate(R.layout.item_left_title, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull LeftTitleHolder holder, @NonNull LeftTitle bean) {
        holder.tv.setText(bean.getStr());
        holder.tv.setOnClickListener(v -> EventBus.getDefault().post(bean));
    }

    static class LeftTitleHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public LeftTitleHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
