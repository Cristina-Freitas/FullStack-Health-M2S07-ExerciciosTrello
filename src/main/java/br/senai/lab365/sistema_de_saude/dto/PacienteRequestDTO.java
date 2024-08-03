package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteRequestDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
}
