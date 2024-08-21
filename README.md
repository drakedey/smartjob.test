# Prueba Smart Job

## Requisitos
* Maven 3.6.3 o Mayor
* Java 17

## Configuracion
Todos los puntos mencionados en este archivo seran propiedades que puede
modificar en el alcance de la prueba en el application.properties

### Validacion de regex de clave
```
smartjob.test.password-regex
```
Por defecto si no es especicado tendra el siguiente valor
```
^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{6,})\S$
```

### Base de datos
Este proyecto utiliza H2 en memoria como base de datos, se puede cambiar
de este el usuario y la clave con el cual se va a loguear en la consola 
(la cual esta activada) y su url path tambien esta especificada en el archivo
properties.
```
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-ui
```
La inicializacion de la base datos (solo su esquema) esta en resources/schema.sql

### JWT
La configuracion por defecto la puede dejar como esta en el archivo application.properties, el token expira en una hora
y la llave secreta ya esta configurada, si desea cambiarla porfavor asegurarse de que sea un String
HMAC hash de 256bits en caso contario la generacion de token fallara

```
security.jwt.secret-key=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
security.jwt.expiration-time=3600000
```

### Otras configuraciones
* Por defecto los logs de JPA estan activados
```
spring.jpa.show-sql=true
```
* El proyecto corre en el puerto 8001
```
server.port=8001
```

## Levantar el proyecto
Asumiendo que tanto Java como Maven estan configurados hay que correr los siguientes comandos:
```
mvn clean install -DskipTests
```
```
mvn spring-boot:run
```

Entonces ya deberia estar corriendo la aplicacion en el puerto 8001

### Para acceder al swagger
Accede a la siguiente url entonces encontraras la documentacion de swagger
```
http://localhost:8001/swagger-ui/index.html
```
### Para acceder a la consola de H2
Si no esta desactivada la consola puede accederse en la siguiente url
```
http://localhost:8001/h2-ui
```
Entonces podras acceder con el usuario y la clave configurada para la BD en memoria
### Para ejecutar las pruebas
```
mvn test
```
o en su defecto
```
mvn -Dtest=UserServiceTest test
```