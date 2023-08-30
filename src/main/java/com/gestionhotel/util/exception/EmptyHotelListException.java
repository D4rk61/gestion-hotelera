package com.gestionhotel.util.exception;

public class EmptyHotelListException extends RuntimeException {
    public EmptyHotelListException() {
        super("Hoteles no encontrados");
    }
}