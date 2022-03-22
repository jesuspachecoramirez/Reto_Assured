# new feature
# Tags: optional

Feature: Verificacion de servicios
  Como visitante de la pagina reqres
  quiero asegurarme que los endpoint que ofrecen funcionan
  para cumplir con el reto de sofkaU

  Scenario: Registro de usuario existoso
    Given Un usuario desea registrarse con un email "eve.holt@reqres.in" y una contrasena "pistol"
    When el usuario realiza el proceso de registro
    Then el usuario recibe una respuesta con un identificador y un token

    Scenario: Registro de usuario incorrecto
      Given Un usuario desea registrarse con un email "sydney@fife" sin ingresar una contrasena
      When La creacion del usuario no es correcta
      Then se recibe un mensaje de error
