package com.nitingupta.xebiaassignment.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nitingupta.xebiaassignment.Models.WeatherResultModel;
import com.nitingupta.xebiaassignment.R;
import com.nitingupta.xebiaassignment.Util.CommonUtil;
import com.nitingupta.xebiaassignment.Util.RecyclerAdapter;
import com.nitingupta.xebiaassignment.asynctasks.WeatherReportReadTask;
import com.nitingupta.xebiaassignment.interfaces.WeatherResultsLoaded;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        getDataFromApi();


    }

    private void initUI() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Xebia Weather App");
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void getDataFromApi() {
        showProgressDialog(false, "Please wait while we set it up for you");
        loadWeatherResults();
    }
    public void loadWeatherResults() {
        WeatherReportReadTask weatherReportReadTask = new WeatherReportReadTask(this, new WeatherResultsLoaded() {
            @Override
            public void onResultLoaded(final WeatherResultModel weatherResultModel) {
                Log.d("Json Response= ", weatherResultModel.toString());
                if (weatherResultModel != null && weatherResultModel.getDayWiseWeatherModelList() != null && weatherResultModel.getDayWiseWeatherModelList().size() > 0) {
                    final List<WeatherResultModel.DayWiseWeatherModel> dayWiseWeatherModels = weatherResultModel.getDayWiseWeatherModelList();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new RecyclerAdapter(MainActivity.this, dayWiseWeatherModels, new RecyclerAdapter.ItemClickListner(){
                        @Override
                        public void itemClicked(int pos) {
                            Intent intent = new Intent(MainActivity.this, WeatherDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("model", dayWiseWeatherModels.get(pos));
                            intent.putExtra("bundle", bundle);
                            startActivity(intent);

                        }
                    }));
                }else{
                    Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "No results found", Snackbar.LENGTH_SHORT).show();
                }
                dismissProgressDialog();
            }

        });
        Object[] toPass = new Object[1];
        //toPass[0] = googleMap;
        toPass[0] = CommonUtil.API_URL;
        if (!CommonUtil.checkNetwork(this)) {
            if (MainActivity.this.findViewById(android.R.id.content) != null) {
                Snackbar snackbar = Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "Please check your network connection!", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                                finish();
                            }
                        });
                View snackView = snackbar.getView();
                snackView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red_bg));
                snackbar.show();
            }
        } else
            weatherReportReadTask.execute(toPass);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showProgressDialog(Boolean cancelable, String message) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(this, "", message
                        + "\t", true, cancelable);
            } else {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = ProgressDialog.show(this, "", message
                            + "\t", true, cancelable);
                } else {
                    mProgressDialog = ProgressDialog.show(this, "", message
                            + "\t", true, cancelable);
                }
            }
        } catch (Exception e) {
        }
    }

    public void dismissProgressDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
