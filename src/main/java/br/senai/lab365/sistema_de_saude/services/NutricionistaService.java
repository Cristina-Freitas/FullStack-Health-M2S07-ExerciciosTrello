package br.senai.lab365.sistema_de_saude.services;

import br.senai.lab365.sistema_de_saude.dto.NutricionistaRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.NutricionistaResponseDTO;
import br.senai.lab365.sistema_de_saude.models.Nutricionista;
import br.senai.lab365.sistema_de_saude.repositories.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NutricionistaService {
    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public NutricionistaResponseDTO criaNutricionista(NutricionistaRequestDTO nutricionistaRequest) {
        // Verifica se já existe um nutricionista com o mesmo nome
        Optional<Nutricionista> existingNutricionista = nutricionistaRepository.findByNome(nutricionistaRequest.getNome());
        if (existingNutricionista.isPresent()) {
            throw new RuntimeException("Nutricionista com o nome " + nutricionistaRequest.getNome() + " já existe.");
        }
        Nutricionista nutricionista = new Nutricionista();
        nutricionista.setNome(nutricionistaRequest.getNome());
        nutricionista.setCrn(nutricionistaRequest.getCrn());
        nutricionista.setEspecialidade(nutricionistaRequest.getEspecialidade());

        Nutricionista salvaNutricionista = nutricionistaRepository.save(nutricionista);

        return mapToNutricionistaResponse(salvaNutricionista);
    }

    public NutricionistaResponseDTO atualizaNutricionista(Long id, NutricionistaRequestDTO nutricionistaRequest) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutricionista não encontrado " + id));

        // Verifica se já existe um nutricionista com o mesmo nome, excluindo o atual
        Optional<Nutricionista> existingNutricionista = nutricionistaRepository.findByNome(nutricionistaRequest.getNome());
        if (existingNutricionista.isPresent() && !existingNutricionista.get().getId().equals(id)) {
            throw new RuntimeException("Nutricionista com o nome " + nutricionistaRequest.getNome() + " já existe.");
        }

        nutricionista.setNome(nutricionistaRequest.getNome());
        nutricionista.setCrn(nutricionistaRequest.getCrn());
        nutricionista.setEspecialidade(nutricionistaRequest.getEspecialidade());

        Nutricionista updatedNutricionista = nutricionistaRepository.save(nutricionista);

        return mapToNutricionistaResponse(updatedNutricionista);
    }

    public void deletaNutricionista(Long id) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutricionista não encontrada " + id));

        nutricionistaRepository.delete(nutricionista);
    }

    public NutricionistaResponseDTO getNutricionistaById(Long id) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutricionista não encontrada " + id));

        return mapToNutricionistaResponse(nutricionista);
    }

    private NutricionistaResponseDTO mapToNutricionistaResponse(Nutricionista nutricionista) {
        NutricionistaResponseDTO response = new NutricionistaResponseDTO();
        response.setId(nutricionista.getId());
        response.setNome(nutricionista.getNome());
        response.setCrn(nutricionista.getCrn());
        response.setEspecialidade(nutricionista.getEspecialidade());

        return response;
    }
}
