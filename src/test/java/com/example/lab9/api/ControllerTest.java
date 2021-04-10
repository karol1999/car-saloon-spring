package com.example.lab9.api;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void addVehicle() throws Exception {
    }

    @Test
    void deleteVehicle() {
    }

    @Test
    void vehiclesToCsv() {
    }

    @Test
    void getAllShowrooms() {
    }

    @Test
    void addShowroom() {
    }

    @Test
    void deleteShowroom() {
    }

    @Test
    void getAllVehiclesInShowroom() {
    }

    @Test
    void getShowroomFill() {
    }

    @Test
    void addRatingToShowroom() {
    }

    @Test
    void getRating() {
    }
}