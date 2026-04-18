import {BrowserRouter, Routes, Route} from 'react-router-dom'

function App() {
  

  return (
    <>
      
    <Router>

    <ul>
      <li>Home</li>
      <li>Residências</li>
      <li>Quartos</li>
      <li>Cliente</li>
      <li>Reserva</li>

    </ul>
    <Switch>
      <Route exact path="/">
      <Home />
      </Route>

      <Route exact path="/residencias">
      <Residencias />
      </Route>

      <Route exact path="/quartos">
      <Quartos />
      </Route>

      <Route exact path="/cliente">
      <Cliente />
      </Route>

      <Route exact path="/reserva">
      <Reserva />
      </Route>

    </Switch>

    <p>footer</p>

    </Router>


    </>
  )
}

export default App
