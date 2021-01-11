package org.wahlzeit.model.location;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.DesignPatternInstance;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    /**
     * Konstante, für Rundung der doubles
     */
    protected static final int NACHKOMMASTELLEN = 7;
    //Map for storing&sharing immutable coordinates


    protected abstract boolean doIsEqual(Coordinate coordinate) throws CheckedCoordinateException;

    /**
     * @param coordinate
     * @return distance between this and coordinate
     */

    @Override

    public double getCartesianDistance(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);
        double result;
        try {
            CartesianCoordinate c1 = this.asCartesianCoordinate();
            CartesianCoordinate c2 = coordinate.asCartesianCoordinate();
            result = c1.getDistance(c2);
        } catch (Exception e) {
            throw new CheckedCoordinateException("Fehler in der Berechnugn der Distanz", e);
        }

        //Post-Condition
        assertIsValidDistance(result);
        assertClassInvariants();
        return result;

    }


    /**
     * @param coordinate
     * @return central angle between this and coordinate
     */
    @Override
    public double getCentralAngel(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);
        double res;
        try {
            SphericCoordinate sc1 = this.asSphericCoordinate();
            SphericCoordinate sc2 = coordinate.asSphericCoordinate();
            res = sc1.doGetCentralAngel(sc2);
        } catch (Exception e) {
            throw new CheckedCoordinateException("Fehler in der Berechnung des CentralAngle", e);
        }

        assertIsValidCentralAngle(res);
        assertClassInvariants();
        return res;
    }


    /**
     * Checks wether this is equal to coordinate
     *
     * @return true if represenation in Cartesian AND Spheric is equal
     * @methodtype query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);
        if (!(coordinate instanceof Coordinate) || !(this instanceof Coordinate)) {
            return false;
        }

        boolean check1;
        boolean check2;
        try {
            CartesianCoordinate cc1 = this.asCartesianCoordinate();
            CartesianCoordinate cc2 = coordinate.asCartesianCoordinate();

            SphericCoordinate sc1 = this.asSphericCoordinate();
            SphericCoordinate sc2 = coordinate.asSphericCoordinate();

            check1 = cc1.doIsEqual(cc2);
            check2 = sc1.doIsEqual(sc2);


        } catch (Exception e) {
            throw new CheckedCoordinateException("Fehler beim Vergleich der Koordinate", e);
        }

        assertClassInvariants();
        return check1 && check2;
    }


    @Override
    public String getIdAsString() {
        assertClassInvariants();

        //NOT IMPLEMENTED
        return null;
    }


    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        assertClassInvariants();

        assertArgumentNotNull(stmt);
        //donothing
    }


    @Override
    public int hashCode() {
        assertClassInvariants();
        return this.asCartesianCoordinate().hashCode();
    }

    @Override
    public boolean isDirty() {
        assertClassInvariants();

        return writeCount != 0;
    }

    /**
     * Checks wether obj equals this, deligates to isEqual()
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        assertClassInvariants();
        assertArgumentNotNull(obj);
        if (!(obj instanceof Coordinate) || !(this instanceof Coordinate)) {
            return false;
        }
        Coordinate cord = (Coordinate) obj;

        boolean res = false;

        try {
            res = this.isEqual(cord);
        } catch (CheckedCoordinateException e) {
            e.printStackTrace();
        }

        return res;

    }


    /**
     * Rints a double value by decimalPoints
     *
     * @methodtype helper
     */
    protected static double rint(double value, int decimalPoints) {
        assertArgumentNotNAN(value);
        assertValGreaterEqualsZero(decimalPoints);

        double d = Math.pow(10, decimalPoints);
        return Math.rint(value * d) / d;
    }

    /**
     * Assert classInvariants from AbstractClass --> should be called from Subclasses when they override
     *
     * @methodtype helper
     */
    protected void assertClassInvariants() {

    }


    /**
     * Assert valid argument
     *
     * @methodtype helper
     */
    protected static void assertArgumentNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        ;
    }

    /**
     * Asserts argument not NAN
     *
     * @methodtype helper
     */
    protected static void assertArgumentNotNAN(double argument) {
        if (Double.isNaN(argument)) {
            throw new IllegalArgumentException("Argument is nan");
        }
        ;
    }

    /**
     * Assert valdi central angle
     *
     * @methodtype helper
     */
    protected static void assertIsValidCentralAngle(double angle) {
        if (angle < 0.0 || angle > 360.0) {
            throw new UncheckedCoordinateException("Central Angle should always be between 0 and 360 deg but was " + angle);
        }
    }

    /**
     * Asserts valid distance greater equals 0
     *
     * @methodtype helper
     */
    protected static void assertIsValidDistance(double angle) throws CheckedCoordinateException {
        if (angle < 0.0 || angle > 360.0) {
            throw new UncheckedCoordinateException("Central Angle should always be between 0 and 360 deg but was " + angle);
        }
    }


    /**
     * Assert valid value greater equal 0
     *
     * @methodtype helper
     */
    protected static void assertValGreaterEqualsZero(double argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("Argument should be greater euquals Zero");

        }
    }


}
