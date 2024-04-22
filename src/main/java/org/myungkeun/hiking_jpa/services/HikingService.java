package org.myungkeun.hiking_jpa.services;

import org.myungkeun.hiking_jpa.dto.hiking.HikingDto;
import org.myungkeun.hiking_jpa.dto.hiking.HikingResponseDto;

import java.util.List;

public interface HikingService {
    String insertHiking(HikingDto hikingDto);

    HikingResponseDto getHikingById(Long id);

    List<HikingResponseDto> searchHiking();
}
