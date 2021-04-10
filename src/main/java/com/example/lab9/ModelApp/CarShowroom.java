package com.example.lab9.ModelApp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class CarShowroom {
    private int id;
    private String showroomName;
    private List<Vehicle> carList;
    private List<ShowroomCarRating> showroomCarRatings;
    private int maxShowroomCapacity;

    public CarShowroom(@JsonProperty("showroom") String showroomName,@JsonProperty("capacity") int maxShowroomCapacity) {
        this.showroomName = showroomName;
        this.maxShowroomCapacity = maxShowroomCapacity;
        this.carList = new ArrayList<>();
        this.showroomCarRatings = new ArrayList<>();
        Random random = new Random();
        this.id=random.nextInt(100);
    }

    public boolean addProduct(Vehicle vehicle) {
        if (vehicle != null) {
            if (carList.size() == maxShowroomCapacity) {
                System.err.println("Pojemność magazynu została przekroczona.");
            } else if (carList.contains(vehicle)) {
                int x = carList.indexOf(vehicle);
                carList.get(x).adjustAmountOfCars(vehicle.getAmount());
                return true;
            }
            return carList.add(vehicle);
        }
        return false;
    }

    public void getProduct(Vehicle vehicle) {
        vehicle.adjustAmountOfCars(-1);
        if (vehicle.getAmount() == 0) {
            carList.remove(vehicle);
        }
    }

    public boolean removeProduct(Vehicle vehicle) {
        if (vehicle != null) {
            return carList.remove(vehicle);
        }
        return false;
    }

    public String search(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(carList);
        for (Vehicle vehicle : carList) {
            if (vehicle.getBrand().equals(name)) {
                stringBuilder.append(vehicle.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String searchPartial(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(carList);
        for (Vehicle vehicle : carList) {
            if (vehicle.getBrand().contains(text)) {
                stringBuilder.append(vehicle.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public void countByCondition(ItemCondition itemCondition) {
        int amount = 0;
        for (Vehicle vehicle : carList) {
            if (vehicle.getCondition().equals(itemCondition)) {
                amount++;
            }
        }
        System.out.println(itemCondition.name() + ": " + amount);
    }

    public void summary() {
        for (Vehicle vehicle : carList) {
            vehicle.print();
        }
    }

    public void sortByName() {
        Collections.sort(carList);
        for (Vehicle vehicle : carList) {
            System.out.println(vehicle);
        }
    }

    public void sortByAmount() {
        Collections.sort(carList, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Integer.compare(o2.getAmount(), o1.getAmount());
            }
        });
        for (Vehicle vehicle : carList) {
            System.out.println(vehicle);
        }
    }

    public Vehicle max() {
        return Collections.max(carList, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Integer.compare(o2.getAmount(), o1.getAmount());
            }
        });
    }

    public boolean isEmpty() {
        if (carList.isEmpty()) return true;
        else return false;
    }

    public double getPercentageFill() {
        double result = (double) carList.size() / maxShowroomCapacity;
        return result * 100;
    }

    public String Name() {
        return showroomName;
    }

    public List<Vehicle> CarList() {
        return carList;
    }

    public int getMaxShowroomCapacity() {
        return maxShowroomCapacity;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setCarList(List<Vehicle> carList) {
        this.carList = carList;
    }

    public void setMaxShowroomCapacity(int maxShowroomCapacity) {
        this.maxShowroomCapacity = maxShowroomCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ShowroomCarRating> Ratings() {
        return showroomCarRatings;
    }
}
