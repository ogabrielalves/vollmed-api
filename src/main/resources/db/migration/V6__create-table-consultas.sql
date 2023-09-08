CREATE TABLE consultas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    data DATETIME NOT NULL,
    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
