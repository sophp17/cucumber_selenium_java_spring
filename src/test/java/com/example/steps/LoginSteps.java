package com.example.steps;

import com.example.annotations.LazyAutowired;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Value;

public class LoginSteps {
    @Value("${browser}")
    private String browser;

    @LazyAutowired
    private HomePage homePage;

    @LazyAutowired
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        homePage
            .goToHomePage();
    }

    @When("I try to login with {string} and {string}")
    public void iTryToLoginWithAnd(String userName, String password) throws InterruptedException {
        loginPage
            .login(userName, password);
    }

    @Then("I verify invalid login message")
    public void iVerifyInvalidLoginMessage() {
        loginPage
                .verifyIdAndPasswordrrorMessage("Identifiants invalides");
    }

}
