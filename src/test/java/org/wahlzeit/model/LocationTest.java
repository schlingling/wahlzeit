package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static  org.junit.Assert.*;

public class LocationTest {


    /**
     * Delta value used for comparison of double values.
     */
    private static double DELTA = 0.0000001;


    //4 different coordinates that are used multiple times in the test methods
    private Location l1, l2, l3, l4, l5, l6, lNull;


    @Before
    public void setupLocations() {

        this.l1 = new Location(1.2334, 2.3451651, 3.4565196814981);
        this.l2 = new Location(3.4565196814981, 2.3451651, 1.2334);

        this.l3 = new Location(-1.2334, -2.3451651, -3.4565196814981);
        this.l4 = new Location(-3.4565196814981, -2.3451651, -1.2334);

        this.l5 = new Location(1, 1, 1);
        this.l6 = new Location(-1, -1, -1);

        this.lNull = null;

    }

    @Test
    public void testGetter(){

        //ACT& ASSERT
        assertEquals(1.2334,this.l1.getX(), DELTA);
        assertEquals(2.3451651,this.l1.getY(), DELTA);
        assertEquals(3.4565196814981,this.l1.getZ(), DELTA);

        assertEquals(3.4565196814981,this.l2.getX(), DELTA);
        assertEquals(2.3451651,this.l2.getY(), DELTA);
        assertEquals(1.2334,this.l2.getZ(), DELTA);

        assertEquals(-1.2334,this.l3.getX(), DELTA);
        assertEquals(-2.3451651,this.l3.getY(), DELTA);
        assertEquals(-3.4565196814981,this.l3.getZ(), DELTA);

        assertEquals(-3.4565196814981,this.l4.getX(), DELTA);
        assertEquals(-2.3451651,this.l4.getY(), DELTA);
        assertEquals(-1.2334,this.l4.getZ(), DELTA);

        assertEquals(1,this.l5.getX(), DELTA);
        assertEquals(1,this.l5.getY(), DELTA);
        assertEquals(1,this.l5.getZ(), DELTA);

        assertEquals(-1,this.l6.getX(), DELTA);
        assertEquals(-1,this.l6.getY(), DELTA);
        assertEquals(-1,this.l6.getZ(), DELTA);


    }


    @Test
    public void testSetter(){

        //ACT& ASSERT
        assertEquals(this.l1.getX(),1.2334, DELTA);
        assertEquals(this.l1.getY(),2.3451651, DELTA);
        assertEquals(this.l1.getZ(), 3.4565196814981,DELTA);

        assertEquals(this.l2.getX(),3.4565196814981, DELTA);
        assertEquals(this.l2.getY(), 2.3451651,DELTA);
        assertEquals(this.l2.getZ(),1.2334, DELTA);

        assertEquals(this.l3.getX(),-1.2334, DELTA);
        assertEquals(this.l3.getY(),-2.3451651, DELTA);
        assertEquals(this.l3.getZ(), -3.4565196814981,DELTA);

        assertEquals(this.l4.getX(),-3.4565196814981, DELTA);
        assertEquals(this.l4.getY(), -2.3451651,DELTA);
        assertEquals(this.l4.getZ(),-1.2334, DELTA);

        assertEquals(this.l5.getX(), 1,DELTA);
        assertEquals(this.l5.getY(), 1,DELTA);
        assertEquals(this.l5.getZ(), 1,DELTA);

        assertEquals(this.l6.getX(),-1, DELTA);
        assertEquals(this.l6.getY(),-1, DELTA);
        assertEquals(this.l6.getZ(), -1,DELTA);
    }


    @Test
    public void testGetDistanceSimple(){
        //ARRANGE
        Location l1 = new Location(1,1,1);
        Location l2 = new Location(1,1,1);

        //ACT
        double result = l1.getDistance(l2);

        //ASSERT
        assertEquals(true,l1.equals(l2));
        assertEquals(0,result, 7);

    }


    @Test
    public void testGetDistanceComplex(){
        //ARRANGE
        double cc1,cc2,cc3,cc4,cc5,cc6;

        //ACT --> Calculate Betrag des Ortsvektor
        cc1=Math.sqrt(Math.pow(l1.getX(), 2) + Math.pow(l1.getY(), 2) + Math.pow(l1.getZ(), 2));
        cc2=Math.sqrt(Math.pow(l2.getX(), 2) + Math.pow(l2.getY(), 2) + Math.pow(l2.getZ(), 2));
        cc3=Math.sqrt(Math.pow(l3.getX(), 2) + Math.pow(l3.getY(), 2) + Math.pow(l3.getZ(), 2));
        cc4=Math.sqrt(Math.pow(l4.getX(), 2) + Math.pow(l4.getY(), 2) + Math.pow(l4.getZ(), 2));
        cc5=Math.sqrt(Math.pow(l5.getX(), 2) + Math.pow(l5.getY(), 2) + Math.pow(l5.getZ(), 2));
        cc6=Math.sqrt(Math.pow(l6.getX(), 2) + Math.pow(l6.getY(), 2) + Math.pow(l6.getZ(), 2));


        //ASSERT
        assertEquals(cc1,new Location().getDistance(l1), 7);
        assertEquals(cc2,new Location().getDistance(l2), 7);
        assertEquals(cc3,new Location().getDistance(l3), 7);
        assertEquals(cc4,new Location().getDistance(l4), 7);
        assertEquals(cc5,new Location().getDistance(l5), 7);
        assertEquals(cc6,new Location().getDistance(l6), 7);

    }


    @Test
    public void testEqualMethod(){

        //ARRANGE
        double b = 1.121345464374852;
        double c = 1.1341646167137311717578687257867863;
        double d = 1.1787786578657867865;

        Location c1 = new Location(b,c,d);
        Location c2 = new Location(1.1213454222,1.1341646222,1.1787786222);
        Location c3 = new Location(1.12,1.1341646222,1.1787786222);


        //ACT
        boolean result2= c1.equals(c2);
        boolean result4= c2.equals(c3);

        //ASSERT
        assertEquals(true,result2);
        assertEquals(false,result4);


    }


    @Test(expected = NullPointerException.class)
    public void testNullPointerException(){
        l1.getDistance(lNull);
    }





}