import api from '@/config/axios'

export const login = (credentials) =>
  api.post('/api/auth/login', credentials).then((r) => r.data)

export const register = (userData) =>
  api.post('/api/auth/register', userData).then((r) => r.data)
