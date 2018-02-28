package com.test;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.java.Link;
import com.hp.lft.sdk.web.*;
import com.sun.org.apache.xpath.internal.operations.String;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
    }

    @AfterMethod
    public void afterMethod() throws Exception {
    }

    @Test
    public void test() throws GeneralLeanFtException, ReportException {
        Browser browser = BrowserFactory.launch(BrowserType.CHROME);
        browser.navigate("http://nimbusserver.aos.com:8000/#/");

        // Wait until page loads
        browser.sync();

        try {
            WebElement xelement = browser.describe(WebElement.class, new WebElementDescription.Builder()
                    .accessibilityName("")
                    .className("roboto-light ng-binding")
                    .index(1)
                    .tagName("H2")
                    .xpath("//DIV[4]/DIV[2]/H2[1]").build());
            //System.out.println("xitem: " + xelement.getInnerHTML().trim());

            Verify.areEqual("ALL YOU WANT FROM A TABLET", xelement.getInnerHTML().trim(), "Text Checkpoint -- Arg0: Expected, Arg1: Actual");
        }catch(AssertionError ex){
            //Report the Exception
            com.hp.lft.report.Reporter.reportEvent("Exception","Test failed", Status.Failed, ex);
            throw ex;
        }
        finally{
            //Close the browser
            browser.close();
        }
    }

}