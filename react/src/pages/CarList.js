import axios from "axios";
import { Link, Outlet } from "react-router-dom";
import { Forn, Container, Button } from "react-bootstrap";
import { userState, React, useState,useEffect  } from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "bootstrap/dist/css/bootstrap.min.css";
import {useNavigate} from "react-router-dom";

const CarList = () => {

  const [cars, setCar] = useState([]);

  useEffect(() => {
    // Извикваме API за данни за хора
    axios.get('http://localhost:8080/api/cars') 
      .then(response => {
        setCar(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
  }, []);

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
        <div className="custom-container shadow-sm rounded">
       
        <h1>Car List</h1>
      <table>
        <thead>
          <tr>
          <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Curency</th>
            <th>Avaable</th>
          </tr>
        </thead>
        <tbody>
          {cars.map(car => (
            <tr key={car.id}>
              <td>{car.id}</td>
              <td>{car.name}</td>
              <td>{car.priceSale}</td>
              <td>{car.currency.name}</td>
              <td>{car.statusAvailable}</td>
            </tr>
          ))}
        </tbody>
      </table>
        </div>
      </Container>
    </>
  );
};
export default CarList;
