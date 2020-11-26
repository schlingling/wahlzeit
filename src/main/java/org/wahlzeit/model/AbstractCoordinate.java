package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    protected final double DELTA = 0.0000001;
    protected final int NACHKOMMASTELLEN = 7;


    @Override
    public double getCartesianDistance() {
        return doGetCartesianDistance();
    }


    @Override
    public double getCentralAngel(Coordinate coordinate) {
        return doGetCentralAngel(coordinate);
    }


    @Override
    public boolean isEqual(Coordinate coordinate) {
        return doIsEqual(coordinate);
    }


    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public  void readFrom(ResultSet rset) throws SQLException {
        doReadFrom(rset);
    }

    @Override
    public  void writeOn(ResultSet rset)throws SQLException{
        doWriteOn(rset);
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        //donothing
    }

    public  double doGetCartesianDistance(){
        //TODO: Impl
        return 0;
    };
    public  double doGetCentralAngel(Coordinate coordinate){
        //TODO: Impl
        return 0;
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



    protected abstract boolean doIsEqual(Coordinate coordinate);
    protected abstract void doReadFrom(ResultSet resultSet) throws SQLException;
    protected abstract void doWriteOn(ResultSet resultSet) throws SQLException;





    //TODO: assertNotNull-Methode hier implementieren

}
