package sg.edu.nus.iss.app.workshop18.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Models implements Serializable {
    private String firstName;
    private String secondName;
    private int percentage;
    private String result;
    private String id;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public static Models create(String json) throws IOException {
        Models p = new Models();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject object = reader.readObject();
            p.setPercentage(Integer.parseInt(object.getString("percentage")));
            p.setFirstName(object.getString("fname"));
            p.setSecondName(object.getString("sname"));
        }
        return p;
    
    }
}