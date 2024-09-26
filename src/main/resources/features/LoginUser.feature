Feature: Iniciar Sesión

  Como usuario registrado,
  Quiero poder iniciar sesión con mi correo y contraseña,
  Para acceder a mi cuenta.

  Scenario: Inicio de sesión exitoso
    Given que el usuario ha ingresado su correo y contraseña correctamente
    When hace clic en "Iniciar sesión"
    Then accede a su cuenta en la aplicación.

  Scenario: Inicio de sesión con credenciales incorrectas
    Given que el usuario ingresa un correo electrónico o contraseña incorrectos
    When hace clic en "Iniciar sesión"
    Then el sistema muestra un mensaje de error indicando que las credenciales son incorrectas.
