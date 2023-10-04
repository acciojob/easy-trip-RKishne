package com.driver.repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        double ans=0;
        for(Flight flight:fligthDb.values()){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                if(ans>flight.getDuration()){
                    ans=flight.getDuration();
                }
            }
        }
        if(ans==0){
            return -1;
        }
        return ans;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int count=0;
        for(Flight flight:fligthDb.values()){
            if(flight.getFlightDate()==date){
                if(airportDb.get(airportName).equals(airportName)){
                    count++;
                }
            }
        }
        return count;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {


        if(fligthDb.get(flightId).getMaxCapacity()==flightPassengerDb.size()){
            return "FAILURE";
        }
        for(Integer passId:flightPassengerDb.values()){
            if(passId==passengerId){
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
        for(Flight flight1:fligthDb.values()){
            if(flight1.getFlightId()==flight.getFlightId()){
                return "flight already present!!";
            }
        }
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
        int fare=0;
        for(Integer Id:flightPassengerDb.keySet()){
            if(Id==flightId){
                count++;
            }
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
        int fare=calculateFlightFare(flightId);
        return fare;
    }
}
