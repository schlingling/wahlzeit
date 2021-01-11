package org.wahlzeit.model;


import org.wahlzeit.utils.DesignPatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

@DesignPatternInstance(
        patternName = "Abstract Factory",
        participants = {"Abstract Factory", "Abstract Product", "Concrete Factory", "Concrete Product"},
        type = "creational",
        roleOfAnnotatedClass = "Concrete Product"
)
public class CarPhoto extends Photo {

    /**
     *
     */
    private String carOEMName="Daimler-Benz";
    private int buildYear =2020;

    /**
     *
     */
    public CarPhoto() {
        super();
    }

    /**
     * @methodtype constructor
     */
    public CarPhoto(PhotoId myId) {
       super(myId);

    }


    /**
     * Location Set bei by values from DB
     *
     * @methodtype constructor
     */
    public CarPhoto(ResultSet rset) throws SQLException {
        super(rset);
    }


    /**
     * @methodtype get
     */
    public String getCarOEMName(){
        return carOEMName;
    }

    /**
     * @methodtype set
     */
    public void setCarOEMName(String s){
        if (s==null)throw new IllegalArgumentException("Name must be set");
        carOEMName=s;
    }

    /**
     * @methodtype get
     */
    public int getBuildYear(){
        return buildYear;
    }

    /**
     * @methodtype set
     */
    public void setBuildYear(int y){
        if (y<=0)throw new IllegalArgumentException("Year must be a valid year");
        buildYear=y;
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("photoclass", String.valueOf(this.getClass()));
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        //Hier könnte man ggf. die Attribute von CarPhoto lesen, aber kann eh nicht mitgegeben werden durch UI
    }
}
