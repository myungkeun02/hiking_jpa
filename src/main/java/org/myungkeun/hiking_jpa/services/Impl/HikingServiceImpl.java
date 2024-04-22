package org.myungkeun.hiking_jpa.services.Impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_jpa.dto.hiking.HikingDto;
import org.myungkeun.hiking_jpa.dto.hiking.HikingResponseDto;
import org.myungkeun.hiking_jpa.entities.Hiking;
import org.myungkeun.hiking_jpa.repositories.HikingRepository;
import org.myungkeun.hiking_jpa.services.HikingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class HikingServiceImpl implements HikingService {
    private final HikingRepository hikingRepository;
    @Override
    public String insertHiking(HikingDto hikingDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        Hiking hiking = new Hiking();
        hiking.setTitle(hikingDto.getTitle());
        hiking.setHikeStartDate(LocalDateTime.parse(hikingDto.getHikeStartDate(), formatter));
        hiking.setHikeEndDate(LocalDateTime.parse(hikingDto.getHikeEndDate(), formatter));
        hiking.setDistance(hiking.getDistance());
        hiking.setElevation(hiking.getElevation());
        hiking.setEstimatedDuration(hiking.getEstimatedDuration());
        hikingRepository.save(hiking);
        return "success hiking info";
    }

    @Override
    public HikingResponseDto getHikingById(Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

//        Hiking hiking = hikingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("찾지못함"));
//        HikingResponseDto response = HikingResponseDto.builder()
//                .id(hiking.getId())
//                .title(hiking.getTitle())
//                .hikeStartDate(LocalDateTime.parse(hiking.getHikeStartDate(), formatter))
        return hikingRepository.findById(id)
                .map(e -> {
                    return HikingResponseDto.builder()
                            .id(e.getId())
                            .title(e.getTitle())
                            .hikeStartDate(e.getHikeStartDate().format(formatter))
                            .hikeEndDate(e.getHikeEndDate().format(formatter))
                            .distance(e.getDistance())
                            .elevation(e.getElevation())
                            .estimatedDuration(e.getEstimatedDuration())
                            .description(e.getDescription())
                            .build();
                }).orElseThrow(() -> new RuntimeException("에러"));
    }

    @Override
    public List<HikingResponseDto> searchHiking() {
        List<HikingResponseDto> hikingList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        List<Hiking> entities = hikingRepository.findAll();

        if(entities != null && entities.size() > 0) {
            hikingList = entities.stream().map(e -> {
                return HikingResponseDto.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .hikeStartDate(e.getHikeEndDate().format(formatter))
                        .hikeEndDate(e.getHikeEndDate().format(formatter))
                        .distance(e.getDistance())
                        .estimatedDuration(e.getEstimatedDuration())
                        .elevation(e.getElevation())
                        .description(e.getDescription())
                        .build();
            }).collect(Collectors.toList());
        }
        return hikingList;
    }




}
