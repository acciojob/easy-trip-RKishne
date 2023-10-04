package com.driver.services;

import com.driver.repository.Repository;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
        return repository.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        return repository.getNumberOfPeopleOn(date,airportName);
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
