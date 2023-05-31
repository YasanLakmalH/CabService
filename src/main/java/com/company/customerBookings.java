package com.company;

public class customerBookings {
    private int userId;
    private int bookingId;
    private String contactNo;
    private int vehicleNo;
    private String vehicleType;
    private String pickupLocation;
    private String destination;
    private String date;
    private String time;
    private String status;
    public customerBookings(int userId,
                            int bookingId,
                            String contactNo,
                            String pickupLocation,
                            String destination,
                            String vehicleType,
                            String date,
                            String time,
                            String status){
        this.userId = userId;
        this.bookingId = bookingId;
        this.contactNo = contactNo;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.vehicleType = vehicleType;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    public customerBookings(int userId,
                            int bookingId,
                            String contactNo,
                            int vehicleNo,
                            String vehicleType,
                            String pickupLocation,
                            String destination,
                            String date,
                            String time,
                            String status){
        this.userId = userId;
        this.bookingId = bookingId;
        this.contactNo = contactNo;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.date = date;
        this.time = time;
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
    public String getDate(){
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }
}
