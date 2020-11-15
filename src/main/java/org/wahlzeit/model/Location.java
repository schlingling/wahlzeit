package org.wahlzeit.model;

public class Location {

    public Coordinate coordinate;

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
     *
     * @methodtype get
     */
    public double getX(){
        return this.coordinate.getX();
    }


    /**
     *
     * @methodtype get
     */
    public double getY(){
        return this.coordinate.getY();
    }


    /**
     *
     * @methodtype get
     */
    public double getZ(){
        return this.coordinate.getZ();
    }

    public void setX(double x){
        this.coordinate.setX(x);
    }

    public void setY(double y){
        this.coordinate.setX(y);
    }

    public void setZ(double z){
        this.coordinate.setX(z);
    }





    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Location)||!(this instanceof Location)){
            return false;
        }
        Location loc = (Location) obj;
        return this.coordinate.equals(loc.coordinate);
    }


    /**

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

*/



}
