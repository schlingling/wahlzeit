package org.wahlzeit.model;

public class Car {

    private CarType type; //every Car has a specific carType
    private CarManager manager; //every car knows his manager
    private String fahrgestellnummer; //every car has a ID (for demonstration purposes just a random number)

    /**
     * @methodtype constructor
     */
    public Car(CarType ct, CarManager m){
        this.type=ct;
        this.manager=m;

        //Set random fahrgestellnummer
        this.fahrgestellnummer=String.valueOf(Math.random());
    }

    /**
     * Returns Farhgestellnummer
     * @methodtype get
     * @return String
     */
    public String getFahrgestellnummer() {
        return fahrgestellnummer;
    }

    /**
     * Returns CarType
     * @methodtype get
     * @return CarType
     */
    public CarType getType() {
        return type;
    }

}
