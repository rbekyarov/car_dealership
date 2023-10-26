import axios from "axios";
import { Link, Outlet } from "react-router-dom";
import { Forn, Container, Button } from "react-bootstrap";
import { userState, React, useState, useEffect } from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "bootstrap/dist/css/bootstrap.min.css";
import { useNavigate } from "react-router-dom";

const VendorsList = () => {

  const [vendors, setVendors] = useState([]);

  useEffect(() => {
    // Извикваме API за данни за хора
    axios.get('http://localhost:8080/api/vendors')
      .then(response => {
        setVendors(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
  }, []);

  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary position-sticky top-0" style={{ zIndex: 1 }}>
        <Container>
          <Navbar.Brand as={Link} to={"/"}>Home</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/cars"}>Cars</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/offers"}>Offers</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/sales"}>Sales</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/clients"}>Clients</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/vendors"}>Vendors</Navbar.Brand>
          <Navbar.Brand as={Link} to={"/vendors"}>Settings</Navbar.Brand>
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

          <h1>Vendors List</h1>
          <Navbar.Brand as={Link} to={"/vendors/add"}>Add Vendor</Navbar.Brand>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Vat</th>
                <th>Email</th>
                <th>Phone</th>
                
              
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              {vendors.map(vendor => (
                <tr key={vendor.id}>
                  <td>{vendor.id}</td>

                  <td>{vendor.name}</td>
                  <td>{vendor.vatOrId}</td>
                  <td>{vendor.email}</td>
                  <td>{vendor.phone}</td>
                  <td>{vendor.dateCreate}</td>
                  
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </Container>
    </>
  );
};
export default VendorsList;
