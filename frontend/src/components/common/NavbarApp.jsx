import { useNavigate } from 'react-router-dom'
import { Navbar, Nav, NavItem, Button } from 'reactstrap'

function NavbarApp() {
  const navigate = useNavigate()
  const session = JSON.parse(localStorage.getItem('session') || '{}')
  const fullName = session.fullName || ''

  const handleSignOut = () => {
    localStorage.removeItem('session')
    navigate('/')
  }

  const avatarInitial = fullName ? fullName.charAt(0).toUpperCase() : '?'

  return (
    <Navbar color="light" light expand="md" className="px-4 shadow-sm">
      <Nav className="me-auto">
        <NavItem>
          <span className="fw-bold fs-5">Citas Medicas</span>
        </NavItem>
      </Nav>
      <Nav className="align-items-center" navbar>
        <NavItem className="d-flex align-items-center gap-2">
          <div
            className="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center"
            style={{ width: 36, height: 36, fontSize: 16, fontWeight: 600 }}
          >
            {avatarInitial}
          </div>
          <span className="me-2">{fullName}</span>
          <Button color="outline-secondary" size="sm" onClick={handleSignOut}>
            Sign Out
          </Button>
        </NavItem>
      </Nav>
    </Navbar>
  )
}

export default NavbarApp
