package com.hms.repository;

import com.hms.entity.VisitorStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorStatsRepository extends JpaRepository<VisitorStats, Long> {
}
