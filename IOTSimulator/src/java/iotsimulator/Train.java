/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsimulator;

/**
 *
 * @author heymeowcat
 */
public class Train {
    String trainid;
    double latitude;
    double longitude;
    double speed;
    double temperature;
    double humidity;

    public Train() {
    }

    public Train(String trainid, double latitude, double longitude, double speed, double temperature, double humidity) {
        this.trainid = trainid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    
    
    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    
}
