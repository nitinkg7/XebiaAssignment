package com.nitingupta.xebiaassignment.interfaces;

import com.nitingupta.xebiaassignment.models.WeatherResultModel;

/**
 * Created by nitin on 05/08/16.
 */
public interface WeatherResultsLoaded {
    void onResultLoaded(WeatherResultModel weatherResultModel);
}

