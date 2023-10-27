import axios from "axios";
import { Link, Outlet } from "react-router-dom";
import { Forn, Container, Button, NavDropdown } from "react-bootstrap";
import { userState, React, useState, useEffect } from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "bootstrap/dist/css/bootstrap.min.css";
import { useNavigate } from "react-router-dom";

const CostsList = () => {

  const [costs, setCosts] = useState([]);

  useEffect(() => {
    // Извикваме API за данни за хора
    axios.get('http://localhost:8080/api/costs')
      .then(response => {
        setCosts(response.data);
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
        <Navbar.Brand as={Link} to={"/costs/add"}>Add Cost</Navbar.Brand>
          <h1>Costs List</h1>
        
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>vendor</th>
                
                <th>Description</th>
                <th>InvoiceNo</th>
                <th>Amount</th>
                <th>Currency</th>
                
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              {costs.map(cost => (
                <tr key={cost.id}>
                  <td>{cost.id}</td>
                  <td>{cost.vendor.id}</td>
                  
                  <td>{cost.description}</td>
                  <td>{cost.invoiceNo}</td>
                  <td>{cost.amount}</td>
                  <td>{cost.currency.code}</td>
                  
                  <td>{cost.dateCost}</td>
                  
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </Container>
    </>
  );
};
export default CostsList;
