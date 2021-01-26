package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;


public class CarManager extends ObjectManager {

    //Single Manager instance
    private static CarManager instance;


    //Tracks carTypes (unique carType is defined by its carOEMName + model + buildYear)
    private static HashMap<String, CarType> carTypes = new HashMap<>();

    //Tracks concrete cars
    private static HashMap<Integer, Car> cars = new HashMap<>();


    /**
     *
     * @methodtype constructor
     * @return CarManager
     */
    private CarManager() {
        carTypes = new HashMap<>();
        cars = new HashMap<>();
        instance=this;

        carTypes.put("default", new CarType("defaultOEM", "defaultModel", 2020, this));
    }

    /**
     * Creates CarManagerinstance or returns it if already present
     * @methodtype mutation
     * @return CarManager
     */
    public static final CarManager getInstance() {
        /**
         * TRACING OF PHOTO & CAR INSTANTIATION
         * Schritt5: returnt CarManager-Singleton
         */

        if (instance == null) {
            return new CarManager();
        }
        return instance;
    }

    /**
     * Creates Carinstance and safes it for management reasons
     * @methodtype mutation
     * @return Car
     */
    public Car createCar(String carOEMName, String model, int buildYear) {
        /**
         * TRACING OF PHOTO & CAR INSTANTIATION
         * Schritt6: überprüft, ob CarType existiert und legt diesen ggf. neu, delegiert weiter an CarType zur Erzeugung des Cars, speichert das Car in der Verwaltungsdatenstruktur cars
         */


        assertIsValidArgument(carOEMName);
        assertIsValidArgument(model);
        assertIsValidBuildYear(buildYear);

        CarType ct = getOrCreateCarType(carOEMName, model, buildYear);
        Car result = ct.createInstance(this);
        cars.put(result.hashCode(), result);
        return result;

    }

    @Override
    protected Persistent createObject(ResultSet rset) throws SQLException {
        return null;
    }

    /**
     * Generates CarType from OEMName, Model, buildyear if absent or returns it if present
     * @methodtype get
     * @return CarType
     */
    protected static CarType getOrCreateCarType(String carOEMName, String model, int buildYear) {
        staticAssertIsValidArgument(carOEMName);
        staticAssertIsValidArgument(model);
        String carType = carOEMName + model + buildYear;
        if (!carTypes.containsKey(carType)) {
            CarType ct = new CarType(carOEMName, model, buildYear, instance);
            carTypes.put(ct.getCarType(), ct);
        }
        return carTypes.get(carType);
    }

    private static void staticAssertIsValidArgument(Object o){
        if (o == null){
            throw new IllegalArgumentException("Argument is null");
        }
    }

    private void assertIsValidArgument(Object o){
        if (o == null){
            throw new IllegalArgumentException("Argument is null");
        }
    }

    private void assertIsValidBuildYear(int i){
        if (i<=0){
            throw new IllegalArgumentException("Year has to be greater than 0");
        }
    }



}
