package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//DONE GIVEN LOCKER有空间，包 WHEN 存包 THEN 存包成功，得到票
//DONE GIVEN LOCKER没有空间 WHEN 存包 THEN 存包失败
//DONE GIVEN 有效的票，WHEN 取包 THEN 取包成功
//DONE GIVEN 取过包的有票 WHEN 取包 THEN 取包失败
//DONE GIVEN 伪造票据 WHEN 取包 THEN 取包失败
public class LockerTest {

    @Test
    void should_get_ticket_when_save_given_not_full_locker() throws LockerIsFullException {
        Locker locker = new Locker(5);
        Bag bag = new Bag();

        Ticket ticket = locker.save(bag);

        assertNotNull(ticket);
    }

    @Test
    void should_fail_when_save_given_full_locker_and_bag() throws LockerIsFullException {
        Locker locker = new Locker(1);
        locker.save(new Bag());
        Bag bag = new Bag();

        assertThrows(LockerIsFullException.class, () -> locker.save(bag));

    }

    @Test
    void should_get_bag_when_take_given_not_full_locker_and_ticket() throws Exception {
        Locker locker = new Locker(5);
        Bag givenBag = new Bag();
        Ticket ticket = locker.save(givenBag);

        Bag receivedBag = locker.take(ticket);

        assertEquals(givenBag, receivedBag);
    }

    @Test
    void should_fail_when_take_given_not_full_locker_and_used_ticket() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker = new Locker(5);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        locker.take(ticket);

        assertThrows(TicketIsInvalidException.class, () -> locker.take(ticket));

    }

    @Test
    void should_fail_when_take_given_not_full_locker_and_not_valid_ticket() {
        Locker locker = new Locker(5);

        assertThrows(TicketIsInvalidException.class, () -> locker.take(new Ticket()));
    }
}
