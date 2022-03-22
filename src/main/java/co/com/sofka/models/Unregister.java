package co.com.sofka.models;

import co.com.sofka.utils.JsonConverter;

public class Unregister extends JsonConverter {

    private String email;


    public Unregister(String jsonPath) {
        super(jsonPath);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        Unregister data = gson.fromJson(json,Unregister.class);
        data.setEmail(email);
         return gson.toJson(data);
    }
}
