package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static CarPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized CarPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting CarPhotoFactory");
            setInstance(new CarPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of CarPhotoFactory.
     */
    protected static synchronized void setInstance(CarPhotoFactory carPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize CarPhotoFactory twice");
        }

        instance = carPhotoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

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
