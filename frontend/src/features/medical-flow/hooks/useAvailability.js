import { useQuery } from '@tanstack/react-query'
import { getAvailability } from '@/features/medical-flow/services/bookingService'

function useAvailability(doctorId) {
  const { data, isLoading, error } = useQuery({
    queryKey: ['availability', doctorId],
    queryFn: () => getAvailability(doctorId),
    enabled: !!doctorId,
  })

  const slots = Array.isArray(data?.availableSlots) ? data.availableSlots : []

  const groupedByDate = slots.reduce((acc, slot) => {
    const date = slot.date
    if (!acc[date]) {
      acc[date] = []
    }
    acc[date].push(slot)
    return acc
  }, {})

  const dates = Object.keys(groupedByDate).sort()

  return { slots, groupedByDate, dates, isLoading, error }
}

export default useAvailability
