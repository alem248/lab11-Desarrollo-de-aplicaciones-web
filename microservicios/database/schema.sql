CREATE DATABASE IF NOT EXISTS bd_paciente;
CREATE DATABASE IF NOT EXISTS bd_medico;
CREATE DATABASE IF NOT EXISTS bd_cita;

-- Las tablas se generan al iniciar cada servicio con spring.jpa.hibernate.ddl-auto=update.
-- El microservicio de citas guarda los IDs externos como valores, sin claves foráneas
-- cruzadas entre bases de datos.
