package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LockerRobotDirectorTest {

    @Test
    @DisplayName("should get correct formatted report" +
            "when director get report " +
            "given director manage 1 manager, manager manage locker1 and locker2, manager manage no robot," +
            "locker's available capacity and capacity is 0,3; 4,5 individually")
    void task1() throws LockerIsFullException {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(new Locker(3), new Locker(5)), Collections.emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(lockerRobotManager);

        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());

        String report = lockerRobotDirector.getReport();

        assertEquals("M  4  8\n    L  0  3\n    L  4  5", report);
    }
}
