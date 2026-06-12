import { FormGroup, Label, Input, FormFeedback } from 'reactstrap'

function CustomInput({ label, name, type = 'text', value, onChange, placeholder, error, ...rest }) {
  return (
    <FormGroup>
      {label && <Label for={name}>{label}</Label>}
      <Input
        id={name}
        name={name}
        type={type}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        invalid={!!error}
        {...rest}
      />
      {error && <FormFeedback>{error}</FormFeedback>}
    </FormGroup>
  )
}

export default CustomInput
