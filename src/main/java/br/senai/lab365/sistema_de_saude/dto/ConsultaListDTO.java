package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaListDTO {
    private LocalDate data;
    private String nutricionistaNome;
    private String pacienteNome;
}
