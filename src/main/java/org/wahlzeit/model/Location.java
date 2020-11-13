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
    public Coordinate getCoordinate() {
        return this.coordinate;
    }


    public double getCoordinateValue(String coordinate) {
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


    public void setCoordinateValue(String coordinate, double value) {
        switch (coordinate){
            case "x":
                 this.coordinate.setX(value);
            case "y":
                 this.coordinate.setY(value);
            case "z":
                 this.coordinate.setZ(value);

        }
        throw new IllegalArgumentException("Wrong Coordinate picker");
    }

    //TODO: getDistance() IMPL
    //TODO: isEqual() IMPL
    //TODO: Comment everything

}
