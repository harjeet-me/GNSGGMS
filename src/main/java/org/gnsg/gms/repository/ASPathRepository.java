package org.gnsg.gms.repository;

import org.gnsg.gms.domain.ASPath;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ASPath entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ASPathRepository extends JpaRepository<ASPath, Long> {
}
