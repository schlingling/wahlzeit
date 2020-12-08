package org.wahlzeit.model.location;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CartesianCoordinate extends AbstractCoordinate {

    private double x;
    private double y;
    private double z;


    /**
     * Instanciate Coordinate.java with default values x=y=z=0
     *
     * @methodtype constructor
     */
    public CartesianCoordinate() {
        setX(0);
        setY(0);
        setZ(0);
    }


    /**
     * Instanciate Location with specific Coordinates
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Calculates the distance between CartesianCoordinatecoordinate and this
     *
     * @methodtype query
     */
    public double getDistance(CartesianCoordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate.java c must be set to a value");
        }

        double a, b, c = 0;

        a = this.getX() - coordinate.getX();
        b = this.getY() - coordinate.getY();
        c = this.getZ() - coordinate.getZ();

        double distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
        return distance;
    }


    /**
     * calculates hashCode from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    @Override
    protected int doHashCode() {
        int result = 17;
        result = (int) (31 * result + rint(getX(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getY(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getZ(), NACHKOMMASTELLEN));
        return result;
    }

    /**
     * calculates CartesianCoordinatte from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    public CartesianCoordinate asCartesianCoordinate() throws Exception {
        return this;
    }


    /**
     * calculates Spheric coordinate from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    public SphericCoordinate asSphericCoordinate()throws Exception {

        double phi; //azimuth, angle of rotation from the inital meridian plane--> x-axis
        double theta; //inclination, polar angle, fangle with respect to polar axis, from z-axis
        double radius; //Distance from origin

        double x, y, z;


        phi = Math.atan2(this.getY(), this.getX());
        if (this.getZ() != 0) {
            theta = Math.atan(
                    (Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)))
                            / this.getZ());
        } else {
            theta = 0;
        }

        radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));

        return new SphericCoordinate(phi, theta, radius);
    }


    /**
     * Checks wether this is equal to coordinate
     *
     * @methodtype query
     */
    @Override
    protected boolean doIsEqual(Coordinate coordinate) throws Exception{
        CartesianCoordinate c = coordinate.asCartesianCoordinate();
        return (compare(this.getX(), c.getX(), DELTA) &&
                compare(this.getY(), c.getY(), DELTA) &&
                compare(this.getZ(), c.getZ(), DELTA));
    }



    /**
     * Reads coordinates from database
     *
     * @methodtype mutation
     */
    @Override
    public void readFrom(ResultSet resultSet) throws SQLException {
        assertArgumentNotNull(resultSet);
        this.setX(resultSet.getDouble("location_x"));
        this.setY(resultSet.getDouble("location_y"));
        this.setZ(resultSet.getDouble("location_z"));
    }

    /**
     * Writes coordinates to database
     *
     * @methodtype mutation
     */
    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        assertArgumentNotNull(resultSet);
        resultSet.updateDouble("location_x", this.getX());
        resultSet.updateDouble("location_y", this.getY());
        resultSet.updateDouble("location_z", this.getZ());
    }

    @Override
    protected void assertClassInvariants() throws Exception {
        if (Double.isNaN(this.x)|| Double.isNaN(this.y) ||Double.isNaN(this.z)){
            throw new Exception("One Instanceattribute is NAN");
        }
    }
}

