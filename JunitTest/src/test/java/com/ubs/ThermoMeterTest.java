package com.ubs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ThermoMeterTest {

	@Mock
	Sensor sensor;

	@InjectMocks
	ThermoMeter thermoMeter;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testThermometer() {

		doReturn(true).when(sensor).isBlocked();

		assertEquals(-1, thermoMeter.getTemprature(), "When sensor is blocked, thermometer is not returning -1");

	}

	@Test
	public void testThermometerCount() {
		ThermoMeter thermo = mock(ThermoMeter.class);
		thermo.getTemprature();
		verify(thermo, times(1)).getTemprature();
	}

	@Test
	public void dependencyInversionTest() {
		Sensor sensor = Mockito.mock(Sensor.class);
		when(sensor.isBlocked()).thenReturn(true);

		ThermoMeter thermo = new ThermoMeter(sensor);
		assertEquals(-1, thermo.getTemprature(), "When sensor is blocked thermometer is not returning correct value");
	}

}
