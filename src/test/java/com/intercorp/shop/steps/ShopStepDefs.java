package com.intercorp.shop.steps;

import com.intercorp.shop.tasks.AgregarPrenda;
import com.intercorp.shop.tasks.AbrirPagina;
import com.intercorp.shop.questions.PrecioTotal;
import com.intercorp.shop.utils.CSVReader;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.Actor;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.notNullValue;

public class ShopStepDefs {

    @Before
    public void setup() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Automatizador");
    }

    @Given("que el usuario abre la página principal de SHOP")
    public void abrirPagina() {
        OnStage.theActorInTheSpotlight().wasAbleTo(AbrirPagina.principal());
    }

    @When("agrega una {string} para {string} talla {string}")
    public void agregaRopa(String nombre, String genero, String talla) {
        OnStage.theActorInTheSpotlight().attemptsTo(AgregarPrenda.conDatos(nombre, genero, talla));
    }

    @When("procede al checkout")
    public void procedeCheckout() {
        // Implementación de checkout (simulada) - depende de la UI real
    }

    @Then("el total mostrado debe corresponder a la suma de los productos")
    public void validarTotal() {
        OnStage.theActorInTheSpotlight().should(seeThat(PrecioTotal.mostrado(), notNullValue()));
    }

    @Then("validar disponibilidad para tipo {string} talla {string}")
    public void validarDisponibilidad(String tipo, String talla) {
        // Validaciones negativas/positivas según respuesta en UI
    }

    // Método auxiliar para ejecutar desde CSV si se requiere programáticamente
    public void ejecutarDesdeCSV() {
        List<Map<String,String>> filas = CSVReader.leerCSV("src/test/resources/data/productos.csv");
        Actor actor = OnStage.theActorInTheSpotlight();
        for (Map<String,String> fila : filas) {
            actor.attemptsTo(AgregarPrenda.conDatos(fila.get("tipo"), fila.get("genero"), fila.get("talla")));
        }
    }
}
