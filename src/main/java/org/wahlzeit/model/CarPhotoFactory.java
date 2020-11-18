package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static PhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting CarPhotoFactory");
            setInstance(new PhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of CarPhotoFactory.
     */
    protected static synchronized void setInstance(PhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize CarPhotoFactory twice");
        }

        instance = photoFactory;
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
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public Photo createPhoto() {
        return new Photo();
    }


    /**
     *
     */
    public Photo createPhoto(PhotoId id) {
        return new Photo(id);
    }

    /**
     *
     */
    public Photo createPhoto(ResultSet rs) throws SQLException {
        return new Photo(rs);
    }

    /**
     *
     */
    public PhotoFilter createPhotoFilter() {
        return new PhotoFilter();
    }

    /**
     *
     */
    public PhotoTagCollector createPhotoTagCollector() {
        return new PhotoTagCollector();
    }


}
