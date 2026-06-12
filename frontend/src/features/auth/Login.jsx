import { Link } from 'react-router-dom'
import { Container, Row, Col, Button, Alert } from 'reactstrap'
import CustomInput from '@/components/ui/CustomInput'
import useLoginForm from '@/features/auth/hooks/useLoginForm'

function Login() {
  const { form, handleChange, handleSubmit, loginMutation } = useLoginForm()

  return (
    <Container>
      <Row className="justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
        <Col xs="12" sm="8" md="6" lg="4">
          <h2 className="text-center mb-4">Iniciar Sesión</h2>
          {loginMutation.isError && (
            <Alert color="danger">
              {loginMutation.error?.response?.data?.message || 'Error al iniciar sesión.'}
            </Alert>
          )}
          <form onSubmit={handleSubmit}>
            <CustomInput
              label="Correo Electrónico"
              name="email"
              type="email"
              placeholder="ejemplo@correo.com"
              value={form.email}
              onChange={handleChange}
              autoComplete="username"
            />
            <CustomInput
              label="Contraseña"
              name="password"
              type="password"
              placeholder="Ingrese su contraseña"
              value={form.password}
              onChange={handleChange}
              autoComplete="current-password"
            />
            <Button
              color="primary"
              block
              className="w-100 mt-3"
              disabled={loginMutation.isPending}
            >
              {loginMutation.isPending ? 'Ingresando...' : 'Ingresar'}
            </Button>
          </form>
          <p className="text-center mt-3">
            ¿No tienes cuenta?{' '}
            <Link to="/register">Registrarse</Link>
          </p>
        </Col>
      </Row>
    </Container>
  )
}

export default Login
