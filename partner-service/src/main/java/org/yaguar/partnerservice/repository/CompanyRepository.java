package org.yaguar.partnerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaguar.partnerservice.entity.CompanyEntity;
import org.yaguar.partnerservice.entity.Status;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {
    Page<CompanyEntity> findAllByStatus(Pageable pageable, Status status);

    Optional<CompanyEntity> findByIdAndStatus(Long id, Status status);
}
