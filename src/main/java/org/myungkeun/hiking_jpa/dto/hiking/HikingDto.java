package org.myungkeun.hiking_jpa.dto.hiking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HikingDto {
    private String title;
    private String hikeStartDate;
    private String hikeEndDate;
    private Integer distance;
    private Integer estimatedDuration;
    private Integer elevation;
    private String description;
}
