package com.peng.androidnewskill.page.expresscard.viewpager;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.peng.androidnewskill.page.expresscard.fragment.CardFragment;

import java.util.List;

/**
 * Created by liushaochen on 2019/3/4.
 */
public class CardViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mManager;  //创建FragmentManager
    private List<CardFragment> mList; //创建一个List<Fragment>

    //定义构造带两个参数
    public CardViewPagerAdapter(FragmentManager fm, List<CardFragment> list) {
        super(fm);
        this.mManager = fm;
        this.mList = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        if (mList == null || mList.size() == 0) {
            return null;
        }
        return mList.get(arg0); //返回第几个fragment
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "title";
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size(); //总共有多少个fragment
    }

}
