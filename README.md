# Sistema de gestión de citas médicas

Implementación del laboratorio de microservicios para la clínica Salud Integral. El sistema separa pacientes, médicos y citas en aplicaciones Spring Boot independientes.

## Requisitos

- Java 21
- Maven 3.9+
- MySQL 8+

Se usa Spring Boot 3.5.7 con Java 21 porque es la versión instalada en el entorno de trabajo. Cada servicio conserva su configuración MySQL por variables de entorno y crea/actualiza sus tablas con JPA.

## Servicios

| Servicio | Puerto | Base de datos | Rutas principales |
|---|---:|---|---|
| paciente-service | 8081 | bd_paciente | `/api/pacientes` |
| medico-service | 8082 | bd_medico | `/api/medicos`, `/api/especialidades`, `/api/horarios` |
| cita-service | 8083 | bd_cita | `/api/citas` |

El servicio de médicos mantiene especialidades y horarios porque forman parte del modelo relacional del laboratorio. Citas registra los identificadores de paciente y médico sin acoplar las bases de datos de los servicios.

## Puesta en marcha

Crear las bases de datos antes de iniciar los servicios:

```sql
CREATE DATABASE bd_paciente;
CREATE DATABASE bd_medico;
CREATE DATABASE bd_cita;
```

En PowerShell, configurar credenciales si no se usa `root` sin contraseña:

```powershell
$env:DB_USERNAME = 'root'
$env:DB_PASSWORD = 'tu_clave'
```

Desde la carpeta `microservicios`, iniciar cada proyecto en una terminal:

```powershell
cd paciente-service; mvn spring-boot:run
cd medico-service; mvn spring-boot:run
cd cita-service; mvn spring-boot:run
```

Cada servicio también incluye `application-dev.yml` y `application-prod.yml`. El perfil activo predeterminado es `dev`; se puede cambiar con `SPRING_PROFILES_ACTIVE=prod`.

## Ejemplos de uso

Crear paciente:

```http
POST http://localhost:8081/api/pacientes
Content-Type: application/json

{"dni":"12345678","nombres":"Ana","apellidos":"Pérez Rojas","fechaNacimiento":"1990-05-12","sexo":"F","telefono":"987654321","direccion":"Lima","correo":"ana@example.com"}
```

Crear especialidad y médico:

```http
POST http://localhost:8082/api/especialidades
Content-Type: application/json

{"nombre":"Medicina general","descripcion":"Atención primaria"}
```

Usar el `idEspecialidad` de la respuesta:

```http
POST http://localhost:8082/api/medicos
Content-Type: application/json

{"cmp":"CMP-12345","nombres":"Luis","apellidos":"García Díaz","telefono":"912345678","correo":"luis@example.com","idEspecialidad":1}
```

Registrar una cita con los identificadores ya creados:

```http
POST http://localhost:8083/api/citas
Content-Type: application/json

{"fecha":"2026-11-20","hora":"09:30:00","idPaciente":1,"idMedico":1,"motivo":"Consulta general"}
```

La cita inicia como `PROGRAMADA`. Para cambiar su estado:

```http
PATCH http://localhost:8083/api/citas/1/estado
Content-Type: application/json

{"estado":"CONFIRMADA"}
```

Estados válidos: `PROGRAMADA`, `CONFIRMADA`, `ATENDIDA`, `CANCELADA`. `DELETE /api/citas/{id}` cancela la cita y conserva su registro.

## Actividades cubiertas

- CRUD de pacientes.
- CRUD de médicos y especialidades.
- Gestión de horarios de atención por médico.
- Registro, consulta y actualización de estado de citas; cancelar conserva la cita como `CANCELADA`.
- Validación de campos, DNI/CMP únicos y respuestas 404/400.
- Perfiles de configuración `dev` y `prod`.

## Conclusiones

1. Separar pacientes, médicos y citas permite mantener responsabilidades y despliegues independientes.
2. Los identificadores entre servicios reducen el acoplamiento y evitan compartir tablas entre dominios.
3. JPA y Spring Data simplifican las operaciones CRUD y el mapeo de relaciones dentro de cada servicio.
4. La validación de entradas y el manejo centralizado de errores hacen más predecible la API.
5. Los perfiles `dev` y `prod` permiten adaptar puertos y conexiones de base de datos al entorno.
