import { useState } from 'react'
import useAuth from './useAuth'

function useRegisterForm() {
  const [form, setForm] = useState({
    fullName: '',
    documentNumber: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: '',
  })
  const [errors, setErrors] = useState({})
  const { registerMutation } = useAuth()

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))
    if (errors[e.target.name]) {
      setErrors((prev) => ({ ...prev, [e.target.name]: '' }))
    }
  }

  const validate = () => {
    const errs = {}
    if (!form.fullName || form.fullName.trim().length < 3) {
      errs.fullName = 'El nombre debe tener al menos 3 caracteres.'
    }
    if (!form.documentNumber || !/^[a-zA-Z0-9]+$/.test(form.documentNumber)) {
      errs.documentNumber = 'Ingrese un número de documento válido.'
    }
    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(form.email)) {
      errs.email = 'Ingrese un correo electrónico válido.'
    }
    if (!/^[67]\d{7}$/.test(form.phone)) {
      errs.phone = 'El número de teléfono debe tener 8 dígitos.'
    }
    if (!form.password || form.password.length < 6) {
      errs.password = 'La contraseña debe tener al menos 6 caracteres.'
    }
    if (form.password !== form.confirmPassword) {
      errs.confirmPassword = 'Las contraseñas no coinciden.'
    }
    return errs
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    const errs = validate()
    setErrors(errs)
    if (Object.keys(errs).length > 0) return
    const { confirmPassword, ...payload } = form
    registerMutation.mutate(payload)
  }

  return { form, errors, handleChange, handleSubmit, registerMutation }
}

export default useRegisterForm
