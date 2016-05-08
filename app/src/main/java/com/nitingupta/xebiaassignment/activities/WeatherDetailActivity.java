/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nitingupta.xebiaassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitingupta.xebiaassignment.models.WeatherResultModel;
import com.nitingupta.xebiaassignment.R;
import com.nitingupta.xebiaassignment.util.CommonUtil;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class WeatherDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "model";
    TextView minMax, description, mornTemp, dayTemp, eveTemp, nightTemp, pressure, humidity, speed, degree, clouds, temperature;
    LinearLayout mornLayout, dayLayout, eveLayout, nightLayout, pressureLayout, speedLayout, humidityLayout, dirLayout, cloudLayout;
    WeatherResultModel.DayWiseWeatherModel weatherModel;
    ImageView icon;
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        minMax = (TextView)findViewById(R.id.maxMin);
        icon = (ImageView) findViewById(R.id.iconDetail);
        description = (TextView) findViewById(R.id.description);
        mornTemp = (TextView) findViewById(R.id.mornTemp);
        dayTemp = (TextView)findViewById(R.id.dayTemp);
        eveTemp = (TextView)findViewById(R.id.eveTemp);
        nightTemp = (TextView)findViewById(R.id.nightTemp);
        pressure = (TextView)findViewById(R.id.pressure);
        humidity = (TextView)findViewById(R.id.humidityText);
        speed = (TextView)findViewById(R.id.speed);
        degree = (TextView)findViewById(R.id.degree);
        clouds= (TextView)findViewById(R.id.clouds);
        mornLayout = (LinearLayout)findViewById(R.id.mornLayout);
        dayLayout = (LinearLayout)findViewById(R.id.dayLayout);
        eveLayout = (LinearLayout)findViewById(R.id.eveLayout);
        nightLayout = (LinearLayout)findViewById(R.id.nightLayout);
        pressureLayout = (LinearLayout)findViewById(R.id.pressureLayout);
        speedLayout = (LinearLayout)findViewById(R.id.speedLayout);
        humidityLayout = (LinearLayout)findViewById(R.id.humidityLayout);
        dirLayout = (LinearLayout)findViewById(R.id.dirLayout);
        temperature = (TextView)findViewById(R.id.temperature);
        cloudLayout = (LinearLayout)findViewById(R.id.cloudLayout);

        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null)
            weatherModel = (WeatherResultModel.DayWiseWeatherModel) bundle.getSerializable(EXTRA_NAME);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (weatherModel!=null)
            setUI();
    }

    public void setUI() {

        if (weatherModel.getWeatherList() != null && weatherModel.getWeatherList().size() > 0) {
            if (weatherModel.getWeatherList().get(0).getIcon() != null) {
                Picasso.with(this).load(CommonUtil.IMAGE_URL + weatherModel.getWeatherList().get(0).getIcon() + ".png").fit().into(icon);
            } else
                icon.setVisibility(View.GONE);
            if (weatherModel.getWeatherList().get(0).getDescription() != null)
                description.setText(weatherModel.getWeatherList().get(0).getDescription());
            else
                description.setVisibility(View.GONE);

        }
        else{
         icon.setVisibility(View.GONE);
        description.setVisibility(View.GONE);
        }
        if (weatherModel.getHumidity() != null)
            humidity.setText(weatherModel.getHumidity() + "%");
        else{
            humidityLayout.setVisibility(View.GONE);
        }
        if (weatherModel.getDate() != null) {
            Date dateNew = new Date(weatherModel.getDate() * 1000);
            String dateString = DateFormat.format("dd/MMM/yyyy", dateNew).toString();
            Calendar now = Calendar.getInstance();
            now.setTime(dateNew);
            actionBar.setTitle(dateString + " " + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
        }else
        actionBar.setTitle("Weather Detail");
        if (weatherModel.getTemperatutre() != null) {
            minMax.setText(weatherModel.getTemperatutre().getMax() + "\u00B0" + "/" + weatherModel.getTemperatutre().getMin() + "\u00B0");
            mornTemp.setText(weatherModel.getTemperatutre().getMorning()+"\u00B0");
            dayTemp.setText(weatherModel.getTemperatutre().getDay()+"\u00B0");
            eveTemp.setText(weatherModel.getTemperatutre().getEvening() + "\u00B0");
            nightTemp.setText(weatherModel.getTemperatutre().getNight() + "\u00B0");
        } else{
          temperature.setVisibility(View.GONE);
            mornLayout.setVisibility(View.GONE);
            dayLayout.setVisibility(View.GONE);
            eveLayout.setVerticalGravity(View.GONE);
            nightLayout.setVisibility(View.GONE);
            minMax.setVisibility(View.GONE);
        }
        if (weatherModel.getPressure()!=null){
            pressure.setText(weatherModel.getPressure()+" hPa");
        }else pressureLayout.setVisibility(View.GONE);
        if (weatherModel.getSpeed()!=null){
            speed.setText(weatherModel.getSpeed()+" M/s");
        }else
        speedLayout.setVisibility(View.GONE);
        if (weatherModel.getDegree()!=null){
            degree.setText(CommonUtil.degToCompass(weatherModel.getDegree()));
        }else dirLayout.setVisibility(View.GONE);
        if (weatherModel.getClouds()!=null){
            clouds.setText(weatherModel.getClouds()+" %");
        }
        else
            cloudLayout.setVisibility(View.GONE);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                onBackPressed();
                break;
        }
        return true;
    }

}
