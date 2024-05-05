import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaPelicula {

    //Metodo
    public Pelicula buscaPelicula(int numeroDePelicula) {
        //Creamos una direccion
        URI direccion = URI.create("https://swapi.py4e.com/api/films/"+numeroDePelicula+"/");

        //Buscamos HttpRequest en la doc jdk21
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            //Buscamos HttpResponse en la doc jdk21
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            //Le pasamos el cuerpo de json creado y luego la clase a la que transformaremos el json
            return new Gson().fromJson(response.body(),Pelicula.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontré esa película");
        }

    }
}
