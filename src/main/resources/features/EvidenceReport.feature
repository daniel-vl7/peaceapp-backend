Feature: Adjuntar Evidencia al Reporte

  Como usuario,
  Quiero poder adjuntar fotos o videos al reporte,
  Para dar mayor credibilidad y detalle al incidente reportado.

  Scenario: Adjuntar evidencia
    Given que el usuario está completando un reporte
    When adjunta una foto o video desde su dispositivo
    Then el reporte se envía con la evidencia adjunta.

  Scenario: Error al subir evidencia
    Given que el usuario intenta subir una imagen o video de gran tamaño que excede el límite permitido
    When hace clic en "Subir evidencia"
    Then el sistema muestra un mensaje de error indicando que el archivo es demasiado grande.
