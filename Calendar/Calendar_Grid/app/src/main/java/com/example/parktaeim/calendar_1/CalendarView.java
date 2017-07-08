package com.example.parktaeim.calendar_1;

    import android.app.Activity;
        import android.content.Context;
    import android.content.Intent;
    import android.content.res.TypedArray;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.util.AttributeSet;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.GridView;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.HashSet;


public class CalendarView extends LinearLayout
{


    private static final String LOGTAG = "Calendar View";

    // 5주일때도 있고 6주일때도 있으니깐 7x6=42
    private static final int DAYS_COUNT = 42;

    private static final String DATE_FORMAT = "MMM yyyy";

    private String dateFormat;

    // 현재 날짜와 시간
    private Calendar currentDate = Calendar.getInstance();

    private EventHandler eventHandler = null;

    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;


    // 계절마다 캘린더 색상 변경
    int[] rainbow = new int[] {
            R.color.summer,
            R.color.fall,
            R.color.winter,
            R.color.spring
    };

    // 0:여름 1:가을 2:겨울 3:
    int[] monthSeason = new int[] {2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};

    public CalendarView(Context context)
    {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }



    private void initControl(Context context, AttributeSet attrs)
    {
        //LayoutInflater: xml에 정의된 resource 들을 view의 형태로 반환
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs)
    {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try
        {
            //date format 으로 설정
            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        }
        finally
        {
            ta.recycle();
        }
    }

    Button addBtn;
    private void assignUiElements()
    {
        header = (LinearLayout)findViewById(R.id.calendar_header);
        btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView)findViewById(R.id.calendar_next_button);
        txtDate = (TextView)findViewById(R.id.calendar_date_display);
        grid = (GridView)findViewById(R.id.calendar_grid);
        addBtn = (Button) findViewById(R.id.addButton);
    }

    private void assignClickHandlers()
    {
        // 다음달로 넘어가는 버튼 누르면 다음달로 넘어감
        btnNext.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentDate.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        // 전월로 넘어가는 버튼 누르면 전월로 돌아감
        btnPrev.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentDate.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                getContext().startActivity(intent);

            }
        });

        // 길게 눌렀을 때
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id)
            {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onDayLongPress((Date)view.getItemAtPosition(position));
                return true;
            }
        });
    }



    public void updateCalendar()
    {
        updateCalendar(null);
    }


    public void updateCalendar(HashSet<Date> events)
    {
        //현재 날짜
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)currentDate.clone();

        //현재 달의 일을 1일로 설정
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        //add(int field,int amount) : 지정한 필드의 값을 원하는 만큼 증가 또는 감소 시킬 수 있음
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // 일
        while (cells.size() < DAYS_COUNT)
        {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // GridView 에 set
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

        // txtDate(다음달 이전달 버튼 가운데 있는 달 표시 하는 텍스트뷰)
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));

        // 계절 따라 색깔 설정
        int month = currentDate.get(Calendar.MONTH);
        int season = monthSeason[month];
        int color = rainbow[season];

        header.setBackgroundColor(getResources().getColor(color));


    }


    private class CalendarAdapter extends ArrayAdapter<Date>
    {
        private HashSet<Date> eventDays;

        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
        {
            super(context, R.layout.control_calendar_day, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            Date date = getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            //현재날짜의 월
            int month2 = currentDate.get(Calendar.MONTH);
            int year = date.getYear();

            // 오늘
            Date today = new Date();

            if (view == null)
                view = inflater.inflate(R.layout.control_calendar_day, parent, false);

            // 일정있으면 표시
            view.setBackgroundResource(0);
            if (eventDays != null)
            {
                for (Date eventDate : eventDays)
                {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year)
                    {
                        // 느낌표 icon 표시
                        view.setBackgroundResource(R.drawable.reminder);
                        break;
                    }
                }
            }


            ((TextView)view).setTypeface(null, Typeface.NORMAL);
            ((TextView)view).setTextColor(Color.BLACK);

            //현재 월의 날짜가 아니면 날짜 회색으로 표시
            if(month2!=month)
            {
               ((TextView)view).setTextColor(getResources().getColor(R.color.greyed_out));

            }
            //오늘 날짜는 더 간지나게 표시
            else if (day == today.getDate() && month==today.getMonth() && year==today.getYear())
            {
                // if it is today, set it to blue/bold
                ((TextView)view).setTypeface(null, Typeface.BOLD);
                ((TextView)view).setTextColor(getResources().getColor(R.color.today));
            }

            ((TextView)view).setText(String.valueOf(date.getDate()));

            return view;
        }
    }


    public void setEventHandler(EventHandler eventHandler)
    {
        this.eventHandler = eventHandler;
    }

    public interface EventHandler
    {
        void onDayLongPress(Date date);
    }






}
