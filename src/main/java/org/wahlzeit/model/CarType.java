package org.wahlzeit.model;


import org.wahlzeit.model.location.Coordinate;
import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CarType {

    private CarManager carManager; //Every CarType know his manager
    private String carOEMName;
    private String model;
    private int buildYear;

    //Hierachy of types
    private CarType superType;
    private Set<CarType> subTypes = new HashSet<CarType>();

    public CarType(String carOEMName, String model, int buildYear, CarManager manager) {
        this.carOEMName = carOEMName;
        this.model = model;
        this.buildYear = buildYear;
        this.carManager = manager;
        assertClassInvariants();
    }

    /**
     * Creates an instance of a car
     * @methodtype mutation
     */
    public Car createInstance(CarManager manager) {
        /**
         * TRACING OF PHOTO & CAR INSTANTIATION
         * Schritt7: initilisiert Auto, indem Konstruktor der Car-Klasse genutzt wird
         */



        assertClassInvariants();
        assertIsValidArgument(manager);
        this.carManager = manager;
        return new Car(this, manager);
    }

    /**
     * @methodtype getter
     */
    public String getCarType() {
        assertClassInvariants();
        return carOEMName + model + buildYear;
    }


    /**
     * @methodtype getter
     */
    public CarManager getCarManager() {
        assertClassInvariants();
        return carManager;
    }

    /**
     * @methodtype getter
     */
    public String getCarOEMName() {
        assertClassInvariants();
        return carOEMName;
    }

    /**
     * @methodtype getter
     */
    public int getBuildYear() {
        assertClassInvariants();
        return buildYear;
    }

    /**
     * @methodtype getter
     */
    public String getModel() {
        return model;
    }

    /**
     * @methodtype getter
     */
    public CarType getSuperType() {
        assertClassInvariants();
        return superType;
    }

    /**return iterator for subtypes
     * @methodtype getter
     */
    public Iterator<CarType> getSubTypeIterator() {
        assertClassInvariants();
        return subTypes.iterator();
    }

    /**
     * @methodtype setter
     */
    public void addSubType(CarType ft) {
        assertClassInvariants();
        assertIsValidArgument(ft);

        assert (ft != null) : "tried to set null sub-type";
        ft.setSuperType(this);
        subTypes.add(ft);
    }

    /**
     * @methodtype setter
     */
    public void setSuperType(CarType superType) {
        assertClassInvariants();
        assertIsValidArgument(superType);
        this.superType = superType;
    }

    /**
     * Checks if this is a subType of a super CarType
     * @methodtype query
     */
    public boolean isSubType (CarType ct){

        CarType st=superType;
        while (st != null){
            if (superType.getCarType().equals(this.getCarType())){
                return true;
            }
            st=st.superType;

        }
       return false;

    }

    private  void assertIsValidArgument(Object o){
        if (o == null){
            throw new IllegalArgumentException("Argument is null");
        }
    }

    /**
     * Asserts Classinvariants
     *
     * @methodtype helper
     */
    protected void assertClassInvariants() {
        if (this.carManager==null || this.carOEMName == null || this.model == null || this.buildYear < 0) {
            throw new IllegalStateException("Class Invariants hurt");
        }
    }

}