package br.senai.lab365.sistema_de_saude.repositories;

import br.senai.lab365.sistema_de_saude.models.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
}
