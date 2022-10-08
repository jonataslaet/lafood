package com.github.jonataslaet.lafood.cadastro;

import static io.restassured.RestAssured.given;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;
import io.restassured.http.ContentType;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import javax.ws.rs.core.Response;

@QuarkusTest
@DBRider
@DBUnit(schema = "public", cacheConnection = false, caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTestResource(CadastroTestLifeCycleManager.class)
public class RestauranteResourceTest {

    @Test
    @DataSet("restaurantes-cenario-01.yml")
    public void recuperarTodosOsRestaurantes() {
        String resultado = given()
          .when().get("/restaurantes")
          .then()
             .statusCode(200)
             .extract().asString();
        Approvals.verify(resultado);
    }

    @Test
    @DataSet("restaurantes-cenario-01.yml")
    public void testAlterarRestaurante() {
        Restaurante dto = new Restaurante();
        dto.nome = "novoNome";
        Long parameterValue = 123L;
        given().contentType(ContentType.JSON).with().pathParam("id", parameterValue).body(dto)
                .when().put("/restaurantes/{id}").then().statusCode(Response.Status.NO_CONTENT.getStatusCode()).extract().asString();

        Restaurante findById = Restaurante.findById(parameterValue);

        Assertions.assertEquals(dto.nome, findById.nome);
    }

}