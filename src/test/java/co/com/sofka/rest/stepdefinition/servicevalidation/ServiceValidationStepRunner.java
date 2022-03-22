package co.com.sofka.rest.stepdefinition.servicevalidation;

import co.com.sofka.models.Register;
import co.com.sofka.models.Unregister;
import co.com.sofka.rest.setup.services.ServiceSetUp;
import co.com.sofka.rest.utils.EndPoint;
import co.com.sofka.rest.utils.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;


import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.when;
import static org.hamcrest.Matchers.*;


public class ServiceValidationStepRunner extends ServiceSetUp {
    private static final Logger LOGGER = Logger.getLogger(ServiceValidationStepRunner.class);
    private Response response;
    private RequestSpecification requestSpecification;

    @Given("Un usuario desea registrarse con un email {string} y una contrasena {string}")
    public void unUsuarioDeseaRegistrarseConUnEmailYUnaContrasena(String email, String password) {
        try {
            Register register = new Register(JsonPath.REGISTER.getValue());
            register.setEmail(email);
            register.setPassword(password);

            generalSetUp();
            requestSpecification = given()
                    .contentType(ContentType.JSON)
                    .body(register.toString());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
    }

    @When("el usuario realiza el proceso de registro")
    public void elUsuarioRealizaElProcesoDeRegistro() {
        try {
            response = requestSpecification.when()
                    .post(EndPoint.SUCCESSFUL_REGISTER.getValue());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Then("el usuario recibe una respuesta con un identificador y un token")
    public void elUsuarioRecibeUnaRespuestaConUnIdentificadorYUnToken() {
        try {
            LOGGER.info("Register Successful Section");
            response.getBody().prettyPrint();
            response.then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", notNullValue(),
                            "token", notNullValue()
                    );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Given("Un usuario desea registrarse con un email {string} sin ingresar una contrasena")
    public void unUsuarioDeseaRegistrarseConUnEmailSinIngresarUnaContrasena(String email) {
        try {
            Unregister unregister = new Unregister(JsonPath.UNREGISTER.getValue());
            unregister.setEmail(email);
            generalSetUp();
            requestSpecification = given()
                    .contentType(ContentType.JSON)
                    .body(unregister.toString());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
    }



    @When("La creacion del usuario no es correcta")
    public void laCreacionDelUsuarioNoEsCorrecta() {
        try {
            generalSetUp();
            response = when()
                    .post(String.valueOf(EndPoint.UNSUCCESSFUL_REGISTER));


        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }

    }


    @Then("se recibe un mensaje de error")
    public void seRecibeUnMensajeDeError() {
        try{
            LOGGER.info("Register Unsuccessful Section");
            response.getBody().prettyPrint();
            response.then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("error", notNullValue());

        }catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.error(e.getMessage(),e);
        }
    }



}
