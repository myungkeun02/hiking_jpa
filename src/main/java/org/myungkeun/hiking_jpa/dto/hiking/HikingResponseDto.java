package org.myungkeun.hiking_jpa.dto.hiking;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data

public class HikingResponseDto {
    private Long id;
    private String title;
    private String hikeStartDate;
    private String hikeEndDate;
    private Integer distance;
    private Integer estimatedDuration;
    private Integer elevation;
    private String description;
}
