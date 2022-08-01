package com.github.jonataslaet.lafood.cadastro;

import static io.restassured.RestAssured.given;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DBRider
@DBUnit(schema = "public", caseSensitiveTableNames = true, cacheConnection = false, caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTestResource(CadastroTestLifeCycleManager.class)
public class RestauranteResourceTest {

    @Test
    @DataSet("restaurantes-cenario-01.yml")
    public void getRestaurantes() {
        String resultado = given()
          .when().get("/restaurantes")
          .then()
             .statusCode(200)
             .extract().asString();
    }

}