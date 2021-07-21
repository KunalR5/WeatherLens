package com.shinchannohara.weatherlens;

class Main {

    double temp;
    double feels_like;
    double temp_min;
    double temp_max;
    double pressure;
    double humidity;

    public Main(double temp, double feels_like, double temp_min, double temp_max, double pressure, double humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }
}
