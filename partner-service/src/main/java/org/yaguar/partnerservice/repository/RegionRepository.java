package org.yaguar.partnerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaguar.partnerservice.entity.RegionEntity;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
}
