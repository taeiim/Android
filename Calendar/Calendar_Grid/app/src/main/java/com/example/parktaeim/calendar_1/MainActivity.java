package com.example.parktaeim.calendar_1;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.HashSet;

public class MainActivity extends ActionBarActivity
{

    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        //ViewPager Adapter 객체 생성
        pager = (ViewPager) findViewById(R.id.pager);

        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());

        pager.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //오른쪽 맨위에 menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // menu 누르면 나오는 것들 (settings, 나중에 더 추가
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
