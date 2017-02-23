package pl.konczak.nzoz.ewus.client.ewus;

public class LoginResponse {

    private final String session;

    private final String authToken;

    private final String response;

    public LoginResponse(String session, String authToken, String response) {
        this.session = session;
        this.authToken = authToken;
        this.response = response;
    }

    public String getSession() {
        return session;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getResponse() {
        return response;
    }

}
