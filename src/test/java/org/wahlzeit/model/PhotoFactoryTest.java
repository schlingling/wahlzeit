package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhotoFactoryTest {

    private PhotoFactory pf;

    @Before
    public void setupPhotoFactory() {



    }

    @Test
    public void testCorrectInstanceofPhotoManager(){
        //ARRANGE
        pf = PhotoFactory.getInstance();

        //ACT

        //ASSERT
        assertEquals(CarPhotoFactory.class,pf.getClass());
    }

}
