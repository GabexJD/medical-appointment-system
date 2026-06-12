-- =============================================
-- Inicializacion Base de Datos - Citas Medicas
-- =============================================

USE citas_medicas;

-- ======================
-- CREACION DE TABLAS
-- ======================

CREATE TABLE IF NOT EXISTS Specialty (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80) UNIQUE NOT NULL,
  description TEXT
);

CREATE TABLE IF NOT EXISTS Doctor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fullName VARCHAR(100) NOT NULL,
  specialtyId INT NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(100),
  experienceYears INT,
  rating DECIMAL(3,2),
  isActive BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (specialtyId) REFERENCES Specialty(id)
);

CREATE TABLE IF NOT EXISTS Schedule (
  id INT AUTO_INCREMENT PRIMARY KEY,
  doctorId INT NOT NULL,
  dayOfWeek VARCHAR(20) NOT NULL,
  startTime TIME NOT NULL,
  endTime TIME NOT NULL,
  slotDuration INT DEFAULT 30,
  isActive BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (doctorId) REFERENCES Doctor(id)
);

CREATE TABLE IF NOT EXISTS User (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fullName VARCHAR(100) NOT NULL,
  documentNumber VARCHAR(20) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone VARCHAR(20),
  password VARCHAR(255) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Appointment (
  id INT AUTO_INCREMENT PRIMARY KEY,
  userId INT NOT NULL,
  doctorId INT NOT NULL,
  appointmentDate DATE NOT NULL,
  appointmentTime TIME NOT NULL,
  status VARCHAR(20) DEFAULT 'PENDING',
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (userId) REFERENCES User(id),
  FOREIGN KEY (doctorId) REFERENCES Doctor(id)
);

-- ======================
-- ESPECIALIDADES
-- ======================
INSERT INTO Specialty (name, description) VALUES
('Cardiologia', 'Enfermedades del corazon y sistema circulatorio'),
('Pediatria', 'Atencion medica a ninos y adolescentes'),
('Dermatologia', 'Enfermedades de la piel'),
('Ginecologia', 'Salud reproductiva femenina'),
('Neurologia', 'Enfermedades del sistema nervioso'),
('Oftalmologia', 'Salud visual y ojos'),
('Ortopedia', 'Lesiones y enfermedades del aparato locomotor')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- ======================
-- DOCTORES
-- ======================
INSERT INTO Doctor (fullName, specialtyId, phone, email, experienceYears, rating, isActive) VALUES
('Dr. Carlos Mendoza', 1, '0987654321', 'carlos.mendoza@email.com', 12, 4.8, true),
('Dra. Laura Rodriguez', 2, '0987654322', 'laura.rodriguez@email.com', 8, 4.9, true),
('Dr. Andres Vargas', 3, '0987654323', 'andres.vargas@email.com', 15, 4.7, true),
('Dra. Sofia Morales', 4, '0987654324', 'sofia.morales@email.com', 10, 4.6, true),
('Dr. Miguel Torres', 5, '0987654325', 'miguel.torres@email.com', 9, 4.5, true),
('Dra. Camila Ruiz', 6, '0987654326', 'camila.ruiz@email.com', 7, 4.8, true),
('Dr. Roberto Silva', 7, '0987654327', 'roberto.silva@email.com', 14, 4.9, true)
ON DUPLICATE KEY UPDATE fullName = VALUES(fullName);

-- ======================
-- HORARIOS RECURRENTES (Schedule)
-- ======================
INSERT INTO Schedule (doctorId, dayOfWeek, startTime, endTime, slotDuration, isActive) VALUES
-- Doctor 1 (Cardiologia) - Lunes y Miercoles
(1, 'MONDAY', '08:00', '12:00', 30, true),
(1, 'MONDAY', '14:00', '18:00', 30, true),
(1, 'WEDNESDAY', '08:00', '12:00', 30, true),

-- Doctor 2 (Pediatria)
(2, 'TUESDAY', '09:00', '13:00', 30, true),
(2, 'TUESDAY', '15:00', '19:00', 30, true),

-- Doctor 3 (Dermatologia)
(3, 'MONDAY', '08:00', '13:00', 30, true),
(3, 'FRIDAY', '14:00', '18:00', 30, true)

ON DUPLICATE KEY UPDATE startTime = VALUES(startTime);