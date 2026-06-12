import api from '@/config/axios'

export const getAppointmentsByUser = (userId) =>
  api.get(`/api/appointments/user/${userId}`).then((r) => r.data)
