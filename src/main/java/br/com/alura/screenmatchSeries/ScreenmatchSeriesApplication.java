package br.com.alura.screenmatchSeries;


import br.com.alura.screenmatchSeries.model.DadosEpisodios;
import br.com.alura.screenmatchSeries.model.DadosSerie;
import br.com.alura.screenmatchSeries.model.DadosTemporada;
import br.com.alura.screenmatchSeries.services.ConsumoAPI;
import br.com.alura.screenmatchSeries.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchSeriesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSeriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// é como se fosse um metodo main
		FileWriter escrita = new FileWriter("series.txt");
		Scanner leitura = new Scanner(System.in);

		System.out.println("Digite o nome da serie que voce gostaria de pesquisar: ");
		var busca = leitura.nextLine();


		String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") +"&apikey=825f193a";
		ConsumoAPI api = new ConsumoAPI();
		String json = api.obterDados(endereco);
		System.out.println(json);


		System.out.println("Digite a temporada: ");
		var temporada = leitura.nextInt();
		ConverteDados conversor = getConverteDados(busca, temporada, api);
		buscarTemporada(busca,temporada, api,conversor);

		System.out.println("Digite o episodio em que voce gostaria de analisar: ");
		var episodio = leitura.nextInt();
		buscarEpisodio(busca, temporada, episodio, api, conversor);



		/*
		// API de fotos de cafe aleatorias
		json = api.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		*/


	}

	private static void buscarEpisodio(String busca, int temporada, int episodio, ConsumoAPI api, ConverteDados conversor) {
		String endereco;
		String json;
		endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + temporada + "&episode=" + episodio + "&apikey=825f193a";
		json = api.obterDados(endereco);
		System.out.println(json);
		DadosEpisodios dadosEpisodios = conversor.obterDados(json, DadosEpisodios.class);
		System.out.println(dadosEpisodios);
	}
	private static void buscarTemporada(String busca, int temporada, ConsumoAPI api, ConverteDados conversor) {
		String endereco;
		String json;
		endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + temporada + "&apikey=825f193a";
		json = api.obterDados(endereco);
		System.out.println(json);
		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
		System.out.println(dadosTemporada);
	}

	private static ConverteDados getConverteDados(String busca, int temporada, ConsumoAPI api) {
		String json;
		String endereco;
		endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + temporada +"&apikey=825f193a";
		json = api.obterDados(endereco);
		System.out.println(json);

		ConverteDados conversor = new ConverteDados(); // cria um conversor de converter dados
		DadosSerie dados = conversor.obterDados(json,DadosSerie.class); // cria uma instancia chamada dados e aplicar o
		// conversor chamando o obter dados, passando os parametros para a interface  sendo o json e a classe DadosSerie
		System.out.println(dados);
		return conversor;
	}

}
