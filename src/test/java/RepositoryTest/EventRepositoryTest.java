package RepositoryTest;

import ma.ehei.prj_Events.Models.Event;
import ma.ehei.prj_Events.Repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Use Mockito extension for mocking
class EventRepositoryTest {

    @Mock
    private EventRepository eventRepo;

    @InjectMocks
    private EventRepositoryTest eventRepositoryTest;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event(null, "Event1", "Description1", new Date(), "Location1", 100, 10);
    }

    @Test
    void shouldSaveAndRetrieveEvent() {
        // Mock the behavior of the repository save method
        when(eventRepo.save(event)).thenReturn(event);

        // Call the repository save method
        Event savedEvent = eventRepo.save(event);

        // Assert that the event was saved correctly
        assertNotNull(savedEvent);
        assertEquals("Event1", savedEvent.getName());

        // Verify that save method was called exactly once
        verify(eventRepo, times(1)).save(event);

        // Mock the findAll method to return a list of events
        when(eventRepo.findAll()).thenReturn(List.of(event));

        // Retrieve the list of events
        List<Event> events = eventRepo.findAll();

        // Assert that the retrieved event is correct
        assertEquals(1, events.size());
        assertEquals("Event1", events.get(0).getName());
    }
}
