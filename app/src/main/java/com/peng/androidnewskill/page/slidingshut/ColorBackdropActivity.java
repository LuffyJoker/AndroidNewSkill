package com.peng.androidnewskill.page.slidingshut;

import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.peng.androidnewskill.R;
import com.peng.androidnewskill.page.slidingshut.baseactivity.BaseSlidingShutActivity;


/**
 * 彩色背景下改变进度条颜色示例
 * <p>
 * Created by Bamboy on 2018/10/25.
 */
public class ColorBackdropActivity extends BaseSlidingShutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_backdrop);

        // 设置进度条颜色
        setProgressColor(ContextCompat.getColor(this, R.color.colorWhite));
    }
}
