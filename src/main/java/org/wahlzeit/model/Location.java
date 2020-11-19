package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location extends DataObject {

    public Coordinate coordinate;

    /**
     *Instanciate Location with default-Coordinates x=0, y=0, z=0
     * @methodtype constructor
     */
    public Location (){
        this.coordinate=new Coordinate();
    }

    /**
     *  Instanciate Location with specific Coordinates
     * @methodtype constructor
     */
    public Location (double x, double y, double z){
        this.coordinate=new Coordinate(x,y,z);
    }


    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Location)||!(this instanceof Location)){
            return false;
        }
        Location loc = (Location) obj;
        return this.coordinate.equals(loc.coordinate);
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode();
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        this.coordinate.readFrom(rset);
    }

    public void writeOn(ResultSet rset) throws SQLException {
        this.coordinate.writeOn(rset);
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        //doNothing
    }

}
