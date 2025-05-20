# JavaReto

# Proyecto de Microservicios para Gestión Bancaria

Este proyecto implementa una arquitectura de microservicios para la gestión de cuentas bancarias y sus movimientos. Está basado en Spring Boot y utiliza Eureka para el descubrimiento de servicios.

## Estructura del Proyecto

El sistema está compuesto por dos microservicios principales:

1. **Microservicio "personacuenta" (puerto 8080)**
    - Gestiona entidades `Person` y `Client`
    - Base de datos PostgreSQL
2. **Microservicio "ms" (puerto 8090)**
    - Gestiona entidades `Account` y `Movements`
    - Base de datos PostgreSQL

Los microservicios se registran en un servidor de descubrimiento Eureka para facilitar la comunicación entre ellos.

## Requisitos Previos

Para ejecutar este proyecto necesitarás:

- Java 21
- Maven
- Lombok
- PostgreSQL
- Spring Boot 3.4.5 o superior

## Configuración y Ejecución

### 1. Configuración de la Base de Datos

Antes de iniciar los microservicios, asegúrate de que PostgreSQL esté en ejecución y crea las bases de datos necesarias:

```sql
-- Crear base de datos para personacuenta
CREATE DATABASE MSCliPer;

-- Crear base de datos para ms
CREATE DATABASE MSAccMov;

```
### 2. Validar credenciales de Base de Datos

Debido que se esta lanzando en un entorno local, el puerto para ambas bases debe ser el 5432 y asignarle credenciales de admin
en los application.properties.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/NameBD
spring.datasource.username=user
spring.datasource.password=psswd
```

### 3. Iniciar el Servidor Eureka

```bash
RUN DiscoverServerApplication

```

El servidor Eureka estará disponible en: [http://localhost:8761](http://localhost:8761/)

### 3. Iniciar Microservicio "personacuenta"

```bash
RUN PersonClientApplication

```

El microservicio estará disponible en: [http://localhost:8080](http://localhost:8080/)

### 4. Iniciar Microservicio "ms"

```
RUN MsAccMovApplication

```

El microservicio estará disponible en: [http://localhost:8090](http://localhost:8090/)

## Ejemplos de Uso (API)

### 1. Crear una Persona
**Endpoint**: `POST <http://localhost:8080/api/person`>
```json
{
    "personName":"Nombre",
    "personGender":"Masculine",
    "personAge":"24",
    "personCI":"0540003210",
    "personAddress":"Castillo",
    "personPhone":"099999999"
}
```

### 2. Crear un Cliente

**Endpoint**: `POST <http://localhost:8080/clientes`>

**Cuerpo (JSON)**:

```json
{
    "clientPassword":"contrasena",
    "clientState":true,  
    "person":{
        "personId": 1
        }
}

```

### 3. Crear una Cuenta

**Endpoint**: `POST <http://localhost:8090/cuentas`>

**Cuerpo (JSON)**:

```json
{
    "accountNumber":"777",
    "accountType":"Ahorro",
    "accountInitBalance":"500",
    "accountState":"true",
    "clientId": 1

}

```

> Nota: El clientId debe corresponder a un cliente existente en el microservicio personacuenta.
> 

### 4. Registrar un Movimiento (Depósito)

**Endpoint**: `POST <http://localhost:8090/movimientos`>

**Cuerpo (JSON)**:

```json
{
  "accountNumber": "777",
  "type": "Depósito",
  "value": 200.00
}

```

### 5. Registrar un Movimiento (Retiro)

**Endpoint**: `POST <http://localhost:8090/movimientos`>

**Cuerpo (JSON)**:

```json
{
  "accountNumber": "777",
  "type": "Retiro",
  "value": -100.00
}

```

> Nota: Para retiros, el valor debe ser negativo. 
> 

### 6. Consultar Movimientos de una Cuenta

**Endpoint**: `GET <http://localhost:8090/movimientos/777`>

Donde `777` es el número de cuenta.


### Error de conexión entre microservicios

Si hay problemas de comunicación entre microservicios:

1. Verifica que ambos servicios estén registrados correctamente en Eureka
2. Comprueba que las direcciones y puertos configurados sean correctos
3. Asegúrate de que no haya reglas de firewall bloqueando la comunicación

## Consideraciones Adicionales

- Todos los tiempos se manejan en UTC
- Los valores monetarios se representan como números de punto flotante (double)
