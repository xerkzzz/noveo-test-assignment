package com.zakharovmm.noveotestassignment.controller;

import com.zakharovmm.noveotestassignment.config.PostgreSQLContainerExtension;
import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import com.zakharovmm.noveotestassignment.entity.Coupon;
import com.zakharovmm.noveotestassignment.entity.Product;
import com.zakharovmm.noveotestassignment.entity.Tax;
import com.zakharovmm.noveotestassignment.repository.CouponRepository;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.zakharovmm.noveotestassignment.entity.CountryCode.DE;
import static com.zakharovmm.noveotestassignment.entity.CouponType.D;
import static com.zakharovmm.noveotestassignment.entity.CouponType.P;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PostgreSQLContainerExtension.class)
@Execution(ExecutionMode.SAME_THREAD)
@SpringBootTest
class CalculatePriceTest {

    private static final String ENDPOINT = "/calculate-price";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        productRepository.deleteAll();;
        couponRepository.deleteAll();
        taxRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideTestParams")
    void calculatePrice(Product product, Tax tax, Coupon coupon, PriceCalculationRequestDto request, Double expectedPrice) {
        // Given.
        var savedProduct  = productRepository.save(product);
        taxRepository.save(tax);
        couponRepository.save(coupon);
        request.setProduct(savedProduct.getId());

        // When.
        var response = postRequest(request);

        // Then.
        var price = response.then().statusCode(HttpStatus.OK.value()).extract().as(Double.class);
        assertThat(price)
                .isNotNull()
                .isEqualTo(expectedPrice);
    }

    private static MockMvcResponse postRequest(PriceCalculationRequestDto request) {

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
                        Coupon.builder()
                                .code("D15")
                                .couponType(D)
                                .discount(15.0f)
                                .build(),
                        PriceCalculationRequestDto.builder()
                                .taxNumber("DE123456789")
                                .couponCode("D15")
                                .build(),
                        104.0),
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
                        Coupon.builder()
                                .code("D15")
                                .couponType(D)
                                .discount(15.0f)
                                .build(),
                        PriceCalculationRequestDto.builder()
                                .taxNumber("DE123456789")
                                .build(),
                        119.0),
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
                        Coupon.builder()
                                .code("P20")
                                .couponType(P)
                                .discount(20.0f)
                                .build(),
                        PriceCalculationRequestDto.builder()
                                .taxNumber("DE123456789")
                                .couponCode("P20")
                                .build(),
                        95.2),
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
                        Coupon.builder()
                                .code("D20")
                                .couponType(D)
                                .discount(1000.0f)
                                .build(),
                        PriceCalculationRequestDto.builder()
                                .taxNumber("DE123456789")
                                .couponCode("D20")
                                .build(),
                        0.0)
        );
    }
}
