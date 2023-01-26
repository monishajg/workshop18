package sg.edu.nus.iss.app.workshop18.service;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.app.workshop18.model.Models;

@Service
public class CompatibilityService {

    private static final String OPEN_COMPATIBILITY_URL
                                = "https://love-calculator.p.rapidapi.com/getPercentage"; // copy and paste from API

    public Optional<Models> getModels (String firstName, String secondName, Model model)throws IOException {

        String apiKey = System.getenv("OPEN_COMPATIBILITY_API_KEY");
        // HttpRequest request = HttpRequest.newBuilder()
        //                                 .uri(URI.create(OPEN_COMPATIBILITY_URL_STRING + "?firstName=" + firstName + "&secondName=" + secondName))
        //                                 .header("X-RapidAPI-Key", apikey)
        //                                 .header("X-RapidAPI-Host", "love-calculator.p.rapidapi.com")
        //                                 .method("GET", HttpRequest.BodyPublishers.noBody())
        //                                 .build();

        //HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());

        String percentageUrl = UriComponentsBuilder
                            .fromUriString(OPEN_COMPATIBILITY_URL)
                            .queryParam("sname", secondName)
                            .queryParam("fname", firstName)
                            .toUriString();

        System.out.println(percentageUrl);

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
        // String loverApiKey = System.getenv("LOVER_API_KEY");
        // String loverApiHost = System.getenv("LOVER_API_HOST");
        
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        
        ResponseEntity<String> resp = template.exchange(percentageUrl, HttpMethod.GET,
                requestEntity, String.class);
        System.out.println(resp);
        
        Models p = Models.create(resp.getBody());
        System.out.println(p);
     if (p != null)
        return Optional.of(p);
    return Optional.empty();
    }                   
    
}
