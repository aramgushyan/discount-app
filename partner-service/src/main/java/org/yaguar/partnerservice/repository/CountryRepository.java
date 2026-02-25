package org.yaguar.partnerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaguar.partnerservice.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {
}
