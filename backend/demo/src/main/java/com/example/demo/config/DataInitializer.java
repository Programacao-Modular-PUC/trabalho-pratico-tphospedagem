package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import com.example.demo.model.*;
import com.example.demo.model.enums.AluguelStatus;
import com.example.demo.repository.*;


@Component
public class DataInitializer implements CommandLineRunner {

    private final ResidenciaRepository residenciaRepository;
    private final QuartoRepository quartoRepository;
    private final ClienteRepository clienteRepository;
    private final AluguelRepository aluguelRepository;

    public DataInitializer(
            ResidenciaRepository residenciaRepository,
            QuartoRepository quartoRepository,
            ClienteRepository clienteRepository,
            AluguelRepository aluguelRepository
    ) {
        this.residenciaRepository = residenciaRepository;
        this.quartoRepository = quartoRepository;
        this.clienteRepository = clienteRepository;
        this.aluguelRepository = aluguelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem dados
        if (residenciaRepository.count() > 0) {
            return; // Não reinicializa se já houver dados
        }

        // Criando residências
        Residencia res1 = new Residencia();
        res1.setNome("Residência Bela Vista");
        res1.setEndereco("Rua das Flores, 123");
        res1.setBairro("Centro");
        res1.setTelefone("(31) 98395-9523");
        res1.setImagem("https://images.unsplash.com/photo-1570129477492-45d003cdd643?w=500");
        res1 = residenciaRepository.save(res1);

        Residencia res2 = new Residencia();
        res2.setNome("Residência Jardim dos Sonhos");
        res2.setEndereco("Rua das Alamedas, 845");
        res2.setBairro("Barreiro");
        res2.setTelefone("(31) 95439-3659");
        res2.setImagem("https://images.unsplash.com/photo-1540932239986-310128078000?w=500");
        res2 = residenciaRepository.save(res2);

        Residencia res3 = new Residencia();
        res3.setNome("Residência Sol Nascente");
        res3.setEndereco("Rua das Lindas, 23");
        res3.setBairro("Industrial");
        res3.setTelefone("(31) 99485-8973");
        res3.setImagem("https://images.unsplash.com/photo-1556909114-f6e7ad7d3136?w=500");
        res3 = residenciaRepository.save(res3);

        // Criando quartos para residência 1
        QuartoIndividual q1 = new QuartoIndividual();
        q1.setValorBase(new BigDecimal("150.00"));
        q1.setPossuiAR(true);
        q1.setPossuiHidro(false);
        q1.setCapacidadeMaxima(1);
        q1.setQuantidadeCamasSolteiro(1);
        q1.setTaxaCamaAdicional(new BigDecimal("0.0"));
        q1.setResidencia(res1);
        quartoRepository.save(q1);

        QuartoDuplo q2 = new QuartoDuplo();
        q2.setValorBase(new BigDecimal("250.00"));
        q2.setPossuiAR(true);
        q2.setPossuiHidro(true);
        q2.setCapacidadeMaxima(2);
        q2.setCamaQueenKing(true);
        q2.setPermiteBerco(false);
        q2.setTaxaBerco(new BigDecimal("0.0"));
        q2.setTaxaQueenKing(new BigDecimal("50.00"));
        q2.setResidencia(res1);
        quartoRepository.save(q2);

        QuartoFamilia q3 = new QuartoFamilia();
        q3.setValorBase(new BigDecimal("350.00"));
        q3.setPossuiAR(true);
        q3.setPossuiHidro(true);
        q3.setCapacidadeMaxima(4);
        q3.setQuantidadeAmbientes(3);
        q3.setValorPorHospedeAdicional(new BigDecimal("30.00"));
        q3.setPercentualDescontoGrupo(new BigDecimal("10"));
        q3.setResidencia(res1);
        quartoRepository.save(q3);

        // Criando quartos para residência 2
        QuartoIndividual q4 = new QuartoIndividual();
        q4.setValorBase(new BigDecimal("120.00"));
        q4.setPossuiAR(false);
        q4.setPossuiHidro(false);
        q4.setCapacidadeMaxima(1);
        q4.setQuantidadeCamasSolteiro(1);
        q4.setTaxaCamaAdicional(new BigDecimal("0.0"));
        q4.setResidencia(res2);
        quartoRepository.save(q4);

        QuartoDuplo q5 = new QuartoDuplo();
        q5.setValorBase(new BigDecimal("200.00"));
        q5.setPossuiAR(true);
        q5.setPossuiHidro(false);
        q5.setCapacidadeMaxima(2);
        q5.setCamaQueenKing(false);
        q5.setPermiteBerco(true);
        q5.setTaxaBerco(new BigDecimal("30.00"));
        q5.setTaxaQueenKing(new BigDecimal("0.0"));
        q5.setResidencia(res2);
        quartoRepository.save(q5);

        // Criando quartos para residência 3
        QuartoFamilia q6 = new QuartoFamilia();
        q6.setValorBase(new BigDecimal("400.00"));
        q6.setPossuiAR(true);
        q6.setPossuiHidro(true);
        q6.setCapacidadeMaxima(5);
        q6.setQuantidadeAmbientes(4);
        q6.setValorPorHospedeAdicional(new BigDecimal("40.00"));
        q6.setPercentualDescontoGrupo(new BigDecimal("15"));
        q6.setResidencia(res3);
        quartoRepository.save(q6);

        // Criando clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setCpf("123.456.789-00");
        cliente1.setEndereco("Rua Principal, 100");
        cliente1.setTelefone("(31) 98765-4321");
        cliente1.setEmail("joao@email.com");
        clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Santos");
        cliente2.setCpf("987.654.321-00");
        cliente2.setEndereco("Avenida Central, 200");
        cliente2.setTelefone("(31) 99876-5432");
        cliente2.setEmail("maria@email.com");
        clienteRepository.save(cliente2);

        System.out.println("✓ Dados iniciais carregados com sucesso!");
    }
}
