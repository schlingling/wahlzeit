package org.wahlzeit.model.location;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate();

    public double getCartesianDistance(Coordinate coordinate);

    public SphericCoordinate asSphericCoordinate() ;

    public double getCentralAngel(Coordinate coordinate);

    public boolean isEqual(Coordinate coordinate) ;

    public class UncheckedCoordinateException extends RuntimeException {

        public UncheckedCoordinateException() {
            super("Fehler bei der Implementation der Coordinate");
        }

        public UncheckedCoordinateException(String fehlermeldung) {
            super(fehlermeldung);
        }
    }


}
