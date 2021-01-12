package org.wahlzeit.model;



import org.wahlzeit.utils.CreationalDesignPatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

@CreationalDesignPatternInstance(
        patternName = "Abstract Factory",
        metaParticipants = {"Abstract Factory", "Abstract Product", "Concrete Factory", "Concrete Product"},
        participants = {"PhotoFactory", "Photo", "CarPhotoFactory", "CarPhoto"},
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
