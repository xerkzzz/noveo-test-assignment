package com.zakharovmm.noveotestassignment.controller;

import com.zakharovmm.noveotestassignment.config.PostgreSQLContainerExtension;
import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.entity.Product;
import com.zakharovmm.noveotestassignment.entity.Tax;
import com.zakharovmm.noveotestassignment.model.PaymentProcessorType;
import com.zakharovmm.noveotestassignment.repository.ProductRepository;
import com.zakharovmm.noveotestassignment.repository.TaxRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.zakharovmm.noveotestassignment.entity.CountryCode.DE;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(PostgreSQLContainerExtension.class)
@Execution(ExecutionMode.SAME_THREAD)
@SpringBootTest
class PurchaseTest {

    private static final String ENDPOINT = "/purchase";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        productRepository.deleteAll();
        taxRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideTestParams")
    void calculatePrice(Product product, Tax tax, PurchaseRequestDto request, String expectedResult) {
        // Given.
        var saverProduct = productRepository.save(product);
        taxRepository.save(tax);
        request.setProduct(saverProduct.getId());

        // When.
        var response = postRequest(request);

        // Then.
        response.then().statusCode(HttpStatus.OK.value());

        if (expectedResult.isEmpty()) {
            assertThat(response.getBody().asString()).isEmpty();
        } else {
            var responseBody = response.getBody().asString();
            assertThat(responseBody).isEqualTo(expectedResult);
        }
    }

    private static MockMvcResponse postRequest(PurchaseRequestDto request) {

        return given().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT);
    }


    private static Stream<Arguments> provideTestParams() {
        return Stream.of(
                Arguments.of(
                        Product.builder()
                                .name("Iphone")
                                .price(BigDecimal.valueOf(100.0))
                                .build(),
                        Tax.builder()
                                .countryCode(DE)
                                .taxPercent(19.0f)
                                .regexPattern("DE123456789")
                                .build(),
                        PurchaseRequestDto.builder()
                                .taxNumber("DE123456789")
                                .paymentProcessor(PaymentProcessorType.PAYPAL.getValue())
                                .build(),
                        ""),
                Arguments.of(
                        Product.builder()
                                .name("Iphone")
                                .price(BigDecimal.valueOf(100.0))
                                .build(),
                        Tax.builder()
                                .countryCode(DE)
                                .taxPercent(19.0f)
                                .regexPattern("DE123456789")
                                .build(),
                        PurchaseRequestDto.builder()
                                .taxNumber("DE123456789")
                                .paymentProcessor(PaymentProcessorType.STRIPE.getValue())
                                .build(),
                        "true"),
                Arguments.of(
                        Product.builder()
                                .name("Iphone")
                                .price(BigDecimal.valueOf(100.0))
                                .build(),
                        Tax.builder()
                                .countryCode(DE)
                                .taxPercent(19.0f)
                                .regexPattern("DE123456789")
                                .build(),
                        PurchaseRequestDto.builder()
                                .taxNumber("DE123456789")
                                .paymentProcessor(PaymentProcessorType.STRIPE.getValue())
                                .build(),
                        "true")
        );
    }
}
