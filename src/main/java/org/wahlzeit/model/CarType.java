package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CarType {

    private CarManager carManager;
    private String carOEMName;
    private String model;
    private int buildYear;
    private CarType superType;
    private Set<CarType> subTypes = new HashSet<CarType>();

    public CarType(String carOEMName, String model,int buildYear, CarManager manager){
        this.carOEMName=carOEMName;
        this.model=model;
        this.buildYear=buildYear;
        this.carManager=manager;

    }

    public Car createInstance(CarManager manager){
        this.carManager=manager;
        return new Car(this,manager);
    }

    public String getCarType(){
        return carOEMName+model+buildYear;
    }


    public CarManager getCarManager() {
        return carManager;
    }

    public String getCarOEMName() {
        return carOEMName;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public String getModel() {
        return model;
    }

    public CarType getSuperType() {
        return superType;
    }
    public Iterator<CarType> getSubTypeIterator() {
        return subTypes.iterator();
    }
    public void addSubType(CarType ft) {
        assert (ft != null) : "tried to set null sub-type";
        ft.setSuperType(this);
        subTypes.add(ft);
    }

    public void setSuperType(CarType superType) {
        this.superType = superType;
    }
}
