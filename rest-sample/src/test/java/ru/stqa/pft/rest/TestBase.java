package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

/**
 * Created by Ксюшенька on 23.04.2016.
 */
public class TestBase {

    public boolean isIssueOpen( int issueId ) {
        String state = getStateById(issueId);
        System.out.println(state);
//        return state.equals("open");
        if (state.equals("Resolved") || state.equals("Closed")) {
            return false;
        } else {
            return true;
        }
    }

    private String getStateById( int id ) {
        String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", id)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        return issue.getAsJsonObject().get("state_name").getAsString();
    }

    public void skipIfNotFixed( int issueId ) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
