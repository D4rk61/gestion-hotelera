-- Insertar 10 hoteles falsos
INSERT INTO hotel (hotel_descripcion, hotel_direccion, hotel_email, hotel_estrellas_numero, hotel_latitud, hotel_longitud, hotel_name, hotel_telefono, category_id) VALUES
('Un hotel boutique con encanto en el centro de la ciudad. Disfrute de nuestras habitaciones elegantes y confortables, nuestro spa y nuestro restaurante gourmet.', 'Calle Mayor 12, Madrid', 'info@hotelmadrid.com', 4, 40.416775, -3.703790, 'Hotel Madrid', '915555555', 1),
('Un resort de lujo frente al mar. Disfrute de nuestras piscinas, nuestro campo de golf, nuestro casino y nuestra animación nocturna.', 'Avenida del Sol 34, Cancún', 'info@resortcancun.com', 5, 21.161908, -86.851528, 'Resort Cancún', '998888888', 2),
('Un hotel de negocios con todas las comodidades. Disfrute de nuestras salas de reuniones, nuestro gimnasio, nuestro servicio de habitaciones y nuestra conexión a internet.', 'Avenida Paulista 1234, São Paulo', 'info@hotelbusiness.com', 3, -23.550520, -46.633309, 'Hotel Business', '1133333333', 3),
('Un motel económico y acogedor. Disfrute de nuestras habitaciones limpias y sencillas, nuestro parking gratuito y nuestro desayuno continental.', 'Route 66 5678, Flagstaff', 'info@motelroute66.com', 2, 35.198284, -111.651299, 'Motel Route 66', '9287777777', 4),
('Un hotel estándar con una buena relación calidad-precio. Disfrute de nuestras habitaciones amplias y luminosas, nuestro servicio de lavandería y nuestro bar.', 'Rue de la Paix 9, París', 'info@hotelparis.com', 3, 48.856614, 2.352222, 'Hotel París', '0144444444', 5),
('Un hotel histórico y elegante en el corazón de Roma. Disfrute de nuestras habitaciones decoradas con antigüedades, nuestro jardín y nuestra terraza con vistas al Coliseo.', 'Via dei Fori Imperiali 45, Roma', 'info@hotelroma.com', 4, 41.902782, 12.496366 , 'Hotel Roma', '0666666666', 1),
('Un resort familiar y divertido en Orlando. Disfrute de nuestras suites temáticas, nuestro parque acuático, nuestro cine y nuestro transporte gratuito a los parques de atracciones.', 'International Drive 6789 , Orlando', 'info@resortorlando.com', 4 ,28.538336 , -81.379234 , 'Resort Orlando' , '4075555555' ,2),
('Un hotel moderno y funcional en Tokio. Disfrute de nuestras habitaciones con tecnología de última generación , nuestro sushi bar , nuestro centro de negocios y nuestra ubicación céntrica.' , 'Shinjuku-ku , Nishishinjuku , Tokyo' , 'info@hoteltokyo.com' ,3 ,35.689487 ,139.691711 , 'Hotel Tokyo' ,'0333333333' ,3),
('Un motel rústico y acogedor en el campo . Disfrute de nuestras habitaciones con chimenea , nuestro restaurante casero , nuestro establo y nuestra granja .' , 'Carretera Nacional km .1234 , Córdoba' ,'info@motelcampo.com' ,2 ,-31.420083 ,-64.188776 , 'Motel Campo' ,'03515555555' ,4),
('Un hotel ecológico y sostenible en Costa Rica . Disfrute de nuestras habitaciones con materiales naturales , nuestro spa , nuestro restaurante vegetariano y nuestra reserva natural .' , 'Carretera a Monteverde , Puntarenas' ,'info@hoteleco.com' ,3 ,10.275283 ,-84.825510 , 'Hotel Eco' ,'26455555' ,5);

gestion_hotelera=# -- Insertar 20 habitaciones falsas asociadas a los hoteles
INSERT INTO habitacion (capacidad, disponible, numero, precio, tipo, hotel_id) VALUES
(2, true, 101, 100, 'Doble', 12),
(2, true, 102, 120, 'Doble superior', 10),
(4, true, 103, 150, 'Familiar', 11),
(2, false, 104, 100, 'Doble', 9),
(2, true, 201, 200, 'Suite', 12),
(2, true, 202, 180, 'Doble deluxe', 8),
(4, true, 203, 220, 'Familiar deluxe', 10),
(6, true, 204, 300, 'Presidencial', 11),
(1, true, 301, 80, 'Individual', 3),
(2, true, 302, 100, 'Doble', 3),
(4, false, 303, 150, 'Familiar', 3),
(2, true, 304 ,120 , 'Doble ejecutiva' ,3),
(2 ,true ,401 ,50 ,'Doble' ,4),
(4 ,true ,402 ,70 ,'Familiar' ,4),
(2 ,false ,403 ,50 ,'Doble' ,4),
(2 ,true ,404 ,60 ,'Doble con jacuzzi' ,4),
(2 ,true ,501 ,120 ,'Doble' ,5),
(3 ,true ,502 ,150 ,'Triple' ,5),
(4 ,true ,503 ,180 ,'Cuádruple' ,5),
(2 ,false ,504 ,120 ,'Doble' ,5);

