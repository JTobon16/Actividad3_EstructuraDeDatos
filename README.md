# Sistema de Gestión con Colecciones Java, Map y Stream

## Descripción

Este proyecto corresponde a la actividad de aprendizaje de la Unidad III de la asignatura **Estructuras de Datos**.

La aplicación fue desarrollada en Java utilizando las colecciones estándar del SDK de Java y programación funcional mediante Stream API. El objetivo principal es gestionar elementos de un sistema mediante estructuras de datos modernas, sin implementar estructuras personalizadas como listas, colas o pilas propias.

El sistema permite registrar elementos, administrarlos según su estado, procesarlos en orden de llegada, mantener un historial de operaciones y generar consultas, filtros, agrupamientos y estadísticas.

---

## Objetivos del Proyecto

* Aplicar correctamente las colecciones del SDK de Java.
* Utilizar List para el registro general de elementos.
* Utilizar Queue para gestionar elementos pendientes.
* Utilizar Deque como historial de elementos procesados.
* Utilizar Map para búsquedas rápidas por identificador.
* Implementar operaciones funcionales mediante Stream API.
* Aplicar principios de Programación Orientada a Objetos.
* Implementar validaciones y manejo de errores.

---

## Tecnologías Utilizadas

* Java 17 (o versión utilizada)
* IntelliJ IDEA
* Git
* GitHub
* Java Collections Framework
* Java Stream API

---

## Estructura del Proyecto

```text
src
│
├── modelo
│   └── EntidadDelCaso.java
│
├── servicio
│   └── GestorDelCaso.java
│
└── Main.java
```

### Modelo

Contiene la entidad principal del sistema.

Responsabilidades:

* Representar la información del elemento gestionado.
* Implementar getters y setters.
* Implementar toString().
* Implementar equals() y hashCode() basados en el identificador principal.

### Servicio

Contiene toda la lógica de negocio.

Responsabilidades:

* Registrar elementos.
* Procesar elementos.
* Cancelar elementos.
* Buscar información.
* Generar estadísticas.
* Gestionar las colecciones utilizadas.

### Main

Contiene el menú de consola y la interacción con el usuario.

---

## Colecciones Implementadas

### List

Se utiliza para almacenar el registro completo de todos los elementos creados en el sistema.

Funciones:

* Registro general.
* Consultas.
* Filtros.
* Ordenamientos.
* Reportes.

```java
List<EntidadDelCaso> elementos = new ArrayList<>();
```

---

### Queue

Se utiliza para administrar los elementos pendientes por procesar siguiendo la política FIFO (First In, First Out).

Funciones:

* Registrar pendientes.
* Consultar siguiente elemento.
* Procesar elementos.

```java
Queue<EntidadDelCaso> pendientes = new LinkedList<>();
```

---

### Deque

Se utiliza como historial de elementos procesados siguiendo la política LIFO (Last In, First Out).

Funciones:

* Guardar historial.
* Consultar último procesado.
* Deshacer procesamiento.

```java
Deque<EntidadDelCaso> historial = new ArrayDeque<>();
```

---

### Map

Se utiliza para realizar búsquedas rápidas por identificador.

Funciones:

* Evitar registros duplicados.
* Acceso rápido a elementos.
* Consultas por código.

```java
Map<String, EntidadDelCaso> indicePorCodigo = new HashMap<>();
```

---

## Uso de Stream API

Durante el desarrollo se implementaron operaciones funcionales como:

### Búsquedas

```java
stream()
filter()
findFirst()
```

### Filtros

```java
filter()
toList()
```

### Ordenamientos

```java
sorted()
```

### Transformaciones

```java
map()
```

### Estadísticas

```java
count()
Collectors.counting()
```

### Agrupamientos

```java
Collectors.groupingBy()
```

### Validaciones

```java
anyMatch()
allMatch()
noneMatch()
```

---

## Funcionalidades Implementadas

* Registrar elemento.
* Ver elementos registrados.
* Ver elementos pendientes.
* Procesar siguiente elemento.
* Ver historial de procesados.
* Buscar por identificador usando Map.
* Buscar por otros criterios usando Stream.
* Filtrar elementos.
* Ordenar elementos.
* Ver estadísticas.
* Ver agrupamientos.
* Cancelar elementos pendientes.
* Deshacer último procesamiento.
* Consultar cantidades almacenadas.
* Salir del sistema.

---

## Flujo General del Sistema

### Registro

```text
Usuario
   │
   ▼
List
   │
   ├── Queue (Pendientes)
   │
   └── Map (Índice rápido)
```

### Procesamiento

```text
Queue
   │
 poll()
   │
   ▼
Elemento Procesado
   │
 push()
   ▼
Deque (Historial)
```

### Deshacer

```text
Deque
   │
 pop()
   │
   ▼
Queue
```

---

## Validaciones Implementadas

* No se permiten identificadores duplicados.
* No se procesan elementos inexistentes.
* No se cancelan elementos ya procesados.
* No se deshacen operaciones inexistentes.
* Se valida la existencia de datos antes de cada operación.

---

## Ejecución del Proyecto

1. Clonar el repositorio:

```bash
git clone URL_DEL_REPOSITORIO
```

2. Abrir el proyecto en IntelliJ IDEA.

3. Ejecutar la clase:

```java
Main.java
```

4. Utilizar el menú de consola para interactuar con el sistema.

---

## Autor

Jonathan Tobon

Asignatura: Estructuras de Datos

Actividad: Sistema de Gestión con Colecciones Java, Map y Stream
