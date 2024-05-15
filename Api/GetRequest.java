package api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetRequest {

    private final String mainPageUrl =
            "http://localhost:8081/parabank/index.htm;jsessionid=BFD15F7C3C86AD9AA71C818A98FBB9DE";
    private int responseStatus;
    private String body;

    public void loadMainPage() throws UnirestException {
        var jsonResponse = Unirest.get
                (mainPageUrl).asString();
        body = jsonResponse.getBody();
        responseStatus = jsonResponse.getStatus();
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public String getResponseBody() {
        return body;
    }
}
