import { useMutation } from '@tanstack/react-query'
import { useNavigate } from 'react-router-dom'
import { createAppointment } from '@/features/medical-flow/services/bookingService'
import queryClient from '@/config/queryClient'

function useCreateAppointment() {
  const navigate = useNavigate()

  const mutation = useMutation({
    mutationFn: createAppointment,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['appointments'] })
      navigate('/dashboard', { state: { bookingSuccess: true } })
    },
  })

  return mutation
}

export default useCreateAppointment
