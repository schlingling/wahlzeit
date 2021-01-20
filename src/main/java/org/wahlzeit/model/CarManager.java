package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;


public class CarManager extends ObjectManager {

    private static CarManager instance;


    //Maps carTypes (type is carOEMName + model + buildYear)
    private static HashMap<String, CarType> carTypes = new HashMap<>();

    //Maps concrete cars
    private static HashMap<Integer, Car> cars = new HashMap<>();


    private CarManager() {
        carTypes = new HashMap<>();
        cars = new HashMap<>();
        instance=this;

        carTypes.put("default", new CarType("defaultOEM", "defaultModel", 2020, this));
    }

    public static final CarManager getInstance() {
        if (instance == null) {
            return new CarManager();
        }
        return instance;
    }


    public Car createCar(String carOEMName, String model, int buildYear) {
        //TODO assert
        CarType ct = getOrCreateCarType(carOEMName, model, buildYear);
        Car result = ct.createInstance(this);
        cars.put(result.hashCode(), result);
        return result;

    }

    @Override
    protected Persistent createObject(ResultSet rset) throws SQLException {
        return null;
    }

    protected static CarType getOrCreateCarType(String carOEMName, String model, int buildYear) {
        String carType = carOEMName + model + buildYear;
        if (!carTypes.containsKey(carType)) {
            CarType ct = new CarType(carOEMName, model, buildYear, instance);
            carTypes.put(ct.getCarType(), ct);
        }
        return carTypes.get(carType);
    }

}
