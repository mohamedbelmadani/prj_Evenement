package ServiceTest;

import ma.ehei.prj_Events.Models.Event;
import ma.ehei.prj_Events.Repository.EventRepository;
import ma.ehei.prj_Events.Services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;  // Mock the repository

    @InjectMocks
    private EventService eventService;  // Inject mock repository into the service

    @Test
    void shouldReturnAllEvents() {
        Event event1 = new Event(1L, "Event1", "Description1", new Date(), "Location1", 100, 10);
        Event event2 = new Event(2L, "Event2", "Description2", new Date(), "Location2", 200, 20);

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        List<Event> events = eventService.getAllEvents();

        assertEquals(2, events.size());
        assertEquals("Event1", events.get(0).getName());
        assertEquals("Event2", events.get(1).getName());
    }

    @Test
    void shouldCreateEvent() {
        Event event = new Event(1L, "Event1", "Description1", new Date(), "Location1", 100, 10);
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event);

        Event createdEvent = eventService.CreateEvent(event);

        assertNotNull(createdEvent);
        assertEquals("Event1", createdEvent.getName());
    }
}
