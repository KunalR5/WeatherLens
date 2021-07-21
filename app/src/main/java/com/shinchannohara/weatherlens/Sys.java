package com.shinchannohara.weatherlens;

class Sys {

    double type;
    double id;
    double message;
    String country;
    double sunrise;
    double sunset;

    public Sys(double type, double id, double message, String country, double sunrise, double sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public double getType() {
        return type;
    }

    public double getId() {
        return id;
    }

    public double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public double getSunrise() {
        return sunrise;
    }

    public double getSunset() {
        return sunset;
    }
}
