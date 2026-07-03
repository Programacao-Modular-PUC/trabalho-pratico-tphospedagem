import { createContext, useContext, useState } from 'react';

const AuthContext = createContext(null);
const STORAGE_KEY = 'cliente';

export function AuthProvider({ children }) {
  const [cliente, setCliente] = useState(() => {
    const raw = localStorage.getItem(STORAGE_KEY);
    return raw ? JSON.parse(raw) : null;
  });

  function login(clienteData) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(clienteData));
    setCliente(clienteData);
  }

  function logout() {
    localStorage.removeItem(STORAGE_KEY);
    setCliente(null);
  }

  return (
    <AuthContext.Provider value={{ cliente, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
