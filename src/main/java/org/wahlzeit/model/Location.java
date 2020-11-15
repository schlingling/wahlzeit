package org.wahlzeit.model;

public class Location {

    private Coordinate coordinate;

    /**
     *Instanciate Location with default-Coordinates x=0, y=0, z=0
     * @methodtype constructor
     */
    public Location (){
        this.coordinate=new Coordinate();
    }

    /**
     *  Instanciate Location with specific Coordinates
     * @methodtype constructor
     */
    public Location (double x, double y, double z){
        this.coordinate=new Coordinate(x,y,z);
    }

    /**
     *
     * @methodtype get
     */
    public Coordinate getLocation() {
        return this.coordinate;
    }


    /**
     *  Gets the coordinate-value choosed by picker coordinate (possible string-value: x,y,z)
     * @methodtype constructor
     */
    public double getLocationCoordinateValue(String coordinate) {
        switch (coordinate){
            case "x":
                return this.coordinate.getX();
            case "y":
                return this.coordinate.getY();
            case "z":
                return this.coordinate.getZ();

        }
    throw new IllegalArgumentException("Wrong Coordinate picker");
    }


    /**
     *  Sets the Coordinate-Value choosed by the picker coordinate (Possbile string-values: x,y,z) to value
     * @methodtype set
     */
    public void setLocationCoordinateValue(String coordinate, double value) {
        switch (coordinate){
            case "x":
                 this.coordinate.setX(value);
                 break;
            case "y":
                 this.coordinate.setY(value);
                break;
            case "z":
                 this.coordinate.setZ(value);
                break;
            default:
                throw new IllegalArgumentException("Wrong Coordinate picker" + " Coodrinate: " + coordinate + " value: " + value);
        }
    }

}
