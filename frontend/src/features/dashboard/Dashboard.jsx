import { useNavigate, useLocation } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { Container, Row, Col, Card, CardBody, CardTitle, CardText, Button, Alert } from 'reactstrap'
import NavbarApp from '@/components/common/NavbarApp'
import LoadingSpinner from '@/components/common/LoadingSpinner'
import EmptyState from '@/components/common/EmptyState'
import useDashboardData from '@/features/dashboard/hooks/useDashboardData'

function Dashboard() {
  const navigate = useNavigate()
  const location = useLocation()
  const { upcoming, isLoading } = useDashboardData()
  const [showSuccess, setShowSuccess] = useState(false)

  useEffect(() => {
    if (location.state?.bookingSuccess) {
      setShowSuccess(true)
      window.history.replaceState({}, document.title)
      const timer = setTimeout(() => setShowSuccess(false), 5000)
      return () => clearTimeout(timer)
    }
  }, [location.state])

  return (
    <Container fluid className="w-100 p-0 m-0" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
      <NavbarApp />

      <Container fluid className="px-5 py-4">
        {showSuccess && (
          <Alert color="success" className="mb-4">
            ¡Cita agendada con éxito!
          </Alert>
        )}

        <Row className="align-items-center mb-4">
          <Col>
            <h2 className="fw-bold text-start m-0">Mis Citas</h2>
          </Col>
          <Col xs="auto">
            <Button color="primary" onClick={() => navigate('/book')}>
              + Agendar Nueva Cita
            </Button>
          </Col>
        </Row>

        <h4 className="text-start fw-semibold mb-3">Proximas Citas</h4>
        {isLoading ? (
          <LoadingSpinner />
        ) : upcoming.length === 0 ? (
          <EmptyState message="No tienes próximas citas programadas." />
        ) : (
          <Row className="mb-5">
            {upcoming.map((appt) => (
              <Col md={6} key={appt.id} className="mb-3">
                <Card className="shadow-sm h-100">
                  <CardBody>
                    <CardTitle tag="h5">{appt.specialtyName}</CardTitle>
                    <CardText>
                      <strong>Doctor:</strong> {appt.doctorFullName}
                      <br />
                      <strong>Fecha:</strong> {appt.appointmentDate} - {appt.appointmentTime?.slice(0, 5)}
                    </CardText>
                    <Button color="danger" size="sm" outline>
                      Cancelar
                    </Button>
                  </CardBody>
                </Card>
              </Col>
            ))}
          </Row>
        )}
      </Container>
    </Container>
  )
}

export default Dashboard
