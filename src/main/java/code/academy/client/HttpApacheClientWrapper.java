package code.academy.client;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;


public class HttpApacheClientWrapper {

    private static final Logger log = Logger.getLogger(HttpApacheClientWrapper.class.getName());
    HttpClient client = HttpClientBuilder.create().build();

    public HttpApacheClientWrapper() {

    }

    public HttpResponse httpPost(String endpoint, Header[] headers, String payload) throws Exception {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        log.info("\nCreate request \nEndpoint: " + endpoint + "\nPayload: " + payload + "\n");
        HttpPost request = new HttpPost(endpoint);
        request.setHeaders(headers);
        request.setEntity(new StringEntity(payload));
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        HttpResponse response = httpClient.execute(request);
        getResponseBody(response);
        return response;
    }

    public HttpResponse httpGet(String endpoint, Header[] headers) throws Exception {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        log.info("\nCreate request " + "\nEndpoint: " + endpoint);
        HttpGet request = new HttpGet(endpoint);
        request.setHeaders(headers);
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        HttpResponse response = httpClient.execute(request);
        getResponseBody(response);
        return response;
    }

    public HttpResponse httpPut(String endpoint, Header[] headers, String PAYLOAD) throws Exception {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        log.info("\nCreate request " + "\nEndpoint: " + endpoint + "\nPayload: " + PAYLOAD + "\n");
        HttpPut request = new HttpPut(endpoint);
        request.setHeaders(headers);
        request.setEntity(new StringEntity(PAYLOAD));
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        HttpResponse response = httpClient.execute(request);
        getResponseBody(response);
        return response;
    }

    public HttpResponse httpDelete(String endpoint, Header[] headers) throws IOException {
        log.info("\nCreate request " + "\nEndpoint: " + endpoint + "\n");
        HttpDelete request = new HttpDelete(endpoint);
        request.setHeaders(headers);
        HttpResponse response = client.execute(request);
        getResponseBody(response);
        return response;
    }

    private void getResponseBody(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(response.getEntity());
        log.info("Response is: \n" + body);

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);
    }

}
