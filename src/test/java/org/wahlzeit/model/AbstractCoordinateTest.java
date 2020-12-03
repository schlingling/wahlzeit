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


    @Test
    public void testGetCentralAngel(){
        //ARRANGE https://de.wikipedia.org/wiki/Orthodrome
        //Check link for example
        SphericCoordinate s1 = new SphericCoordinate(52.517, 13.4, 3.5);
        SphericCoordinate s2 = new SphericCoordinate(35.7, 139.767, 3.5);
        double exp = 80.21;

        //ACT
        double ca = s1.getCentralAngel(s2);

        //ASSERT
        assertEquals(exp, ca, 0.01);

    }

    @Test
    public void testGetCartesianDistanceSimple(){
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

    @Test
    public void testGetCartesianDistanceComplex(){
        //ARRANGE
        CartesianCoordinate c1 = new CartesianCoordinate(5,5,5);
        CartesianCoordinate c2 = new CartesianCoordinate(7,7,7);

        //ACT
        double exp = Math.sqrt(Math.pow(c1.getX()-c2.getX(), 2) + Math.pow(c1.getY()-c2.getY(), 2) + Math.pow(c1.getZ()-c2.getZ(), 2));
        double act = c1.getDistance(c2);

        //ASSERT
        assertTrue(exp==act);

    }

    @Test
    public void testCompare(){
        //ARRANGE
        double d1 = 0.123456789;
        double d2 = 0.123456;

        //ACT
        //ASSERT
        assertTrue(AbstractCoordinate.compare(d1,d2,0.000001));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertArgumentNotNull(){
        //ARRANGE
        String s = null;
        //ACT
        //ASSERT
       AbstractCoordinate.assertArgumentNotNull(s);

    }

}
