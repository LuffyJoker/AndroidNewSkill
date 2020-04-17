package com.peng.androidnewskill.page.application;

import android.app.Application;

import com.peng.androidnewskill.utils.UtilBox;

/**
 * Created by liushaochen on 2017/5/4.
 */
public class BamApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化工具箱
        UtilBox.getBox().initBox();
    }

}
