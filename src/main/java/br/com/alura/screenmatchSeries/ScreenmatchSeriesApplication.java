package br.com.alura.screenmatchSeries;


import br.com.alura.screenmatchSeries.model.Episodios;
import br.com.alura.screenmatchSeries.services.ConsumoAPI;
import br.com.alura.screenmatchSeries.services.ConverteDados;
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

		System.out.println("Digite o nome da serie que voce gostaria de pesquisar: ");
		var busca = leitura.nextLine();


		String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") +"&apikey=825f193a";
		ConsumoAPI api = new ConsumoAPI();
		String json = api.obterDados(endereco);
		System.out.println(json);


		System.out.println("Digite a temporada: ");
		var temporada = leitura.nextInt();
		ConverteDados conversor = ExibirMenu.getConverteDados(busca, temporada, api);
		ExibirMenu.buscarTemporada(busca,temporada, api,conversor);

		System.out.println("Digite o episodio em que voce gostaria de analisar: ");
		var episodio = leitura.nextInt();
		ExibirMenu.buscarEpisodio(busca, temporada, episodio, api, conversor);



		/*
		// API de fotos de cafe aleatorias
		json = api.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		*/


	}





}
