package rbekyarov.car_dealership.repository;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Car;
import rbekyarov.car_dealership.models.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("select c.priceSale from Car as c where c.id=:id")
    BigDecimal calculatePriceOnCars(@Param("id") Long id);

    @Transactional
    @Modifying
//    @Query("update Car as c SET " +
//            "c.name=:name,c.vendorPurchase.id=:vendorId,c.model.id=:modelId," +
//            "c.vinNumber=:vinNumber,c.conditionCar=:conditionCar,c.category=:category," +
//            "c.power=:power,c.cubature=:cubature,c.fuelType=:fuelType,c.doorCount=:doorCount," +
//            "c.regDate=:regDate,c.transmision=:transmision,c.airbags=:airbags,c.alarm=:alarm," +
//            "c.autoStartStop=:autoStartStop,c.climatic=:climatic,c.color=:color,c.comments=:comments," +
//            "c.elMirrors=:elMirrors,c.elWindows=:elWindows,c.eurostandard=:eurostandard,c.halogenHeadlights=:halogenHeadlights," +
//            "c.leatherSalon=:leatherSalon,c.metallic=:metallic,c.navigation=:navigation,c.parktronik=:parktronik," +
//            "c.serviceBook=:serviceBook,c.statusAvailable=:statusAvailable,c.dateIncome=:dateIncome,c.author.id=:editAuthorId," +
//            "c.dateCreate=:dateEdit where c.id=:id")
    @Query("update Car as c SET c.name=:name,c.vendorPurchase.id=:vendorId,c.model.id=:modelId,c.vinNumber=:vinNumber,c.conditionCar=:conditionCar,c.category=:category,c.horsepower=:horsepower, c.cubature=:cubature,c.fuelType=:fuelType,c.doorCount=:doorCount,c.regDate=:regDate,c.transmision=:transmision,c.airbags=:airbags,c.alarm=:alarm,c.autoStartStop=:autoStartStop,c.climatic=:climatic,c.color=:color,c.comments=:comments,c.elMirrors=:elMirrors,c.elWindows=:elWindows,c.eurostandard=:eurostandard,c.halogenHeadlights=:halogenHeadlights,c.leatherSalon=:leatherSalon,c.metallic=:metallic,c.navigation=:navigation,c.parktronik=:parktronik,c.serviceBook=:serviceBook,c.statusAvailable=:statusAvailable,c.datePurchase=:datePurchase,c.dateIncome=:dateIncome,c.author.id=:editAuthorId,c.dateCreate=:dateEdit,c.pricePurchase=:pricePurchase,c.priceSale=:priceSale,c.priceSaleMin=:priceSaleMin where c.id=:id ")
    void editCar(@Param("name") String name,
            @Param("vendorId") Long vendorId,
            @Param("modelId") Long modelId,
            @Param("vinNumber") String vinNumber,
            @Param("conditionCar") ConditionCar conditionCar,
            @Param("category") Category category,
            @Param("horsepower") BigDecimal horsepower,
            @Param("cubature") BigDecimal cubature,
            @Param("fuelType") FuelType fuelType,
            @Param("doorCount") DoorCount doorCount,
            @Param("regDate") LocalDate regDate,
            @Param("transmision") Transmision transmision,
            @Param("airbags") Airbags airbags,
            @Param("alarm") Alarm alarm,
            @Param("autoStartStop") AutoStartStop autoStartStop,
            @Param("climatic") Climatic climatic,
            @Param("color") Color color,
            @Param("comments") String comments,
            @Param("elMirrors") ElMirrors elMirrors,
            @Param("elWindows") ElWindows elWindows,
            @Param("eurostandard") Eurostandard eurostandard,
            @Param("halogenHeadlights") HalogenHeadlights halogenHeadlights,
            @Param("leatherSalon") LeatherSalon leatherSalon,
            @Param("metallic") Metallic metallic,
            @Param("navigation") Navigation navigation,
            @Param("parktronik") Parktronik parktronik,
            @Param("serviceBook") ServiceBook serviceBook,
            @Param("statusAvailable") StatusAvailable statusAvailable,
            @Param("datePurchase") LocalDate datePurchase,
            @Param("dateIncome") LocalDate dateIncome,
            @Param("editAuthorId") Long editAuthorId,
            @Param("dateEdit") LocalDate dateEdit,
            @Param("pricePurchase") BigDecimal pricePurchase,
            @Param("priceSale") BigDecimal priceSale,
            @Param("priceSaleMin") BigDecimal priceSaleMin,
            @Param("id") Long id);
}
