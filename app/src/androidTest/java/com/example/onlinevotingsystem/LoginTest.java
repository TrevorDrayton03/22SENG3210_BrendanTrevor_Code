package com.example.onlinevotingsystem;

import static android.service.autofill.Validators.not;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.onlinevotingsystem.controller.Login;
import com.example.onlinevotingsystem.controller.ViewTopic;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    /*
    Enters a passed username and password then clicks the login button
     */
    public void loginDetailsEntry(String username, String password){
        ViewInteraction usernameTextEdit = onView(withId(R.id.usernameTextInput));
        ViewInteraction passwordTextEdit = onView(withId(R.id.passwordTextInput));

        usernameTextEdit.perform(clearText(), typeText(username), closeSoftKeyboard());
        passwordTextEdit.perform(clearText(), typeText(password), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_managerLogin(){
        String username = "u1";
        String password = "p1";

        ActivityScenario scenario = ActivityScenario.launch(Login.class);

        loginDetailsEntry(username, password);

        ViewInteraction tasksListTextView = onView(withId(R.id.statisticsTitle));
        tasksListTextView.check(matches(withText(containsString("Dashboard"))));
    }

    @Test
    public void test_voterLogin(){
        String username = "u2";
        String password = "p2";

        ActivityScenario scenario = ActivityScenario.launch(Login.class);

        loginDetailsEntry(username, password);

        ViewInteraction tasksListTextView = onView(withId(R.id.voterDashboardTitle));
        tasksListTextView.check(matches(withText(containsString("Dashboard"))));
    }

    @Test
    public void test_failedLogin(){
        String username = "hello";
        String password = "itIsMe";

        ActivityScenario scenario = ActivityScenario.launch(Login.class);

        loginDetailsEntry(username, password);

        ViewInteraction tasksListTextView = onView(withId(R.id.loginTitle));
        tasksListTextView.check(matches(withText(containsString("Login to OVS"))));
    }
}
