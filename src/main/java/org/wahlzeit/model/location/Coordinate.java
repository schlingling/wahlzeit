package org.wahlzeit.model.location;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate() throws Exception;
    public double getCartesianDistance(Coordinate coordinate)  throws Exception;
    public SphericCoordinate asSphericCoordinate()  throws Exception;
    public double getCentralAngel(Coordinate coordinate)  throws Exception;
    public boolean isEqual(Coordinate coordinate) throws Exception;

}
