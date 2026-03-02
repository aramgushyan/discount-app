package org.yaguar.partnerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaguar.partnerservice.entity.CountryEntity;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {
    @EntityGraph(attributePaths = {"regions"})
    Page<CountryEntity> findAll(Pageable pageable);
}
