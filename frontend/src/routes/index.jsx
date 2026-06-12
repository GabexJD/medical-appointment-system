import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from '@/features/auth/Login'
import Register from '@/features/auth/Register'
import Dashboard from '@/features/dashboard/Dashboard'
import SpecialtySelection from '@/features/medical-flow/SpecialtySelection'
import DoctorSelection from '@/features/medical-flow/DoctorSelection'
import DateTimeSelection from '@/features/medical-flow/DateTimeSelection'
import ConfirmationView from '@/features/medical-flow/ConfirmationView'

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/book" element={<SpecialtySelection />} />
        <Route path="/book/doctors" element={<DoctorSelection />} />
        <Route path="/book/doctors/datetime" element={<DateTimeSelection />} />
        <Route path="/book/confirm" element={<ConfirmationView />} />
      </Routes>
    </BrowserRouter>
  )
}

export default AppRouter
