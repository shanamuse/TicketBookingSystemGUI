/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nz.event.ticketbookingsystem.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Seat class.
 * Tests all getters, setters, and the toString() method.
 *
 * @author madhurima
 */
public class SeatTest {
    
    public SeatTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting Seat class tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("All Seat class tests completed.");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCode and setCode methods.
     */
    @Test
    public void testSetAndGetCode() {
        Seat seat = new Seat("A1", SeatStatus.AVAILABLE);
        seat.setCode("B2");
        assertEquals("B2", seat.getCode());
    }

    /**
     * Test of getStatus and setStatus methods.
     */
    @Test
    public void testSetAndGetStatus() {
        Seat seat = new Seat("C3", SeatStatus.AVAILABLE);
        seat.setStatus(SeatStatus.RESERVED);
        assertEquals(SeatStatus.RESERVED, seat.getStatus());
    }

    /**
     * Test of constructor and get methods.
     */
    @Test
    public void testSeatConstructor() {
        Seat seat = new Seat("D4", SeatStatus.RESERVED);
        assertEquals("D4", seat.getCode());
        assertEquals(SeatStatus.RESERVED, seat.getStatus());
    }

    /**
     * Test of toString method, ensuring it returns correct format.
     */
    @Test
    public void testToString() {
        Seat seat = new Seat("E5", SeatStatus.AVAILABLE);
        String expected = "E5 (AVAILABLE)";
        assertEquals(expected, seat.toString());
    }
}
