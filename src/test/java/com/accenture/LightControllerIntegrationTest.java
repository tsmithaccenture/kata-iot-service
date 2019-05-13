package com.accenture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LightController.class)
@Import(Store.class)
public class LightControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Store store;

    @Test
    public void base_ShouldRedirectToHome() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void home_ShouldRender() throws Exception {
        final String testImageUrl = "/test/image/location";
        store.setImageUrl(testImageUrl);

        mvc.perform( MockMvcRequestBuilders
                .get("/home")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("imageUrl", testImageUrl));
    }

    @Test
    public void imageUrl_ShouldReturnImageUrl() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/imageurl")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void on_ShouldUpdateToOnImage() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/turn/on")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void off_ShouldUpdateToOnImage() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/turn/off")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
    }
}