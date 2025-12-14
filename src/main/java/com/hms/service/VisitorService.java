package com.hms.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import com.hms.repository.VisitorStatsRepository;
import com.hms.entity.VisitorStats;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VisitorService {

    private final AtomicLong totalVisitors = new AtomicLong(0);
    private Long statsId = null;

    // Track active sessions: SessionID -> LastActiveTime (Epoch start)
    private final java.util.concurrent.ConcurrentHashMap<String, Long> activeSessions = new java.util.concurrent.ConcurrentHashMap<>();

    @Autowired
    private VisitorStatsRepository statsRepository;

    @PostConstruct
    public void init() {
        // Load initial total visitors from DB
        VisitorStats stats = statsRepository.findFirstByOrderByIdAsc();
        if (stats != null) {
            totalVisitors.set(stats.getTotalVisitors());
            statsId = stats.getId();
        } else {
            // Initial run, create default
            stats = new VisitorStats(0L);
            stats = statsRepository.save(stats);
            totalVisitors.set(0);
            statsId = stats.getId();
        }
    }

    public void logVisit(String sessionId) {
        // Update heartbeat
        activeSessions.put(sessionId, System.currentTimeMillis());
        // Clean up old sessions (inactive for > 2 minutes) to keep count accurate
        long threshold = System.currentTimeMillis() - (2 * 60 * 1000);
        activeSessions.entrySet().removeIf(entry -> entry.getValue() < threshold);
    }

    public int getLiveVisitors() {
        // Double check cleanup on read to be sure
        long threshold = System.currentTimeMillis() - (2 * 60 * 1000);
        activeSessions.entrySet().removeIf(entry -> entry.getValue() < threshold);
        return activeSessions.size();
    }

    public void incrementTotalVisitors() {
        long newVal = totalVisitors.incrementAndGet();
        // Persist to DB using the cached statsId
        if (statsId != null) {
            VisitorStats stats = statsRepository.findById(statsId).orElse(null);
            if (stats == null) {
                // Should not happen, but recover if row deleted
                stats = new VisitorStats(newVal);
                stats = statsRepository.save(stats);
                statsId = stats.getId();
            } else {
                stats.setTotalVisitors(newVal);
                statsRepository.save(stats);
            }
        }
    }

    public long getTotalVisitors() {
        return totalVisitors.get();
    }
}
