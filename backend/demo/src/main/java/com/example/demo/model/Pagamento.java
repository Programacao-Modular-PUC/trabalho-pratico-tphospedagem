package com.example.demo.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import com.example.demo.model.enums.TipoPagamento;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    private BigDecimal valorOriginal;
    private BigDecimal taxa;
    private BigDecimal valorFinal;
    private String resultado;

    @OneToOne
    @JoinColumn(name = "aluguel_id")
    private Aluguel aluguel;
}