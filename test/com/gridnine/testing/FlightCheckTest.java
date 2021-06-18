package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FlightCheckTest {
    ArrayList<Flight> flightsList = new ArrayList<>(FlightBuilder.createFlights());

    @Test
    public void arrivalBeforeDepartureTest() {
        ArrayList<Flight> tempList = flightsList;
        tempList.remove(3);
        Assertions.assertEquals(tempList, FlightCheck.doFilter(flightsList, FlightCheck.SortingType.ARRIVAL_BEFORE_DEPARTURE));
    }

    @Test
    public void departureBeforeNowTest() {
        ArrayList<Flight> tempList = flightsList;
        tempList.remove(2);
        Assertions.assertEquals(tempList, FlightCheck.doFilter(flightsList, FlightCheck.SortingType.DEPARTURE_BEFORE_NOW));
    }

    @Test
    public void moreThan2HoursParkingTest() {
        ArrayList<Flight> tempList = flightsList;
        tempList.remove(5);
        tempList.remove(4);
        Assertions.assertEquals(tempList, FlightCheck.doFilter(flightsList, FlightCheck.SortingType.MORE_THAN_2_HOURS_PARKING));
    }
}
