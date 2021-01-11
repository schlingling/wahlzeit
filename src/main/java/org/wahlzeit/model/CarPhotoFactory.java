package org.wahlzeit.model;



import org.wahlzeit.utils.DesignPatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

@DesignPatternInstance(
        patternName = "Abstract Factory",
        participants = {"Abstract Factory", "Abstract Product", "Concrete Factory", "Concrete Product"},
        type = "creational",
        roleOfAnnotatedClass = "Concrete Factory"
)
public class CarPhotoFactory extends PhotoFactory {


    /**
     *
     */
    protected CarPhotoFactory() {
        super();
    }

    /**
     * @methodtype factory
     */
    public CarPhoto createPhoto() {
        return new CarPhoto();
    }


    /**
     *
     */
    public CarPhoto createPhoto(PhotoId id) {
        return new CarPhoto(id);
    }

    /**
     *
     */
    public CarPhoto createPhoto(ResultSet rs) throws SQLException {
        return new CarPhoto(rs);
    }





}
