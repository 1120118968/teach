package com.example.teacher;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
public class CalendarActivity extends Activity implements GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private GridView gridView = null;
    private TextView topText = null;
    private static int jumpMonth = 0;  //每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0;  //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private Bundle bd=null;//发送参数
    private Bundle bun=null;//接收参数
    private String ruzhuTime;
    private String lidianTime;
    private String state="";
    public CalendarActivity() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date); //当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        bd=new Bundle();//out
        bun=getIntent().getExtras();//in
        if(bun!=null&&bun.getString("state").equals("ruzhu"))
        {
            state=bun.getString("state");
            System.out.println("%%%%%%"+state);
        }else if(bun!=null&&bun.getString("state").equals("lidian")){

            state=bun.getString("state");
            System.out.println("|||||||||||"+state);
        }
        gestureDetector = new GestureDetector(this);
        bd=new Bundle();
        calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
        addGridView();
        gridView.setAdapter(calV);
        topText = (TextView) findViewById(R.id.tv_month);
        addTextToTopTextView(topText);
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int gvFlag = 0;   //每次添加gridview到viewflipper中时给的标记
        if (e1.getX() - e2.getX() > 120) {
            addGridView(); //添加一个gridView
            jumpMonth++;  //下一个月
            calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
            gridView.setAdapter(calV);
            addTextToTopTextView(topText);
            gvFlag++;
            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            addGridView(); //添加一个gridView
            jumpMonth--;  //上一个月
            calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
            gridView.setAdapter(calV);
            gvFlag++;
            addTextToTopTextView(topText);
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, menu.FIRST, menu.FIRST, "今天");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case Menu.FIRST:
                int xMonth = jumpMonth;
                int xYear = jumpYear;
                int gvFlag =0;
                jumpMonth = 0;
                jumpYear = 0;
                addGridView(); //添加一个gridView
                year_c = Integer.parseInt(currentDate.split("-")[0]);
                month_c = Integer.parseInt(currentDate.split("-")[1]);
                day_c = Integer.parseInt(currentDate.split("-")[2]);
                calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
                gridView.setAdapter(calV);
                addTextToTopTextView(topText);
                gvFlag++;
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }
    @Override
    public void onLongPress(MotionEvent e) {
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    public void addTextToTopTextView(TextView view){
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
        view.setTextColor(Color.WHITE);
        view.setTypeface(Typeface.DEFAULT_BOLD);
    }
    private void addGridView() {
        gridView =(GridView)findViewById(R.id.gridview);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            //将gridview中的触摸事件回传给gestureDetector
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return CalendarActivity.this.gestureDetector.onTouchEvent(event);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //gridView中的每一个item的点击事件

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if(startPosition <= position+7 && position <= endPosition-7){
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0];
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();//     Toast.makeText(CalendarActivity.this, scheduleYear+"-"+scheduleMonth+"-"+scheduleDay, 2000).show();
                    ruzhuTime=scheduleMonth+"月"+scheduleDay+"日";
                    lidianTime=scheduleMonth+"月"+scheduleDay+"日";
                    Intent intent=new Intent();
                    if(state.equals("ruzhu"))
                    {
                        bd.putString("ruzhu", ruzhuTime);
                        System.out.println("ruzhuuuuuu"+bd.getString("ruzhu"));
                    }else if(state.equals("lidian")){
                        bd.putString("lidian", lidianTime);
                    }
                    intent.putExtras(bd);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
}
