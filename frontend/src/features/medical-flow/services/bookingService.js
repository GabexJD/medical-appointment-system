import api from '@/config/axios'

export const getSpecialties = () =>
  api.get('/api/specialties').then((r) => r.data)

export const getDoctors = () =>
  api.get('/api/doctors').then((r) => r.data)

export const getAvailability = (doctorId) =>
  api.get(`/api/doctors/${doctorId}/availability`).then((r) => r.data)

export const createAppointment = (payload) =>
  api.post('/api/appointments', payload).then((r) => r.data)
