Feature: Recibir Notificaciones sobre Reportes

  Como ciudadano,
  Quiero recibir notificaciones sobre los reportes recientes en mi área,
  Para estar informado y tomar precauciones.

  Scenario: Notificaciones activadas
    Given que el usuario tiene activadas las notificaciones
    When se recibe un nuevo reporte en su área
    Then el usuario es notificado inmediatamente.

  Scenario: Configuración de notificaciones
    Given que el ciudadano desea ajustar la frecuencia o tipo de notificaciones que recibe
    When accede a la configuración de notificaciones en la aplicación
    Then puede seleccionar qué tipo de reportes desea ser notificado y con qué frecuencia.
