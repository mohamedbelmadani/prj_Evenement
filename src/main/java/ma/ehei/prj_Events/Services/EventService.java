package ma.ehei.prj_Events.Services;

import ma.ehei.prj_Events.Models.Event;
import ma.ehei.prj_Events.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event CreateEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

}
