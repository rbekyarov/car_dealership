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

const PrincingsList = () => {

  const [princings, setPrincings] = useState([]);

  useEffect(() => {
    // Извикваме API за данни за хора
    axios.get('http://localhost:8080/api/pricing_percent_dates')
      .then(response => {
        setPrincings(response.data);
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
        <Navbar.Brand as={Link} to={"/princings/add"}>Add Princings</Navbar.Brand>
          <h1>Princings List</h1>
        
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Sale Price %</th>
                
                <th>Min Sale Price %</th>
                <th>Commission %</th>
                <th>Active</th>
                <th>VAT %</th>
                <th>author</th>
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              {princings.map(princing => (
                <tr key={princing.id}>
                  <td>{princing.id}</td>
                  <td>{princing.percentSaleCar}</td>
                  
                  <td>{princing.percentSaleCarMin}</td>
                  <td>{princing.percentCommission}</td>
                  <td>{princing.activePricingPercentData}</td>
                  <td>{princing.percentVAT}</td>
                  <td>{princing.author}</td>
                  <td>{princing.dateCreate}</td>
                  
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </Container>
    </>
  );
};
export default PrincingsList;
