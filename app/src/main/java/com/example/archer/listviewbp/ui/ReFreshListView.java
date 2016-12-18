package com.example.archer.listviewbp.ui;

import android.content.Context;
import android.util.AttributeSet;
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

        addHeaderView(mHeadView);

    }


}
