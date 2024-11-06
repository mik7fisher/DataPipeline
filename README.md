# DataPipeline

# Proyecto: Pipeline de Análisis de Datos de Puntos de Acceso WiFi - Ciudad de México

## Descripción

Este proyecto consiste en desarrollar un pipeline de análisis de datos utilizando los datos abiertos de la Ciudad de México sobre los puntos de acceso WiFi en la ciudad. La información se almacena en una base de datos y se expone a través de un API REST para permitir su consulta de forma accesible y eficiente.

## Introducción

La Ciudad de México cuenta con una extensa red de puntos de acceso WiFi gratuitos en diversas colonias y lugares públicos. Este proyecto tiene como objetivo desarrollar una solución que permita almacenar y consultar estos datos mediante una API REST, proporcionando servicios que permiten obtener listas de puntos de acceso y buscar puntos específicos de acuerdo con distintas consultas, como la colonia o la proximidad a una coordenada geográfica.

Este pipeline es útil para usuarios que deseen acceder a información sobre los puntos de acceso WiFi en la ciudad, y puede ser extendido para aplicaciones móviles, portales web, o integrarse con otras plataformas de información urbana.

## Dependencias y Versiones

- **Java**: 8 
- **Spring Boot**: 2.5.4 o superior
- **Base de datos**: PostgreSQL
- **Maven**: 3.6.3 o superior
- **Swagger**: Para documentar la API REST
- **JPA / Hibernate**: Para la manipulación de datos en la base de datos

## Instrucciones de Despliegue

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/mik7fisher/DataPipeline.git
   cd DataPipeline.git
