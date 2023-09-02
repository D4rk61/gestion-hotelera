package com.gestionhotel.domain.service;

import com.gestionhotel.domain.service.email.EmailServiceImpl;
import com.gestionhotel.persistance.entity.cliente.Cliente;
import com.gestionhotel.persistance.repository.crud.IClienteCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IClientePaginRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service       @Transactional
public class ClienteService {

    @Autowired
    private final IClientePaginRepository clientePaginRepository;

    @Autowired
    private final IClienteCrudRepository  clienteCrudRepository;

    @Autowired
    private final EmailServiceImpl emailService;

    public ClienteService(IClientePaginRepository clientePaginRepository, IClienteCrudRepository clienteCrudRepository, EmailServiceImpl emailService) {
        this.clientePaginRepository = clientePaginRepository;
        this.clienteCrudRepository = clienteCrudRepository;
        this.emailService = emailService;
    }

    public Page<Cliente> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return clientePaginRepository.findAll(pageable);
    }

    public Optional<Cliente> getById(String id){
        try {
            return clienteCrudRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cliente con el dni: " + id + ". No encontrado");
        }
    }

    public Cliente save(Cliente newCliente){

        // validaciones y envio de gmail antes al crear un nuevo cliente
        Cliente savedCliente = clienteCrudRepository.save(newCliente);
        String to = savedCliente.getEmail();
        String subject = "¡Bienvenido/a a " + savedCliente.getNombre() + "!";
        String body = "Estimado/a " + savedCliente.getNombre() + ",\n\n";

        // Aquí empieza el mensaje de bienvenida
        body += "¡Gracias por registrarte en [nombre de tu sistema]! Estamos encantados de tenerte como parte de nuestra comunidad.\n\n";
        body += "[Nombre de tu sistema] es el sistema de hotelería más completo y fácil de usar que te permite encontrar el hotel perfecto para tu viaje. Con [nombre de tu sistema] podrás:\n\n";
        body += "- Buscar y comparar entre miles de hoteles en todo el mundo.\n";
        body += "- Reservar tu habitación con solo unos clics y sin comisiones.\n";
        body += "- Disfrutar de ofertas exclusivas y descuentos especiales.\n";
        body += "- Acceder a información detallada y actualizada sobre los hoteles, sus servicios, sus ubicaciones y sus opiniones.\n\n";
        body += "Para empezar a usar [nombre de tu sistema], solo tienes que ingresar a tu cuenta con tu nombre de usuario y contraseña. Si necesitas ayuda para usar el sistema, puedes consultar nuestro [manual de usuario](https://blog.hubspot.com/marketing/welcome-email-examples), donde encontrarás instrucciones paso a paso y consejos útiles.\n\n";
        body += "También te invitamos a visitar nuestro [sitio web](https://www.indeed.com/career-advice/career-development/welcome-email-to-new-client), donde podrás encontrar más información sobre nosotros, nuestros valores, nuestra misión y nuestra visión. Además, podrás acceder a nuestra [guía de destinos](https://customersfirstacademy.com/welcome-email-to-a-new-client/), donde te ofrecemos recomendaciones e ideas para planificar tu próximo viaje.\n\n";
        body += "Si tienes alguna pregunta, sugerencia o problema, no dudes en contactarnos. Estamos aquí para ayudarte y hacer que tu experiencia con [nombre de tu sistema] sea la mejor posible. Puedes escribirnos a [tu dirección de email] o llamarnos al [tu número de teléfono].\n\n";
        body += "Esperamos que disfrutes de [nombre de tu sistema] y que encuentres el hotel ideal para tu viaje. Estamos a tu disposición para lo que necesites.\n\n";
        body += "Saludos cordiales,\n\n";
        body += "Jose Reynoso\n";
        body += "Desarrollador y administrador de sistema\n";

        emailService.sendEmail(to, subject, body);

        return savedCliente;
    }

    public Cliente update(String dni, Cliente newCliente){
        try {
            if(!this.clienteCrudRepository.existsById(dni)) {
                throw new RuntimeException("Cliente con el dni: " + dni + ". No encontrado");
            }

            if(this.clienteCrudRepository.findById(dni).isEmpty()) {
                throw new RuntimeException("Cliente con el dni: " + dni + ". No encontrado");
            }

            return clienteCrudRepository.save(newCliente);

        } catch (Exception e) {

            throw new RuntimeException("Cliente con el dni: " + newCliente.getDni() + ". No encontrado");

        }
    }


    public void delete(String id){
        try {
            if(!clienteCrudRepository.existsById(id)) {
                throw new RuntimeException("Cliente con el dni: " + id + ". No encontrado");
            }
            clienteCrudRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cliente con el dni: " + id + ". No encontrado");
        }
    }

}
