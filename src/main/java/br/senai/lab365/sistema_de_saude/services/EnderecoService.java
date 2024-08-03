package br.senai.lab365.sistema_de_saude.services;

import br.senai.lab365.sistema_de_saude.dto.EnderecoRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.EnderecoResponseDTO;
import br.senai.lab365.sistema_de_saude.models.Endereco;
import br.senai.lab365.sistema_de_saude.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;


    public EnderecoResponseDTO createEndereco(EnderecoRequestDTO enderecoRequest) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setCep(enderecoRequest.getCep());

        Endereco savedEndereco = enderecoRepository.save(endereco);

        return mapToEnderecoResponse(savedEndereco);
    }

    public EnderecoResponseDTO updateEndereco(Long id, EnderecoRequestDTO enderecoRequest) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado  " + id));

        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setCep(enderecoRequest.getCep());

        Endereco updatedEndereco = enderecoRepository.save(endereco);

        return mapToEnderecoResponse(updatedEndereco);
    }

    public void deleteEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado  " + id));

        enderecoRepository.delete(endereco);
    }

    public EnderecoResponseDTO getEnderecoById(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado " + id));

        return mapToEnderecoResponse(endereco);
    }

    private EnderecoResponseDTO mapToEnderecoResponse(Endereco endereco) {
        EnderecoResponseDTO response = new EnderecoResponseDTO();
        response.setId(endereco.getId());
        response.setLogradouro(endereco.getLogradouro());
        response.setEstado(endereco.getEstado());
        response.setCidade(endereco.getCidade());
        response.setNumero(endereco.getNumero());
        response.setCep(endereco.getCep());

        return response;
    }
}

