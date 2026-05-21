package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ResidenciaRequestDTO;
import com.example.demo.dto.ResidenciaResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Residencia;
import com.example.demo.repository.ResidenciaRepository;

@Service // essa classe faz parte da loogica do sistema
public class ResidenciaService {

    private final ResidenciaRepository repository;

    public ResidenciaService(ResidenciaRepository repository) {
        this.repository = repository;
    }
    
    public List<ResidenciaResponseDTO> listar(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ResidenciaResponseDTO salvar(ResidenciaRequestDTO dto){
        Residencia residencia = new Residencia();
        residencia.setNome(dto.nome());
        residencia.setEndereco(dto.endereco());
        residencia.setBairro(dto.bairro());
        residencia.setTelefone(dto.telefone());
        residencia.setImagem(dto.imagem());

        return toResponse(repository.save(residencia));
    }

    public Residencia buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Residência não encontrada: " + id));
    }

    private ResidenciaResponseDTO toResponse(Residencia residencia) {
        return new ResidenciaResponseDTO(
            residencia.getId(),
            residencia.getNome(),
            residencia.getEndereco(),
            residencia.getBairro(),
            residencia.getTelefone(),
            residencia.getImagem(),
            residencia.getQuartos() == null ? 0 : residencia.getQuartos().size()
        );
    }
}
