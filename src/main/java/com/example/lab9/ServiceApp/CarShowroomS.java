package com.example.lab9.ServiceApp;

import com.example.lab9.Database.CarShowroomC;
import com.example.lab9.ModelApp.CarShowroom;
import com.example.lab9.ModelApp.ShowroomCarRating;
import com.example.lab9.ModelApp.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service // używane do oznaczania logiki aplikacji na innej płaszczyźnie niż @RestController
public class CarShowroomS // Showroom
{
    private final CarShowroomC carShowroomC;

    @Autowired // może automatycznie łączyć beana na setterze tak samo jak @Require
               // konstruktor tudzież właściwość
    public CarShowroomS(CarShowroomC newCarShowroomC)
    {
        carShowroomC = newCarShowroomC;
    }

    public boolean addParticularVehicle(Vehicle newVehicle)
    {
        return carShowroomC.insertCar(newVehicle);
    }

    public boolean addParticularShowroom(CarShowroom carShowroom) {
        return carShowroomC.insertShowroomOfCars(carShowroom);
    }

    public boolean deleteParticularVehicle(int id) {
        return carShowroomC.deleteVehicle(id);
    }

    public boolean vehiclesToCsv() throws IOException {
        return carShowroomC.carsToCsv();
    }

    public List<CarShowroom> returnAllShowrooms() {
        return carShowroomC.getAllShowrooms();
    }

    public boolean deleteParticularShowroom(int id) {
        return carShowroomC.removeShowroom(id);
    }

    public List<Vehicle> returnAllVehiclesInShowroom(int id) {
        return carShowroomC.getAllVehiclesInShowroom(id);
    }

    public double returnShowroomFill(int id) {
        return carShowroomC.getShowroomFill(id);
    }

    public boolean addRatingToParticularShowroom(int id, ShowroomCarRating showroomCarRating) {
        return carShowroomC.addRatingToShowroom(id, showroomCarRating);
    }

    public double returnRating(int id) {
        return carShowroomC.getRating(id);
    }
}
