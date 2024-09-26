Feature: Generar Reporte de Incidentes

  Como usuario,
  Quiero poder generar reportes de incidentes de seguridad,
  Para contribuir a la actualización del mapa de calor.

  Scenario: Reporte exitoso
    Given que el usuario ha presenciado un incidente
    When completa el formulario de reporte en la aplicación
    Then el incidente se registra y el mapa de calor se actualiza.

  Scenario: Reporte con datos incompletos
    Given que el usuario intenta enviar un reporte sin completar toda la información requerida
    When hace clic en "Enviar reporte"
    Then el sistema muestra un mensaje de error indicando los campos faltantes.

  Scenario: Cancelación del reporte
    Given que el usuario ha comenzado a llenar un reporte de incidente
    When decide cancelar el envío antes de completar el formulario
    Then el sistema le pregunta si está seguro de que desea cancelar y descartar los datos ingresados.
