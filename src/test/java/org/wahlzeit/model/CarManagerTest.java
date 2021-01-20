package org.wahlzeit.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class CarManagerTest {

    private CarManager cm1;

    @Before
    public void setupCarPhotos() {

        this.cm1=CarManager.getInstance();
    }


    @Test
    public void testGetInstance(){

       CarManager cm2 = cm1.getInstance();
       Assert.assertTrue(cm1==cm2);

   }

   @Test
    public void testGetOrCreateCarType(){

       CarType ct1 = CarManager.getOrCreateCarType("defaultOEM", "defaultModel", 2020);
       CarType ct2 = CarManager.getOrCreateCarType("defaultOEM", "defaultModel", 2020);
       Assert.assertTrue(ct1==ct2);


   }

   @Test
   public void testCreateCar(){
        Car c1 = cm1.createCar("defaultOEM", "defaultModel", 2020);
        Car c2 = cm1.createCar("defaultOEM", "defaultModel", 2020);

        Assert.assertTrue(c1.getType()==c2.getType());


   }

}
