## API: Instalación y Ejecución de Pruebas

### 1. Clona el repositorio

```bash
git clone https://github.com/nicolasback04/proyecto-informa.git
cd proyecto-informa
```
![Clonación del repositorio](docs/img/instalacion-clonacion.png)

---

### 2. Construcción y despliegue con Docker Compose

```bash
docker-compose up --build
```
![Despliegue con Docker Compose](docs/img/instalacion-docker-compose.png)

---

### 3. Pruebas de la API con Postman

#### API: Autores

- **Guardar Autor**

  ![Guardar Autor](docs/img/postman-guardar-autor.png)

- **Consultar Autor**

  ![Consultar Autor por ID](docs/img/postman-consultar-autor.png)

- **Consultar Autores con libros asociados**

  ![Consultar Autores con Libros](docs/img/postman-consultar-autores-libros.png)

- **Consultar Todos los Autores**

  ![Consultar Todos los Autores](docs/img/postman-consultar-todos-autores.png)

- **Actualizar Autor**

  ![Actualizar Autor](docs/img/postman-actualizar-autor.png)

- **Eliminar Autor**

  ![Eliminar Autor](docs/img/postman-eliminar-autor.png)

---

#### API: Libros

- **Registrar Libro**

  ![Registrar Libro](docs/img/postman-registrar-libro.png)

- **Consultar libro por ID**

  ![Consultar Libro por ID](docs/img/postman-consultar-libro-id.png)

- **Consultar Todos los Libros**

  ![Consultar Todos los Libros](docs/img/postman-consultar-todos-libros.png)

- **Actualizar libro por ID**

  ![Actualizar Libro por ID](docs/img/postman-actualizar-libro.png)

- **Eliminar libro por ID**

  ![Eliminar Libro por ID](docs/img/postman-eliminar-libro.png)

---


> Las imágenes muestran respuestas exitosas obtenidas directamente desde la URL pública de la API.

> **Nota:** Todas las pruebas se realizaron sobre la instancia local corriendo en [http://localhost:8081/api/](http://localhost:8081/api/) o en la instancia en producción [https://app-biblio-production-ed53.up.railway.app/api/](https://app-biblio-production-ed53.up.railway.app/api/).
