import { useQuery } from '@tanstack/react-query'
import { getAppointmentsByUser } from '@/features/dashboard/services/dashboardService'
import { UPCOMING_STATUSES } from '@/constants/appointmentStatuses'

function useDashboardData() {
  const session = JSON.parse(localStorage.getItem('session') || '{}')
  const userId = session.id

  const { data, isLoading, error } = useQuery({
    queryKey: ['appointments', userId],
    queryFn: () => getAppointmentsByUser(userId),
    enabled: !!userId,
  })

  const appointments = Array.isArray(data) ? data : []

  const upcoming = appointments.filter((appt) =>
    UPCOMING_STATUSES.includes(appt.status)
  )

  return { appointments, upcoming, isLoading, error }
}

export default useDashboardData
