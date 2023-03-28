import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        //Conectar no imdb e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_t51hw2lc";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);
        //Filtrar para obter apenas os dados que me interessam (titulo, poster, classificação)
        //criadaa a clase para segmentar
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m \u001b[4m" + filme.get("fullTitle"));
            System.out.println("Poster: " + filme.get("image"));
            
            var rating = Float.parseFloat(filme.get("imDbRating"));
            
            System.out.println("\u001b[37;1m \u001b[40;1m" + "Classificação: " + filme.get("imDbRating"));
            
            String star = "\u2B50";
            for (int i = 0; i < rating; i++) {
                System.out.print(star);
            }
            System.out.println();
            System.out.println();
        }
    }
}
