package br.com.alura.screenmatchSeries.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverterDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe); // aqui ele vai tentar ler o mapper e converter na classe que nos passamos
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
