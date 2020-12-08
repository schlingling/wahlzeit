package org.wahlzeit.model.location;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate();
    public double getCartesianDistance(Coordinate coordinate);
    public SphericCoordinate asSphericCoordinate();
    public double getCentralAngel(Coordinate coordinate);
    public boolean isEqual(Coordinate coordinate);

}
