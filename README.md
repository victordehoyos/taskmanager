# Task Manager - Gestor de Tareas
Se requería desarrollar una aplicación que contara con un Front(Angular), un Back(Java - Spring) y una Base de Datos(PostgreSQL), la aplicación debía contar con los siguientes módulos:

### Módulo de usuarios:
* Registro de usuarios con atributos: nombre, correo electrónico, rol (Desarrollador, Líder Técnico)
* Obtener lista de usuarios (filtrando por rol).
### Módulo de tareas:
* Asignación de tareas a usuarios registrados.
* Cambio de estado - Estados posibles de las tareas: Pendiente, En Progreso, Completada.
* Obtener lista de tareas por estado o por usuario.

## Arquitectura
Si bien el ejercicio es pequeño y no requiere una gran arquitectura, con el fin de proyectar el ejercicio, la arquitectura aplicada fue **Clean Architecture**, esta puede ser implementada en proyectos pequeños y grandes, teniendo mayor relevancia en proyectos grandes, teniendo los siguientes beneficios:
* Separación responsabilidad.
* Fácil mantenimiento
* Mejoramiento de escalabilidad
* Independencia de frameworks => Si bien el proyecto está realizado con Spring, al aplicar **Clean Architecture** se podría cambiar el framework con cambios no tan críticos y a un menor costo.
* Pruebas sencillas.

## Patrones
* IoC, al utilizar Spring se trabaja con el principio de inversión de control debido a las anotaciones.
* DTO, no es un patrón de diseño pero si un patron arquitectónico que permite crear aplicaciones lo más desacopladas posible
* Observer, se utilizó el patrón Observer con el fin de notificar los cambios de estado de las tareas, específicamente cuándo cambien a Completado.
* Factory, en ese mismo orden, si bien se solicitó que se utilizara el patrón **Factory** para la creación de tareas. Se decidió utilizarlo para crear las diferentes formas de notificación (Websocket y Email)

## Front
Para el front se decidió utilizar un administrador de Angular llamado **NGX-Admin** el cual abstrae la complejidad de crear una aplicación totalmente de ceros, este administrador permite reutilizar componentes y consumir servicios de backend de forma más simple. 
Adicional, se creó una conexión mediante Websocket entre el back y el front con el fin de recibir las notificaciones cuándo una tarea se encuentre en estado completo.
Se utilizó la versión demo, por lo que existen más funcionalidades de las necesarias.

## Configuración
Para el correcto funcionamiento se requiere configurar una cuenta de correo para enviar las notificaciones, para eso, vaya a **https://myaccount.google.com/** y seleccione la sección seguridad (**https://myaccount.google.com/security**), luego en la barra de búsqueda escriba **Contraseñas de aplicación**, cree un nombre de aplicación y se generará una clave de 16 dígitos que deberá poner en el archivo application.properties en la sección **spring.mail.password=xxxxxxxxx**, adicionalmente deberá poner en la sección **spring.mail.username=email@email.com** el email con el que generó la clave. 

## Docker
Las aplicaciones se encuentran dockerizadas, por lo que solo es necesario ejecutar los contenedores para que estas funcionen correctamente, a continuación se detalla el paso a paso para levantar las aplicaciones.
Verifique que los puertos 8080, 5432 y 4200 se encuentren disponibles, serán usados por las aplicaciones

Al utilizar docker composer se debe ejecutar desde la raiz del proyecto dónde se encuentra el archivo **docker-compose.yml**
* Eliminar las imágenes si se han creado previamente con el comando **docker-compose down --rmi all**
* Compilar los contenedores con el comando **docker-compose build --no-cache**
* Levantar los diferentes servicios con el comando **docker compose -f 'docker-compose.yml' up -d --build**
* Si se desea levantar solo un servicio se puede hacer con el comando **ing task: docker compose -f 'docker-compose.yml' up -d --build 'backend'** se debe indicar el nombre del servicio a levantar existente en el archivo composer.