package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.junit.Assert.*;


public class CarTypeTest {

    CarType ct;
    CarManager cm;

    @Before
    public void setupTest(){
      cm = CarManager.getInstance();
        this.ct=  new CarType("defaultOEM", "defaultModel", 2020, cm);

    }

    @Test
    public void testCreateInstance(){
        //ARRANGE

        //Act & ASSERT
        Assert.assertTrue(ct.getModel().equals("defaultModel"));
        Assert.assertTrue(ct.getCarOEMName().equals("defaultOEM"));
        Assert.assertTrue(ct.getBuildYear() == (2020));
        Assert.assertTrue(ct.getCarManager() == cm);

    }

    @Test
    public void testAddSubtype(){
        CarType ct2 = new CarType("defaultOEM", "defaultModel", 2020,this.cm );
        ct.addSubType(ct2);

        Iterator<CarType> it = ct.getSubTypeIterator();
        Assert.assertTrue(ct2==it.next());
    }


    @Test
    public void testSetSuperType(){
        CarType ct2 = new CarType("defaultOEM", "defaultModel", 2020,this.cm );
        ct.setSuperType(ct2);
        Assert.assertTrue(ct.getSuperType()==ct2);
    }

    @Test
    public void testIsSubtype(){
        CarType ct2 = new CarType("defaultOEM", "defaultModel", 2020,this.cm );
        ct.setSuperType(ct2);

        boolean result = ct.isSubType(ct2);

        Assert.assertTrue(result);


    }

}
