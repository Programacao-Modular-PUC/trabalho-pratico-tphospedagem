package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Quarto;
import com.example.demo.repository.QuartoRepository;
@Service


public class QuartoService {
    @Autowired
    private QuartoRepository repository;

    public List<Quarto> listar(){
        return repository.findAll();
    }

    public Quarto salvar(Quarto q){
        return repository.save(q);
    }
    
}

public double calcularDiaria(Quarto q, boolean solicitaBerco) {
    double total = q.getValorBase();

    if ("INDIVIDUAL".equalsIgnoreCase(q.getTipo())) {
        int camas = q.getNumeroCamas() != null ? q.getNumeroCamas() : 1;
        if (camas > 1) {
            total += (camas - 1) * q.getAdicionalPorCama();
        }
    } else if ("DUPLO".equalsIgnoreCase(q.getTipo())) {
        total += q.getAdicionalConforto() != null ? q.getAdicionalConforto() : 0;
        if (solicitaBerco && q.isPermiteBerco()) {
            total += q.getAdicionalBerco() != null ? q.getAdicionalBerco() : 0;
        }
    }

    return total;
}
