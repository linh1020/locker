package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;

import java.util.List;

public class LockerRobotManager {
    private final List<Locker> lockers;

    public LockerRobotManager(List<Locker> lockers, List<Object> emptyList) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        return lockers.get(0).save(bag);
    }
}