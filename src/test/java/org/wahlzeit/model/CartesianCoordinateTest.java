package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static  org.junit.Assert.*;

public class CartesianCoordinateTest {



    //Delta value used for comparison of double values.
    private static double DELTA = 0.0000001;


    //different coordinates that are used multiple times in the test methods
    private CartesianCoordinate c1,c2,c3,c4,c5,c6,cNull;


    @Before
    public void setupCoordinates() {

        this.c1 = new CartesianCoordinate(1.2334, 2.3451651, 3.4565196814981);
        this.c2 = new CartesianCoordinate(3.4565196814981, 2.3451651, 1.2334);

        this.c3 = new CartesianCoordinate(-1.2334, -2.3451651, -3.4565196814981);
        this.c4 = new CartesianCoordinate(-3.4565196814981, -2.3451651, -1.2334);

        this.c5 = new CartesianCoordinate(1, 1, 1);
        this.c6 = new CartesianCoordinate(-1, -1, -1);

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
    public void testSetter(){

        //ACT& ASSERT
        assertEquals(this.c1.getX(),1.2334, DELTA);
        assertEquals(this.c1.getY(),2.3451651, DELTA);
        assertEquals(this.c1.getZ(), 3.4565196814981,DELTA);

        assertEquals(this.c2.getX(),3.4565196814981, DELTA);
        assertEquals(this.c2.getY(), 2.3451651,DELTA);
        assertEquals(this.c2.getZ(),1.2334, DELTA);

        assertEquals(this.c3.getX(),-1.2334, DELTA);
        assertEquals(this.c3.getY(),-2.3451651, DELTA);
        assertEquals(this.c3.getZ(), -3.4565196814981,DELTA);

        assertEquals(this.c4.getX(),-3.4565196814981, DELTA);
        assertEquals(this.c4.getY(), -2.3451651,DELTA);
        assertEquals(this.c4.getZ(),-1.2334, DELTA);

        assertEquals(this.c5.getX(), 1,DELTA);
        assertEquals(this.c5.getY(), 1,DELTA);
        assertEquals(this.c5.getZ(), 1,DELTA);

        assertEquals(this.c6.getX(),-1, DELTA);
        assertEquals(this.c6.getY(),-1, DELTA);
        assertEquals(this.c6.getZ(), -1,DELTA);
    }


    @Test
    public void testGetDistanceSimple(){
        //ARRANGE
        CartesianCoordinate c1 = new CartesianCoordinate(1,1,1);
        CartesianCoordinate c2 = new CartesianCoordinate(1,1,1);

        //ACT
        double result = c1.getDistance(c2);

        //ASSERT
        //assertEquals(true,c1.equals(c2));
        assertEquals(0,result, 7);

    }


    @Test
    public void testGetDistanceComplex(){
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
        assertEquals(cc1,new CartesianCoordinate().getDistance(c1), 7);
        assertEquals(cc2,new CartesianCoordinate().getDistance(c2), 7);
        assertEquals(cc3,new CartesianCoordinate().getDistance(c3), 7);
        assertEquals(cc4,new CartesianCoordinate().getDistance(c4), 7);
        assertEquals(cc5,new CartesianCoordinate().getDistance(c5), 7);
        assertEquals(cc5,new CartesianCoordinate().getDistance(c6), 7);

    }


    @Test
    public void testEqualMethods(){

        //ARRANGE
        double a = 1.1;
        double b = 1.121345464374852;
        double c = 1.1341646167137311717578687257867863;
        double d = 1.1787786578657867865;
        double e = 1.1456456;

        CartesianCoordinate c1 = new CartesianCoordinate(b,c,d);
        CartesianCoordinate c2 = new CartesianCoordinate(1.1213454222,1.1341646222,1.1787786222);
        CartesianCoordinate c3 = new CartesianCoordinate(1.12,1.1341646222,1.1787786222);


        //ACT
        boolean result = c1.isEqual(c2);
        boolean result2= c1.equals(c2);

        boolean result3= c2.isEqual(c3);
        boolean result4= c2.equals(c3);

        //ASSERT
        assertEquals(true,result);
        assertEquals(true,result2);

        assertEquals(false,result3);
        assertEquals(false,result4);


    }




//TODO: Code wieder einkommentieren

    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentExceptions(){
        c1.getDistance(cNull);
    }





}
