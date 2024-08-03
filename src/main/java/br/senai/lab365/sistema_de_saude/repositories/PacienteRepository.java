package br.senai.lab365.sistema_de_saude.repositories;

import br.senai.lab365.sistema_de_saude.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
