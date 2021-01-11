package org.wahlzeit.model;


import org.wahlzeit.utils.DesignPatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

@DesignPatternInstance(
        patternName = "Singleton",
        participants = {},
        roleOfAnnotatedClass = "Singleton"
)
public class CarPhotoManager extends PhotoManager {

    @Override
    /**
     *Owerwritten to make clear that a CarPhotoFactory is called, not a normal Photofactory
     */
    protected Photo createObject(ResultSet rset) throws SQLException {
        return CarPhotoFactory.getInstance().createPhoto(rset);
    }



}
