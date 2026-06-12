import { useQuery } from '@tanstack/react-query'
import { getSpecialties } from '@/features/medical-flow/services/bookingService'

function useSpecialties() {
  const { data, isLoading, error } = useQuery({
    queryKey: ['specialties'],
    queryFn: getSpecialties,
  })

  const specialties = Array.isArray(data) ? data : []

  return { specialties, isLoading, error }
}

export default useSpecialties
