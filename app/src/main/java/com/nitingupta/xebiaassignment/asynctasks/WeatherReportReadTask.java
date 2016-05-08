package com.nitingupta.xebiaassignment.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.nitingupta.xebiaassignment.util.HttpHelper;
import com.nitingupta.xebiaassignment.interfaces.WeatherResultsLoaded;

public class WeatherReportReadTask extends AsyncTask<Object, Integer, String> {
    String weatherReportData = null;
    WeatherResultsLoaded weatherResultsLoaded;
    Context mContext;
    public WeatherReportReadTask(Context context, WeatherResultsLoaded resultLoaded){
        this.weatherResultsLoaded = resultLoaded;
        this.mContext = context;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Object... inputObj) {
        try {
            String weatherUrl = (String) inputObj[0];
            HttpHelper http = new HttpHelper();
            weatherReportData = http.read(weatherUrl);
        } catch (Exception e) {
        }
        return weatherReportData;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result==null){
            weatherResultsLoaded.onResultLoaded(null);
        }
        else{
            ResultsProcessTask resultProcessTask = new ResultsProcessTask(mContext, weatherResultsLoaded);
            Object[] toPass = new Object[1];
            toPass[0] = result;
            resultProcessTask.execute(toPass);
        }

    }

}