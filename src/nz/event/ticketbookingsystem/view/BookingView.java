/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nz.event.ticketbookingsystem.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import nz.event.ticketbookingsystem.controller.BookingController;
import nz.event.ticketbookingsystem.model.Seat;
import nz.event.ticketbookingsystem.model.SeatStatus;

/**
 *
 * @author madhurima
 */
public class BookingView extends JFrame {
    private JPanel mainPanel;
    private JPanel seatPanel;
    private JPanel infoPanel;
    private JLabel ticketLabel;
    private JLabel priceLabel;
    private final BookingController bookingController;
    private final Map<String, JButton> seatButtons = new HashMap<>();
    private final List<String> selectedSeats = new ArrayList<>();

    private static final Color PURPLE = new Color(138, 43, 226);
    private static final Color GREEN = new Color(50, 205, 50);
    private static final Color DARK_GRAY = new Color(100, 100, 100);

    public BookingView() {
        bookingController = new BookingController();
        setupFrame();
        setupBackground();
        addHeader();
        addStageAndSeats();
        addInfoPanel();
        addBookButton();
    }

    private void setupFrame() {
        setTitle("Ticket Booking System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
    }

    private void setupBackground() {
        ImageIcon bgIcon = new ImageIcon("/Users/shanamusekiwa/Desktop/TicketBookingSystemGUI/src/nz/event/ticketbookingsystem/view/images/MammaMia.png");
        Image bgImg = bgIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        background.setBounds(0, 0, getWidth(), getHeight());

        JPanel overlay = new JPanel();
        overlay.setBackground(new Color(255, 255, 255, 230));
        overlay.setBounds(80, 80, 740, 400);
        overlay.setLayout(null);

        getLayeredPane().add(background, JLayeredPane.DEFAULT_LAYER);
        getLayeredPane().add(overlay, JLayeredPane.PALETTE_LAYER);
        mainPanel = overlay;
    }

    private void addHeader() {
        JLabel header = new JLabel("Buy Tickets");
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        header.setForeground(PURPLE);
        header.setBounds(40, 20, 300, 40);
        mainPanel.add(header);
    }

    private void addStageAndSeats() {
        JLabel stage = new JLabel("STAGE", SwingConstants.CENTER);
        stage.setOpaque(true);
        stage.setBackground(Color.GRAY);
        stage.setForeground(Color.WHITE);
        stage.setFont(new Font("SansSerif", Font.BOLD, 16));
        stage.setBounds(40, 70, 300, 30);
        mainPanel.add(stage);

        seatPanel = new JPanel(new GridLayout(5, 6, 5, 5)); // 5x5 + label col
        seatPanel.setOpaque(false);
        seatPanel.setBounds(40, 120, 300, 200);

        List<Seat> allSeats = bookingController.getAllSeats();
        int seatIndex = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++) {
                if (col == 5) {
                    JLabel label = new JLabel(String.valueOf((char) ('A' + row)), SwingConstants.CENTER);
                    label.setFont(new Font("SansSerif", Font.BOLD, 14));
                    seatPanel.add(label);
                    continue;
                }

                Seat seat = allSeats.get(seatIndex++);
                JButton seatButton = new JButton(String.valueOf(col + 1)); // show numbers 1-5
                seatButton.setFont(new Font("SansSerif", Font.BOLD, 10));
                seatButton.setFocusPainted(false);
                seatButton.setOpaque(true);
                seatButton.setBorderPainted(false);

                if (seat.getStatus() == SeatStatus.AVAILABLE) {
                    seatButton.setBackground(PURPLE);
                    seatButton.setForeground(Color.WHITE);
                    seatButton.addActionListener(e -> toggleSeat(seatButton, seat.getCode()));
                } else {
                    seatButton.setBackground(DARK_GRAY);
                    seatButton.setEnabled(false);
                }

                seatButtons.put(seat.getCode(), seatButton);
                seatPanel.add(seatButton);
            }
        }

        mainPanel.add(seatPanel);
    }

    private void toggleSeat(JButton button, String code) {
        if (selectedSeats.contains(code)) {
            selectedSeats.remove(code);
            button.setBackground(PURPLE);
        } else {
            selectedSeats.add(code);
            button.setBackground(GREEN);
        }
        updateInfo();
    }
    
    private void addInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(null);
        infoPanel.setBounds(380, 70, 320, 250);

        JLabel infoTitle = new JLabel("Your Information");
        infoTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        infoTitle.setForeground(PURPLE);
        infoTitle.setBounds(10, 0, 200, 30);
        infoPanel.add(infoTitle);

        JLabel dateLabel = new JLabel("DATE");
        dateLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        dateLabel.setForeground(PURPLE);
        dateLabel.setBounds(10, 40, 100, 20);
        infoPanel.add(dateLabel);

        JComboBox<String> dateBox = new JComboBox<>(new String[]{"Thu, 28 Aug — 7:30 pm"});
        dateBox.setBounds(10, 60, 250, 25);
        infoPanel.add(dateBox);

        JLabel ticketText = new JLabel("TICKET NUMBER:");
        ticketText.setFont(new Font("SansSerif", Font.BOLD, 13));
        ticketText.setForeground(PURPLE);
        ticketText.setBounds(10, 100, 150, 20);
        infoPanel.add(ticketText);

        ticketLabel = new JLabel("0");
        ticketLabel.setBounds(160, 100, 50, 20);
        infoPanel.add(ticketLabel);

        JLabel priceText = new JLabel("PRICE:");
        priceText.setFont(new Font("SansSerif", Font.BOLD, 13));
        priceText.setForeground(PURPLE);
        priceText.setBounds(10, 130, 100, 20);
        infoPanel.add(priceText);

        priceLabel = new JLabel("0.00");
        priceLabel.setBounds(160, 130, 100, 20);
        infoPanel.add(priceLabel);

        JLabel eventLabel = new JLabel("Event: Mamma Mia");
        eventLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        eventLabel.setForeground(PURPLE);
        eventLabel.setBounds(10, 160, 200, 20);
        infoPanel.add(eventLabel);

        JLabel pricePerTicket = new JLabel("Price per ticket: $84.00");
        pricePerTicket.setFont(new Font("SansSerif", Font.BOLD, 13));
        pricePerTicket.setForeground(PURPLE);
        pricePerTicket.setBounds(10, 190, 250, 20);
        infoPanel.add(pricePerTicket);

        mainPanel.add(infoPanel);
    }

    private void addBookButton() {
        JButton bookButton = new JButton("Book Tickets");
        bookButton.setBackground(PURPLE);
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookButton.setFocusPainted(false);
        bookButton.setBounds(520, 340, 180, 40);
        bookButton.setBorder(BorderFactory.createEmptyBorder());
        bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookButton.setOpaque(true);

        bookButton.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one seat.", "No Seats Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String message = "Successfully booked " + selectedSeats.size() + " tickets for Mamma Mia.\nTotal: " + bookingController.bookSeats(selectedSeats);
            JOptionPane.showMessageDialog(this, message, "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);

            // Reset selected seats
            for (String code : selectedSeats) {
                JButton seatBtn = seatButtons.get(code);
                seatBtn.setBackground(DARK_GRAY);
                seatBtn.setEnabled(false);
            }
            selectedSeats.clear();
            updateInfo();
        });

        mainPanel.add(bookButton);
    }

    private void updateInfo() {
        ticketLabel.setText(String.valueOf(selectedSeats.size()));
        priceLabel.setText(bookingController.bookSeats(selectedSeats));
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new BookingView().setVisible(true));
//    }
}
    