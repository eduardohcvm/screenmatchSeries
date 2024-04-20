package br.com.alura.screenmatchSeries;


import br.com.alura.screenmatchSeries.model.DadosTemporada;
import br.com.alura.screenmatchSeries.services.ConsumoAPI;
import br.com.alura.screenmatchSeries.services.ExibirMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchSeriesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSeriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ExibirMenu exibirMenu = new ExibirMenu();
		// é como se fosse um metodo main
		FileWriter escrita = new FileWriter("series.txt");
		Scanner leitura = new Scanner(System.in);

		Resultado result = getResultado(leitura);
		System.out.println("Apartir desta serie você gostaria de exibir uma temporada e episodio específico ou listar" +
				" todas as temporadas  e episódios?:\n Digite 1 para o primeiro ou 2 para o segundo: ");

		int opcao = leitura.nextInt();
		String buffer = leitura.nextLine();




		switch (opcao){
			case 1:
				ExibirMenu.exibirTemporadaEEpisodio(leitura, exibirMenu, result);
				break;
			default: throw new IllegalStateException("Unexpected value: " + leitura);
        }



		/*
		// API de fotos de cafe aleatorias
		json = api.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		*/


	}



	private static Resultado getResultado(Scanner leitura) {
		System.out.println("Digite o nome da serie que voce gostaria de pesquisar: ");
		var busca = leitura.nextLine();


		String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") +"&apikey=825f193a";
		ConsumoAPI api = new ConsumoAPI();
		String json = api.obterDados(endereco);
		System.out.println(json);

		return new Resultado(busca, api);
	}

	public record Resultado(String busca, ConsumoAPI api) {
	}


}
