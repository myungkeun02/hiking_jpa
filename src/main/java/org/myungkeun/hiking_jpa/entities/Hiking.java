package org.myungkeun.hiking_jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity

public class Hiking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    private LocalDateTime hikeStartDate;
    private LocalDateTime hikeEndDate;
    private Integer distance;
    private Integer estimatedDuration;
    private Integer elevation;
    private String description;
}
