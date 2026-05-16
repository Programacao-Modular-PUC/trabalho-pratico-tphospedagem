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
];

export default quartos;