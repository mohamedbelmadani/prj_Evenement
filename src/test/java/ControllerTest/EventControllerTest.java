package ControllerTest;

import ma.ehei.prj_Events.Models.Event;
import ma.ehei.prj_Events.Controllers.EventController;
import ma.ehei.prj_Events.PrjEventsApplication;
import ma.ehei.prj_Events.Services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes =  PrjEventsApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)  // If you're using JUnit 5
class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSaveAndRetrieveEvent() throws Exception {
        Event newEvent = new Event(null, "Event4", "Description4", new Date(), "Location4", 200, 20);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Event4\", \"description\":\"Description4\", \"date\":\"2024-01-01T00:00:00\", \"location\":\"Location4\", \"maxParticipants\":200, \"currentParticipants\":20}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Event4"))
                .andExpect(jsonPath("$.description").value("Description4"))
                .andExpect(jsonPath("$.location").value("Location4"));
    }
}
