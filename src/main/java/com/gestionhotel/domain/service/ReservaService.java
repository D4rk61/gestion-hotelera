package com.gestionhotel.domain.service;

import com.gestionhotel.domain.service.email.EmailServiceImpl;
import com.gestionhotel.persistance.entity.cliente.Cliente;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.entity.reserva.Reserva;
import com.gestionhotel.persistance.repository.crud.IClienteCrudRepository;
import com.gestionhotel.persistance.repository.crud.IHabitacionCrudRepository;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.persistance.repository.crud.IReservaCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHabitacionPaginRepository;
import com.gestionhotel.persistance.repository.pagin.IReservaPaginRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private final EmailServiceImpl emailService;
    @Autowired
    private final IReservaCrudRepository reservaCrudRepository;

    @Autowired
    private final IHotelCrudRepository hotelCrudRepository;
    @Autowired
    private final IHabitacionCrudRepository habitacionCrudRepository;
    @Autowired
    private final IHabitacionPaginRepository habitacionPaginRepository;
    @Autowired
    private final IReservaPaginRepository reservaPaginRepository;

    @Autowired
    private final IClienteCrudRepository clienteCrudRepository;

    public ReservaService(EmailServiceImpl emailService,
                          IReservaCrudRepository reservaCrudRepository,
                          IHotelCrudRepository hotelCrudRepository, IHabitacionCrudRepository habitacionCrudRepository,
                          IHabitacionPaginRepository habitacionPaginRepository, IReservaPaginRepository reservaPaginRepository, IClienteCrudRepository clienteCrudRepository) {
        this.emailService = emailService;
        this.reservaCrudRepository = reservaCrudRepository;
        this.hotelCrudRepository = hotelCrudRepository;
        this.habitacionCrudRepository = habitacionCrudRepository;
        this.habitacionPaginRepository = habitacionPaginRepository;
        this.reservaPaginRepository = reservaPaginRepository;
        this.clienteCrudRepository = clienteCrudRepository;
    }

    public Page<Reserva> getAll(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            return reservaPaginRepository.findAll(pageable);
        } catch (Exception e) {
            throw e;
        }
    }

    public Reserva getById(Long id) {

        if (this.reservaCrudRepository.existsById(id)) {
            throw new RuntimeException("No existe el id");
        }

        return this.reservaCrudRepository.findById(id).get();
    }


    /* Metodo antiguo
    public Reserva save(Reserva reserva){

        // Construye el mensaje de correo electrónico
        String mensaje = "Estimado/a " + reserva.getCliente().getNombre() + ",\n\n" +
                "Su reserva ha sido confirmada con éxito. A continuación, se muestran los detalles de su reserva:\n" +
                "Habitación ID: " + reserva.getId() + "\n" +
                "Tipo de habitación: " + reserva.getHabitaciones().get(0).getTipo() + "\n" +
                "Capacidad de habitación: " + reserva.getHabitaciones().get(0).getCapacidad() + "\n" +
                "Precio de habitación: " + reserva.getHabitaciones().get(0).getPrecio() + "\n" +
                "Hotel asociado: " + reserva.getHabitaciones().get(0).getHotel().getName() + "\n" +
                "Fecha de entrada: " + reserva.getFechaEntrada() + "\n" +
                "Fecha de salida: " + reserva.getFechaSalida() + "\n" +
                "Estado de la reserva: " + reserva.getEstado() + "\n\n" +
                "Gracias por elegir a" + reserva.getHabitaciones().get(0).getHotel().getName() + "\n\n" +
                "Saludos cordiales,\n\n" +
                "~ Gestión Hotel";

        // Envía el correo electrónico al cliente
        emailService.sendEmail(reserva.getCliente().getEmail(), "Confirmación de Reserva", mensaje);

        // Guarda la reserva en la base de datos
        Reserva savedReserva = reservaCrudRepository.save(reserva);

        return savedReserva;
    }
     */

    /*
    public Reserva save(Reserva reserva){


        if (reserva.getFechaEntrada().isAfter(reserva.getFechaSalida())) {
            throw new IllegalArgumentException("La fecha de entrada debe ser anterior a la fecha de salida");
        }
        long duracionEnDias = ChronoUnit.DAYS.between(reserva.getFechaEntrada(), reserva.getFechaSalida());

        if (duracionEnDias < 1) {
            throw new IllegalArgumentException("La reserva debe tener una duración de al menos 1 día");
        }

        // Construye el mensaje de correo electrónico
        String mensaje = "Estimado/a " + reserva.getCliente().getNombre() + ",\n\n" +
                "¡Gracias por reservar con [nombre de tu sistema]! Estamos encantados de confirmar su reserva en [nombre del hotel].\n\n" +
                "Estos son los detalles de su reserva:\n\n";

        // Usa una tabla para mostrar los detalles de la reserva
        mensaje += "| Habitación ID | Tipo de habitación | Capacidad de habitación | Precio de habitación | Hotel asociado | Fecha de entrada | Fecha de salida | Estado de la reserva |\n";
        mensaje += "| ------------- | ------------------ | ----------------------- | -------------------- | -------------- | --------------- | -------------- | ------------------- |\n";
        /*
        mensaje += "| " + reserva.getId() + " | " + reserva.getHabitaciones().get(0).getTipo() + " | " + reserva.getHabitaciones().get(0).getCapacidad() + " | " + reserva.getHabitaciones().get(0).getPrecio() + " | " + reserva.getHabitaciones().get(0).getHotel().getName() + " | " + reserva.getFechaEntrada() + " | " + reserva.getFechaSalida() + " | " + reserva.getEstado() + " |\n\n";
        */

    /*
        Habitacion habitacion = reserva.getHabitaciones().get(0);
        if (habitacion != null && habitacion.getHotel() != null) {
            mensaje += "| " + reserva.getId() + " | " + habitacion.getTipo() + " | " + habitacion.getCapacidad() + " | " + habitacion.getPrecio() + " | " + habitacion.getHotel().getName() + " | " + reserva.getFechaEntrada() + " | " + reserva.getFechaSalida() + " | " + reserva.getEstado() + " |\n\n";
        } else {
            mensaje += "| " + reserva.getId() + " | " + "N/A" + " | " + "N/A" + " | " + "N/A" + " | " + "N/A" + " | " + reserva.getFechaEntrada() + " | " + reserva.getFechaSalida() + " | " + reserva.getEstado() + " |\n\n";
        }

        // Usa un tono más positivo y motivador
        mensaje += "Esperamos que disfrute de su viaje y de su estancia en [nombre del hotel]. Estamos seguros de que le encantará el hotel, sus servicios, su ubicación y su ambiente.\n\n";

        // Ofrece recursos e información adicional
        mensaje += "Para más información sobre el hotel, puede visitar su página web [aquí]. También puede ver el mapa con la dirección del hotel [aquí]. Además, puede acceder a nuestra guía de destinos [aquí], donde le ofrecemos recomendaciones e ideas para planificar su viaje.\n\n";

        // Invita al cliente a contactarte
        mensaje += "Si tiene alguna pregunta, sugerencia o problema, no dude en contactarnos. Estamos aquí para ayudarle y hacer que su experiencia con [nombre de tu sistema] sea la mejor posible. Puede escribirnos a [tu dirección de email] o llamarnos al [tu número de teléfono].\n\n";



        mensaje += "Si desea modificar o cancelar su reserva, puede hacerlo desde su cuenta de [nombre de tu sistema], accediendo a la sección de 'Mis reservas'. Allí podrá ver el historial y el estado de todas sus reservas, y podrá realizar los cambios que necesite. Tenga en cuenta que algunas modificaciones o cancelaciones pueden estar sujetas a cargos adicionales, según la política del hotel.\n\n";

        mensaje += "Para cualquier consulta o inconveniente relacionado con su estancia en el hotel, puede contactar directamente con el personal del hotel. Estos son los datos de contacto del hotel [nombre del hotel]:\n\n";

        mensaje += "- Teléfono: " + reserva.getHabitaciones().get(0).getHotel().getTelefono() + "\n";
        mensaje += "- Email: " + reserva.getHabitaciones().get(0).getHotel().getEmail() + "\n";
        mensaje += "- Dirección: " + reserva.getHabitaciones().get(0).getHotel().getDireccion() + "\n\n";

        mensaje += "Si tiene alguna sugerencia o problema técnico con el sistema [nombre de tu sistema], puede contactar conmigo como desarrollador del sistema. Estos son mis datos de contacto:\n\n";
        mensaje += "- Email: reynosojose2005@gmail.com\n\n";

        // Despídete con un tono cordial y amigable
        mensaje += "Saludos cordiales,\n\n" +
                "Jose Reynoso\n" +
                "Desarrollador y administrador del sistema [nombre de tu sistema]\n\n" +
                "¡Gracias por elegir a [nombre de tu sistema]!";

        // Envía el correo electrónico al cliente
        emailService.sendEmail(reserva.getCliente().getEmail(), "¡Su reserva en [nombre del hotel] ha sido confirmada!", mensaje);

        // Guarda la reserva en la base de datos
        Reserva savedReserva = reservaCrudRepository.save(reserva);

        return savedReserva;
    }
    */
    public void crearReservaConCliente(Reserva reserva) {
        Reserva savedReserva = this.reservaCrudRepository.save(reserva);

        String emailTo = this.reservaCrudRepository.findEmailClienteByReservaId(savedReserva.getId());
        String obtenerDNIporReserva_id = this.reservaCrudRepository.findDniByReservaId(savedReserva.getId());
        String obtenerNombreDelHotel = this.reservaCrudRepository.findHotelNameByReservaIdAndClienteDni(savedReserva.getId(), obtenerDNIporReserva_id);

        // contacto con el hotel
        String telefonoDelHotel = this.reservaCrudRepository.findTelefonoHotelByReservaId(savedReserva.getId());
        String emailDelHotel = this.reservaCrudRepository.findEmailHotelByReservaId(savedReserva.getId());
        String direccionDelHotel = this.reservaCrudRepository.findDireccionHotelByReservaId(savedReserva.getId());


        if(emailTo != null && !emailTo.isEmpty()) {
            // enviar correo electronico
            String to = emailTo;
            String subject = "¡Su reserva en " + obtenerNombreDelHotel + " ha sido confirmada!";

            String body = "Disfruta de tu estancia en el hotel: " + obtenerNombreDelHotel + "\n\n" +
                    "Pero antes te daremos datos de tu reserva: \n\n" +
                    "ID de la reserva: " + savedReserva.getId() + "\n" +
                    "Fecha de entrada: " + savedReserva.getFechaEntrada() + "\n" +
                    "Fecha de salida: " + savedReserva.getFechaSalida() + "\n\n" +
                    "Estado de la reserva: " + savedReserva.getEstado() + "\n\n";

            emailService.sendEmail(to, subject, body);
        } else {
            throw new RuntimeException("No se encontrón datos del cliente");
        }

    }

}