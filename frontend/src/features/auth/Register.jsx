import { Link } from 'react-router-dom'
import { Container, Row, Col, Button, Alert, Spinner } from 'reactstrap'
import CustomInput from '@/components/ui/CustomInput'
import useRegisterForm from '@/features/auth/hooks/useRegisterForm'

function Register() {
  const { form, errors, handleChange, handleSubmit, registerMutation } = useRegisterForm()

  return (
    <Container>
      <Row className="justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
        <Col xs="12" sm="8" md="6" lg="4">
          <h2 className="text-center mb-4">Crear Cuenta</h2>
          {registerMutation.isError && (
            <Alert color="danger">
              {registerMutation.error?.response?.data?.message || 'Error al registrar.'}
            </Alert>
          )}
          <form onSubmit={handleSubmit} noValidate>
            <CustomInput
              label="Nombre Completo"
              name="fullName"
              placeholder="Juan Pérez"
              value={form.fullName}
              onChange={handleChange}
              error={errors.fullName}
            />
            <CustomInput
              label="Cédula de Identidad"
              name="documentNumber"
              placeholder="Ej. 7948234"
              value={form.documentNumber}
              onChange={handleChange}
              error={errors.documentNumber}
            />
            <CustomInput
              label="Correo Electrónico"
              name="email"
              type="email"
              placeholder="ejemplo@correo.com"
              value={form.email}
              onChange={handleChange}
              error={errors.email}
              autoComplete="username"
            />
            <CustomInput
              label="Teléfono"
              name="phone"
              type="tel"
              placeholder="Ej. 74589568"
              value={form.phone}
              onChange={handleChange}
              error={errors.phone}
            />
            <CustomInput
              label="Contraseña"
              name="password"
              type="password"
              placeholder="Mínimo 6 caracteres"
              value={form.password}
              onChange={handleChange}
              error={errors.password}
              autoComplete="new-password"
            />
            <CustomInput
              label="Confirmar Contraseña"
              name="confirmPassword"
              type="password"
              placeholder="Repita la contraseña"
              value={form.confirmPassword}
              onChange={handleChange}
              error={errors.confirmPassword}
              autoComplete="new-password"
            />
            <Button
              color="primary"
              block
              className="w-100 mt-3"
              disabled={registerMutation.isPending}
            >
              {registerMutation.isPending ? (
                <>
                  <Spinner size="sm" className="me-2" />
                  Registrando...
                </>
              ) : (
                'Registrarse'
              )}
            </Button>
          </form>
          <p className="text-center mt-3">
            ¿Ya tienes cuenta?{' '}
            <Link to="/">Iniciar Sesión</Link>
          </p>
        </Col>
      </Row>
    </Container>
  )
}

export default Register
