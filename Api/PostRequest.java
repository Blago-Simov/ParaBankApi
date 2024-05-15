package api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PostRequest {
    private final String loginUrl = "http://localhost:8081/parabank/login.htm";
    private final String registrationUrl = "http://localhost:8081/parabank/register.htm";

    private int responseStatus;
    private String body;

    public void user_Login(String arg1, String arg2) throws UnirestException {
        var response = Unirest.post(loginUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("username", arg1)
                .field("password", arg2)
                .asString();
        body = response.getBody();
        responseStatus = response.getStatus();
    }
    public void user_LoginInvalidUserName( String arg2) throws UnirestException {
        var response = Unirest.post(loginUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("password", arg2)
                .asString();
        body = response.getBody();
        responseStatus = response.getStatus();
    }
    public void user_LoginInvalidPassword(String arg1) throws UnirestException {
        var response = Unirest.post(loginUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("username", arg1)
                .asString();
        body = response.getBody();
        responseStatus = response.getStatus();
    }


    public void user_Registration(String jsessionId ,String firstName, String lastName, String street, String sity,
                                  String state, String zipcode, String phoneNumber, String ssn, String user_Name,
                                  String password_, String repeatedPassword) throws UnirestException {
        var response = Unirest.post(registrationUrl)
                .header("Content-Type", "application/json")
                .header("Cookie", jsessionId)
                .body("{\"customer\":{\"firstName\":\"" + firstName + ",\"lastName\":\"" + lastName + "," +
                        "\"address\":{\"street\":\"" + street + ",\"sity\":\"" + sity + ",\"state\":\"" + state + "" +
                        ",\"zipCode\":\"" + zipcode + "},\"phoneNumber\":\"" + phoneNumber + ",\"ssn\":\"" + ssn + "," +
                        "\"username\":\"" + user_Name + ",\"password\":\"" + password_ + "," +
                        "\"repeatedPassword\":\"" + repeatedPassword + "}}")
                .asString();
        body = response.getBody();
        responseStatus = response.getStatus();
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public String getResponseBody() {
        return body;
    }

}
