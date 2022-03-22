package co.com.sofka.rest.utils;

public enum JsonPath {
    REGISTER("src/test/resources/files/Register/RegisterSuccessful.json"),
    UNREGISTER("src/test/resources/files/Register/RegisterUnsuccessful.json");


    private final String value;

    JsonPath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
