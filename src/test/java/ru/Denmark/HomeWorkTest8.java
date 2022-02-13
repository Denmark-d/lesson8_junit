package ru.Denmark;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;


public class HomeWorkTest8 {

    public static Stream<Arguments> testCommon() {
        return Stream.of(
                Arguments.of("Направления деятельности"),
                Arguments.of("Новости"),
                Arguments.of("Карьера")
        );
    }

    @BeforeEach
    void openPage() {
        open("https://www.greenatom.ru/");
    }

    //просто тест
    @Test
    @DisplayName("Проверка")
    @Tag("test1")
    void checkTopics() {
        $(".top-menu-block pl-0 col-10 mr-auto");
        $(".container-fluid").shouldHave(text("Направления деятельности"));
        $(".container-fluid").shouldHave(text("Новости"));
        $(".container-fluid").shouldHave(text("Карьера"));
    }


    //ValueSource(testData)
    @Tag("test2")
    @ValueSource(strings = {"Направления деятельности", "Новости", "Карьера"})
    @ParameterizedTest(name = "тест с параметром: {0}")
    void testWithParams(String testData) {
        $(".top-menu-block pl-0 col-10 mr-auto");
        $(".container-fluid").shouldHave(text(testData));

    }

    //CSV (expectedResult)
    @Tag("test3")
    @CsvSource(value = {
            "Направления деятельности", "Новости", "Карьера"
    })
    @ParameterizedTest
    void testWithCSVParams(String expectedResult) {
        $(".top-menu-block pl-0 col-10 mr-auto");
        $(".container-fluid").shouldHave(text(expectedResult));
    }
    //MethodSource
    @Tag("test4")
    @MethodSource
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void testCommon(String expected) {
        $(".top-menu-block pl-0 col-10 mr-auto");
        $(".container-fluid").shouldHave(text(expected));
    }

    // игнорируем тест
    @Tag("test5")
    @Disabled
    @Test
    @DisplayName("Проверка_Disabled")
    void checkTopicsDisabled() {
        $(".top-menu-block pl-0 col-10 mr-auto");
        $(".container-fluid").shouldHave(text("Направления деятельности"));
        $(".container-fluid").shouldHave(text("Новости"));
        $(".container-fluid").shouldHave(text("Карьера"));
    }
}
