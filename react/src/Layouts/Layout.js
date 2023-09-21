import { Link, Outlet } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const Layout = () => {
  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary position-sticky top-0" style={{ zIndex: 1 }}>
        <Container>
          <Navbar.Brand as={Link} to={"/"}>Home</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav" />
          <Nav className="me-auto">
            <Nav.Link as={Link} to={"/register"}>Register</Nav.Link>
            <Nav.Link as={Link} to={"/login"}>Login</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <Container>
      <div className="d-flex justify-content-center align-items-center" style={{ minHeight: "90vh" }}>
        <p className="fs-1">Welcome to this page</p>
      </div>
    </Container>
      <Outlet/>
    </>
  );
};

export default Layout;
