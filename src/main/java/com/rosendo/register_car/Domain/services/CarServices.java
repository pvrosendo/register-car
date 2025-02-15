package com.rosendo.register_car.Domain.services;

import com.rosendo.register_car.Domain.dtos.CarModelDto;
import com.rosendo.register_car.Domain.models.CarModels;
import com.rosendo.register_car.Domain.repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarServices {


    @Autowired
    private CarRepository carRepository;

    public List<CarModels> getAllCars() { return carRepository.findAll(); }

    public Optional<CarModels> getCarById(Long carId) {
        return carRepository.findById(carId);
    }

    public CarModels registerCar(CarModelDto carModelDto){
        //TODO: verification of exists model/year
        CarModels carModels = new CarModels();
        BeanUtils.copyProperties(carModelDto, carModels);
        carRepository.save(carModels);
        return carModels;
    }

    public CarModels updateCar(Long id, CarModelDto carModelDto){
        Optional<CarModels> carModel = carRepository.findById(id);
        carModel.orElseThrow().setBrand(carModelDto.brand());
        carModel.orElseThrow().setModel(carModelDto.model());
        carModel.orElseThrow().setYear(carModelDto.year());
        carModel.orElseThrow().setPrice(carModelDto.price());
        carModel.orElseThrow().setRegisterDate(carModelDto.registerDate());

        return carRepository.save(carModel.get());
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

}
