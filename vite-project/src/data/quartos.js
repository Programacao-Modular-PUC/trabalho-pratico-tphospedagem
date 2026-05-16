const quartos = [
  // Residência 1
  {
    id: 1, residenciaId: 1, tipo: "INDIVIDUAL",
    numeroCamas: 2, valorBase: 100, adicionalPorCama: 40,
    ar: true, hidro: false
  },
  {
    id: 2, residenciaId: 1, tipo: "DUPLO",
    tipoCama: "QUEEN", valorBase: 200, adicionalConforto: 50,
    permiteBerco: true, adicionalBerco: 30,
    ar: true, hidro: true
  },

  // Residência 2
  {
    id: 3, residenciaId: 2, tipo: "INDIVIDUAL",
    numeroCamas: 1, valorBase: 80, adicionalPorCama: 0,
    ar: false, hidro: false
  },
  {
    id: 4, residenciaId: 2, tipo: "DUPLO",
    tipoCama: "COMUM", valorBase: 150, adicionalConforto: 20,
    permiteBerco: true, adicionalBerco: 25,
    ar: true, hidro: false
  },

  // Residência 3 — Sol Nascente
  {
    id: 5, residenciaId: 3, tipo: "INDIVIDUAL",
    numeroCamas: 3, valorBase: 90, adicionalPorCama: 35,
    ar: true, hidro: false
  },
  {
    id: 6, residenciaId: 3, tipo: "DUPLO",
    tipoCama: "KING", valorBase: 250, adicionalConforto: 70,
    permiteBerco: true, adicionalBerco: 30,
    ar: true, hidro: true
  },
  {
    id: 7, residenciaId: 3, tipo: "DUPLO",
    tipoCama: "COMUM", valorBase: 160, adicionalConforto: 20,
    permiteBerco: false, adicionalBerco: 0,
    ar: false, hidro: false
  },
];

export default quartos;