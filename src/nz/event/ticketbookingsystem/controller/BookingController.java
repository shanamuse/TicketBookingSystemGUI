/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nz.event.ticketbookingsystem.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import nz.event.ticketbookingsystem.model.Seat;
import nz.event.ticketbookingsystem.model.SeatStatus;

/**
 *
 * @author shanamusekiwa
 */
public class BookingController {
    private static final double PRICE_PER_SEAT = 84.00;
    private final List<Seat> seats = new ArrayList<>();

    public BookingController() {
        initializeSeats();
    }

    private void initializeSeats() {
        char[] rows = {'A', 'B', 'C', 'D', 'E'};
        for (char row : rows) {
            for (int col = 1; col <= 5; col++) {
                Seat seat = new Seat(row + String.valueOf(col), SeatStatus.RESERVED);
                // Only middle seats in row C (2, 3, 4) are available
                if (row == 'C' && col >= 2 && col <= 4) {
                    seat.setStatus(SeatStatus.AVAILABLE);
                }
                seats.add(seat);
            }
        }
    }

    public List<Seat> getAllSeats() {
        return seats;
    }

    public String bookSeats(List<String> selectedCodes) {
        double totalPrice = selectedCodes.size() * PRICE_PER_SEAT;
        return NumberFormat.getCurrencyInstance(Locale.US).format(totalPrice);
    }
}