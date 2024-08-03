package br.senai.lab365.sistema_de_saude.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Consulta")
@Data
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Nutricionista nutricionista;

    @ManyToOne
    private Paciente paciente;
    private LocalDate data;
    private String Observacoes;
}
