package org.wahlzeit.model;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate();
    public double getCartesianDistance();
    public SphericCoordinate asSphericCoordinate();
    public double getCentralAngel(Coordinate coordinate);
    public boolean isEqual(Coordinate coordinate);

}
