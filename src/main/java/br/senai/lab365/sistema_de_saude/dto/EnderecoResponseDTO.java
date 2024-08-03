package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

@Data
public class EnderecoResponseDTO {
    private Long id;
    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}
