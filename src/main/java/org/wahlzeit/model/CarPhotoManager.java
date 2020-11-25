package org.wahlzeit.model;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CarPhotoManager extends PhotoManager {

    @Override
    /**
     *Owerwritten to make clear that a CarPhotoFactory is called, not a normal Photofactory
     */
    protected Photo createObject(ResultSet rset) throws SQLException {
        return CarPhotoFactory.getInstance().createPhoto(rset);
    }



}
