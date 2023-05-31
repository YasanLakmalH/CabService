package com.company;

public class ActiveBookings extends Bookings {
    private String vehicleNo;
    public ActiveBookings(int userId,
                          int bookingId,
                          String contactNo,
                          String pickupLocation,
                          String destination,
                          String vehicleType,
                          String bookingDate,
                          String bookingTime,
                          String status,
                          String vehicleNo){

        super(userId,bookingId,contactNo,pickupLocation,destination,vehicleType,bookingDate,bookingTime,status);
        this.vehicleNo = vehicleNo;

    }

    public String getVehicleNo() {
        return vehicleNo;
    }
}
