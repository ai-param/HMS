package com.hms.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VisitorService {

    private final AtomicInteger liveVisitors = new AtomicInteger(0);
    private final AtomicLong totalVisitors = new AtomicLong(0);

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
        totalVisitors.incrementAndGet();
    }

    public long getTotalVisitors() {
        return totalVisitors.get();
    }
}
