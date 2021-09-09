package code.academy.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.net.URI;

public class PeopleApiClient {

    private HttpApacheClientWrapper client = new HttpApacheClientWrapper();
    private HttpResponse response;
    private Header contentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");



    public HttpResponse getAllHttpPeople() throws Exception {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();


        HttpGet request = new HttpGet("http://localhost:3000/api/people");
        request.setHeader(contentType);
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;
    }


//    public HttpResponse getAllPeople() throws Exception {
//        response = client.httpGet("http://localhost:3000/api/people", new Header[]{contentType});
//        return response;
//    }
//
//    public HttpResponse getSinglePerson(String personId) throws Exception {
//        response = client.httpGet("http://localhost:3000/api/" + personId, new Header[]{contentType});
//        return response;
//    }
//
//    public HttpResponse postNewPerson(String payload) throws Exception {
//        response = client.httpPost("http://localhost:3000/api/person", new Header[]{contentType}, payload);
//        return response;
//    }
//
//    public HttpResponse putExistingPerson(String payload) throws Exception {
//        response = client.httpPut("http://localhost:3000/api/person", new Header[]{contentType}, payload);
//        return response;
//    }

}
