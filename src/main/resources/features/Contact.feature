Feature: Enviar un mensaje a los desarrolladores

  Como visitante,
  Quiero poder contactar a los desarrolladores de la aplicación,
  Para poder enviarles consultas o comentarios.

  Scenario: Enviar un mensaje a los desarrolladores
    Given que el visitante tiene una consulta o comentario relacionado con la aplicación
    When redacte un mensaje para contactar a los desarrolladores
    Then el sistema enviará el mensaje a la dirección de correo electrónico del startup