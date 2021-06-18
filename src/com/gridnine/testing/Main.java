package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightCheck.doFilter(flights, FlightCheck.SortingType.ARRIVAL_BEFORE_DEPARTURE);
        FlightCheck.doFilter(flights, FlightCheck.SortingType.DEPARTURE_BEFORE_NOW);
        FlightCheck.doFilter(flights, FlightCheck.SortingType.MORE_THAN_2_HOURS_PARKING);
    }
}
