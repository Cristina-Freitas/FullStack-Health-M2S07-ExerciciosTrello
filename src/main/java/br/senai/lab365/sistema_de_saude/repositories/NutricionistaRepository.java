package br.senai.lab365.sistema_de_saude.repositories;

import br.senai.lab365.sistema_de_saude.models.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Optional<Nutricionista> findByNome(String nome);
}
