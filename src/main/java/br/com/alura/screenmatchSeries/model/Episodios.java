package br.com.alura.screenmatchSeries.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Episodios {
    private Integer temporada;
    private String titulo;
    private Integer numEpisodio;
    private  String avaliacao;
    private LocalDate dataLancamento;
}
