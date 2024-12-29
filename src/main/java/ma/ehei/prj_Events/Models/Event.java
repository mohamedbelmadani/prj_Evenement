package ma.ehei.prj_Events.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Event
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    private Date date;

    private String location;


    private int maxParticipants;

    private int currentParticipants = 0;



}
