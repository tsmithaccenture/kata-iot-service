package com.accenture.test;

import com.accenture.IotImage;
import com.accenture.LightController;
import com.accenture.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LightControllerTest {

    private static final String INITIAL_IMAGE_URL = "test/url";
    @Mock
    private Model model;

    @Mock
    private Store store;

    @InjectMocks
    private LightController controller;

    @Before
    public void setUp(){
        when(store.getImageUrl()).thenReturn(INITIAL_IMAGE_URL);
    }

    @Test
    public void base_ShouldRedirectToHome(){
        assertEquals("redirect:/home", controller.base());
    }

    @Test
    public void homePage_ShouldRender(){
        String viewName = controller.homePage(model);

        assertEquals("home", viewName);

        verify(model).addAttribute("imageUrl", INITIAL_IMAGE_URL);
    }

    @Test
    public void imageUrl_ShouldReturnImageUrl(){
        String imageUrl = controller.imageUrl();

        assertEquals(INITIAL_IMAGE_URL, imageUrl);
    }

    @Test
    public void on_ShouldSetImageUrl(){
        controller.on();

        verify(store).setImageUrl(IotImage.ON_IMAGE_URL);
    }

    @Test
    public void off_ShouldSetImageUrl(){
        controller.off();

        verify(store).setImageUrl(IotImage.OFF_IMAGE_URL);
    }
}
