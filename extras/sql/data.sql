-- crear la database en postgreSQL
create DATABASE gestion_hotelera;

-- categotias de hoteles
INSERT INTO public.category_hotel(
	category_id, category_name)
	VALUES (1, 'Boutique'),
	(2, 'Resort'),
	(3, 'Business'),
	(4, 'Motel'),
	(5, 'Standard');

-- servicios de hoteles

INSERT INTO public.services_hotel(services_id, services_hotel_name)
VALUES
(1, 'WiFi'),
(2, 'Estacionamiento'),
(3, 'Piscina'),
(4, 'Spa'),
(5, 'Restaurante'),
(6, 'Gimnasio'),
(7, 'Servicio de Habitaciones'),
(8, 'Servicio de Lavandería'),
(9, 'Transporte al Aeropuerto'),
(10, 'Recepción 24 Horas'),
(11, 'Servicio de Conserjería'),
(12, 'Sala de Reuniones'),
(13, 'Centro de Negocios'),
(14, 'Servicio de Alquiler de Autos'),
(15, 'Servicio de Cuidado de Niños'),
(16, 'Servicio de Limpieza Diaria'),
(17, 'Bar y Lounge'),
(18, 'Jacuzzi'),
(19, 'Sauna'),
(20, 'Servicio de Despertador'),
(21, 'Servicio de Mascotas'),
(22, 'Servicio de Alojamiento para Mascotas'),
(23, 'Servicio de Alquiler de Bicicletas'),
(24, 'Servicio de Traslado'),
(25, 'Servicio de Valet'),
(26, 'Servicio de Peluquería'),
(27, 'Servicio de Caja de Seguridad'),
(28, 'Actividades Recreativas'),
(29, 'Servicio de Información Turística'),
(30, 'Servicio de Entradas para Espectáculos');

