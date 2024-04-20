package br.com.alura.screenmatchSeries.services;

import br.com.alura.screenmatchSeries.ScreenmatchSeriesApplication;
import br.com.alura.screenmatchSeries.model.DadosEpisodios;
import br.com.alura.screenmatchSeries.model.DadosSerie;
import br.com.alura.screenmatchSeries.model.DadosTemporada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExibirMenu {
    public static List<DadosTemporada> temporadas = new ArrayList<>();
    public static void buscarEpisodio(String busca, int temporada, int episodio, ConsumoAPI api, ConverteDados conversor) {
        String endereco;
        String json;
        endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + temporada + "&episode=" + episodio + "&apikey=825f193a";
        json = api.obterDados(endereco);

        DadosEpisodios dadosEpisodios = conversor.obterDados(json, DadosEpisodios.class);
        System.out.println(dadosEpisodios);
    }
    public static void buscarTemporada(String busca, int temporada, ConsumoAPI api, ConverteDados conversor) {
        String endereco;
        String json;
        endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + temporada + "&apikey=825f193a";
        json = api.obterDados(endereco);
        System.out.println(json);
        DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
        System.out.println(dadosTemporada);
    }

    public static ConverteDados getConverteDados(String busca, int temporada, ConsumoAPI api) {
        String json;
        String endereco;
        endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + temporada +"&apikey=825f193a";
        json = api.obterDados(endereco);

        ConverteDados conversor = new ConverteDados(); // cria um conversor de converter dados
        DadosSerie dados = conversor.obterDados(json,DadosSerie.class); // cria uma instancia chamada dados e aplicar o
        // conversor chamando o obter dados, passando os parametros para a interface  sendo o json e a classe DadosSerie
        
        System.out.println(dados);
        ArrayTemporadasFunc(busca, dados, conversor);

        return conversor;
    }

    public static void ArrayTemporadasFunc(String busca, DadosSerie dados, ConverteDados conversor) {
        String endereco;
        for (int i = 0; i < dados.totalTemporadas(); i++) {
            endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&season=" + i +"&apikey=825f193a";
            DadosTemporada dadosTemporada = conversor.obterDados(endereco, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
    }

    public static void exibirTemporadaEEpisodio(Scanner leitura, ExibirMenu exibirMenu, ScreenmatchSeriesApplication.Resultado result) {
        System.out.println("Digite a temporada: ");
        var temporada = leitura.nextInt();
        ConverteDados conversor = getConverteDados(result.busca(), temporada, result.api());
        buscarTemporada(result.busca(),temporada, result.api(),conversor);

        System.out.println("Digite o episodio em que voce gostaria de analisar: ");
        var episodio = leitura.nextInt();
        buscarEpisodio(result.busca(), temporada, episodio, result.api(), conversor);
    }
    public static void exibirMelhoresEpisodios(Scanner leitura, ExibirMenu exibirMenu, ScreenmatchSeriesApplication.Resultado result){
       // List<DadosEpisodios> dadosEpisodios =
    }




}
