package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "visitor_stats")
public class VisitorStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalVisitors;

    public VisitorStats() {
    }

    public VisitorStats(Long totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalVisitors() {
        return totalVisitors;
    }

    public void setTotalVisitors(Long totalVisitors) {
        this.totalVisitors = totalVisitors;
    }
}
