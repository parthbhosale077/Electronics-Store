import React, { useState } from "react";

import "../../node_modules/bootstrap/dist/css/bootstrap.min.css"
import { Container, Form, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import userService from "../services/userService";
import validation from "../services/validation";
import "../style/SignupPage.css";



function SignupPage() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    phone: "",
    email: "",
    gender: "",
    password: "",
    confirmPassword:"",
    roles:["USER"]
  });

  const handleChange = (event) => 
  {
    const { name, value } = event.target;

      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    
  };

  const navigate=useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
      try{
        const response= await userService.userRegistration(formData);
        if(response.data.message==='success')
        {
          validation.success("user registered!!");
          navigate('/signin');
        }
        else
        {
          validation.error("something went wrong");
        }  
      }
      catch(ex)
      {
        console.log("in exception")
        validation.error("something went wrong");
      }
    
  
  };



  return (
    <Container className="signup-container">
      <div className="signup-box" style={{ width: "450px" }}>
        <h2>Register New User</h2>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="firstName">
            <Form.Label>First Name</Form.Label>
            <Form.Control
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="lastName">
            <Form.Label>Last Name</Form.Label>
            <Form.Control
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="mobile">
            <Form.Label>Mobile Number</Form.Label>
            <Form.Control
              type="tel"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="email">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="gender">
            <Form.Label>Gender</Form.Label>
            <Form.Control
              as="select"
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              required
            >
              <option value=""></option>
              <option value="MALE">Male</option>
              <option value="FEMALE">Female</option>
            </Form.Control>
          </Form.Group>

          <Form.Group controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="confirmPassword">
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control
              type="password"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Button  variant="primary" type="submit" size="lg" className="mt-3">
            Sign Up
          </Button>
        </Form>
        <p className="already-have-account">
          Already have an account? <Link to="/signin">Log in</Link>
        </p>
      </div>
    </Container>
  );
}

export default SignupPage;
