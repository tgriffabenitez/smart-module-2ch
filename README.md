# Proyecto de Integración con el Módulo Smart Module 2CH (Movilum)

## Descripción
Este proyecto integra el módulo **Smart Module 2CH** de la marca **Movilum** utilizando un enfoque basado en **Java Spring Boot**. El sistema está diseñado para recibir y procesar información de temperatura y humedad enviada por el módulo a través de **MQTT**, almacenarla en una base de datos **QuestDB**, y exponer endpoints REST para acceder a los registros.

## Tecnologías Utilizadas
- **Java Spring Boot**: Framework principal para el desarrollo del backend.
- **QuestDB**: Base de datos para almacenar los datos de temperatura y humedad.
- **EMQX**: Broker MQTT para la comunicación con el módulo.
- **JDBCTemplate**: Herramienta utilizada para la persistencia en la base de datos.
- **MQTT**: Protocolo utilizado para recibir los datos del módulo.

## Funcionalidades
- Recepción de datos de **temperatura** y **humedad** enviados por el módulo a través de **MQTT**.
- Persistencia de los datos en **QuestDB** utilizando **JDBCTemplate**.
- Exposición de endpoints REST para:
    - Obtener los registros almacenados en la base de datos.
    - Consultas específicas según criterios definidos.