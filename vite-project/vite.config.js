import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/residencias': 'http://localhost:8080',
      '/quartos': 'http://localhost:8080',
      '/clientes': 'http://localhost:8080',
      '/alugueis': 'http://localhost:8080',
    },
  },
})
