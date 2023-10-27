import axios, { HttpStatusCode } from "axios";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import { NavDropdown } from "react-bootstrap";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import Button from "react-bootstrap/Button";
import { useState, useEffect } from "react";
import React from "react";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link, Outlet } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle } from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";
const ClientAdd = () => {
  

  const navigate = useNavigate();
  
  const [name, SETname] = useState("");
  const [phone, SETphone] = useState("");
  const [city, SETcity] = useState("");
  const [address, SETaddress] = useState("");
  const [vatOrId, SETvatOrId] = useState("");
  const [email, SETemail] = useState("");


  const [error, setError] = useState("");


  const HANDLEname = (event) => {
    SETname(event.target.value);

  };
  const HANDLEphone = (event) => {
    SETphone(event.target.value);

  };
  const HANDLEcity = (event) => {
    SETcity(event.target.value);

  };
  const HANDLEaddress = (event) => {
    SETaddress(event.target.value);

  };
  const HANDLEvatOrId = (event) => {
    SETvatOrId(event.target.value);

  };
  const HANDLEemail = (event) => {
    SETemail(event.target.value);

  };

  

  const handleClientData = async (event) => {
    event.preventDefault();

    const data = {
      name: name,
      phone: phone,
      city: city,
      address: address,
      vatOrId: vatOrId,
      email: email

    };

    try {
      
      const token = sessionStorage.getItem('token');
    
      
      const config = {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      };
    
      
      const response = await axios.post("http://localhost:8080/api/clients", data, config);
    
      
      SETname("");
      SETphone("");
      SETcity("");
      SETaddress("");
      SETvatOrId("");
      SETemail("");
    
      
      navigate("/clients");
    
    } catch (error) {
      setError("The email or username you entered is already registered");
    }
  };



  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary position-sticky top-0" style={{ zIndex: 1 }}>
        <Container>
          <Navbar.Brand as={Link} to={"/"}>Home</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/cars"}>Cars</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/offers"}>Offers</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/sales"}>Sales</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/invoices"}>Invoices</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/clients"}>Clients</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/vendors"}>Vendors</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/costs"}>Costs</Navbar.Brand>
          <NavDropdown title="Settings" id="basic-nav-dropdown" className="bg-body-tertiary position-sticky top-0">
            <NavDropdown.Item as={Link} to={"/companies"}>My Companies</NavDropdown.Item>
            <NavDropdown.Item as={Link} to={"/bankAccounts"}>Bank Accounts</NavDropdown.Item>
            <NavDropdown.Item as={Link} to={"/currencies"}>Currencies</NavDropdown.Item>
            <NavDropdown.Item as={Link} to={"/users"}>Users</NavDropdown.Item>
            <NavDropdown.Item as={Link} to={"/sellers"}>Sellers</NavDropdown.Item>
            <NavDropdown.Item as={Link} to={"/princings"}>Princing</NavDropdown.Item>
          </NavDropdown>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav" />
          <Nav className="me-auto">
            <Nav.Link as={Link} to={"/register"}>Register</Nav.Link>
            <Nav.Link as={Link} to={"/login"}>Login</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <Container>

        <div className="custom-container shadow-sm rounded">
          <h1 className="mb-3 text-center">Add Client</h1>
          <Form onSubmit={handleClientData}>
            <Form.Group className="mb-3" controlId="name">
              <Form.Label>Name</Form.Label>
              <Form.Control
                value={name}
                type="name"
                placeholder="Enter Name"
                onChange={HANDLEname}
              />
            </Form.Group>


            <Form.Group className="mb-3" controlId="phone">
              <Form.Label>Phone</Form.Label>
              <Form.Control
                value={phone}
                type="phone"
                placeholder="Enter Country"
                onChange={HANDLEphone}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="city">
              <Form.Label>City</Form.Label>
              <Form.Control
                value={city}
                type="city"
                placeholder="Enter City"
                onChange={HANDLEcity}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="address">
              <Form.Label>Address</Form.Label>
              <Form.Control
                value={address}
                type="address"
                placeholder="Enter Address"
                onChange={HANDLEaddress}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="vatOrId">
              <Form.Label>Vat Number</Form.Label>
              <Form.Control
                value={vatOrId}
                type="vatOrId"
                placeholder="Enter Vat Number"
                onChange={HANDLEvatOrId}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="email">
              <Form.Label>Email</Form.Label>
              <Form.Control
                value={email}
                type="email"
                placeholder="Enter Email"
                onChange={HANDLEemail}
              />
            </Form.Group>


            <div className="d-flex justify-content-end">
              <Button className="mt-2" variant="primary" type="submit">
                Add Client
              </Button>
            </div>
          </Form>
        </div>


      </Container>
    </>
  );
};

export default ClientAdd;