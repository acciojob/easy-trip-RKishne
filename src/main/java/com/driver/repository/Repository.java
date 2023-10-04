package com.driver.repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;

import java.util.*;

@org.springframework.stereotype.Repository
public class Repository {
    Map<String,Airport> airportDb=new HashMap<>();
    Map<Integer, Flight> fligthDb=new HashMap<>();
    Map<Integer, Passenger> passengerDb=new HashMap<>();

    Map<Integer, Integer> flightPassengerDb=new HashMap<>();
    public void addAirport(Airport airport) {
        airportDb.put(airport.getAirportName(),airport);
    }

    public String getLargetAirportName() {
        String ans="";
        int max=0;

        for(Airport airport:airportDb.values()){
            if(max<=airport.getNoOfTerminals()){
                max=airport.getNoOfTerminals();
                ans=airport.getAirportName();
            }
        }
        return ans;
    }

    public List<Flight> getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        List<Flight> listOfFlight=new ArrayList<>();
        for(Flight flight:fligthDb.values()){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                listOfFlight.add(flight);
            }
        }

        return listOfFlight;
    }

    public List<Flight> getNumberOfPeopleOn(Date date, String airportName) {

        List<Flight> ans=new ArrayList<>();
        for(Flight flight:fligthDb.values()) {
            if (flight.getFlightDate() == date ) {
                for(Airport airport:airportDb.values()){
                    if(flight.getFromCity().equals(airport.getCity())){
                        ans.add(flight);
                    }
                }
            }
        }
        return ans;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {


        if(fligthDb.get(flightId).getMaxCapacity()<flightPassengerDb.size()){
            return "FAILURE";
        }
        for(Map.Entry<Integer,Integer> entry:flightPassengerDb.entrySet()){
            Integer key= entry.getKey();
            Integer passId=entry.getValue();
            if( flightId == key && passId == passengerId){
                return "FAILURE";
            }
        }
        flightPassengerDb.put(flightId,passengerId);
        return "SUCCESS";
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        for(Map.Entry<Integer,Integer> entry: flightPassengerDb.entrySet()){
            Integer key=entry.getKey();
            Integer value= entry.getValue();
            if(fligthDb.get(key).getFlightId()==flightId && passengerDb.get(value).getPassengerId()==passengerId){
                flightPassengerDb.remove(flightId);
                return "SUCCESS";
            }
        }
        return "FAILURE";
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        int count=0;
        for(Integer passid :flightPassengerDb.values()){
            if(passid==passengerId){
                count++;
            }
        }
        return count;
    }

    public String addFlight(Flight flight) {

        fligthDb.put(flight.getFlightId(),flight);
        return "SUCCESS";
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        String ans="";
        for(Flight flight:fligthDb.values()){
            if(flight.getFlightId()==flightId){
                for(Airport airport:airportDb.values()){
                    if(airport.getCity().equals(flight.getFromCity())){
                        ans=airport.getAirportName();
                        return ans;
                    }
                }
            }
        }
        return null;
    }

    public String addPassenger(Passenger passenger) {
        passengerDb.put(passenger.getPassengerId(),passenger);
        return "SUCCESS";
    }

    public int calculateFlightFare(Integer flightId) {
        int count=0;
        int fare;
        for(Integer Id:flightPassengerDb.keySet()){
            if(Id==flightId){
                count++;
            }
        }
        if(count==0){
            return 3000;
        }
        if(count==3){
            fare= 3000+2*50;
        }
        else{
            fare=3000+count*50;
        }
        return fare;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int fare=0;
        for(Integer flightid :flightPassengerDb.keySet()){
            if(flightId==flightid){
                fare+=3000;
            }
        }
        return fare;
    }
}
