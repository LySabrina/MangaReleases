import React from 'react'
import Navbar from '../layouts/Navbar'
import { Form, Button, Container, Card } from "react-bootstrap";

export default function SignUp() {
  return (
    <div>
        <Navbar />
      <Container>
        <Form>

        <Form.Group className="mb-3" controlId="formBasicUsername">
            <Form.Label>Username</Form.Label>
            <Form.Control type="text" placeholder="Enter Username" />
          </Form.Group>

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
        
          <Form.Group className="mb-3" controlId="formBasicRePassword">
            <Form.Label>Re-enter Password</Form.Label>
            <Form.Control type="password" placeholder="Re-enter Password" />
          </Form.Group>

          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </Container>   
    </div>
  )
}
