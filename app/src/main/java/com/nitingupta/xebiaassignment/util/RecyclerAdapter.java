package com.nitingupta.xebiaassignment.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitingupta.xebiaassignment.models.WeatherResultModel;
import com.nitingupta.xebiaassignment.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<WeatherResultModel.DayWiseWeatherModel> weatherResultModels;
    Context mContext;
    ItemClickListner itemClickListner;

    public RecyclerAdapter(Context context, List<WeatherResultModel.DayWiseWeatherModel> weatherResultModel, ItemClickListner itemClickListner) {
        this.mContext = context;
        this.weatherResultModels = weatherResultModel;
        this.itemClickListner = itemClickListner;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh = null;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.day_item, viewGroup, false);
        vh = new DayWeatherHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, final int i) {
        final WeatherResultModel.DayWiseWeatherModel dayWiseWeatherModel = weatherResultModels.get(i);
        final DayWeatherHolder dayWeatherHolder = (DayWeatherHolder) vh;
        //reset the visibility of views
        dayWeatherHolder.description.setVisibility(View.VISIBLE);
        dayWeatherHolder.humidity.setVisibility(View.VISIBLE);
        dayWeatherHolder.date.setVisibility(View.VISIBLE);
        dayWeatherHolder.temp.setVisibility(View.VISIBLE);
        dayWeatherHolder.day.setVisibility(View.VISIBLE);
        dayWeatherHolder.icon.setVisibility(View.VISIBLE);
        dayWeatherHolder.main.setVisibility(View.VISIBLE);

        if (dayWiseWeatherModel.getWeatherList() != null && dayWiseWeatherModel.getWeatherList().size() > 0) {
            if (dayWiseWeatherModel.getWeatherList().get(0).getIcon() != null) {
                Picasso.with(mContext).load(CommonUtil.IMAGE_URL + dayWiseWeatherModel.getWeatherList().get(0).getIcon() + ".png").fit().into(dayWeatherHolder.icon);
            } else
                dayWeatherHolder.icon.setVisibility(View.GONE);
            if (dayWiseWeatherModel.getWeatherList().get(0).getDescription() != null)
                dayWeatherHolder.description.setText(dayWiseWeatherModel.getWeatherList().get(0).getDescription());
            else
                dayWeatherHolder.description.setVisibility(View.GONE);
            if (dayWiseWeatherModel.getWeatherList().get(0).getMain() != null) {
                dayWeatherHolder.main.setText(dayWiseWeatherModel.getWeatherList().get(0).getMain());
            }
        } else {
            dayWeatherHolder.main.setVisibility(View.GONE);
            dayWeatherHolder.icon.setVisibility(View.GONE);
            dayWeatherHolder.description.setVisibility(View.GONE);
        }
        if (dayWiseWeatherModel.getHumidity() != null)
            dayWeatherHolder.humidity.setText(dayWiseWeatherModel.getHumidity() + "%");
        else
            dayWeatherHolder.humidity.setVisibility(View.GONE);
        if (dayWiseWeatherModel.getDate() != null) {
            Date date = new Date(dayWiseWeatherModel.getDate() * 1000);
            String dateString = DateFormat.format("MM/dd/yy", date).toString();
            dayWeatherHolder.date.setText(dateString);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            dayWeatherHolder.day.setText(now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
        } else {
            dayWeatherHolder.date.setVisibility(View.GONE);
            dayWeatherHolder.day.setVisibility(View.GONE);
            ;
        }
        if (dayWiseWeatherModel.getTemperatutre() != null) {
            dayWeatherHolder.temp.setText(dayWiseWeatherModel.getTemperatutre().getMax() + "\u00B0" + "/" + dayWiseWeatherModel.getTemperatutre().getMin() + "\u00B0");
        } else
            dayWeatherHolder.temp.setVisibility(View.GONE);
        dayWeatherHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListner.itemClicked(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != weatherResultModels ? weatherResultModels.size() : 0);
    }

    public class DayWeatherHolder extends RecyclerView.ViewHolder {
        protected TextView description;
        protected TextView humidity;
        protected TextView temp;
        protected TextView date;
        protected TextView day;
        protected ImageView icon;
        protected TextView main;

        public DayWeatherHolder(View view) {
            super(view);
            this.description = (TextView) view.findViewById(R.id.description);
            this.humidity = (TextView) view.findViewById(R.id.humidity);
            this.temp = (TextView) view.findViewById(R.id.temp);
            this.date = (TextView) view.findViewById(R.id.date);
            this.day = (TextView) view.findViewById(R.id.day);
            this.icon = (ImageView) view.findViewById(R.id.icon);
            this.main = (TextView) view.findViewById(R.id.main);
        }
    }

    public interface ItemClickListner {
        void itemClicked(int pos);
    }
}
