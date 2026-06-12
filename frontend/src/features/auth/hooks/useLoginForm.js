import { useState } from 'react'
import useAuth from './useAuth'

function useLoginForm() {
  const [form, setForm] = useState({ email: '', password: '' })
  const { loginMutation } = useAuth()

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    loginMutation.mutate(form)
  }

  return { form, handleChange, handleSubmit, loginMutation }
}

export default useLoginForm
