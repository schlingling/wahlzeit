package org.wahlzeit.model;

import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import static  org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AbstractCoordinateTest {

    private CartesianCoordinate cc1,cc2;
    private SphericCoordinate sc1,sc2;


    @Before
    public void setupAbstractCoordinate() {

        this.sc1 = new SphericCoordinate();
        this.sc2 = new SphericCoordinate(5,3,10);

        this.cc1 = new CartesianCoordinate(5,5,5);
        this.cc2 = new CartesianCoordinate(10,5,3);


    }


    /**

    @Test
    public void testGetCentralAngel(){
        //ARRANGE https://de.wikipedia.org/wiki/Orthodrome
        SphericCoordinate s1 = new SphericCoordinate(52.517, 13.4, 3.5);
        SphericCoordinate s2 = new SphericCoordinate(35.7, 139.767, 3.5);
        double ca = s1.getCentralAngel(s2);

        double expectedCentralAngle = 80.21;

        assertEquals(expectedCentralAngle, ca, 0.001);



        //ACT



        //ASSERT


    }

    @Test
    public void testGetCartesianDistance(){
        //ARRANGE
        CartesianCoordinate c1 = new CartesianCoordinate();
        CartesianCoordinate c2 = new CartesianCoordinate();
        SphericCoordinate s1 = new SphericCoordinate();
        SphericCoordinate s2 = new SphericCoordinate();


        //ACT
        //ASSERT
        assertTrue(c1.getCartesianDistance(c2)==0);
        assertTrue(s1.getCartesianDistance(s2)==0);
        assertTrue(s1.getCartesianDistance(c2)==0);
        assertTrue(c1.getCartesianDistance(s2)==0);


    }

*/


}
