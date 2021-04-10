package com.example.lab9.ModelApp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

public class Vehicle implements Comparable<Vehicle>{
    private int id;
    private String brand;
    private String model;
    private ItemCondition condition;
    private double price;
    private int yearOfProduction;
    private double mileage;
    private double engineCapacity;
    private int amount;

    public Vehicle(@JsonProperty("brand") String brand,@JsonProperty("model") String model,@JsonProperty("condition") String condition,
                   @JsonProperty("price") double price,@JsonProperty("year") int yearOfProduction,@JsonProperty("mileage") double mileage,
                   @JsonProperty("capacity") double engineCapacity,@JsonProperty("amount") int amount) {
        Random random= new Random();
        this.id=random.nextInt(1000);
        this.brand = brand;
        this.model = model;
        this.condition = ItemCondition.valueOf(condition.toUpperCase());
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.amount = amount;
    }

    public void print(){
        System.out.println("Brand: "+brand+" Model: "+model+" Condition: "+condition+" Price: "+price+" Year of production: "+
                yearOfProduction+" Mileage: "+mileage+" Engine capacity: "+engineCapacity+" Amount: "+amount);
    }

    @Override
    public int compareTo(Vehicle o) {
        if (this.brand.equals(o.brand)){
            return this.model.compareTo(o.model);
        }
        return this.brand.compareTo(o.brand);
    }

    public int adjustAmountOfCars(int amount){
        return this.amount+=amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", condition=" + condition +
                ", price=" + price +
                ", yearOfProduction=" + yearOfProduction +
                ", mileage=" + mileage +
                ", engineCapacity=" + engineCapacity +
                ", amount=" + amount +
                '}';
    }

    public ItemCondition getCondition() {
        return condition;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Vehicle vehicle = (Vehicle) object;
            if (this.brand.equals(vehicle.getBrand()) && this.model.equals(vehicle.getModel())   && this.engineCapacity == vehicle.getEngineCapacity()
                    && this.mileage==vehicle.getMileage() && this.yearOfProduction== vehicle.getYearOfProduction() && this.price==vehicle.getPrice()
                    && this.condition.equals(vehicle.getCondition())) {
                result = true;
            }
        }
        return result;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public double getMileage() {
        return mileage;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
