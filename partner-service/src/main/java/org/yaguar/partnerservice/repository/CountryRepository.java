package org.yaguar.partnerservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaguar.partnerservice.entity.CountryEntity;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {
    @Query("SELECT c from CountryEntity c LEFT JOIN FETCH c.regions WHERE c.id = :id")
    Optional<CountryEntity> findById(Long id);

    Optional<CountryEntity> findByIdAndByUser(Long id);
}
