package com.car.crud.controller;

import com.car.crud.model.Car;
import com.car.crud.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @GetMapping
    public List<Car> all(){
        return  carRepository.findAll();
    }
@GetMapping("/{id}")
    public Car findybyId(
            @PathVariable Integer id
){
        if(!carRepository.existsById(id)){
       return new Car();
       }
        return carRepository.findById(id).orElse( new Car());
}

@PostMapping
    public Car crea (
            @RequestBody Car c
){    return carRepository.save(c);
    }
    @PatchMapping("{id}")
    public Car pachCar(
            @PathVariable Integer id,
            @RequestParam String type
    ){
        Optional<Car> car = carRepository.findById(id);
        if(!carRepository.existsById(id)){
            return  new Car();
        }
        Car c = car.get();
        c.setType(type);
        return carRepository.save(c);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteAll(){
        carRepository.deleteAll();
        return ResponseEntity.ok("All Car deleted");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable Integer id
    ){if (!carRepository.existsById(id)){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Car not found");
    }
        carRepository.deleteById(id);
        return ResponseEntity.ok(" Car "+id+" deleted");
    }
}
