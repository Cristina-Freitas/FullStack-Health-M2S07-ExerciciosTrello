package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

@Data
public class EnderecoRequestDTO {
    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}
