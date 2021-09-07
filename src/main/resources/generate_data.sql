DROP TABLE IF EXISTS `rooms` CASCADE;
DROP TABLE IF EXISTS `seats`;

CREATE TABLE `rooms` (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(50)
);

CREATE TABLE `seats` (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    uniqid CHAR(36),
    seat_row CHAR(1),
    seat_number INT(11),
    room_id INT(11),
    is_taken BOOL,
    FOREIGN KEY(room_id) REFERENCES `rooms`(id)
);

INSERT INTO `rooms` (room_name) VALUES
    ('Sala Alpha'),
    ('Sala Beta'),
    ('Sala Gamma');

INSERT INTO `seats` (uniqid, seat_row, seat_number, room_id, is_taken) VALUES
    (RANDOM_UUID(), 'A', 1, 1, FALSE),
    (RANDOM_UUID(), 'A', 2, 1, FALSE),
    (RANDOM_UUID(), 'A', 3, 1, FALSE),
    (RANDOM_UUID(), 'A', 4, 1, FALSE),
    (RANDOM_UUID(), 'A', 5, 1, FALSE),
    (RANDOM_UUID(), 'B', 1, 1, FALSE),
    (RANDOM_UUID(), 'B', 2, 1, FALSE),
    (RANDOM_UUID(), 'B', 3, 1, FALSE),
    (RANDOM_UUID(), 'B', 4, 1, FALSE),
    (RANDOM_UUID(), 'B', 5, 1, FALSE),
    (RANDOM_UUID(), 'C', 1, 1, FALSE),
    (RANDOM_UUID(), 'C', 2, 1, FALSE),
    (RANDOM_UUID(), 'C', 3, 1, FALSE),
    (RANDOM_UUID(), 'C', 4, 1, FALSE),
    (RANDOM_UUID(), 'C', 5, 1, FALSE),
    (RANDOM_UUID(), 'A', 1, 2, FALSE),
    (RANDOM_UUID(), 'A', 2, 2, FALSE),
    (RANDOM_UUID(), 'A', 3, 2, FALSE),
    (RANDOM_UUID(), 'A', 4, 2, FALSE),
    (RANDOM_UUID(), 'A', 5, 2, FALSE),
    (RANDOM_UUID(), 'B', 1, 2, FALSE),
    (RANDOM_UUID(), 'B', 2, 2, FALSE),
    (RANDOM_UUID(), 'B', 3, 2, FALSE),
    (RANDOM_UUID(), 'B', 4, 2, FALSE),
    (RANDOM_UUID(), 'B', 5, 2, FALSE),
    (RANDOM_UUID(), 'C', 1, 2, FALSE),
    (RANDOM_UUID(), 'C', 2, 2, FALSE),
    (RANDOM_UUID(), 'C', 3, 2, FALSE),
    (RANDOM_UUID(), 'C', 4, 2, FALSE),
    (RANDOM_UUID(), 'C', 5, 2, FALSE),
    (RANDOM_UUID(), 'A', 1, 3, FALSE),
    (RANDOM_UUID(), 'A', 2, 3, FALSE),
    (RANDOM_UUID(), 'A', 3, 3, FALSE),
    (RANDOM_UUID(), 'A', 4, 3, FALSE),
    (RANDOM_UUID(), 'A', 5, 3, FALSE),
    (RANDOM_UUID(), 'B', 1, 3, FALSE),
    (RANDOM_UUID(), 'B', 2, 3, FALSE),
    (RANDOM_UUID(), 'B', 3, 3, FALSE),
    (RANDOM_UUID(), 'B', 4, 3, FALSE),
    (RANDOM_UUID(), 'B', 5, 3, FALSE),
    (RANDOM_UUID(), 'C', 1, 3, FALSE),
    (RANDOM_UUID(), 'C', 2, 3, FALSE),
    (RANDOM_UUID(), 'C', 3, 3, FALSE),
    (RANDOM_UUID(), 'C', 4, 3, FALSE),
    (RANDOM_UUID(), 'C', 5, 3, FALSE);

CREATE TABLE `movies` (
      id INT(11) AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(50),
      room_id INT(11),
      FOREIGN KEY (room_id) REFERENCES `rooms`(id)
);

INSERT INTO `movies` (title, room_id) VALUES
    ('Przeminęło z wiatrem', 1),
    ('Epoka Lodowcowa 3', 2),
    ('Więzień Labiryntu', 3);