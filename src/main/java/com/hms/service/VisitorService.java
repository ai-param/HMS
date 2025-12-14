package com.hms.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import com.hms.repository.VisitorStatsRepository;
import com.hms.entity.VisitorStats;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VisitorService {

    private final AtomicInteger liveVisitors = new AtomicInteger(0);
    private final AtomicLong totalVisitors = new AtomicLong(0);

    @Autowired
    private VisitorStatsRepository statsRepository;

    @PostConstruct
    public void init() {
        // Load initial total visitors from DB
        statsRepository.findById(1L).ifPresentOrElse(
                stats -> totalVisitors.set(stats.getTotalVisitors()),
                () -> {
                    // Initial run, create default
                    VisitorStats stats = new VisitorStats(0L);
                    statsRepository.save(stats);
                    totalVisitors.set(0);
                });
    }

    public void incrementLiveVisitors() {
        liveVisitors.incrementAndGet();
    }

    public void decrementLiveVisitors() {
        liveVisitors.decrementAndGet();
    }

    public int getLiveVisitors() {
        return Math.max(0, liveVisitors.get());
    }

    public void incrementTotalVisitors() {
        long newVal = totalVisitors.incrementAndGet();
        // Persist to DB
        VisitorStats stats = statsRepository.findById(1L).orElse(new VisitorStats(newVal));
        stats.setTotalVisitors(newVal);
        statsRepository.save(stats);
    }

    public long getTotalVisitors() {
        return totalVisitors.get();
    }
}
