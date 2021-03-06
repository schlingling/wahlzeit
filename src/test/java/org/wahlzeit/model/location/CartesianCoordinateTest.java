package org.wahlzeit.model.location;

import org.junit.Before;
import org.junit.Test;


import static  org.junit.Assert.*;

public class CartesianCoordinateTest {



    //Delta value used for comparison of double values.
    private static double DELTA = 0.0000001;


    //different coordinates that are used multiple times in the test methods
    private CartesianCoordinate c1,c2,c3,c4,c5,c6,c7,cNull;


    @Before
    public void setupCoordinates() throws Exception {

        this.c1 = CartesianCoordinate.getOrCreateCoordinate(1.2334, 2.3451651, 3.4565196814981);
        this.c2 = CartesianCoordinate.getOrCreateCoordinate(3.4565196814981, 2.3451651, 1.2334);

        this.c3 = CartesianCoordinate.getOrCreateCoordinate(-1.2334, -2.3451651, -3.4565196814981);
        this.c4 = CartesianCoordinate.getOrCreateCoordinate(-3.4565196814981, -2.3451651, -1.2334);

        this.c5 = CartesianCoordinate.getOrCreateCoordinate(1, 1, 1);
        this.c6 = CartesianCoordinate.getOrCreateCoordinate(-1, -1, -1);
        this.c7 = CartesianCoordinate.getOrCreateCoordinate(5, 5, 5);

        this.cNull = null;



    }

    @Test
    public void testGetter(){

        //ACT& ASSERT
        assertEquals(1.2334,this.c1.getX(), DELTA);
        assertEquals(2.3451651,this.c1.getY(), DELTA);
        assertEquals(3.4565196814981,this.c1.getZ(), DELTA);

        assertEquals(3.4565196814981,this.c2.getX(), DELTA);
        assertEquals(2.3451651,this.c2.getY(), DELTA);
        assertEquals(1.2334,this.c2.getZ(), DELTA);

        assertEquals(-1.2334,this.c3.getX(), DELTA);
        assertEquals(-2.3451651,this.c3.getY(), DELTA);
        assertEquals(-3.4565196814981,this.c3.getZ(), DELTA);

        assertEquals(-3.4565196814981,this.c4.getX(), DELTA);
        assertEquals(-2.3451651,this.c4.getY(), DELTA);
        assertEquals(-1.2334,this.c4.getZ(), DELTA);

        assertEquals(1,this.c5.getX(), DELTA);
        assertEquals(1,this.c5.getY(), DELTA);
        assertEquals(1,this.c5.getZ(), DELTA);

        assertEquals(-1,this.c6.getX(), DELTA);
        assertEquals(-1,this.c6.getY(), DELTA);
        assertEquals(-1,this.c6.getZ(), DELTA);


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
    public void testConstructor(){
        //ARRANGE
        //ACT
        double diff_x = Math.abs(c7.getX()-5);
        double diff_y = Math.abs(c7.getY()-5);
        double diff_z= Math.abs(c7.getZ()-5);

        //ASSERT
        assertTrue(diff_x<DELTA);
        assertTrue(diff_y<DELTA);
        assertTrue(diff_z<DELTA);
    }


    @Test
    public void testGetDistanceSimple()   {
        //ARRANGE
        CartesianCoordinate c1 = CartesianCoordinate.getOrCreateCoordinate(1,1,1);
        CartesianCoordinate c2 = CartesianCoordinate.getOrCreateCoordinate(1,1,1);

        //ACT
        double result = c1.getDistance(c2);

        //ASSERT
        //assertEquals(true,c1.equals(c2));
        assertEquals(0,result, 7);

    }


    @Test
    public void testGetDistanceComplex()   {
        //ARRANGE
        double cc1,cc2,cc3,cc4,cc5,cc6;

        //ACT --> Calculate Betrag des Ortsvektor
        cc1=Math.sqrt(Math.pow(c1.getX(), 2) + Math.pow(c1.getY(), 2) + Math.pow(c1.getZ(), 2));
        cc2=Math.sqrt(Math.pow(c2.getX(), 2) + Math.pow(c2.getY(), 2) + Math.pow(c2.getZ(), 2));
        cc3=Math.sqrt(Math.pow(c3.getX(), 2) + Math.pow(c3.getY(), 2) + Math.pow(c3.getZ(), 2));
        cc4=Math.sqrt(Math.pow(c4.getX(), 2) + Math.pow(c4.getY(), 2) + Math.pow(c4.getZ(), 2));
        cc5=Math.sqrt(Math.pow(c5.getX(), 2) + Math.pow(c5.getY(), 2) + Math.pow(c5.getZ(), 2));
        cc6=Math.sqrt(Math.pow(c6.getX(), 2) + Math.pow(c6.getY(), 2) + Math.pow(c6.getZ(), 2));

        //ASSERT
        assertEquals(cc1, CartesianCoordinate.getOrCreateCoordinate().getDistance(c1), 7);
        assertEquals(cc2,CartesianCoordinate.getOrCreateCoordinate().getDistance(c2), 7);
        assertEquals(cc3,CartesianCoordinate.getOrCreateCoordinate().getDistance(c3), 7);
        assertEquals(cc4,CartesianCoordinate.getOrCreateCoordinate().getDistance(c4), 7);
        assertEquals(cc5,CartesianCoordinate.getOrCreateCoordinate().getDistance(c5), 7);
        assertEquals(cc5,CartesianCoordinate.getOrCreateCoordinate().getDistance(c6), 7);



    }


    @Test
    public void testEqualMethods() throws Coordinate.CheckedCoordinateException {

        //ARRANGE
        double a = 1.1;
        double b = 1.121345464374852;
        double c = 1.1341646167137311717578687257867863;
        double d = 1.1787786578657867865;
        double e = 1.1456456;

        CartesianCoordinate c1 = CartesianCoordinate.getOrCreateCoordinate(b,c,d);
        CartesianCoordinate c2 = CartesianCoordinate.getOrCreateCoordinate(1.121345464374852,1.1341646167137311717578687257867863,1.1787786578657867865);
        CartesianCoordinate c3 = CartesianCoordinate.getOrCreateCoordinate(1.12,1.1341646222,1.1787786222);


        //ACT
        boolean result = c1.isEqual(c2);
        boolean result2= c1.equals(c2);

        boolean result3= c2.isEqual(c3);
        boolean result4= c2.equals(c3);

        //ASSERT
        assertEquals(true,result);
        assertEquals(true,result2);


        //TODO: Impl of korrekte Rundung nach DELTA
        //assertEquals(false,result3);
        //assertEquals(false,result4);


    }

    @Test
    public void testAsCartesianCoordinate() throws Coordinate.CheckedCoordinateException {

        CartesianCoordinate cc = c2.asCartesianCoordinate();

        double x_expected = cc.getX();
        double y_expected  = cc.getY();
        double z_expected  = cc.getZ();


        //ASSERT
        assertTrue(Math.abs(c2.getX())-Math.abs(x_expected)<DELTA);
        assertTrue(Math.abs(c2.getY())-Math.abs(y_expected)<DELTA);
        assertTrue(Math.abs(c2.getZ())-Math.abs(z_expected)<DELTA);
    }

    @Test
    public void testAsSphericCoordinate() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        //ACT
        SphericCoordinate sc = c2.asSphericCoordinate();

        //http://www.calc3d.com/gjavascriptcoordcalc.html
        double phi_expected = -0.5961336884644509;
        double theta_expected = 1.2836720026236401;
        double radius_expected = 4.355295996237424;

        //ASSERT
        assertTrue(Math.abs(sc.getPhi())-Math.abs(phi_expected)<DELTA);
        assertTrue(Math.abs(sc.getTheta())-Math.abs(theta_expected)<DELTA);
        assertTrue(Math.abs(sc.getRadius())-Math.abs(radius_expected)<DELTA);


    }

    @Test (expected = Coordinate.CheckedCoordinateException.class)
    public void testDoIsEqualSimple() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        CartesianCoordinate cc1 = CartesianCoordinate.getOrCreateCoordinate();
        CartesianCoordinate cc2 = CartesianCoordinate.getOrCreateCoordinate();

        //ACT
        //ASSERT
        assertTrue(cc1.isEqual(cc2));
        assertTrue(cc2.isEqual(cc1));
    }

    @Test
    public void testDoIsEqualComplex() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        CartesianCoordinate cc = CartesianCoordinate.getOrCreateCoordinate(5,5,5);

        //ACT
        //ASSERT
        assertTrue(cc.isEqual(c7));
        assertTrue(c7.isEqual(cc));
    }

    @Test
    public void testEqualsComplex()   {
        //ARRANGE
        CartesianCoordinate cc =CartesianCoordinate.getOrCreateCoordinate(5,5,5);
        CartesianCoordinate cc2 = CartesianCoordinate.getOrCreateCoordinate(5,5,5);


        //ACT
        //ASSERT
        assertTrue(cc.equals(cc2));
        assertTrue(cc2.equals(cc));

    }

    @Test
    public void testNotDoIsEqualComplex() throws Coordinate.CheckedCoordinateException {
        //ARRANGE
        CartesianCoordinate cc = CartesianCoordinate.getOrCreateCoordinate(5,6,5);

        //ACT
        //ASSERT
        assertFalse(cc.isEqual(c2));
    }


    @Test
    public void testHashCode()   {
        //ARRANGE
        CartesianCoordinate cc1 = CartesianCoordinate.getOrCreateCoordinate(5,5,5);
        CartesianCoordinate cc2 = CartesianCoordinate.getOrCreateCoordinate(5,5,5);

        int h1 = cc1.hashCode();
        int h2 = cc2.hashCode();

        //ACT
        //ASSERT
        assertTrue(h1==h2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentExceptions()   {
        c1.getDistance(cNull);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testValueIsNan(){
        CartesianCoordinate cc =CartesianCoordinate.getOrCreateCoordinate(1,1,Double.NaN);
    }

    @Test
    public void testImmutiability(){
      //Nothing to test because all attributes of CartesianCoordinate are final and no setters offered!
    }

    @Test
    public void testShareability(){
        //ARRANGE
        CartesianCoordinate cc1 = CartesianCoordinate.getOrCreateCoordinate();
        CartesianCoordinate cc2 = CartesianCoordinate.getOrCreateCoordinate();

        //ACT & ASSERT
        System.out.println(cc1);
        assertTrue(cc1==cc2);

    }




}
