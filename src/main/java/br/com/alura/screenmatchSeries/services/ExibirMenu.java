package br.com.alura.screenmatchSeries.services;

import br.com.alura.screenmatchSeries.model.DadosEpisodios;
import br.com.alura.screenmatchSeries.model.DadosSerie;
import br.com.alura.screenmatchSeries.model.DadosTemporada;

public class ExibirMenu {
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
        dadosTemporada.temporada();
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
        return conversor;
    }
}