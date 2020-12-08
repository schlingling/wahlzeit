/*
 * LocationTest
 *
 * Copyright (c) 2020 by https://github.com/NicoHambauer/
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.wahlzeit.model.locationTest;


import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.location.AbstractCoordinate;
import org.wahlzeit.model.location.Coordinate;
import org.wahlzeit.model.location.Location;
import org.wahlzeit.services.DataObject;

import static org.junit.Assert.*;

public class LocationTest {

    private Location loc;
    private Location other_loc;

    @Before //each
    public void init() {
        loc = new Location();
    }


    //TODO: Einkommentieren

    @Test
    public void testSuperClass(){
        assertTrue(AbstractCoordinate.class.getSuperclass() == DataObject.class);
    }


    @Test
    public void testLocationCoordinateNotNull() {
        //arrange
        Coordinate c = loc.getCoordinate();
        //act
        //assert
        assertNotNull(c);
    }

    @Test
    public void testLocationIsDirty() {
        //arrange see init
        //act
        //assert
        assertTrue(loc.isDirty());
    }


    @Test
    public void testLocationEqualsOther() {
        //arrange
        other_loc = new Location();
        //act
        //assert

        boolean test = loc.equals(other_loc);
        assertTrue(test);
    }

    @Test
    public void testLocationNotEqualsOther() {
        //arrange
        other_loc = new Location(10,10,10);
        //act
        //assert
        assertFalse(loc.equals(other_loc));
    }


    //TODO: Code wieder einkommentieren



    @Test
    public void testLocationHashCodes() {
        //arrange
        other_loc = new Location();
        //act
        //assert
        assertEquals(loc.hashCode(), other_loc.hashCode());
    }

}