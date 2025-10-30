/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nz.event.ticketbookingsystem.controller;

import java.util.Arrays;
import java.util.List;
import nz.event.ticketbookingsystem.model.Seat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author madhurima
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

/**
 * Unit tests for BookingController.
 * These tests check whether the BookingController behaves as expected —
 * ensuring seats are initialized correctly and total booking price is accurate.
 *
 * @author madhurima
 */
public class BookingControllerTest {

    /**
     * Test that getAllSeats() returns a non-empty list with 25 total seats.
     */
    @Test
    public void testGetAllSeats_NotEmpty() {
        BookingController controller = new BookingController();
        List<Seat> seats = controller.getAllSeats();
        assertNotNull(seats);
        assertFalse(seats.isEmpty());
        assertEquals(25, seats.size());
    }

    /**
     * Test that bookSeats() correctly calculates the total cost
     * when the user selects multiple seats.
     */
    @Test
    public void testBookSeats_CalculatesTotalCorrectly() {
        BookingController controller = new BookingController();
        String result = controller.bookSeats(Arrays.asList("C2", "C3", "C4"));
        assertEquals("$252.00", result);
    }

    /**
     * Test that bookSeats() returns "$0.00" when no seats are selected.
     */
    @Test
    public void testBookSeats_EmptyListReturnsZero() {
        BookingController controller = new BookingController();
        String result = controller.bookSeats(Arrays.asList());
        assertEquals("$0.00", result);
    }
}