import { useNavigate } from 'react-router-dom'
import { Container, Button, ListGroup } from 'reactstrap'
import NavbarApp from '@/components/common/NavbarApp'
import LoadingSpinner from '@/components/common/LoadingSpinner'
import BookingSelectionItem from '@/components/ui/BookingSelectionItem'
import useSpecialties from '@/features/medical-flow/hooks/useSpecialties'

function SpecialtySelection() {
  const navigate = useNavigate()
  const { specialties, isLoading } = useSpecialties()

  const handleSpecialtyClick = (specialty) => {
    navigate('/book/doctors', { state: { specialtyId: specialty.id, specialtyName: specialty.name } })
  }

  return (
    <Container fluid className="w-100 p-0 m-0" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
      <NavbarApp />

      <Container fluid className="px-5 py-4" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
        <Button color="link" className="text-decoration-none p-0 mb-3" onClick={() => navigate('/dashboard')}>
          ← Nueva Cita
        </Button>

        <h2 className="text-center fw-semibold my-4">¿Qué Especialidad Médica Necesitas?</h2>

        {isLoading ? (
          <LoadingSpinner />
        ) : (
          <ListGroup className="mx-auto" style={{ maxWidth: 720 }}>
            {specialties.map((spec) => (
              <BookingSelectionItem
                key={spec.id}
                title={spec.name}
                isDisabled={!spec.hasAvailableSlots}
                onClick={() => handleSpecialtyClick(spec)}
              />
            ))}
          </ListGroup>
        )}
      </Container>
    </Container>
  )
}

export default SpecialtySelection
