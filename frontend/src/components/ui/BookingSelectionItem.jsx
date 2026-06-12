import { ListGroupItem, Badge } from 'reactstrap'

function BookingSelectionItem({ title, isDisabled, onClick }) {
  return (
    <ListGroupItem
      tag="button"
      action
      disabled={isDisabled}
      onClick={isDisabled ? undefined : onClick}
      className={`d-flex justify-content-between align-items-center py-3 px-4 ${isDisabled ? 'bg-light border-light' : ''}`}
    >
      <span className={`fw-medium ${isDisabled ? 'text-muted' : ''}`}>{title}</span>
      <Badge
        pill
        className={
          isDisabled
            ? 'bg-danger-subtle text-danger'
            : 'bg-success-subtle text-success'
        }
      >
        {isDisabled ? 'Sin Horarios' : 'Disponible'}
      </Badge>
    </ListGroupItem>
  )
}

export default BookingSelectionItem
