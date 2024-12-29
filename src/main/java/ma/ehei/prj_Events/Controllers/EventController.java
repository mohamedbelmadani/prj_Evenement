package ma.ehei.prj_Events.Controllers;

import ma.ehei.prj_Events.Models.Event;
import ma.ehei.prj_Events.Repository.EventRepository;
import ma.ehei.prj_Events.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> GetEvents()
    {
        return eventService.getAllEvents();
    }

    /*@PostMapping
    public Event CreeEvent(@RequestBody Event event)
    {
        Event createdEvent = eventService.CreateEvent(event);
        return eventService.CreateEvent(event);
    }*/
    @PostMapping
    public ResponseEntity<Event> CreeEvent(@RequestBody Event event) {
        Event createdEvent = eventService.CreateEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
}
