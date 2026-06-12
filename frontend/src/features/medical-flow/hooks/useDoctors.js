import { useQuery } from '@tanstack/react-query'
import { getDoctors } from '@/features/medical-flow/services/bookingService'

function useDoctors(specialtyId) {
  const { data, isLoading, error } = useQuery({
    queryKey: ['doctors', specialtyId],
    queryFn: getDoctors,
    enabled: !!specialtyId,
  })

  const allDoctors = Array.isArray(data) ? data : []
  const doctors = specialtyId
    ? allDoctors.filter((doc) => String(doc.specialtyId) === String(specialtyId))
    : allDoctors

  return { doctors, isLoading, error }
}

export default useDoctors
