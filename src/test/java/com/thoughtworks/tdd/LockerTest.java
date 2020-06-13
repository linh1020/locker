package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//DONE GIVEN LOCKER有空间，包 WHEN 存包 THEN 存包成功，得到票
//DONE GIVEN LOCKER没有空间 WHEN 存包 THEN 存包失败
//DONE GIVEN 有效的票，WHEN 取包 THEN 取包成功
//DONE GIVEN 取过包的有票 WHEN 取包 THEN 取包失败
//TODO GIVEN 伪造票据 WHEN 取包 THEN 取包失败
public class LockerTest {

    @Test
    void should_get_ticket_given_not_full_locker_and_bag_when_save_bag() throws Exception {
        Locker locker = new Locker(5);
        Bag bag = new Bag();

        Ticket ticket = locker.save(bag);

        assertNotNull(ticket);
    }

    @Test
    void should_fail_given_full_locker_and_bag_when_save_bag() {
        Locker locker = new Locker(0);
        Bag bag = new Bag();

        Exception exception = assertThrows(Exception.class, () -> locker.save(bag));

        assertEquals("Locker is full", exception.getMessage());
    }

    @Test
    void should_get_bag_given_not_full_locker_and_ticket_when_take_bag() throws Exception {
        Locker locker = new Locker(5);
        Bag givenBag = new Bag();
        Ticket ticket = locker.save(givenBag);

        Bag receivedBag = locker.take(ticket);

        assertEquals(givenBag, receivedBag);
    }

    @Test
    void should_fail_given_not_full_locker_and_used_ticket_when_take_bag() throws Exception {
        Locker locker = new Locker(5);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        locker.take(ticket);

        Exception exception = assertThrows(Exception.class, () -> locker.take(ticket));

        assertEquals("Illegal ticket", exception.getMessage());

    }
}