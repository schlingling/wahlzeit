package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class PhotoTest {

    private PhotoManager pm;
    private Photo p1, p2, p3, p4, p5, p6;

    @Before
    public void setupFotoTest() {

        pm = PhotoManager.getInstance();

    }

    @Test
    public void testGetIdFromString() {

        //ACT

        String s1 = p4.getIdAsString();
        String s2 = p5.getIdAsString();



    }


}
