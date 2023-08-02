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
    
      <form className ="container">
        <div className = 'form-group m-2'>
          <label htmlFor = "exampleInputEmail1">Email Address</label>
          <input type ='email' class = 'form-control' id ='exampleInputEmail1' placeholder="Enter Email"/>
        </div>

        <div className ='form-group m-2 mt-3'>
          <label htmlFor = "password">Password</label>
          <input type ='password' class = 'form-control' placeholder="Enter Password" id = "password"/>
        </div>

        <button type='submit' className = "btn btn-primary m-2">Login</button>
      </form>
    </div>
  );
}
