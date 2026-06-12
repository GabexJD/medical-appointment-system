import { useNavigate, useLocation } from 'react-router-dom'
import { Container, Button, Row, Col } from 'reactstrap'
import NavbarApp from '@/components/common/NavbarApp'
import LoadingSpinner from '@/components/common/LoadingSpinner'
import useDateTimeSelection from '@/features/medical-flow/hooks/useDateTimeSelection'

function DateTimeSelection() {
  const navigate = useNavigate()
  const location = useLocation()
  const { specialtyId, specialtyName, doctorId } = location.state || {}
  const { activeDate, setActiveDate, currentSlots, dates, isLoading } = useDateTimeSelection(doctorId)

  const handleSlotClick = (slot) => {
    navigate('/book/confirm', {
      state: {
        specialtyId,
        specialtyName,
        doctorId,
        doctorName: location.state?.doctorName,
        date: slot.date,
        time: slot.time,
      },
    })
  }

  const formatDateLabel = (dateStr) => {
    const date = new Date(dateStr + 'T12:00:00')
    const days = ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab']
    return `${days[date.getDay()]} ${date.getDate()}`
  }

  return (
    <Container fluid className="w-100 p-0 m-0" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
      <NavbarApp />

      <Container fluid className="px-5 py-4">
        <Button
          color="link"
          className="text-decoration-none p-0 mb-3"
          onClick={() =>
            navigate('/book/doctors', { state: { specialtyId, specialtyName } })
          }
        >
          ← Seleccionar Doctor
        </Button>

        <h2 className="text-center fw-semibold my-4">Selecciona Fecha y Hora</h2>

        {isLoading ? (
          <LoadingSpinner />
        ) : dates.length === 0 ? (
          <p className="text-muted text-center">No hay disponibilidad para este doctor.</p>
        ) : (
          <>
            <div className="d-flex gap-3 mb-5 justify-content-center flex-wrap">
              {dates.map((date) => (
                <Button
                  key={date}
                  color={activeDate === date ? 'primary' : 'outline-primary'}
                  className="px-4 py-2"
                  onClick={() => setActiveDate(date)}
                >
                  {formatDateLabel(date)}
                </Button>
              ))}
            </div>

            <Row className="g-3">
              {currentSlots.map((slot) => {
                const isFree = slot.available !== false
                return (
                  <Col xs={6} sm={4} md={3} lg={2} key={`${slot.date}-${slot.time}`}>
                    <Button
                      color={isFree ? 'outline-primary' : 'secondary'}
                      disabled={!isFree}
                      className={`w-100 py-2 fw-medium ${!isFree ? 'bg-light text-muted border-0 opacity-50' : ''}`}
                      onClick={() => isFree && handleSlotClick(slot)}
                    >
                      {slot.time.slice(0, 5)}
                    </Button>
                  </Col>
                )
              })}
            </Row>
          </>
        )}
      </Container>
    </Container>
  )
}

export default DateTimeSelection
