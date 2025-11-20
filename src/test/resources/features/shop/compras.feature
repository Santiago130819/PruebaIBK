Feature: Compras en SHOP utilizando Serenity Screenplay

  Background:
    Given que el usuario abre la página principal de SHOP

  @Escenario1
  Scenario: Agregar chaqueta hombre XL y mujer S y validar el precio total
    When agrega una "YouTube Ultimate Hooded Sweatshirt" para "hombre" talla "XL"
    And agrega una "jacket" para "mujer" talla "S"
    Then el total mostrado debe corresponder a la suma de los productos

  @Escenario2
  Scenario: Agregar chaqueta hombre L y mujer XS y realizar checkout
    When agrega una "Android Nylon Packable Jacket" para "hombre" talla "L"
    And agrega una "Android Nylon Packable Jacket" para "mujer" talla "XS"
    And procede al checkout
    Then debe visualizar la confirmación del pedido

  @CSV @Escenario3
  Scenario Outline: Validar chaquetas ingresadas desde CSV (positivas y negativas)
    When agrega una chaqueta de tipo "<tipo>" talla "<talla>"
    Then validar disponibilidad para tipo "<tipo>" talla "<talla>"

    Examples: from csv
      | csv | productos.csv |
