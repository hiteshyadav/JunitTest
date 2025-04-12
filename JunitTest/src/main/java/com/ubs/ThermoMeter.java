package com.ubs;

public class ThermoMeter {

	Sensor sensor;
	int temprature;

	public ThermoMeter(Sensor sensor) {
		this.sensor = sensor;
	}

	public int getTemprature() {
		if (sensor.isBlocked()) {
			return -1;
		} else {
			return temprature;
		}
	}

	public void setTemprature(int temprature) {
		this.temprature = temprature;
	}

}
