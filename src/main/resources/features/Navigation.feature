Feature: Navegar en la Landing Page

  Como visitante de la Landing Page,
  Quiero encontrar las secciones bien definidas,
  Para comprender fácilmente la información mostrada.

  Scenario: Visualizar información
    Given que el visitante está recorriendo la landing page
    When acceda a una sección de la landing page
    Then podrá comprender la información, ya que cada sección estará organizada.

  Scenario: Navegación a través del menú principal
    Given que el visitante está en la landing page
    When hace clic en una opción del menú principal (como "About Us", "Services", entre otros)
    Then es redirigido a la sección correspondiente y la información se muestra claramente.
