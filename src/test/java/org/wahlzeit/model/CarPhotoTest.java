package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.Language;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class CarPhotoTest {

    private CarPhoto p1, p2, p3;

    @Before
    public void setupCarPhotos() {

        this.p1 = new CarPhoto();
        this.p2 = new CarPhoto();
        this.p3 = new CarPhoto();

    }

    @Test
    public void testSetter()throws MalformedURLException {

        //ARRANGE & ACT
        p1.setOwnerEmailAddress(EmailAddress.getFromString("test@testmaail.com"));
        p1.setOwnerHomePage(new URL("https://test.url"));
        p1.setOwnerId(100);
        p1.setOwnerLanguage(Language.GERMAN);
        p1.setOwnerName("Johannes");
        p1.setOwnerNotifyAboutPraise(true);
        p1.setStatus(PhotoStatus.DELETED);
        p1.setTags(new Tags("testtesttest"));
        p1.setWidthAndHeight(100,100);


        //ASSERT
        assertEquals(p1.getOwnerEmailAddress(),EmailAddress.getFromString("test@testmaail.com"));
        assertEquals( p1.getOwnerHomePage(),new URL("https://test.url"));
        assertEquals( p1.getOwnerId(),100);
        assertEquals(p1.getOwnerLanguage(),Language.GERMAN);
        assertEquals( p1.getOwnerName(), "Johannes");
        assertEquals( p1.getOwnerNotifyAboutPraise(),true);
        assertEquals( p1.getStatus(),PhotoStatus.DELETED);
        assertEquals( p1.getWidth(),100);
        assertEquals(p1.getHeight(),100);


    }


    @Test
    public void testGetter() throws MalformedURLException {

        //ARRANGE & ACT
        p1.setOwnerEmailAddress(EmailAddress.getFromString("test@testmaail.com"));
        p1.setOwnerHomePage(new URL("https://test.url"));
        p1.setOwnerId(100);
        p1.setOwnerLanguage(Language.GERMAN);
        p1.setOwnerName("Johannes");
        p1.setOwnerNotifyAboutPraise(true);
        p1.setStatus(PhotoStatus.DELETED);
        p1.setTags(new Tags("testtesttest"));
        p1.setWidthAndHeight(100,100);


        //ASSERT
        assertEquals(EmailAddress.getFromString("test@testmaail.com"), p1.getOwnerEmailAddress());
        assertEquals(new URL("https://test.url"), p1.getOwnerHomePage());
        assertEquals(100, p1.getOwnerId());
        assertEquals(Language.GERMAN, p1.getOwnerLanguage());
        assertEquals("Johannes", p1.getOwnerName());
        assertEquals(true, p1.getOwnerNotifyAboutPraise());
        assertEquals(PhotoStatus.DELETED, p1.getStatus());
        assertEquals(100, p1.getWidth());
        assertEquals(100, p1.getHeight());



    }


}
