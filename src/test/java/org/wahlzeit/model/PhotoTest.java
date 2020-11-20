package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class PhotoTest {

    private PhotoId id1, id2, id3, id4;
    private Photo p1, p2, p3, p4, p5, p6;

    @Before
    public void setupFoto() {

        id1 = PhotoId.getRandomId();
        id2 = PhotoId.getRandomId();


        id3 = PhotoId.getIdFromString("testid");
        id4 = PhotoId.getIdFromString("asdfasdfasdfasdf");


        //TODO: Setup Photo(rset)

        p1 = new Photo();

        p2 = new Photo(id1);
        p3 = new Photo(id2);

        p4 = new Photo(id3);
        p5 = new Photo(id4);


    }

    @Test
    public void testGetIdFromString() {

        //ACT

        String s1 = p4.getIdAsString();
        String s2 = p5.getIdAsString();

        //ASSERT
        assertEquals(true,true);


    }


}
