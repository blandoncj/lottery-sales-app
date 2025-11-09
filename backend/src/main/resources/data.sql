-- script to initialize the database with sample data

INSERT INTO customers (id, document_number, name, email) VALUES
(1, '1012345678', 'Carlos Pérez', 'carlos.perez@example.com'),
(2, '1023456789', 'Ana Gómez', 'ana.gomez@example.com'),
(3, '1034567890', 'Juan Rodríguez', 'juan.rodriguez@example.com'),
(4, '1045678901', 'María López', 'maria.lopez@example.com'),
(5, '1056789012', 'Pedro Sánchez', 'pedro.sanchez@example.com'),
(6, '1067890123', 'Lucía Torres', 'lucia.torres@example.com'),
(7, '1078901234', 'Andrés Díaz', 'andres.diaz@example.com'),
(8, '1089012345', 'Camila Rojas', 'camila.rojas@example.com'),
(9, '1090123456', 'Felipe Gómez', 'felipe.gomez@example.com'),
(10, '1101234567', 'Diana Castillo', 'diana.castillo@example.com'),
(11, '1112345678', 'Miguel Vega', 'miguel.vega@example.com'),
(12, '1123456789', 'Laura Ortiz', 'laura.ortiz@example.com'),
(13, '1134567890', 'Javier Ramos', 'javier.ramos@example.com'),
(14, '1145678901', 'Daniela Cruz', 'daniela.cruz@example.com'),
(15, '1156789012', 'Oscar Ruiz', 'oscar.ruiz@example.com'),
(16, '1167890123', 'Valentina Moreno', 'valentina.moreno@example.com'),
(17, '1178901234', 'Santiago Méndez', 'santiago.mendez@example.com'),
(18, '1189012345', 'Elena Navarro', 'elena.navarro@example.com'),
(19, '1190123456', 'David Herrera', 'david.herrera@example.com'),
(20, '1201234567', 'Isabela Vargas', 'isabela.vargas@example.com');

-- reset the sequence for customers id to avoid conflicts
ALTER TABLE customers ALTER COLUMN id RESTART WITH 21;

INSERT INTO lottery_draws (id, name, draw_date) VALUES
(1, 'Lotería de Boyacá', '2026-01-01T21:00:00'),
(2, 'Lotería de Cundinamarca', '2026-02-05T21:00:00'),
(3, 'Baloto Revancha', '2026-03-10T20:00:00'),
(4, 'Lotería del Valle', '2026-04-15T21:00:00'),
(5, 'Lotería de Medellín', '2026-05-20T21:00:00'),
(6, 'Lotería del Cauca', '2026-06-25T21:00:00'),
(7, 'Lotería de Risaralda', '2026-07-30T21:00:00'),
(8, 'Lotería de Bogotá', '2026-08-05T21:00:00'),
(9, 'Baloto Noche', '2026-09-10T21:00:00'),
(10, 'Lotería de Tolima', '2026-10-15T21:00:00');

-- reset the sequence for lottery_draws id to avoid conflicts
ALTER TABLE lottery_draws ALTER COLUMN id RESTART WITH 11;

INSERT INTO lottery_tickets (id, number, price, status, draw_id) VALUES
(1, '00001', 5000, 'AVAILABLE', 1),
(2, '00002', 5000, 'AVAILABLE', 1),
(3, '00003', 5000, 'AVAILABLE', 1),
(4, '00004', 5000, 'AVAILABLE', 1),
(5, '00005', 5000, 'AVAILABLE', 1),
(6, '00006', 10000, 'AVAILABLE', 2),
(7, '00007', 10000, 'AVAILABLE', 2),
(8, '00008', 10000, 'AVAILABLE', 2),
(9, '00009', 10000, 'AVAILABLE', 2),
(10, '00010', 10000, 'AVAILABLE', 2),
(11, '00011', 15000, 'AVAILABLE', 3),
(12, '00012', 15000, 'AVAILABLE', 3),
(13, '00013', 15000, 'AVAILABLE', 3),
(14, '00014', 15000, 'AVAILABLE', 3),
(15, '00015', 15000, 'AVAILABLE', 3),
(16, '00016', 8000, 'AVAILABLE', 4),
(17, '00017', 8000, 'AVAILABLE', 4),
(18, '00018', 8000, 'AVAILABLE', 4),
(19, '00019', 8000, 'AVAILABLE', 4),
(20, '00020', 8000, 'AVAILABLE', 4),
(21, '00021', 12000, 'AVAILABLE', 5),
(22, '00022', 12000, 'AVAILABLE', 5),
(23, '00023', 12000, 'AVAILABLE', 5),
(24, '00024', 12000, 'AVAILABLE', 5),
(25, '00025', 12000, 'AVAILABLE', 5),
(26, '00026', 5000, 'AVAILABLE', 6),
(27, '00027', 5000, 'AVAILABLE', 6),
(28, '00028', 5000, 'AVAILABLE', 6),
(29, '00029', 5000, 'AVAILABLE', 6),
(30, '00030', 5000, 'AVAILABLE', 6),
(31, '00031', 7000, 'AVAILABLE', 7),
(32, '00032', 7000, 'AVAILABLE', 7),
(33, '00033', 7000, 'AVAILABLE', 7),
(34, '00034', 7000, 'AVAILABLE', 7),
(35, '00035', 7000, 'AVAILABLE', 7),
(36, '00036', 9000, 'AVAILABLE', 8),
(37, '00037', 9000, 'AVAILABLE', 8),
(38, '00038', 9000, 'AVAILABLE', 8),
(39, '00039', 9000, 'AVAILABLE', 8),
(40, '00040', 9000, 'AVAILABLE', 8),
(41, '00041', 6000, 'AVAILABLE', 9),
(42, '00042', 6000, 'AVAILABLE', 9),
(43, '00043', 6000, 'AVAILABLE', 9),
(44, '00044', 6000, 'AVAILABLE', 9),
(45, '00045', 6000, 'AVAILABLE', 9),
(46, '00046', 11000, 'AVAILABLE', 10),
(47, '00047', 11000, 'AVAILABLE', 10),
(48, '00048', 11000, 'AVAILABLE', 10),
(49, '00049', 11000, 'AVAILABLE', 10),
(50, '00050', 11000, 'AVAILABLE', 10);

-- reset the sequence for lottery_tickets id to avoid conflicts
ALTER TABLE lottery_tickets ALTER COLUMN id RESTART WITH 51;

INSERT INTO sales (id, customer_id, total_amount, sale_date) VALUES
(1, 1, 10000, '2025-11-01T10:30:00'),
(2, 2, 5000, '2025-11-02T11:00:00'),
(3, 3, 20000, '2025-11-03T12:00:00'),
(4, 4, 15000, '2025-11-04T13:00:00'),
(5, 5, 10000, '2025-11-05T14:00:00'),
(6, 6, 12000, '2025-11-06T15:00:00'),
(7, 7, 8000, '2025-11-07T16:00:00'),
(8, 8, 9000, '2025-11-08T17:00:00'),
(9, 9, 7000, '2025-11-09T18:00:00'),
(10, 10, 13000, '2025-11-10T19:00:00'),
(11, 11, 15000, '2025-11-11T10:30:00'),
(12, 12, 20000, '2025-11-12T11:00:00'),
(13, 13, 18000, '2025-11-13T12:00:00'),
(14, 14, 10000, '2025-11-14T13:00:00'),
(15, 15, 5000, '2025-11-15T14:00:00'),
(16, 16, 22000, '2025-11-16T15:00:00'),
(17, 17, 11000, '2025-11-17T16:00:00'),
(18, 18, 7000, '2025-11-18T17:00:00'),
(19, 19, 9500, '2025-11-19T18:00:00'),
(20, 20, 8000, '2025-11-20T19:00:00');

-- reset the sequence for sales id to avoid conflicts
ALTER TABLE sales ALTER COLUMN id RESTART WITH 21;

INSERT INTO sale_tickets (sale_id, ticket_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 5),
(3, 6),
(4, 8),
(5, 9),
(6, 10),
(7, 11),
(8, 12),
(9, 13),
(10, 14),
(11, 15),
(12, 16),
(13, 17),
(14, 18),
(15, 19),
(16, 20),
(17, 21),
(18, 22),
(19, 23),
(20, 24);

UPDATE lottery_tickets
SET status = 'SOLD'
WHERE id IN (1,2,3,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
