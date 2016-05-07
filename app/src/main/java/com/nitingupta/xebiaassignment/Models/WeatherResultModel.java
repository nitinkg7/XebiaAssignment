package com.nitingupta.xebiaassignment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nitin on 5/8/16.
 */
public class WeatherResultModel {
    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public Double getMessage() {
        return message;
    }

    public Long getCount() {
        return count;
    }

    public List<DayWiseWeatherModel> getDayWiseWeatherModelList() {
        return dayWiseWeatherModelList;
    }

    @SerializedName("city")
    City city;
    public class City{
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public String getCountry() {
            return country;
        }

        public Long getPopulation() {
            return population;
        }

        @SerializedName("id")
        Long id;
        @SerializedName("name")
        String name;
        @SerializedName("coord")
        Coordinate coordinate;
        class Coordinate{
            public Double getLon() {
                return lon;
            }

            public Double getLat() {
                return lat;
            }

            @SerializedName("lon")
            Double lon;
            @SerializedName("lat")
            Double lat;
        }
        @SerializedName("country")
        String country;
        @SerializedName("population")
        Long population;
    }
    @SerializedName("cod")
    String cod;
    @SerializedName("message")
    Double message;
    @SerializedName("cnt")
    Long count;
    @SerializedName("list")
    List<DayWiseWeatherModel> dayWiseWeatherModelList;

    public class DayWiseWeatherModel{
        public Long getDate() {
            return date;
        }

        public Temperatutre getTemperatutre() {
            return temperatutre;
        }

        public Double getPressure() {
            return pressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public List<Weather> getWeatherList() {
            return weatherList;
        }

        public Double getSpeed() {
            return speed;
        }

        public Double getDegree() {
            return degree;
        }

        public Long getClouds() {
            return clouds;
        }

        public Double getRain() {
            return rain;
        }

        @SerializedName("dt")
        Long date;
        @SerializedName("temp")
        Temperatutre temperatutre;
        public class Temperatutre{
            public Double getDay() {
                return day;
            }

            public Double getMin() {
                return min;
            }

            public Double getMax() {
                return max;
            }

            public Double getNight() {
                return night;
            }

            public Double getEvening() {
                return evening;
            }

            public Double getMorning() {
                return morning;
            }

            @SerializedName("day")
            Double day;
            @SerializedName("min")
            Double min;
            @SerializedName("max")
            Double max;
            @SerializedName("night")
            Double night;
            @SerializedName("eve")
            Double evening;
            @SerializedName("morn")
            Double morning;
        }
        @SerializedName("pressure")
        Double pressure;
        @SerializedName("humidity")
        Integer humidity;
        @SerializedName("weather")
        List<Weather> weatherList;
        public class Weather{
            public Long getId() {
                return id;
            }

            public String getMain() {
                return main;
            }

            public String getDescription() {
                return description;
            }

            public String getIcon() {
                return icon;
            }

            @SerializedName("id")
            Long id;
            @SerializedName("main")
            String main;
            @SerializedName("description")
            String description;
            @SerializedName("icon")
            String icon;
        }
        @SerializedName("speed")
        Double speed;
        @SerializedName("deg")
        Double degree;
        @SerializedName("clouds")
        Long clouds;
        @SerializedName("rain")
        Double rain;

    }
}
