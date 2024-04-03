package br.com.alura.screenmatchSeries.services;

public interface IConverterDados {
   <T> T obterDados(String json, Class<T> classe );// AQUI EU USEI O GENERICS
    /*
    Generics é um recurso no java que faz uma função poder passar qualquer tipo de variavel como parametro
     e a função ira aceitar, alé disso depois do primeiro parametro, esta o tipo de retorno desejavel
     depois é trabalho do converte dados convertes os dados desse obter dados para a classe em si
     */

}
