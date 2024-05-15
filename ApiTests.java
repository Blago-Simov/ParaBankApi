import Utils.ReadAndWriteExelL;
import api.GetRequest;
import com.mashape.unirest.http.exceptions.UnirestException;
import api.PostRequest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.asynchttpclient.util.Assertions.assertNotNull;

public class ApiTests {

    PostRequest postRequest ;

    GetRequest getRequest;

    ReadAndWriteExelL readAndWriteExelL;

    private static String exelPath1 = "C:\\Users\\35989\\IdeaProjects\\ParaBankApp\\ParabankUserData.xlsx";
    private static String exelPath2 = "C:\\Users\\35989\\IdeaProjects\\ParaBankApp\\ParaBankFakeData.xlsx";

    @Test
    public void testMainPage() throws UnirestException {
        getRequest = new GetRequest();
        getRequest.loadMainPage();
        assertNotNull(getRequest.getResponseBody(), "The request has body");
        assertEquals(200, getRequest.getResponseStatus());
    }

    @Test
    public void successfulLogin() throws UnirestException, IOException {
        postRequest = new PostRequest();
        readAndWriteExelL = new ReadAndWriteExelL();
        postRequest.user_Login
                (readAndWriteExelL.getCellValue(0,"Login",exelPath1),
                        readAndWriteExelL.getCellValue(1,"Login",exelPath1));
        assertNotNull(postRequest.getResponseBody(),"The request has body");
        assertEquals(302, postRequest.getResponseStatus());
    }

    @Test
    public void unsuccessfulLoginWithInvalidUsername() throws IOException, UnirestException {
        postRequest = new PostRequest();
        readAndWriteExelL = new ReadAndWriteExelL();
        postRequest.user_LoginInvalidUserName(readAndWriteExelL.getCellValue(1,"login",exelPath1));
        Assert.assertTrue(postRequest.getResponseBody().isEmpty());
        assertEquals(400, postRequest.getResponseStatus());
    }
    @Test
    public void unsuccessfulLoginWithInvalidPassword() throws UnirestException, IOException {
        postRequest = new PostRequest();
        readAndWriteExelL = new ReadAndWriteExelL();
        postRequest.user_LoginInvalidPassword(readAndWriteExelL.getCellValue(0,"Login",exelPath1));
        Assert.assertTrue(postRequest.getResponseBody().isEmpty());
        assertEquals(400, postRequest.getResponseStatus());
    }

    @Test
    public void successfulRegistration() throws UnirestException, IOException {
        postRequest = new PostRequest();
        readAndWriteExelL = new ReadAndWriteExelL();
        readAndWriteExelL.writeInCell(exelPath2);

        postRequest.user_Registration("FF801D5AABBF30C181B0C22706FE2731",
                readAndWriteExelL.getCellValue(0, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(1, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(2, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(3, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(4, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(5, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(6, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(7, "Registration",exelPath1),
                readAndWriteExelL.getCellValue(0,"WriteIn",exelPath2),
                readAndWriteExelL.getCellValue(1, "Login",exelPath1),
                readAndWriteExelL.getCellValue(1, "Login",exelPath1));
        assertNotNull(postRequest.getResponseBody(), "The request has body");
        assertEquals(200, postRequest.getResponseStatus());

    }

    }
