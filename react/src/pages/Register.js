import axios, { HttpStatusCode } from "axios";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import React from "react";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link, Outlet } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle } from "@fortawesome/free-solid-svg-icons";

const Register = () => {
  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPassword, setRepeatPassword] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const [isRegistered, setIsRegistered] = useState(false);

  const [RegisterEnabled, setRegisterEnabled] = useState(false);

  const handleUsername = (event) => {
    setUsername(event.target.value);

    if (username.length <= 5) {
      setRegisterEnabled(false);
    } else {
      setRegisterEnabled(true);
    }
  };
  const handleFirstName = (event) => {
    setFirstName(event.target.value);

    if (firstName.length <= 1) {
      setRegisterEnabled(false);
    } else {
      setRegisterEnabled(true);
    }
  };
  const handleLastName = (event) => {
    setLastName(event.target.value);

    if (lastName.length <= 1) {
      setRegisterEnabled(false);
    } else {
      setRegisterEnabled(true);
    }
  };

  const handlePassword = (event) => {
    setPassword(event.target.value);

    if (password.length < 6) {
      setRegisterEnabled(false);
    } else {
      setRegisterEnabled(true);
    }
  };
  const handleRepeatPassword = (event) => {
    setRepeatPassword(event.target.value);

    if (repeatPassword.length < 6) {
      setRegisterEnabled(false);
    } else {
      setRegisterEnabled(true);
    }
  };

  const handleEmail = (event) => {
    setEmail(event.target.value);
    const regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;

    if (email !== "" && regex.test(email)) {
      setRegisterEnabled(true);
    } else {
      setRegisterEnabled(false);
    }
  };

  const handleRegister = async (event) => {
    event.preventDefault();

    const data = {
      username: username,
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
    };

    try {
      const response = await axios.post("http://localhost:8080/api/register", data);
      setUsername("");
      setFirstName("");
      setLastName("");
      setEmail("");
      setPassword("");
      setRepeatPassword("");
      setIsRegistered(true);

    } catch (error) {
      setError("The email or username you entered is already registered");
    }
  };

  return (
    <>
      <Navbar
        expand="lg"
        className="bg-body-tertiary position-sticky top-0"
        style={{ zIndex: 1 }}
      >
        <Container>
          <Navbar.Brand as={Link} to={"/"}>
            Home
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav" />
          <Nav className="me-auto">
            <Nav.Link as={Link} to={"/register"}>
              Register
            </Nav.Link>
            <Nav.Link as={Link} to={"/login"}>
              Login
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <Container>
        {isRegistered ? (
          <div className="custom-container shadow-sm rounded text-center">
            <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <h1 className="mb-3">You are registered</h1>
              <FontAwesomeIcon
                icon={faCheckCircle}
                style={{ fontSize: "3rem", color: "green" }}
              />
            </div>
          </div>
        ) : (
          <div className="custom-container shadow-sm rounded">
          <h1 className="mb-3 text-center">Registration</h1>
          <Form onSubmit={handleRegister}>
            <Form.Group className="mb-3" controlId="username">
              <Form.Label>User name</Form.Label>
              <Form.Control
                value={username}
                type="username"
                placeholder="Enter User name"
                onChange={handleUsername}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="firstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                value={firstName}
                type="firstName"
                placeholder="Enter First Name"
                onChange={handleFirstName}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="lastName">
              <Form.Label>Last name</Form.Label>
              <Form.Control
                value={lastName}
                type="lastName"
                placeholder="Enter Last name"
                onChange={handleLastName}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="email">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                value={email}
                type="email"
                placeholder="Enter Email address"
                onChange={handleEmail}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="password">
              <Form.Label>Password</Form.Label>
              <Form.Control
                value={password}
                type="password"
                placeholder="Enter password"
                onChange={handlePassword}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="repeatPassowrd">
              <Form.Label>Repeat Password</Form.Label>
              <Form.Control
                value={repeatPassword}
                type="password"
                placeholder="Repeat password"
                onChange={handleRepeatPassword}
              />
            </Form.Group>
            {error && <div className="text-danger mb-3">{error}</div>}
            <div className="d-flex justify-content-end">
              <Button className="mt-2" variant="primary" type="submit">
                Register
              </Button>
            </div>
          </Form>
        </div>
        )}
       
      </Container>
    </>
  );
};

export default Register;
