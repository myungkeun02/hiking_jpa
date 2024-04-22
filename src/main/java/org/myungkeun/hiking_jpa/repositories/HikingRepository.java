package org.myungkeun.hiking_jpa.repositories;

import org.myungkeun.hiking_jpa.entities.Hiking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikingRepository extends JpaRepository<Hiking, Long> {
}
