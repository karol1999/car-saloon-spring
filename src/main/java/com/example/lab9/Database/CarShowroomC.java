package com.example.lab9.Database;

import com.example.lab9.Exceptions.ExceptionBadRequestException;
import com.example.lab9.Exceptions.ExceptionResourceNotFoundException;
import com.example.lab9.ModelApp.CarShowroom;
import com.example.lab9.ModelApp.ShowroomCarRating;
import com.example.lab9.ModelApp.Vehicle;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository // jest to potrzebne do oznaczenia klasy, że zapewnia mechanizm do składowania
            // odbierania, aktualizacji oraz usuwania operacji na obiektach
public class CarShowroomC
{
    private Map<String,CarShowroom> carSaloons;

    public CarShowroomC()
    {
        carSaloons = new HashMap<>();
    }

    public void addCenterShowrooms(String newName, CarShowroom newCarShowroom) { carSaloons.put(newName,newCarShowroom);
    }


    public List<CarShowroom> findEmptySpace(){ List<CarShowroom> carSaloonsList = new ArrayList<>();
        for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
            if (entry.getValue().isEmpty()){
                carSaloonsList.add(entry.getValue());
            }
        }
        return carSaloonsList;
    }

    public void totalSummary(){
        for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) { System.out.println("Nazwa salonu: " + entry.getKey() + " Procentowe zapełninie: " +
                    entry.getValue().getPercentageFill() + " %");
        }
    }

    public String [] arrayOfParticularShowrooms(){ String [] array = new String[carSaloons.size()];
        int counter = 0;
        for (Map.Entry<String,CarShowroom> entry : carSaloons.entrySet()){
            array[counter]=entry.getKey();
            counter++;
        }
        return array;
    }

    public String [] arrayOfSortedShowrooms(){ int counter = 0;
        String [] array=new String[carSaloons.size()];
        ArrayList<CarShowroom> valueList = new ArrayList<>(carSaloons.values());
        Collections.sort(valueList, new Comparator<CarShowroom>() {
            @Override
            public int compare(CarShowroom carShowroom1, CarShowroom carShowroom2) {
                return Double.compare(carShowroom2.getPercentageFill(),carShowroom1.getPercentageFill());
            }
        });
        for (CarShowroom showroom : valueList) {
            array[counter]=showroom.Name();
            counter++;
        }
        return array;
    }


    public CarShowroom onSelectedShowroom(String newName){ for (Map.Entry<String,CarShowroom> entry : carSaloons.entrySet()){
            if (entry.getKey().equals(newName)){
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean insertCar(Vehicle newVehicle) throws ExceptionBadRequestException { try {
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                if (entry.getKey().equals(newVehicle.getBrand())) {
                    entry.getValue().addProduct(newVehicle);
                    return true;
                }
            }
            return false;
        }catch (Exception exception){
            throw new ExceptionBadRequestException("Nieprawidlowe zadanie");
        }
    }

    public boolean insertShowroomOfCars(CarShowroom newCarShowroom) throws ExceptionBadRequestException {
        try {
            if (!carSaloons.containsKey(newCarShowroom.Name())) {
                carSaloons.put(newCarShowroom.Name(), newCarShowroom);
                return true;
            }
            return false;
        }catch (Exception exception){
            throw new ExceptionBadRequestException("Nieprawidlowe zadanie");
        }
    }

    public boolean deleteVehicle(int ID)throws ExceptionResourceNotFoundException { try {
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                List<Vehicle> list = entry.getValue().CarList();
                for (Vehicle vehicle : list) {
                    if (vehicle.getId() == ID) {
                        entry.getValue().removeProduct(vehicle);
                        return true;
                    }
                }
            }
        return false;
        }catch (Exception exception){
            throw new ExceptionResourceNotFoundException("Nie znaleziono pojazdu");

        }
    }

    public boolean carsToCsv() throws ExceptionResourceNotFoundException { try {
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                List<Vehicle> vehicleList = entry.getValue().CarList();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Vehicles"), "UTF-8"));
                write(bufferedWriter, vehicleList);
            }
            return true;
        }catch (Exception exception){
            throw new ExceptionResourceNotFoundException("Nie znaleziono pojazdu");
        }
    }

    public void write(BufferedWriter bufferedWriter, List<Vehicle> newList) throws IOException {
        String CSV_SEPARATOR = ";";
        for (Vehicle vehicle : newList) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(vehicle.getId() < 0 ? "" : vehicle.getId());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getBrand().trim().length() == 0 ? "" : vehicle.getBrand());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getModel().trim().length() == 0 ? "" : vehicle.getModel());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getPrice() < 0 ? "" : vehicle.getPrice());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getYearOfProduction() < 0 ? "" : vehicle.getYearOfProduction());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getMileage() < 0 ? "" : vehicle.getMileage());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getEngineCapacity() < 0 ? "" : vehicle.getEngineCapacity());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(vehicle.getAmount() < 0 ? "" : vehicle.getAmount());
            bufferedWriter.write(oneLine.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public List<CarShowroom> getAllShowrooms()throws ExceptionResourceNotFoundException {
        List<CarShowroom> newList = new ArrayList<>();
        for (Map.Entry<String,CarShowroom> entry : carSaloons.entrySet()){
            newList.add(entry.getValue());
        }
        return newList;
    }

    public boolean removeShowroom(int ID) throws ExceptionResourceNotFoundException {
        try {
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                if (entry.getValue().getId() == ID) {
                    carSaloons.remove(entry.getKey(), entry.getValue());
                    return true;
                }
            }
            return false;
        }catch (Exception exception){
            throw new ExceptionResourceNotFoundException("Nie znaleziono CarShowrooma");
        }
    }

    public List<Vehicle> getAllVehiclesInShowroom(int ID) throws ExceptionResourceNotFoundException { try {
            List<Vehicle> newList = new ArrayList<>();
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                if (entry.getValue().getId() == ID) {
                    newList = entry.getValue().CarList();
                }
            }
            return newList;
        }catch (Exception exception){
            throw new ExceptionResourceNotFoundException("Nie znaleziono CarShowrooma");
        }
    }

    public double getShowroomFill(int ID)throws ExceptionResourceNotFoundException {
        try {
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                if (entry.getValue().getId() == ID) {
                    return entry.getValue().getPercentageFill();
                }
            }
            return 0.0;
        }catch (Exception exception){
            throw new ExceptionResourceNotFoundException("Nie znaleziono CarShowrooma");
        }
    }

    public boolean addRatingToShowroom(int ID, ShowroomCarRating newShowroomCarRating) throws ExceptionBadRequestException {
        try {
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                if (entry.getValue().getId() == ID) {
                    entry.getValue().Ratings().add(newShowroomCarRating);
                    return true;
                }
            }
            return false;
        }catch (Exception exception){
            throw new ExceptionBadRequestException("Nieprawidlowe zadanie");
        }
    }

    public double getRating(int ID)throws ExceptionResourceNotFoundException { try {
            List<ShowroomCarRating> newList = new ArrayList<>();
            double wynik = 0.0;
            for (Map.Entry<String, CarShowroom> entry : carSaloons.entrySet()) {
                if (entry.getValue().getId() == ID) {
                    newList = entry.getValue().Ratings();
                }
            }
            for (ShowroomCarRating showroomCarRating : newList) {
                wynik += showroomCarRating.getScore();
            }
            return wynik / newList.size();
        }catch (Exception exception){
            throw new ExceptionResourceNotFoundException("Nie znaleziono CarShowrooma");
        }
    }
}
