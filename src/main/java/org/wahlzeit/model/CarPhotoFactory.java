package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

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
