package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightCheck {

    enum SortingType {
        DEPARTURE_BEFORE_NOW,
        ARRIVAL_BEFORE_DEPARTURE,
        MORE_THAN_2_HOURS_PARKING
    }

    public static List<Flight> doFilter(List<Flight> flightList, SortingType sortingType) {
        if (flightList.size() > 0) {
            switch (sortingType) {
                case DEPARTURE_BEFORE_NOW:
                    return departureBeforeNow(flightList);
                case ARRIVAL_BEFORE_DEPARTURE:
                    return arrivalBeforeDeparture(flightList);
                case MORE_THAN_2_HOURS_PARKING:
                    return moreThan2HoursParking(flightList);
            }
        }
        return null;
    }

    private static List<Flight> departureBeforeNow(List<Flight> flightsList) {
        List<Flight> resultList = flightsList.stream()
                .filter(f -> !f.getSegments().stream()
                        .anyMatch(s -> s.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
        System.out.println(resultList);
        return resultList;
    }

    private static List<Flight> arrivalBeforeDeparture(List<Flight> flightsList) {
        List<Flight> resultList = flightsList.stream()
                .filter(f -> f.getSegments().stream()
                        .noneMatch(s -> s.getArrivalDate().isBefore(s.getDepartureDate())))
                .collect(Collectors.toList());
        System.out.println(resultList);
        return resultList;
    }

    private static List<Flight> moreThan2HoursParking(List<Flight> flightsList) {
        List<Flight> resultList = new ArrayList<>();
        long parkingTime = 0;
        for (Flight f : flightsList) {
            for (int i = 0; i < f.getSegments().size() - 1; i++) {
                parkingTime += f.getSegments().get(i).getArrivalDate().until(f.getSegments().get(i + 1).getDepartureDate(), ChronoUnit.MILLIS);
            }
            if ((parkingTime) < 7200000) {
                resultList.add(f);
            }
            parkingTime = 0;
        }
        System.out.println(resultList);
        return resultList;
    }
}
