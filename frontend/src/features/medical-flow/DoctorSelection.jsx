import { useNavigate, useLocation } from 'react-router-dom'
import { Container, Button, ListGroup } from 'reactstrap'
import NavbarApp from '@/components/common/NavbarApp'
import LoadingSpinner from '@/components/common/LoadingSpinner'
import BookingSelectionItem from '@/components/ui/BookingSelectionItem'
import useDoctors from '@/features/medical-flow/hooks/useDoctors'

function DoctorSelection() {
  const navigate = useNavigate()
  const location = useLocation()
  const { specialtyId, specialtyName } = location.state || {}
  const { doctors, isLoading } = useDoctors(specialtyId)

  const handleDoctorClick = (doctor) => {
    navigate('/book/doctors/datetime', {
      state: { specialtyId, specialtyName, doctorId: doctor.id, doctorName: doctor.fullName },
    })
  }

  return (
    <Container fluid className="w-100 p-0 m-0" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
      <NavbarApp />

      <Container fluid className="px-5 py-4" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
        <Button
          color="link"
          className="text-decoration-none p-0 mb-3"
          onClick={() => navigate('/book', { state: { specialtyId, specialtyName } })}
        >
          ← {specialtyName || 'Especialidades'}
        </Button>

        <h2 className="text-center fw-semibold my-4">Elige un Profesional</h2>

        {isLoading ? (
          <LoadingSpinner />
        ) : (
          <ListGroup className="mx-auto" style={{ maxWidth: 720 }}>
            {doctors.map((doc) => (
              <BookingSelectionItem
                key={doc.id}
                title={doc.fullName}
                isDisabled={!doc.hasAvailableSlots}
                onClick={() => handleDoctorClick(doc)}
              />
            ))}
          </ListGroup>
        )}
      </Container>
    </Container>
  )
}

export default DoctorSelection
