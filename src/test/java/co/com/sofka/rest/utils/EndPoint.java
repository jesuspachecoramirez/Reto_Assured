package co.com.sofka.rest.utils;

public enum EndPoint {
    SUCCESSFUL_REGISTER("/register"),
    UNSUCCESSFUL_REGISTER("/register");

    private final String value;

    EndPoint(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
