package exam03;

import java.time.LocalDate;
import java.util.*;

public class Cruise {

    private Boat boat;
    private LocalDate sailing;
    private double basicPrice;
    private List<Passenger> passengers = new ArrayList<>();

    public Cruise(Boat boat, LocalDate sailing, double basicPrice) {
        this.boat = boat;
        this.sailing = sailing;
        this.basicPrice = basicPrice;
    }

    public Boat getBoat() {
        return boat;
    }

    public LocalDate getSailing() {
        return sailing;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void bookPassenger(Passenger passenger) {
        if (passengers.size() >= boat.getMaxPassengers()) {
            throw new IllegalArgumentException("Boat full");
        }
        passengers.add(passenger);
    }

    public double getPriceForPassenger(Passenger passenger) {
        return basicPrice * (passenger.getCruiseClass().getMultiplier());
    }

    public Passenger findPassengerByName(String name) {
        Passenger found = passengers.get(0);
        for (Passenger item : passengers) {
            if (item.getName().equals(name)) {
                found = item;
            }
        }
        return found;
    }

    public List<String> getPassengerNamesOrdered() {
        List<String> namesInOrder = new ArrayList<>();
        passengers.sort(Comparator.comparing(Passenger::getName));
        for (Passenger item : passengers) {
            namesInOrder.add(item.getName());

        }
        return namesInOrder;
    }

    public double sumAllBookingsCharged(){
        double income = 0.0;
        for(Passenger item: passengers){
            income += basicPrice * (item.getCruiseClass().getMultiplier());
        }
        return income;
    }

    public Map<CruiseClass,Integer> countPassengerByClass(){
        Map<CruiseClass,Integer> statistics = new HashMap<>();
        for(Passenger item : passengers){
            if (!statistics.containsKey(item.getCruiseClass())) {
                statistics.put(item.getCruiseClass(), 0);
            }
            statistics.put(item.getCruiseClass(), statistics.get(item.getCruiseClass()) + 1);
        }
        return statistics;

    }
}
