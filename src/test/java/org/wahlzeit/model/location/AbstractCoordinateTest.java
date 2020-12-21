package org.wahlzeit.model.location;

import org.junit.Before;
import org.junit.Test;

import static  org.junit.Assert.*;

public class AbstractCoordinateTest {

    private CartesianCoordinate cc1,cc2;
    private SphericCoordinate sc1,sc2;


    @Before
    public void setupAbstractCoordinate()   {

        this.sc1 = SphericCoordinate.getOrCreateDefaultCoordinate();
        this.sc2 = SphericCoordinate.getOrCreateCoordinate(5,3,10);

        this.cc1 =  CartesianCoordinate.getOrCreateCoordinate(5,5,5);
        this.cc2 = CartesianCoordinate.getOrCreateCoordinate(10,5,3);


    }


    @Test
    public void testGetCentralAngel() throws Coordinate.CheckedCoordinateException {
        //ARRANGE https://de.wikipedia.org/wiki/Orthodrome
        //Check link for example
        SphericCoordinate s1 = SphericCoordinate.getOrCreateCoordinate(52.517, 13.4, 3.5);
        SphericCoordinate s2 = SphericCoordinate.getOrCreateCoordinate(35.7, 139.767, 3.5);
        double exp = 80.21;

        //ACT
        double ca = s1.getCentralAngel(s2);

        //ASSERT
        assertEquals(exp, ca, 0.01);

    }

    @Test
    public void testGetCartesianDistanceSimple() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        CartesianCoordinate c1 = CartesianCoordinate.getOrCreateDefaultCoordinate();
        CartesianCoordinate c2 = CartesianCoordinate.getOrCreateDefaultCoordinate();
        SphericCoordinate s1 = SphericCoordinate.getOrCreateDefaultCoordinate();
        SphericCoordinate s2 = SphericCoordinate.getOrCreateDefaultCoordinate();

        //ACT
        //ASSERT
        assertTrue(c1.getCartesianDistance(c2)==0);
        assertTrue(s1.getCartesianDistance(s2)==0);
        assertTrue(s1.getCartesianDistance(c2)==0);
        assertTrue(c1.getCartesianDistance(s2)==0);


    }

    @Test
    public void testGetCartesianDistanceComplex()   {
        //ARRANGE
        CartesianCoordinate c1 = CartesianCoordinate.getOrCreateCoordinate(5,5,5);
        CartesianCoordinate c2 = CartesianCoordinate.getOrCreateCoordinate(7,7,7);

        //ACT
        double exp = Math.sqrt(Math.pow(c1.getX()-c2.getX(), 2) + Math.pow(c1.getY()-c2.getY(), 2) + Math.pow(c1.getZ()-c2.getZ(), 2));
        double act = c1.getDistance(c2);

        //ASSERT
        assertTrue(exp==act);

    }


    @Test
    public void testCompare(){
        //ARRANGE
        double d1 = AbstractCoordinate.rint(0.123456789, AbstractCoordinate.NACHKOMMASTELLEN);
        double d2 = AbstractCoordinate.rint(0.12345678910510515151, AbstractCoordinate.NACHKOMMASTELLEN);

        //ACT
        //ASSERT
        assertTrue(Double.compare(d1,d2)==0);

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
