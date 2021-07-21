package com.shinchannohara.weatherlens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "7ac7448e9debee6546cba3e494e42421";
    ImageView weathericon;
    TextView temp,weatherdesc,cityname;

    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    RelativeLayout cityfinder;

    LocationManager locationManager;
    LocationListener locationListener;

    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weathericon = (ImageView)findViewById(R.id.weathericon);
        temp = (TextView)findViewById(R.id.temperature);
        weatherdesc = (TextView)findViewById(R.id.weathercondition);
        cityname = (TextView)findViewById(R.id.cityname);
        cityfinder = (RelativeLayout)findViewById(R.id.cityfinder);

        cityfinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, city_finder.class));
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Intent intent = getIntent();
        String city = intent.getStringExtra("City");
        if (city != null)
        {
            getweatherfornewcity(city);
        }
        else
        {
            getweatherforcurrention();
        }
    }

    private void getweatherfornewcity(String city)
    {
        Call<weatherdata> call = api.getWeather(city,API_KEY);

        call.enqueue(new Callback<weatherdata>() {
            @Override
            public void onResponse(Call<weatherdata> call, Response<weatherdata> response) {
                if(response.isSuccessful()){
                    String name = response.body().getName();
                    double temper = response.body().getMain().getTemp()-273.15;
                    int t = (int)Math.rint(temper);
                    String temperature = Integer.toString(t)+"°C";
                    cityname.setText(name);
                    temp.setText(temperature);
                    String weathertype = response.body().getWeather().get(0).getMain();
                    weatherdesc.setText(weathertype);
                    int condition = response.body().getWeather().get(0).getId();
                    if(condition>=0&&condition<=400)
                    {
                        weathericon.setImageResource(R.drawable.thunderstorme);
                    }
                    if(condition>400&&condition<=600)
                    {
                        weathericon.setImageResource(R.drawable.light);
                    }
                    if(condition>600&&condition<=700)
                    {
                        weathericon.setImageResource(R.drawable.snow);
                    }
                    if(condition>700&&condition<800)
                    {
                        weathericon.setImageResource(R.drawable.fog);
                    }
                    if(condition==800)
                    {
                        weathericon.setImageResource(R.drawable.sunny);
                    }
                    if(condition>=801&&condition<=804)
                    {
                        weathericon.setImageResource(R.drawable.cloudy);
                    }
                    if(condition>=900&&condition<=902)
                    {
                        weathericon.setImageResource(R.drawable.thunderstorme);
                    }
                    if(condition==903)
                    {
                        weathericon.setImageResource(R.drawable.snow);
                    }
                    if(condition==904)
                    {
                        weathericon.setImageResource(R.drawable.sunny);
                    }
                    if(condition>=905&&condition<=1000)
                    {
                        weathericon.setImageResource(R.drawable.thunderstorme);
                    }
                }
            }

            @Override
            public void onFailure(Call<weatherdata> call, Throwable t) {

            }
        });
    }

    private void getweatherforcurrention() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());

                Call<weatherdata> call = api.getWeatherNewCity(latitude,longitude,API_KEY);

                call.enqueue(new Callback<weatherdata>() {
                    @Override
                    public void onResponse(Call<weatherdata> call, Response<weatherdata> response) {
                        if(response.isSuccessful()){
                            String name = response.body().getName();
                            double temper = response.body().getMain().getTemp()-273.15;
                            int t = (int)Math.rint(temper);
                            String temperature = Integer.toString(t)+"°C";
                            cityname.setText(name);
                            temp.setText(temperature);
                            String weathertype = response.body().getWeather().get(0).getMain();
                            weatherdesc.setText(weathertype);
                            int condition = response.body().getWeather().get(0).getId();
                            if(condition>=0&&condition<=400)
                            {
                                weathericon.setImageResource(R.drawable.thunderstorme);
                            }
                            if(condition>400&&condition<=600)
                            {
                                weathericon.setImageResource(R.drawable.light);
                            }
                            if(condition>600&&condition<=700)
                            {
                                weathericon.setImageResource(R.drawable.snow);
                            }
                            if(condition>700&&condition<800)
                            {
                                weathericon.setImageResource(R.drawable.fog);
                            }
                            if(condition==800)
                            {
                                weathericon.setImageResource(R.drawable.sunny);
                            }
                            if(condition>=801&&condition<=804)
                            {
                                weathericon.setImageResource(R.drawable.cloudy);
                            }
                            if(condition>=900&&condition<=902)
                            {
                                weathericon.setImageResource(R.drawable.thunderstorme);
                            }
                            if(condition==903)
                            {
                                weathericon.setImageResource(R.drawable.snow);
                            }
                            if(condition==904)
                            {
                                weathericon.setImageResource(R.drawable.sunny);
                            }
                            if(condition>=905&&condition<=1000)
                            {
                                weathericon.setImageResource(R.drawable.thunderstorme);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<weatherdata> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        locationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, locationListener);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getweatherforcurrention();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(locationManager!=null)
        {
            locationManager.removeUpdates(locationListener);
        }
    }
}
