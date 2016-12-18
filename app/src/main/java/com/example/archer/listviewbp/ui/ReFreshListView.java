package com.example.archer.listviewbp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.archer.listviewbp.R;

/**
 * 自定义一个listview
 * Created by Archer on 2016/12/15.
 */

public class ReFreshListView extends ListView {

    private View mHeadView;

    public ReFreshListView(Context context) {
        super(context);
        init();//一定要记得初始化
    }


    public ReFreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReFreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {

        initHeadView();
        initFootView();
    }


    /**
     * 添加脚布局
     */
    private void initFootView() {
    }

    /**
     * 添加头布局
     */
    private void initHeadView() {

        mHeadView = View.inflate(getContext(), R.layout.layout_header_list, null);

        //在设置数据之前执行添加头布局/叫布局方法
        addHeaderView(mHeadView);

        //提前手动测量宽高
        mHeadView.measure(0,0);//按照设置的规则来测量

        int height = mHeadView.getHeight(); //最后在界面中得到的控件的宽高，必须经过渲染才有值
        int mHeadViewHeight = mHeadView.getMeasuredHeight();//得到的控件本身大小



        //设置内边距，可以隐藏当前控件
        mHeadView.setPadding(0,-mHeadViewHeight,0,0);


    }


}
