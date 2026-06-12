import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from '@/features/auth/Login'
import Dashboard from '@/features/dashboard/Dashboard'
import BookingFlow from '@/features/booking/BookingFlow'

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/book" element={<BookingFlow />} />
      </Routes>
    </BrowserRouter>
  )
}

export default AppRouter
