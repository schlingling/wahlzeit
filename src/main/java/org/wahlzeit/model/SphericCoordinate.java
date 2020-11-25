package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends DataObject implements Coordinate {
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    @Override
    public double getCartesianDistance() {
        return 0;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return null;
    }

    @Override
    public double getCentralAngel(Coordinate coordinate) {
        return 0;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        return false;
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {

    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {

    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }
}
