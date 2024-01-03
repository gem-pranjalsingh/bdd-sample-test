package com.qa.StepDefination;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;


public class Hook {
    @Before
    public void start() throws GemException {
        DriverManager.setUpBrowser();
    }
}
