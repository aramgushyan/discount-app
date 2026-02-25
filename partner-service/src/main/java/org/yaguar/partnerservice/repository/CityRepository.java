package org.yaguar.partnerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaguar.partnerservice.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
}
