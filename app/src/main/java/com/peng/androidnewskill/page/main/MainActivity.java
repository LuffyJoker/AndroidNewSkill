package com.peng.androidnewskill.page.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.peng.androidnewskill.R;
import com.peng.androidnewskill.base.actiivty.BamActivity;
import com.peng.androidnewskill.base.freedom.FreedomAdapter;
import com.peng.androidnewskill.base.freedom.FreedomBean;
import com.peng.androidnewskill.base.freedom.FreedomCallback;
import com.peng.androidnewskill.base.freedom.ViewHolderManager;
import com.peng.androidnewskill.page.anim_click.AnimClickActivity;
import com.peng.androidnewskill.page.auto_line.AutoLineActivity;
import com.peng.androidnewskill.page.bean.SingleBtnBean;
import com.peng.androidnewskill.page.bean.TitleBean;
import com.peng.androidnewskill.page.blur.BlurActivity;
import com.peng.androidnewskill.page.divide_load.DivideLoadActivity;
import com.peng.androidnewskill.page.expresscard.ExpressCardActivity;
import com.peng.androidnewskill.page.freedom.FreedomListActivity;
import com.peng.androidnewskill.page.progress.ProgressActivity;
import com.peng.androidnewskill.page.slidingshut.SlidingShutActivity;
import com.peng.androidnewskill.page.toast.ToastActivity;
import com.peng.androidnewskill.page.toast.bamtoast.BamToast;
import com.peng.androidnewskill.page.viewflipper.ViewFlipperActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * <p>
 * 动画显示技术点列表
 * 每个按钮进入一个技术点展示页面
 * <p>
 * Created by Bamboy on 2017/3/24.
 */
public class MainActivity extends BamActivity implements FreedomCallback {

    private List<FreedomBean> mList;
    private FreedomAdapter mAdapter;
    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findView() {
        rv_list = findViewById(R.id.rv_list);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void init() {

        // 初始化手机信息
        utils.info.initPhoneInfo(this);
        // 初始化3DPress
        initFlyme3DPress();

        // 关闭当前界面的右滑关闭功能
        openSlideFinish(false);

        initList();
    }

    /**
     * 初始化List
     */
    private void initList() {
        mList = new ArrayList<>();

        mList.add(new TitleBean("最受欢迎Demo："));
        mList.add(new SingleBtnBean("非约束列表 Demo  ", FreedomListActivity.class));
        mList.add(new SingleBtnBean("右滑关闭 Demo", SlidingShutActivity.class));
        mList.add(new SingleBtnBean("无感分页 Demo", DivideLoadActivity.class));

        mList.add(new TitleBean("最新Demo："));
        mList.add(new SingleBtnBean("进度条 Demo", ProgressActivity.class));
        mList.add(new SingleBtnBean("信息滚动 Demo", ViewFlipperActivity.class));
        mList.add(new SingleBtnBean("物流卡片 Demo", ExpressCardActivity.class));

        mList.add(new TitleBean("Bamboy经典："));
        mList.add(new SingleBtnBean("高斯模糊 Demo", BlurActivity.class));
        mList.add(new SingleBtnBean("点击动画 Demo", AnimClickActivity.class));
        mList.add(new SingleBtnBean("Toast Demo", ToastActivity.class));
        mList.add(new SingleBtnBean("自动换行 Demo", AutoLineActivity.class));

        mAdapter = new FreedomAdapter(this, mList);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(mAdapter);
    }

    /**
     * 条目里的View的点击事件
     *
     * @param view     点击的View
     * @param position 列表的第N个条目
     * @param holder   所使用的的ViewHolder
     */
    @Override
    public void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder) {
        switch (view.getId()) {
            case R.id.rl_single:
                if (false == mList.get(position) instanceof SingleBtnBean) {
                    break;
                }
                SingleBtnBean bean = (SingleBtnBean) mList.get(position);
                if (bean.getStartClass() != null) {
                    Intent intent = new Intent(MainActivity.this, bean.getStartClass());
                    MainActivity.this.startActivity(intent);
                } else {
                    BamToast.showText(MainActivity.this, "该Demo尚未整合\n请耐心等待");
                }
                break;
        }
    }

    /**
     * 初始化3DPress
     */
    private void initFlyme3DPress() {
        Intent intent = getIntent();
        if (intent != null) {
            Uri data = intent.getData();
            if (data != null && TextUtils.equals("flyme_3dtouch", data.getScheme())) {
                // 进入到这里，
                // 说明是通过3D Press进来的，
                // 所以把主页的列表动画取消，
                // 以免浪费性能。
                rv_list.setLayoutAnimation(null);

                // 判断是通过桌面菜单的哪一项进来的
                if (TextUtils.equals("/freemod", data.getPath())) {
                    startActivity(new Intent(this, FreedomListActivity.class));
                    return;

                } else if (TextUtils.equals("/blus", data.getPath())) {
                    startActivity(new Intent(this, BlurActivity.class));
                    return;

                } else if (TextUtils.equals("/toast", data.getPath())) {
                    startActivity(new Intent(this, ToastActivity.class));
                    return;

                } else if (TextUtils.equals("/click", data.getPath())) {
                    startActivity(new Intent(this, AnimClickActivity.class));
                    return;
                }
            }
        }
    }
}