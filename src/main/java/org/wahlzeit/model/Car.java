package org.wahlzeit.model;

public class Car {

    private CarType type;
    private CarManager manager;
    private String fahrgestellnummer;

    public Car(CarType ct, CarManager m){
        this.type=ct;
        this.manager=m;

        //Set random fahrgestellnummer
        this.fahrgestellnummer=String.valueOf(Math.random());
    }

    public String getFahrgestellnummer() {
        return fahrgestellnummer;
    }

    public CarType getType() {
        return type;
    }

}
