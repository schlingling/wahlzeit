package org.wahlzeit.model.location;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    /**
     * Konstanten, für Toleranz bei double Vergleich
     */
    protected final double DELTA = 0.0000001;
    protected final int NACHKOMMASTELLEN = 7;


    /**
     * @param coordinate
     * @return distance between this and coordinate
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws Exception {
        assertArgumentNotNull(coordinate);
        return doGetCartesianDistance(coordinate);
    }


    /**
     * @param coordinate
     * @return central angle between this and coordinate
     */
    @Override
    public double getCentralAngel(Coordinate coordinate) throws Exception{
        assertArgumentNotNull(coordinate);
        return doGetCentralAngle(coordinate);
    }


    /**
     * Checks wether this is equal to coordinate
     *
     * @return true if represenation in Cartesian AND Spheric is equal
     * @methodtype query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) throws Exception {
        assertArgumentNotNull(coordinate);
        if (!(coordinate instanceof Coordinate) || !(this instanceof Coordinate)) {
            return false;
        }

        CartesianCoordinate cc1 = this.asCartesianCoordinate();
        CartesianCoordinate cc2 = coordinate.asCartesianCoordinate();

        SphericCoordinate sc1 = this.asSphericCoordinate();
        SphericCoordinate sc2 = coordinate.asSphericCoordinate();

        boolean check1 = cc1.doIsEqual(cc2);
        boolean check2 = sc1.doIsEqual(sc2);

        return check1 && check2;
    }


    @Override
    public String getIdAsString() {
        //NOT IMPLEMENTED
        return null;
    }


    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        assertArgumentNotNull(stmt);
        //donothing
    }


    @Override
    public boolean isDirty() {
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
        assertArgumentNotNull(obj);
        if (!(obj instanceof Coordinate) || !(this instanceof Coordinate)) {
            return false;
        }
        Coordinate cord = (Coordinate) obj;


        try {
            return this.isEqual(cord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return false;
    }

    @Override
    public int hashCode() {
        try {
            return doHashCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param coordinate
     * @return cartesian Distance between this and coordinate
     */
    protected double doGetCartesianDistance(Coordinate coordinate) throws Exception {
        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coordinate.asCartesianCoordinate();
        return c1.getDistance(c2);
    }

    protected double doGetCentralAngle(Coordinate coordinate) throws Exception{
        SphericCoordinate sc1 = this.asSphericCoordinate();
        SphericCoordinate sc2 = coordinate.asSphericCoordinate();
        return sc1.doGetCentralAngel(sc2);
    }


    /**
     * Compares two doubles with buffer-tolerance defined by epsilon
     *
     * @methodtype helper
     */
    protected static boolean compare(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

    /**
     * Rints a double value by decimalPoints
     *
     * @methodtype helper
     */
    protected static double rint(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.rint(value * d) / d;
    }


    /**
     * Assert valid argument
     *
     * @methodtype helper
     */
    protected static void assertArgumentNotNull(Object obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        ;
    }


     protected abstract int doHashCode() throws Exception;
     protected abstract boolean doIsEqual(Coordinate coordinate) throws Exception;
     protected abstract void assertClassInvariants() throws Exception;



}
