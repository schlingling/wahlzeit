package org.wahlzeit.model;


import org.wahlzeit.utils.CreationalDesignPatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

@CreationalDesignPatternInstance(
        patternName = "Abstract Factory",
        metaParticipants = {"Abstract Factory", "Abstract Product", "Concrete Factory", "Concrete Product"},
        participants = {"PhotoFactory", "Photo", "CarPhotoFactory", "CarPhoto"},
        roleOfAnnotatedClass = "Concrete Product"
)
public class CarPhoto extends Photo {

    /**
     *
     */

    Car car;

    /**
     *
     */
    public CarPhoto() {
        super();

        //For Demopurposes using a default carType
        CarManager.getInstance().createCar("defaultOEM", "defaultModel", 2020);
    }

    /**
     * @methodtype constructor
     */
    public CarPhoto(PhotoId myId) {
       super(myId);

        /**
         * TRACING OF PHOTO & CAR INSTANTIATION
         * Schritt4: Über den CarManager-Singleton wird ein neues Car erzeugt
         */

        //For Demopurposes using a default carType
        CarManager.getInstance().createCar("defaultOEM", "defaultModel", 2020);

        /**
         * TRACING OF PHOTO & CAR INSTANTIATION
         * Schritt9: Instanz-Attribut car erfolgreich initialisiert
         */
    }


    /**
     * Location Set bei by values from DB
     *
     * @methodtype constructor
     */
    public CarPhoto(ResultSet rset) throws SQLException {
        super(rset);

        //For Demopurposes using a default carType
        CarManager.getInstance().createCar("defaultOEM", "defaultModel", 2020);
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
