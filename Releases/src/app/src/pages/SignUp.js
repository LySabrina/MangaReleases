import React, { useState } from 'react'
import Navbar from '../layouts/Navbar'
import { Form, Button, Container, Card } from "react-bootstrap";

export default function SignUp() {
  const [formData, setFormData] = useState({username:"", fname:"", lname:"", email:"", password:"", repassword:""});
  const [error, setError] = useState({passwordError: false, usernameError: false, emailError: false}); //check in the backend if the username or email already exists 
  
  
  function handleSubmit(e){
    e.preventDefault();
    setError((prevError)=>{
      return(
        {
          ...prevError,
          passwordError: formData.password === formData.repassword ? false : true
        }
      )
    })    


  }

  //universal function to handle all input 
  function handleChange(e){
      const {name, value} = e.target;
      setFormData((prevFormData)=>{
        return(
          {
            ...prevFormData,
            [name] : value
          }
        )
      })
  }

  return (
    <div>
      

        {error.passwordError && <div className = "alert alert-danger">Passwords do not match!</div>}
        {error.usernameError && <div className = "alert alert-danger">Username already exists!</div>}
        {error.emailError && <div className = "alert alert-danger">Email already exists!</div>}
        
        <form className ='container'>
          <div className = 'form-group m-2 mb-3'>
            <label htmlFor = 'username' >Username</label>
            <input type ='text' name = 'username' id ='username' placeholder='Enter Username'  className = 'form-control' value = {formData.username} onChange={handleChange}/>
          </div>

          <div className = 'form-group m-2 mb-3'>
            <label htmlFor = 'fname'>First Name</label>
            <input type ='text' name='fname' id ='fname' placeholder='Enter First Name' className = 'form-control' value={formData.fname} onChange={handleChange}/>
          </div>

          <div className = 'form-group m-2 mb-3'>
            <label htmlFor = 'lname'>Last Name</label>
            <input type ='text' name ='lname' id ='lname' placeholder='Enter Last Name' className = 'form-control' value={formData.lname} onChange={handleChange}/>
          </div>

          <div className = 'form-group m-2 mb-3'>
            <label htmlFor = 'email'>Email</label>
            <input type ='email' name='email' id ='email' placeholder='Enter Email' className = 'form-control' value={formData.email} onChange={handleChange}/>
          </div>

          <div className = 'form-group m-2 mb-3'>
            <label htmlFor = 'password'>Password</label>
            <input type ='password' name='password' id ='password' placeholder='Enter Password' className = 'form-control' value={formData.password} onChange={handleChange}/>
          </div>

          <div className = 'form-group m-2 mb-3' >
            <label htmlFor = 'repassword'>Re-enter Password</label>
            <input type ='password' name='repassword' id ='repassword' placeholder='Re-enter Password' className = 'form-control' value={formData.repassword} onChange={handleChange}/>
          </div>
          <button type ='submit' className ='btn btn-success m-2' onClick = {handleSubmit}>Submit</button>
        </form>
 
    </div>
  )
}
