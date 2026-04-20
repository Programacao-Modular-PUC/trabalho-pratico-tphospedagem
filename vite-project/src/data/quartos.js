import { captureOwnerStack } from "react";


const quartos = [
  // quartos da residência 1
  { id: 1, residenciaId: 1, tipo: "Casal", valor: 200, ar: true, hidro: false, capacidade: 2 },
  { id: 2, residenciaId: 1, tipo: "Solteiro", valor: 120, ar: false, hidro: false, capacidade: 1 },
  { id: 3, residenciaId: 1, tipo: "Casal", valor: 250, ar: true, hidro: true, capacidade: 2 },

  // quartos da residência 2
  { id: 4, residenciaId: 2, tipo: "Solteiro", valor: 100, ar: false, hidro: false, capacidade: 1 },
  { id: 5, residenciaId: 2, tipo: "Casal", valor: 180, ar: true, hidro: false, capacidade: 2 },

  // quartos da residência 3
  { id: 6, residenciaId: 3, tipo: "Casal", valor: 220, ar: true, hidro: false, capacidade: 2 }
];

export default quartos;