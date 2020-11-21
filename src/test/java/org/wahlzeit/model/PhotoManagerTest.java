package org.wahlzeit.model;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PhotoManagerTest {

    private PhotoManager pm;

    @Before
    public void setupPhotomanager() {



    }

    @Test
    public void testCorrectInstanceofPhotoManager(){
        //ARRANGE
        pm = PhotoManager.getInstance();

        //ACT

        //ASSERT
        assertEquals(CarPhotoManager.class,pm.getClass());
    }

}
