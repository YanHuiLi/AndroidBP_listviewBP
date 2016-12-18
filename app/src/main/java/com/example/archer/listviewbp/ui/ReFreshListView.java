package com.example.archer.listviewbp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.example.archer.listviewbp.R;

/**
 * 自定义一个listview
 * Created by Archer on 2016/12/15.
 */

public class ReFreshListView extends ListView {

    private View mHeadView;
    private float downY;
    private float moveY;
    private float upY;
    private int mHeadViewHeight;
    private int currentState;//当前刷新模式


    private static  final int  PULL_TO_REFRESH =0;
    private static  final int  RELEASE_REFRESH =1;
    private static  final int  REFRESHING =2;



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
        //得到的控件本身大小
        mHeadViewHeight = mHeadView.getMeasuredHeight();

        //设置内边距，可以隐藏当前控件
        mHeadView.setPadding(0,-mHeadViewHeight,0,0);


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){//触摸事件

            case MotionEvent.ACTION_DOWN:

                downY = ev.getY();


                break;
            case MotionEvent.ACTION_MOVE:

                moveY = ev.getY();
                float offset = moveY - downY;//得到一个偏移量

//只有当偏移量大于0并且当第一个条目索引是0时，才放大
                if (offset>0&&getFirstVisiblePosition()==0){
                    int paddingTop = (int) (-mHeadViewHeight + offset);
                    mHeadView.setPadding(0,paddingTop,0,0);

                     if (paddingTop>=0&&currentState!=RELEASE_REFRESH){//完全显示

                         //变成释放刷新的状态
                         currentState = RELEASE_REFRESH;
                         System.out.println("切换成成释放刷新模式"+paddingTop);

                         updateHeader();//根据头布局更新显示的动画和内容

                     }else if (paddingTop<=0&&currentState!=PULL_TO_REFRESH){//不完全显示头布局

                         //释放刷新

                         currentState = PULL_TO_REFRESH;
                         System.out.println("切换成下拉模式"+paddingTop);
                         updateHeader();
                     }

                    return  true;//当前事件被我们处理消费
                }


                break;
            case MotionEvent.ACTION_UP:

                upY = ev.getY();

                break;



        }




        return super.onTouchEvent(ev);//这行代码要留着，因为还会有很多操作删了容易出bug
    }

    /**
     * 根据状态，更新内容
     */

    private void updateHeader() {
    }
}
