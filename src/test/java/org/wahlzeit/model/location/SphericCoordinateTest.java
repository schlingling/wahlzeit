package org.wahlzeit.model.location;

import org.junit.Before;
import org.junit.Test;

import static  org.junit.Assert.*;

public class SphericCoordinateTest {
    //Delta value used for comparison of double values.
    private static double DELTA = 0.0000001;


    //different coordinates that are used multiple times in the test methods
    private SphericCoordinate c1,c2,cNull;


    @Before
    public void setupCoordinates()   {

        this.c1 = SphericCoordinate.getOrCreateCoordinate();
        this.c2 = SphericCoordinate.getOrCreateCoordinate(5,5,5);
        this.cNull = null;

    }

    @Test
    public void testNotNull(){
        //ARRANGE
        //ACT

        //ASSERT
        assertNotNull(c1);
        assertNotNull(c2);
    }


    @Test
    public void testConstructor()   {
        //ARRANGE
        //ACT
        double diff_phi = Math.abs(c2.getPhi()-5);
        double diff_theta = Math.abs(c2.getTheta()-5);
        double diff_radius= Math.abs(c2.getRadius()-5);

        //ASSERT
        assertTrue(diff_phi<DELTA);
        assertTrue(diff_theta<DELTA);
        assertTrue(diff_radius<DELTA);
    }


    @Test
    public void testAsCartesianCoordinate() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        //ACT
        CartesianCoordinate cc = c2.asCartesianCoordinate();

        //http://www.calc3d.com/gjavascriptcoordcalc.html
        double x_expected = -1.3600527772234245;
        double y_expected = 4.597678822691131;
        double z_expected = 1.4183109273161312;

        //ASSERT
        assertTrue(Math.abs(cc.getX())-Math.abs(x_expected)<DELTA);
        assertTrue(Math.abs(cc.getY())-Math.abs(y_expected)<DELTA);
        assertTrue(Math.abs(cc.getZ())-Math.abs(z_expected)<DELTA);
    }

    @Test
    public void testAsSphericCoordinate() throws Coordinate.CheckedCoordinateException {
        SphericCoordinate sc = c2.asSphericCoordinate();


        double phi_expected = sc.getPhi();
        double radius_expected  = sc.getRadius();
        double theta_expected  = sc.getTheta();


        //ASSERT
        assertTrue(Math.abs(c2.getPhi())-Math.abs(phi_expected)<DELTA);
        assertTrue(Math.abs(c2.getRadius())-Math.abs(radius_expected)<DELTA);
        assertTrue(Math.abs(c2.getTheta())-Math.abs(theta_expected)<DELTA);

    }

    @Test
    public void testDoIsEqualSimple() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate();

        //ACT
        //ASSERT
        assertTrue(sc.isEqual(c1));
        assertTrue(c1.isEqual(sc));
    }

    @Test
    public void testDoIsEqualComplex() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(5,5,5);

        //ACT
        //ASSERT
        assertTrue(sc.isEqual(c2));
        assertTrue(c2.isEqual(sc));

    }

    @Test
    public void testEqualsComplex()   {
        //ARRANGE
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(5,5,5);


        //ACT
        //ASSERT
        assertTrue(sc.equals(c2));
        assertTrue(c2.equals(sc));

    }

    @Test
    public void testNotDoIsEqualComplex() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(5,6,5);

        //ACT
        //ASSERT
        assertFalse(sc.isEqual(c2));
    }


    @Test
    public void testHashCode()   {
        //ARRANGE
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(5,5,5);

        int h1 = c2.hashCode();
        int h2 = sc.hashCode();

        //ACT
        //ASSERT
        assertTrue(h1==h2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueIsNan(){
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(5,5,Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClassInvariants(){
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(5,5,-1);
    }

    @Test
    public void testImmutiability(){
        //Nothing to test because all attributes of CartesianCoordinate are final and no setters offered!
    }

    @Test
    public void testShareability(){
        //ARRANGE
        SphericCoordinate cc1 = SphericCoordinate.getOrCreateCoordinate();
        SphericCoordinate cc2 = SphericCoordinate.getOrCreateCoordinate();

        //ACT & ASSERT
        assertTrue(cc1==cc2);

    }


}
