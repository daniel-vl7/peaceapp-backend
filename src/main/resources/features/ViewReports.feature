Feature: Visualización de Reportes

  Como ciudadano,
  Quiero poder ver los reportes de otros usuarios sobre incidentes ocurridos en la zona,
  Para estar al tanto de los eventos de seguridad.

  Scenario: Visualización de reportes recientes
    Given que el ciudadano está navegando por la aplicación
    When accede a la opción de "ver reportes"
    Then la aplicación muestra los reportes más recientes en la zona del ciudadano.

  Scenario: Visualización de reportes en el mapa
    Given que el ciudadano está utilizando el mapa interactivo en la aplicación
    When activa la opción de mostrar reportes en el mapa
    Then la aplicación superpone los reportes relevantes en el mapa, mostrando la ubicación exacta de cada incidente.
