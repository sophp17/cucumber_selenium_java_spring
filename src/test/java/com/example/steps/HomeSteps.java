package com.example.steps;

import com.example.annotations.LazyAutowired;
import com.example.annotations.LazyComponent;
import com.example.annotations.TakeScreenshot;
import com.example.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeSteps {

    @LazyAutowired
    HomePage homePage;

    @Then("I should see these elements")
    public void i_Should_See_These_Elements(DataTable dataTable) throws InterruptedException {
        List<String> expectedElements = dataTable.asList();
        List<String> slideElements = homePage.getElementsPresentOnSlide(expectedElements);
        List<String> misslingELements = homePage.getMissingElementsOnSlide(expectedElements);

        assertTrue("Certains éléments attendus ne sont pas affichés : " + misslingELements,
                misslingELements.isEmpty());
        assertEquals(expectedElements.size(), slideElements.size());
    }

    @Given("I am at the homepage")
    public void i_Am_At_The_Homepage() {
        homePage
                .goToHomePage();
    }

    @Then("I should see these elements on side Menu")
    public void iShouldSeeTheseElementsOnSideMenu(DataTable dataTable) throws InterruptedException {
        List<String> expectedElements = dataTable.asList();
        List<String> slideElements = homePage.getElementPresentOnSideMenu(expectedElements);
        List<String> missingElements = homePage.getMissingElementsOnSideMenu(expectedElements);
        assertTrue("Certains éléments attendus ne sont pas affichés : " + missingElements,
                missingElements.isEmpty());
        assertEquals(expectedElements.size(), slideElements.size());
    }

    @And("I click on {string}")
    public void iClickOn(String service) throws InterruptedException {
    homePage.openSlideMenu();
    }

    @And("I should be redirected to {string} category")
    public void IShouldBeRedirected(String service) throws InterruptedException {
        assertTrue(homePage.isRedirection(service));
    }
}
