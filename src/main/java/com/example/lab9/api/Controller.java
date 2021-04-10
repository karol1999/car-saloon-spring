package com.example.lab9.api;

import com.example.lab9.Exceptions.ExceptionBadRequestException;
import com.example.lab9.Exceptions.ExceptionResourceNotFoundException;
import com.example.lab9.ModelApp.CarShowroom;
import com.example.lab9.ModelApp.ShowroomCarRating;
import com.example.lab9.ModelApp.Vehicle;
import com.example.lab9.ServiceApp.CarShowroomS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api") // mapuje żądanie HTTP do metody obsługującej MVC tudzież REST
@RestController // tworzenie Restowych kontrolerów
public class Controller {

    private final CarShowroomS carShowroomS;

    @Autowired
    public Controller(CarShowroomS newCarShowroomS)
    {
        carShowroomS = newCarShowroomS;
    }

    @PostMapping("/product")
    public ResponseEntity<Map<String ,Boolean>> addCars(@RequestBody Vehicle newVehicle) {
        boolean success = carShowroomS.addParticularVehicle(newVehicle);
        if(!success) {
            throw new ExceptionBadRequestException("Nieprawidlowe zadanie");
        }
        else
            {
            Map<String,Boolean> map = new HashMap<>();
            map.put("Powodzenie: ", success);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCars(@PathVariable("id") int ID)
    {
        boolean success = carShowroomS.deleteParticularVehicle(ID);
        if(!success) throw new ExceptionResourceNotFoundException("Nie mozna usunac samochodu");
        else {
            Map<String,Boolean> map = new HashMap<>();
            map.put("Powodzenie: ", success);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/product/csv")
    public ResponseEntity<Map<String,Boolean>> writeVehiclesToCSV() throws IOException {
        boolean success = carShowroomS.vehiclesToCsv();
        if(!success) throw new ExceptionResourceNotFoundException("Nie mozna zapisac samochodu");
        else
        {
            Map<String,Boolean> map = new HashMap<>();
            map.put("Powodzenie: ", success);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }


    @GetMapping("/fulfillment")
    public ResponseEntity<List<CarShowroom>> returnAllShowrooms(){
        List<CarShowroom> carShowroomList = carShowroomS.returnAllShowrooms();
        return new ResponseEntity<>(carShowroomList,HttpStatus.OK);
    }

    @PostMapping("/fulfillment")
    public ResponseEntity<Map<String,Boolean>> addParticularShowroom(@RequestBody CarShowroom newCarShowroom){
        boolean success = carShowroomS.addParticularShowroom(newCarShowroom);
        if(!success) throw new ExceptionBadRequestException("Nieprawidlowe zapytanie");
        else
        {
            Map<String,Boolean> map = new HashMap<>();
            map.put("Powodzenie: ", success);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/fulfillment/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteParticularShowroom(@PathVariable("id")int ID){
        boolean success = carShowroomS.deleteParticularShowroom(ID);
        if(!success) throw new ExceptionResourceNotFoundException("Nie mozna usunac");
        else
        {
            Map<String,Boolean> map = new HashMap<>();
            map.put("Powodzenie: ", success);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/fulfillment/{id}/products")
    public ResponseEntity<List<Vehicle>> returnAllVehiclesInShowroom(@PathVariable("id") int ID){

        List<Vehicle> carShowroomList= carShowroomS.returnAllVehiclesInShowroom(ID);
        return new ResponseEntity<>(carShowroomList,HttpStatus.OK);
    }

    @GetMapping("/fulfillment/{id}/fill")
    public ResponseEntity<Map<String,Double>> getShowroomFillage(@PathVariable("id") int ID){
        Map<String,Double> map = new HashMap<>();
        map.put("Wypelnienie CarShowrooma: ", carShowroomS.returnShowroomFill(ID));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/rating/{id}")
    public ResponseEntity<Map<String,Boolean>> addRatingToParticularShowroom(@PathVariable("id") int ID, @RequestBody ShowroomCarRating newShowroomCarRating){
        boolean success = carShowroomS.addRatingToParticularShowroom(ID, newShowroomCarRating);
        if(!success) throw new ExceptionBadRequestException("Nieprawidlowe zapytanie");
        else
        {
            Map<String,Boolean> map = new HashMap<>();
            map.put("Powodzenie: ", success);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
    }

    @GetMapping("/product/{id}/rating") // po prostu zwroci NaN albo 0
    public ResponseEntity<Map<String,Double>> getParticularRating(@PathVariable("id")int ID){
            Map<String,Double> map = new HashMap<>();
            map.put("Ocena CarShowrooma: ", carShowroomS.returnRating(ID));
            return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
