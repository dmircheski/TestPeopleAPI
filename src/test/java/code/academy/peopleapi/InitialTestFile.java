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

    @Test
    public void initialTest() throws Exception {
        response = peopleApiClient.getAllPeople();

        String body = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(body);

    }
}
