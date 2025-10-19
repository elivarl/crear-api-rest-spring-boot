# 🧩 API REST de Categorías con Spring Boot 3.6 y Java 21

Este proyecto es una **API RESTful** desarrollada con **Spring Boot 3.6**, **Java 21** y **PostgreSQL**, diseñada para enseñar a los estudiantes cómo crear y exponer endpoints CRUD (Crear, Leer, Actualizar y Eliminar) de manera profesional.

El proyecto forma parte del curso **“Spring Boot desde cero a avanzado”**, donde aprenderás a construir APIs REST completas, aplicar buenas prácticas y documentar tus servicios con Swagger.

---

## 🚀 Tecnologías utilizadas

| Tecnología                        | Versión | Propósito |
|-----------------------------------|--------|-----------|
| **Java**                          | 21 | Lenguaje principal |
| **Spring Boot**                   | 3.6.x | Framework backend principal |
| **Spring Data JPA**               | - | Persistencia y acceso a datos |
| **MySQL**                         | 8.0.40+ | Base de datos relacional |
| **Lombok**                        | - | Reducción de código repetitivo |
| **Maven**                         | - | Gestión de dependencias y build |

---

## ⚙️ Configuración del entorno

1. Clona el repositorio:
   ```bash
   git clone https://github.com/elivarl/crear-api-rest-spring-boot.git
2: Configurar la base de datos MySQL (puedes usar Docker)

Puedes crear una base de datos MySQL local con Docker usando el siguiente comando:
docker run --name mysql-categories \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=demoapi \
-p 3306:3306 -d mysql:8

Esto hará lo siguiente:
- Crea un contenedor llamado mysql-categories
- Usa la contraseña de root: root
- Crea automáticamente la base de datos demoapi
- Expone el puerto 3306 (puerto por defecto de MySQL)

💡 Si ya tienes MySQL instalado localmente, simplemente crea la base de datos manualmente:
CREATE DATABASE demoapi CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

3: Configurar application.properties
Edita el archivo src/main/resources/application.properties:

4: Ejecutar la aplicación
Ejecuta el proyecto desde tu IDE o con Maven:
- mvn spring-boot:run

Si todo está correcto, verás algo similar en la consola:
```
Tomcat started on port(s): 8080 (http)
Started DemoApiApplication in 2.3 seconds
```
La aplicación quedará disponible en:

👉 http://localhost:8080/api/categories

5: Probar la API – Crear una categoría

Puedes usar Postman o cURL para probar los endpoints.

Método: POST
URL: http://localhost:8080/api/categories
Encabezado:
Content-Type: application/json

Body (JSON):
```
{
"name": "Electrónica",
"description": "Productos y dispositivos tecnológicos."
}
```
