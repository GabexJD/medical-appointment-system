import { useNavigate, useLocation } from 'react-router-dom'
import { Container, Button, Card, CardBody, Spinner, Alert } from 'reactstrap'
import NavbarApp from '@/components/common/NavbarApp'
import useCreateAppointment from '@/features/medical-flow/hooks/useCreateAppointment'

function ConfirmationView() {
  const navigate = useNavigate()
  const location = useLocation()
  const { specialtyName, doctorName, doctorId, date, time } = location.state || {}
  const session = JSON.parse(localStorage.getItem('session') || '{}')
  const userId = session.id || session.userId
  const mutation = useCreateAppointment()

  const handleConfirm = () => {
    const finalUserId = Number(userId)
    const finalDoctorId = Number(doctorId)

    if (isNaN(finalUserId) || !finalUserId || isNaN(finalDoctorId) || !finalDoctorId) {
      return
    }

    mutation.mutate({
      userId: finalUserId,
      doctorId: finalDoctorId,
      appointmentDate: date,
      appointmentTime: time,
    })
  }

  const formatDate = (dateStr) => {
    if (!dateStr) return ''
    const d = new Date(dateStr + 'T12:00:00')
    const months = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
    return `${d.getDate()} ${months[d.getMonth()]} ${d.getFullYear()}`
  }

  return (
    <Container fluid className="w-100 p-0 m-0" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
      <NavbarApp />

      <Container fluid className="px-5 py-4">
        <Button
          color="link"
          className="text-decoration-none p-0 mb-3"
          onClick={() =>
            navigate('/book/doctors/datetime', {
              state: { specialtyName, doctorId, doctorName, date, time },
            })
          }
        >
          ← Modificar Fecha y Hora
        </Button>

        <div className="d-flex justify-content-center">
          <Card className="shadow-sm" style={{ maxWidth: 600, width: '100%' }}>
            <CardBody className="p-4">
              <h3 className="fw-bold text-center mb-4">Resumen de tu Cita</h3>

              {mutation.isError && (
                <Alert color="danger">
                  {mutation.error?.response?.data?.message ||
                    'El horario seleccionado ya fue reservado o ya cuenta con una cita en esta especialidad para el mismo día.'}
                </Alert>
              )}

              <div className="mb-3">
                <small className="text-muted text-uppercase fw-semibold">Especialidad</small>
                <p className="fs-5 mb-0">{specialtyName}</p>
              </div>
              <div className="mb-3">
                <small className="text-muted text-uppercase fw-semibold">Doctor</small>
                <p className="fs-5 mb-0">{doctorName}</p>
              </div>
              <div className="mb-3">
                <small className="text-muted text-uppercase fw-semibold">Fecha</small>
                <p className="fs-5 mb-0">{formatDate(date)}</p>
              </div>
              <div className="mb-4">
                <small className="text-muted text-uppercase fw-semibold">Hora</small>
                <p className="fs-5 mb-0">{time?.slice(0, 5)}</p>
              </div>

              <Button
                color="primary"
                size="lg"
                block
                className="w-100"
                disabled={mutation.isPending}
                onClick={handleConfirm}
              >
                {mutation.isPending ? (
                  <>
                    <Spinner size="sm" className="me-2" />
                    Confirmando...
                  </>
                ) : (
                  'Confirmar Cita'
                )}
              </Button>
            </CardBody>
          </Card>
        </div>
      </Container>
    </Container>
  )
}

export default ConfirmationView
