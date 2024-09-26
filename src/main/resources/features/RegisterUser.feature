Feature: Registro de Usuarios

  Como usuario,
  Quiero poder registrarme en la aplicación,
  Para acceder a las funcionalidades de PeaceApp.

  Scenario: Registro exitoso
    Given que el usuario ha completado todos los campos del formulario de registro
    When hace clic en "Crear cuenta"
    Then la cuenta se crea y el usuario accede a la aplicación.

  Scenario: Registro incompleto
    Given que el usuario intenta registrarse sin completar todos los campos obligatorios
    When hace clic en "Crear cuenta"
    Then el sistema muestra un mensaje de error indicando qué campos faltan por completar.

  Scenario: Registro con credenciales ya utilizadas
    Given que el usuario intenta registrarse utilizando un correo electrónico ya registrado en la base de datos
    When hace clic en "Crear cuenta"
    Then el sistema muestra un mensaje de error indicando que el correo electrónico ya está en uso y sugiere recuperar la contraseña.
