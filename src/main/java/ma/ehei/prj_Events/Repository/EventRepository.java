package ma.ehei.prj_Events.Repository;

import ma.ehei.prj_Events.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

}
