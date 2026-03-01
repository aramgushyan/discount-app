package org.yaguar.partnerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaguar.partnerservice.entity.Status;
import org.yaguar.partnerservice.entity.UnitEntity;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    List<UnitEntity> findAllByStatus(Status status);

    Optional<UnitEntity> findByIdAndStatus(Long id, Status status);
}
