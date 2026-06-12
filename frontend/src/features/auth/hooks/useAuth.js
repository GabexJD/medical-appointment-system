import { useMutation } from '@tanstack/react-query'
import { useNavigate } from 'react-router-dom'
import * as authService from '@/features/auth/services/authService'

function useAuth() {
  const navigate = useNavigate()

  const saveSession = (data) => {
    const { id, fullName } = data
    localStorage.setItem('session', JSON.stringify({ id, fullName }))
    navigate('/dashboard')
  }

  const loginMutation = useMutation({
    mutationFn: authService.login,
    onSuccess: saveSession,
  })

  const registerMutation = useMutation({
    mutationFn: authService.register,
    onSuccess: saveSession,
  })

  return { loginMutation, registerMutation }
}

export default useAuth
