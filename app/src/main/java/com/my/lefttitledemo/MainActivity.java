package com.my.lefttitledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private Items items = new Items();
    private MultiTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new MultiTypeAdapter(items);
        adapter.register(LeftTitle.class, new LeftTitleBinder());
        adapter.register(LeftTitleBig.class).to(new LeftTitleBigTopBinder(), new LeftTitleBigBinder(), new LeftTitleBigBottomBinder())
                .withLinker((position, leftTitleBig) -> leftTitleBig.getLast() == null ? 0 : leftTitleBig.getNext() == null ? 2 : 1);
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        rv.setAdapter(adapter);
        //模拟数据
        for (int i = 0; i < 40; i++) {
            items.add(new LeftTitle(i, "标题" + (i + 1), i == 0));
        }
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void threadHandle(LeftTitle bean) {
        if (Checker.isNull(bean)) return;
        if (Checker.noList(items)) return;
        List<LeftTitle> listOri = new ArrayList<>();
        List<LeftTitle> listNew = new ArrayList<>();
        //去big化
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            if (o instanceof LeftTitleBig) {
                LeftTitleBig big = (LeftTitleBig) o;
                if (Checker.notNull(big.getLast())) listOri.add(big.getLast());
                if (Checker.notNull(big.getCurrent())) listOri.add(big.getCurrent());
                if (Checker.notNull(big.getNext())) listOri.add(big.getNext());
            } else if (o instanceof LeftTitle) {
                LeftTitle lt = (LeftTitle) o;
                listOri.add(lt);
            }
        }
        //组装big
        LeftTitleBig big = new LeftTitleBig();
        int position = 0;//big要加入的位置
        for (int i = 0; i < listOri.size(); i++) {
            LeftTitle lt = listOri.get(i);
            if (lt.getPosition() == bean.getPosition()) {
                if (bean.getPosition() == 0) {//头
                    position = 0;
                    big.setLast(null);
                    big.setCurrent(lt);
                    big.setNext(listOri.get(i + 1));
                } else if (bean.getPosition() == listOri.size() - 1) {//尾
                    position = bean.getPosition() - 1;
                    big.setLast(listOri.get(i - 1));
                    big.setCurrent(lt);
                    big.setNext(null);
                } else {//中间
                    position = bean.getPosition() - 1;
                    big.setLast(listOri.get(i - 1));
                    big.setCurrent(lt);
                    big.setNext(listOri.get(i + 1));
                }
            }
        }
        //获得新的big以外的普通标题
        for (LeftTitle lt :
                listOri) {
            if (Checker.notNull(big.getLast()) && big.getLast().getPosition() == lt.getPosition()) continue;
            if (Checker.notNull(big.getCurrent()) && big.getCurrent().getPosition() == lt.getPosition()) continue;
            if (Checker.notNull(big.getNext()) && big.getNext().getPosition() == lt.getPosition()) continue;
            listNew.add(lt);
        }
        //先清除所有标题，然后按序加入普通标题，最后加入big
        if (Checker.hasList(items)) {
            items.clear();
            adapter.notifyDataSetChanged();
            items.addAll(listNew);
            adapter.notifyDataSetChanged();
            items.add(position, big);
            adapter.notifyDataSetChanged();
            if (rv != null && rv.getLayoutManager() != null) {//尽量控制选中的标题在中间
                GridLayoutManager manager = (GridLayoutManager) rv.getLayoutManager();
                manager.scrollToPositionWithOffset(position, rv.getHeight() / 2);
            }
        }
    }
}