import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultaCEP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o CEP (apenas números): ");
        String cep = scanner.nextLine();
        
        // URL da API ViaCEP
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        // Criando o cliente e a requisição
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Enviando a requisição e recebendo a resposta
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println) // Imprime o JSON bruto na tela
                .join();
        
        scanner.close();
    }
}