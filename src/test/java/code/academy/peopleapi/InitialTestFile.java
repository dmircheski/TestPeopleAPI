package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InitialTestFile {

    HttpResponse response;
    PeopleApiClient peopleApiClient = new PeopleApiClient();

    public InitialTestFile() throws Exception {
    }

    @Test
    public void initialTest() throws Exception {
        response = peopleApiClient.getAllHttpPeople();


        String body = EntityUtils.toString(response.getEntity());
        JSONObject responseBodyAsObject = new JSONObject(body);
        String responseMessage = responseBodyAsObject.get("peopleData").toString();

        JSONObject peopleData = new JSONObject(responseMessage);
        String ime = peopleData.get("name").toString();



    }
}
