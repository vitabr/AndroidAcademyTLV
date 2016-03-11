package com.aat.rntv.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aat.rntv.business.SharedPref;
import com.aat.rntv.business.Utils;
import com.aat.rntv.model.FirebaseAttendant;
import com.champions.are.we.androidacademytlv.R;
import com.firebase.client.Firebase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class RsvpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LESSON_NUMBER = "lessonNumber";
    private static final String LESSON_TITLE = "title";
    private static final String LESSON_ATTENDANTS = "attendants";

    private String mLessonNumber;

    private Calendar mCalendar = Calendar.getInstance();

    private TextView mDatePickerTxt, mTimePickerTxt;
    private TextView mRsvpYes, mRsvpNo;
    private ImageView mAddToCalendarImg;

    private boolean mDateSet = false;
    private boolean mTimeSet = false;
    private boolean mPlanToShow = false;
    private boolean mAddCalendar = false;

    public static Intent getIntent(Context context, String lessonNumber, String title, String attCount) {
        Intent intent = new Intent(context, RsvpActivity.class);
        intent.putExtra(LESSON_NUMBER, lessonNumber);
        intent.putExtra(LESSON_TITLE, title);
        intent.putExtra(LESSON_ATTENDANTS, attCount);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsvp);

        mLessonNumber = getIntent().getStringExtra(LESSON_NUMBER);
        String lessonTitle = getIntent().getStringExtra(LESSON_TITLE);
        String attendants = getIntent().getStringExtra(LESSON_ATTENDANTS);

        prepareViews(mLessonNumber, lessonTitle, attendants);
    }

    private void prepareViews(String lessonNumber, String lessonTitle, String attCount) {

        //set top view
        String lessonNumberTxt = getString(R.string.lesson_number_format)+lessonNumber;
        String attendants = attCount + getString(R.string.attendants_txt);
        ((TextView)findViewById(R.id.lesson_number)).setText(lessonNumberTxt);
        ((TextView)findViewById(R.id.lesson_title)).setText(lessonTitle);
        ((TextView)findViewById(R.id.attendants_count)).setText(attendants);

        mDatePickerTxt = (TextView) findViewById(R.id.date_picker_text);
        mDatePickerTxt.setText(R.string.set_date);

        mTimePickerTxt = (TextView) findViewById(R.id.time_picker_text);
        mTimePickerTxt.setText(R.string.set_time);

        mRsvpYes = (TextView) findViewById(R.id.pts_yes);
        mRsvpNo = (TextView) findViewById(R.id.pts_no);

        mRsvpYes.setBackgroundColor(getResources().getColor(R.color.lightGray));
        mRsvpNo.setBackgroundColor(getResources().getColor(R.color.red));

        mAddToCalendarImg = (ImageView) findViewById(R.id.img_calendar);

        //set onclicks
        mRsvpYes.setOnClickListener(this);
        mRsvpNo.setOnClickListener(this);
        findViewById(R.id.add_to_calendar_layout).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.save).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.date_picker_layout).setOnClickListener(this);
        findViewById(R.id.time_picker_layout).setOnClickListener(this);
    }

    private void showTimePicker(final TextView textView, final String text) {

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

                mTimeSet = true;

                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);

                boolean is24 = DateFormat.is24HourFormat(RsvpActivity.this);

                String min, hour, ampm;

                if (is24) {
                    // 24 Hour
                    min = minute > 9 ? minute+"" : "0"+minute;
                    hour = hourOfDay > 9 ? hourOfDay+"" : "0"+hourOfDay;
                    textView.setText(text + " " + hour + ":" + min);
                } else {
                    // AM:PM
                    ampm = " AM";
                    if (hourOfDay > 12) {
                        ampm = " PM";
                        hourOfDay -= 12;
                    }
                    textView.setText(text + " " + hourOfDay + ":" + minute + ampm);
                }
                textView.setTextColor(getResources().getColor(R.color.darkGray));
            }
        };

        int mHour = 9; //now.get(Calendar.HOUR);
        int mMin = 0; //now.get(Calendar.MINUTE);
        boolean is24hour = DateFormat.is24HourFormat(RsvpActivity.this);
        TimePickerDialog dialog = TimePickerDialog.newInstance(listener, mHour, mMin, is24hour);

        dialog.show(getFragmentManager(), "AAT::TimePickerDialog");
    }

    private void showDatePicker(final TextView textView, final String text) {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {

                mDateSet = true;

                textView.setText(text+dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                textView.setTextColor(getResources().getColor(R.color.darkGray));

                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                listener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "AAT::DatePickerDialog");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                saveRSVP();
                break;
            case R.id.btn_back:
            case R.id.cancel:
                onBackPressed();
                break;
            case R.id.pts_yes:
                rsvpYes();
                break;
            case R.id.pts_no:
                rsvpNo();
                break;
            case R.id.date_picker_layout:
                showDatePicker(mDatePickerTxt, "Date: ");
                break;
            case R.id.time_picker_layout:
                showTimePicker(mTimePickerTxt, "Time: ");
                break;
            case R.id.add_to_calendar_layout:
                handleCalendarClick();
                break;
        }
    }

    private void handleCalendarClick() {
        if (mAddCalendar) {
            mAddToCalendarImg.setImageResource(R.drawable.btn_checkmarkoff);
            mAddCalendar = false;
        } else {
            mAddToCalendarImg.setImageResource(R.drawable.btn_checkmarkon);
            mAddCalendar = true;
        }
    }

    private void rsvpYes() {
        mPlanToShow = true;
        mRsvpYes.setBackgroundColor(getResources().getColor(R.color.green));
        mRsvpNo.setBackgroundColor(getResources().getColor(R.color.lightGray));
    }

    private void rsvpNo() {
        mPlanToShow = false;
        mRsvpYes.setBackgroundColor(getResources().getColor(R.color.lightGray));
        mRsvpNo.setBackgroundColor(getResources().getColor(R.color.red));
    }

    private void createCalendarEvent() {
        //TODO - Give this the right time.
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("endTime", cal.getTimeInMillis() + TimeUnit.HOURS.toMillis(2));
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
    }

    private void saveRSVP() {

        if (!mPlanToShow) {
            Log.i("Not going to show %s", "Closing rsvp.");
            finish();
            return;
        }

        if (mTimeSet && mDateSet) {
            Utils.createAlarm(mCalendar.getTimeInMillis());
        }

        if (mAddCalendar) {
            createCalendarEvent();
        }

        Firebase firebase = new Firebase("https://flickering-torch-6484.firebaseio.com/Course_4/Lessons/"+mLessonNumber);

        Firebase attendanceRef = firebase.child("mAttendants").child(SharedPref.getUserId());
        FirebaseAttendant attendant = new FirebaseAttendant("blat1", "blat2");//(SharedPref.getProfileImageURL(), SharedPref.getDisplayName());
        attendanceRef.setValue(attendant);

        finish();
    }
}
