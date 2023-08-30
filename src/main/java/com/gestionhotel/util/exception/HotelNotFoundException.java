package com.gestionhotel.util.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long hotelId) {
        super("Hotel with id " + hotelId + " not found");
    }
}
