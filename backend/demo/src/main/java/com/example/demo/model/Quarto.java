package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long residenciaId;

    // "INDIVIDUAL" ou "DUPLO"
    private String tipo;

    private boolean ar;
    private boolean hidro;

    // --- Quarto Individual ---
    private Integer numeroCamas; // 1 ou mais camas de solteiro
    private Double valorBase;
    private Double adicionalPorCama;

    // --- Quarto Duplo ---
    // "COMUM", "QUEEN" ou "KING"
    private String tipoCama;
    private boolean permiteBerco;
    private Double adicionalBerco;
    private Double adicionalConforto; // adicional por tipo de cama (queen/king)

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getResidenciaId() { return residenciaId; }
    public void setResidenciaId(Long residenciaId) { this.residenciaId = residenciaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isAr() { return ar; }
    public void setAr(boolean ar) { this.ar = ar; }

    public boolean isHidro() { return hidro; }
    public void setHidro(boolean hidro) { this.hidro = hidro; }

    public Integer getNumeroCamas() { return numeroCamas; }
    public void setNumeroCamas(Integer numeroCamas) { this.numeroCamas = numeroCamas; }

    public Double getValorBase() { return valorBase; }
    public void setValorBase(Double valorBase) { this.valorBase = valorBase; }

    public Double getAdicionalPorCama() { return adicionalPorCama; }
    public void setAdicionalPorCama(Double adicionalPorCama) { this.adicionalPorCama = adicionalPorCama; }

    public String getTipoCama() { return tipoCama; }
    public void setTipoCama(String tipoCama) { this.tipoCama = tipoCama; }

    public boolean isPermiteBerco() { return permiteBerco; }
    public void setPermiteBerco(boolean permiteBerco) { this.permiteBerco = permiteBerco; }

    public Double getAdicionalBerco() { return adicionalBerco; }
    public void setAdicionalBerco(Double adicionalBerco) { this.adicionalBerco = adicionalBerco; }

    public Double getAdicionalConforto() { return adicionalConforto; }
    public void setAdicionalConforto(Double adicionalConforto) { this.adicionalConforto = adicionalConforto; }
}