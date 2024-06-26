package org.example.trainingxyz;


import org.example.models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    public void getCategories(){
        String endpoint = "http://localhost:8888/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
                given().queryParam("id", 2).
                        when().
                        get(endpoint).
                        then().
                        assertThat().statusCode(200).body("id", equalTo("2")).
                body("name", equalTo("Cross-Back Training Tank")).
                        body("description", equalTo("The most awesome phone of 2013!")).
                        body("price", equalTo("299.00")).
                        body("category_id", equalTo("2")).
                        body("category_name", equalTo("Active Wear - Women"));



    }

    @Test
    public void creatProducts(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 12,
                "category_id": 3
                
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 19,
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 16,
                "category_id": 3 
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 1001
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializableProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle",
                "Blue water bottle. Holds 64 ounces",
                12,
                3
        );
        var respnose = given().body(product).when().post(endpoint).then();
        respnose.log().body();
    }

    @Test
    public void creatSweatBand(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Sweatband",
                "description": "Sweatband test",
                "price": 5,
                "category_id": 3
                
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSweatBand(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 1003,
                "name": "Sweatband",
                "description": "Sweatband test",
                "price": 6,
                "category_id": 3 
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteSweatBand(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 1003
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializableSweatBand(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle",
                "Blue water bottle. Holds 64 ounces",
                12,
                3
        );
        var respnose = given().body(product).when().post(endpoint).then();
        respnose.log().body();
    }

    @Test
    public void getProducts(){
        String endpoint = "http://localhost:8888/api_testing/product/read.php";
        given().when().get(endpoint).then().log().headers().assertThat().statusCode(200).
                headers("Content-Type", equalTo("application/json; charset=UTF-8")).
                body("records.size()", greaterThan(0)).
                body("records.id", everyItem(notNullValue())).
                body("records.name", everyItem(notNullValue())).
                body("records.description", everyItem(notNullValue())).
                body("records.price", everyItem(notNullValue())).
                body("records.category_id", everyItem(notNullValue())).
                body("records.category_name", everyItem(notNullValue())).
                body("records.id[0]", equalTo("1004"));

    }



}
