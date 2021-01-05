package org.wahlzeit.model.location;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate() ;

    public double getCartesianDistance(Coordinate coordinate) throws CheckedCoordinateException;

    public SphericCoordinate asSphericCoordinate();

    public double getCentralAngel(Coordinate coordinate) throws CheckedCoordinateException;

    public boolean isEqual(Coordinate coordinate) throws CheckedCoordinateException;


    public class UncheckedCoordinateException extends RuntimeException {

        public UncheckedCoordinateException() {
            super("Fehler bei der Implementation der Coordinate");
        }

        public UncheckedCoordinateException(String fehlermeldung) {
            super(fehlermeldung);
        }
    }

    public class CheckedCoordinateException extends Exception {

        public CheckedCoordinateException() {
            super("Berechnungsfehler in der Coordinate");
        }

        public CheckedCoordinateException(String fehlermeldung) {
            super(fehlermeldung);
        }

        public CheckedCoordinateException(String fehlermeldung, Throwable ex) {
            super(fehlermeldung,ex);
        }



    }


}
