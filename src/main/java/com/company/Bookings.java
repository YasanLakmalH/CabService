package com.company;

public class Bookings {
    private int userId;
    private int bookingId;
    private String contactNo;
    private String pickupLocation;
    private String destination;
    private String vehicleType;
    private String bookingDate;
    private String bookingTime;
    private String status;
    public Bookings(int userId,
                          int bookingId,
                          String contactNo,
                          String pickupLocation,
                          String destination,
                          String vehicleType,
                          String bookingDate,
                          String time,
                          String status){
        this.userId = userId;
        this.bookingId = bookingId;
        this.contactNo = contactNo;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.vehicleType = vehicleType;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.status = status;
    }
    public int getUserId() {
        return userId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }
    public String getDestination(){
        return destination;
    }

    public String getVehicleType() {
        return vehicleType;
    }
    public String getDate(){
        return bookingDate;
    }
    public String getTime() {
        return bookingTime;
    }
    public String getStatus() {
        return status;
    }

}
