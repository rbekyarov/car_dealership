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
import { useNavigate } from "react-router-dom";
const CostAdd = () => {

  useEffect(() => {
    //CARID
    axios.get('http://localhost:8080/api/cars')
      .then(response => {
        SETcarIdE(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //VendorID
    axios.get('http://localhost:8080/api/vendors')
      .then(response => {
        SETvendorIdE(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });


  }, []);


  const navigate = useNavigate();

  const [carId, SETcarId] = useState("");
  const [carIdE, SETcarIdE] = useState([]);

  const [vendorId, SETvendorId] = useState("");
  const [vendorIdE, SETvendorIdE] = useState([]);


  const [description, SETdescription] = useState("");
  const [invoiceNo, SETinvoiceNo] = useState("");
  const [amount, SETamount] = useState("");
  const [dateCost, SETdateCost] = useState("");


  const [error, setError] = useState("");


  const HANDLEcarId = (event) => {
    SETcarId(event.target.value);

  };
  const HANDLEvendorId = (event) => {
    SETvendorId(event.target.value);

  };
  const HANDLEdescription = (event) => {
    SETdescription(event.target.value);

  };
  const HANDLEinvoiceNo = (event) => {
    SETinvoiceNo(event.target.value);

  };
  const HANDLEamount = (event) => {
    SETamount(event.target.value);

  };
  const HANDLEdateCost = (event) => {
    SETdateCost(event);

  };


  const handleCostData = async (event) => {
    event.preventDefault();

    const data = {
      carId: carId,
      vendorId: vendorId,
      description: description,
      invoiceNo: invoiceNo,
      amount: amount,
      dateCost: dateCost

    };

    try {

      const token = sessionStorage.getItem('token');


      const config = {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      };


      const response = await axios.post("http://localhost:8080/api/clients", data, config);

      SETcarId("");
      SETvendorId("");
      SETdescription("");
      SETinvoiceNo("");
      SETamount("");
      SETdateCost("");


      navigate("/costs");

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
          <h1 className="mb-3 text-center">Add Cost</h1>
          <Form onSubmit={handleCostData}>
            <Form.Group className="mb-3" controlId="carId">
              <Form.Label>Car</Form.Label>
              <Form.Select value={carId} onChange={HANDLEcarId}>
                <option value="">Select</option>
                {carIdE.map(car => (
                  <option key={car.id} value={car.name}>
                    {car.name}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="vendorId">
              <Form.Label>Vendor</Form.Label>
              <Form.Select value={vendorId} onChange={HANDLEvendorId}>
                <option value="">Select</option>
                {vendorIdE.map(vendor => (
                  <option key={vendor.id} value={vendor.name}>
                    {vendor.name}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="description">
              <Form.Label>description</Form.Label>

              <Form.Control
                as="textarea"
                rows={2}
                value={description}
                onChange={HANDLEdescription}
                placeholder=""
              />

            </Form.Group>

            <Form.Group className="mb-3" controlId="amount">
              <Form.Label>Amount</Form.Label>
              <Form.Control
                value={amount}
                type="amount"
                placeholder="Enter Address"
                onChange={HANDLEamount}
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label >Cost Date:</Form.Label>
              <DatePicker className="mb-3"

                selected={dateCost}
                onChange={HANDLEdateCost}
                dateFormat="dd.MM.yyyy" // Формат на датата
                placeholderText="Select Date"
              />
            </Form.Group>


            <div className="d-flex justify-content-end">
              <Button className="mt-2" variant="primary" type="submit">
                Add Cost
              </Button>
            </div>
          </Form>
        </div >


      </Container >
    </>
  );
};

export default CostAdd;