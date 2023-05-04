import React, {useState} from "react";
import { Form, Button, Container, Card } from "react-bootstrap";
import Navbar from "../layouts/Navbar";

export default function Login() {
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const [errMsg, setErrMsg] = useState('');

  function handleSubmit(){
    alert('submit');
  }

  return (
    <div>
      <Navbar />
      <Container>
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control type="email" placeholder="Enter email" />
            <Form.Text className="text-muted">
              We'll never share your email with anyone else.
            </Form.Text>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
          </Form.Group>
        
          <Button variant="primary" type="submit" onClick={handleSubmit}>
            Submit
          </Button>
        </Form>
      </Container>
    </div>
  );
}
