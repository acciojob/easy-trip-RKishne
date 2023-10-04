package com.driver.services;

import com.driver.repository.Repository;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    Repository repository=new Repository();

    public void addAirport(Airport airport) {
        repository.addAirport(airport);
    }

    public String getLargestAirportName() {
        return repository.getLargetAirportName();
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        List<Flight> listOfFlight =repository.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);

        if (!listOfFlight.isEmpty()) {
            // Find the shortest duration among the direct flights
            double shortestDuration = listOfFlight.stream()
                    .mapToDouble(Flight::getDuration)
                    .min()
                    .orElse(0); // Default value if no flights found, replace with an appropriate default value

            return shortestDuration;
        } else {
            // No direct flights found
            return -1;
        }
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int count = 0;
        Airport airport = repository.getNumberOfPeopleOn(date,airportName);
        if (airport == null)
            return count;

        List<Flight> list = repository.getAllFlights();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        for (Flight flight : list)
            if (fmt.format(flight.getFlightDate()).equals(fmt.format(date)) && (flight.getFromCity() == airport.getCity() || flight.getToCity() == airport.getCity()))
                count += repository.getPassengersByFlight(flight.getFlightId()).size();

        return count;

    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        return repository.bookATicket(flightId,passengerId);
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        return repository.cancelATicket(flightId,passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return repository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    public String addFlight(Flight flight) {
        return repository.addFlight(flight);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        return repository.getAirportNameFromFlightId(flightId);
    }

    public String addPassenger(Passenger passenger) {
        return repository.addPassenger(passenger);
    }

    public int calculateFlightFare(Integer flightId) {
        return repository.calculateFlightFare(flightId);
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        return repository.calculateRevenueOfAFlight(flightId);
    }
}
