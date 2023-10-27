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

const AddCar = () => {




  useEffect(() => {
    //MODEL
    axios.get('http://localhost:8080/api/models')
      .then(response => {
        SETModelE(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //Vendor
    axios.get('http://localhost:8080/api/vendors')
      .then(response => {
        SETVendorE(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //TRANSMISION
    axios.get('http://localhost:8080/api/enumTransmission')
      .then(response => {
        SETtransmisionE(response.data);
      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    // FUELTYPE
    axios.get('http://localhost:8080/api/enumFuelType')
      .then(response => {
        SETfuelTypeE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //COLOR
    axios.get('http://localhost:8080/api/enumColor')
      .then(response => {
        SETcolorE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //DOORCOUNT
    axios.get('http://localhost:8080/api/enumDoorCount')
      .then(response => {
        SETdoorCountE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //ConditionCar
    axios.get('http://localhost:8080/api/enumConditionCar')
      .then(response => {
        SETconditionCarE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });

    //Eurostandard
    axios.get('http://localhost:8080/api/enumEurostandard')
      .then(response => {
        SETeurostandardE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });

    //category
    axios.get('http://localhost:8080/api/enumCategory')
      .then(response => {
        SETcategoryE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //autoStartStop
    axios.get('http://localhost:8080/api/enumAutoStartStop')
      .then(response => {
        SETautoStartStopE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //alarm
    axios.get('http://localhost:8080/api/enumAlarm')
      .then(response => {
        SETalarmE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //metallic
    axios.get('http://localhost:8080/api/enumMetallic')
      .then(response => {
        SETmetallicE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //leatherSalon
    axios.get('http://localhost:8080/api/enumLeatherSalon')
      .then(response => {
        SETleatherSalonE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //halogenHeadlights
    axios.get('http://localhost:8080/api/enumHalogenHeadlights')
      .then(response => {
        SEThalogenHeadlightsE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //serviceBook
    axios.get('http://localhost:8080/api/enumServiceBook')
      .then(response => {
        SETserviceBookE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //parktronik
    axios.get('http://localhost:8080/api/enumParktronik')
      .then(response => {
        SETparktronikE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //airbags
    axios.get('http://localhost:8080/api/enumAirbags')
      .then(response => {
        SETairbagsE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //elMirrors
    axios.get('http://localhost:8080/api/enumElMirrors')
      .then(response => {
        SETelMirrorsE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //elWindows
    axios.get('http://localhost:8080/api/enumElWindows')
      .then(response => {
        SETelWindowsE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //climatic
    axios.get('http://localhost:8080/api/enumClimatic')
      .then(response => {
        SETclimaticE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //navigation
    axios.get('http://localhost:8080/api/enumNavigation')
      .then(response => {
        SETnavigationE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });
    //statusAvailable
    axios.get('http://localhost:8080/api/enumStatusAvailable')
      .then(response => {
        SETstatusAvailableE(response.data);

      })
      .catch(error => {
        console.error('Грешка при извличане на данните:', error);
      });

  }, []);

  const [selectedDate, setSelectedDate] = useState(null);

  const [vinNumber, SETvinNumber] = useState("");

  const [modelId, SETmodelId] = useState("");
  const [modelE, SETModelE] = useState([]);

  const [vendorId, SETvendorId] = useState("");
  const [vendorE, SETVendorE] = useState([]);

  const [transmision, SETtransmision] = useState("");
  const [transmisionE, SETtransmisionE] = useState([]);

  const [fuelType, SETfuelType] = useState("");
  const [fuelTypeE, SETfuelTypeE] = useState([]);

  const [color, SETcolor] = useState("");
  const [colorE, SETcolorE] = useState([]);

  const [doorCount, SETdoorCount] = useState("");
  const [doorCountE, SETdoorCountE] = useState([]);

  const [conditionCar, SETconditionCar] = useState("");
  const [conditionCarE, SETconditionCarE] = useState([]);

  const [eurostandard, SETeurostandard] = useState("");
  const [eurostandardE, SETeurostandardE] = useState([]);

  const [category, SETcategory] = useState("");
  const [categoryE, SETcategoryE] = useState([]);

  const [autoStartStop, SETautoStartStop] = useState("");
  const [autoStartStopE, SETautoStartStopE] = useState([]);

  const [metallic, SETmetallic] = useState("");
  const [metallicE, SETmetallicE] = useState([]);

  const [alarm, SETalarm] = useState("");
  const [alarmE, SETalarmE] = useState([]);

  const [leatherSalon, SETleatherSalon] = useState("");
  const [leatherSalonE, SETleatherSalonE] = useState([]);

  const [halogenHeadlights, SEThalogenHeadlights] = useState("");
  const [halogenHeadlightsE, SEThalogenHeadlightsE] = useState([]);

  const [serviceBook, SETserviceBook] = useState("");
  const [serviceBookE, SETserviceBookE] = useState([]);

  const [parktronik, SETparktronik] = useState("");
  const [parktronikE, SETparktronikE] = useState([]);

  const [airbags, SETairbags] = useState("");
  const [airbagsE, SETairbagsE] = useState([]);

  const [elMirrors, SETelMirrors] = useState("");
  const [elMirrorsE, SETelMirrorsE] = useState([]);

  const [elWindows, SETelWindows] = useState("");
  const [elWindowsE, SETelWindowsE] = useState([]);

  const [climatic, SETclimatic] = useState("");
  const [climaticE, SETclimaticE] = useState([]);

  const [navigation, SETnavigation] = useState("");
  const [navigationE, SETnavigationE] = useState([]);

  const [statusAvailable, SETstatusAvailable] = useState("");
  const [statusAvailableE, SETstatusAvailableE] = useState([]);



  const [horsepower, SEThorsepower] = useState("");
  const [cubature, SETcubature] = useState("");
  const [comments, SETcomments] = useState("");
  const [pricePurchase, SETpricePurchase] = useState("");

  const [regDate, SETregDate] = useState("");
  const [dateIncome, SETdateIncome] = useState("");
  const [datePurchase, SETdatePurchase] = useState("");


  const [error, setError] = useState("");


  const HANDLEvinNumber = (event) => {
    SETvinNumber(event.target.value);

  };
  const HANDLEhorsepower = (event) => {
    SEThorsepower(event.target.value);

  };
  const HANDLEcubature = (event) => {
    SETcubature(event.target.value);

  };
  const HANDLEcomments = (event) => {
    SETcomments(event.target.value);

  };
  const HANDLEregDate = (event) => {
    SETregDate(event);

  };
  const HANDLEdateIncome = (event) => {
    SETdateIncome(event);

  };
  const HANDLEdatePurchase = (event) => {
    SETdatePurchase(event.target.value);

  };
  const HANDLEpricePurchase = (event) => {
    SETpricePurchase(event.target.value);

  };

  const HANDairbags = (event) => {
    SETairbags(event.target.value);

  };
  const HANDelMirrors = (event) => {
    SETelMirrors(event.target.value);

  };
  const HANDelWindows = (event) => {
    SETelWindows(event.target.value);

  };
  const HANDclimatic = (event) => {
    SETclimatic(event.target.value);

  };
  const HANDnavigation = (event) => {
    SETnavigation(event.target.value);

  };
  const HANDstatusAvailable = (event) => {
    SETstatusAvailable(event.target.value);

  };

  const HANDhalogenHeadlights = (event) => {
    SEThalogenHeadlights(event.target.value);

  };
  const HANDserviceBook = (event) => {
    SETserviceBook(event.target.value);

  };
  const HANDparktronik = (event) => {
    SETparktronik(event.target.value);

  };


  const HANDLEmodelId = (event) => {
    SETmodelId(event.target.value);

  };
  const HANDLEvendorId = (event) => {
    SETvendorId(event.target.value);

  };
  const HANDLEtransmision = (event) => {
    SETtransmision(event.target.value);

  };
  const HANDLEfuelType = (event) => {
    SETfuelType(event.target.value);

  };
  const HANDLEcolor = (event) => {
    SETcolor(event.target.value);

  };
  const HANDdoorCount = (event) => {
    SETdoorCount(event.target.value);

  };
  const HANDconditionCar = (event) => {
    SETconditionCar(event.target.value);

  };
  const HANDeurostandard = (event) => {
    SETeurostandard(event.target.value);

  };
  const HANDcategory = (event) => {
    SETcategory(event.target.value);

  };
  const HANDautoStartStop = (event) => {
    SETautoStartStop(event.target.value);

  };
  const HANDmetallic = (event) => {
    SETmetallic(event.target.value);

  };
  const HANDalarm = (event) => {
    SETalarm(event.target.value);

  };
  const HANDleatherSalon = (event) => {
    SETleatherSalon(event.target.value);

  };



  const handleCarData = async (event) => {
    event.preventDefault();

    const data = {
      vinNumber: vinNumber,
      modelId: modelId,
      transmision: transmision,
      fuelType: fuelType,
      horsepower: horsepower,
      cubature: cubature,
      color: color,
      doorCount: doorCount,
      conditionCar: conditionCar,
      vendorId: vendorId,
      eurostandard: eurostandard,
      category: category,
      comments: comments,
      autoStartStop: autoStartStop,
      metallic: metallic,
      alarm: alarm,
      leatherSalon: leatherSalon,
      halogenHeadlights: halogenHeadlights,
      serviceBook: serviceBook,
      parktronik: parktronik,
      airbags: airbags,
      elMirrors: elMirrors,
      elWindows: elWindows,
      climatic: climatic,
      navigation: navigation,
      statusAvailable: statusAvailable,
      regDate: regDate,
      dateIncome: dateIncome,
      datePurchase: datePurchase,
      pricePurchase: pricePurchase
    };

    try {
      const response = await axios.post("http://localhost:8080/api/cars", data);
      SETvinNumber("");
      SETmodelId("");
      SETairbags("");
      SETcolor("");
      SETalarm("");
      SETtransmision("");
      SETfuelType("");
      SEThorsepower("");
      SETcubature("");
      SETdoorCount("");
      SETconditionCar("");
      SETvendorId("");
      SETeurostandard("");
      SETcategory("");
      SETcomments("");
      SETautoStartStop("");
      SETmetallic("");
      SETleatherSalon("");
      SEThalogenHeadlights("");
      SETserviceBook("");
      SETparktronik("");
      SETelMirrors("");
      SETelWindows("");
      SETclimatic("");
      SETnavigation("");
      SETstatusAvailable("");
      SETregDate("");
      SETdateIncome("");
      SETdatePurchase("");
      SETpricePurchase("");


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
          <h1 className="mb-3 text-center">Add car</h1>
          <Form onSubmit={handleCarData}>
            <Form.Group className="mb-3" controlId="vinNumber">
              <Form.Label>Vin Number</Form.Label>
              <Form.Control
                value={vinNumber}
                type="vinNumber"
                placeholder="Enter Vin Number"
                onChange={HANDLEvinNumber}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="modelId">
              <Form.Label>Model</Form.Label>
              <Form.Select value={modelId} onChange={HANDLEmodelId}>

                <option value="">Select</option>
                {modelE.map(model => (
                  <option key={model.id} value={model.name}>
                    {model.name}
                  </option>
                ))}

              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="vendorId">
              <Form.Label>Vendor</Form.Label>
              <Form.Select value={vendorId} onChange={HANDLEvendorId}>

                <option value="">Select</option>
                {vendorE.map(model => (
                  <option key={model.id} value={model.name}>
                    {model.name}
                  </option>
                ))}

              </Form.Select>
            </Form.Group>



            <Form.Group className="mb-3">
              <Form.Label >Registration Date:</Form.Label>
              <DatePicker className="mb-3"

                selected={regDate}
                onChange={HANDLEregDate}
                dateFormat="dd.MM.yyyy" // Формат на датата
                placeholderText="Select Date"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label >Income Date:</Form.Label>
              <DatePicker className="mb-3"

                selected={dateIncome}
                onChange={HANDLEdateIncome}
                dateFormat="dd.MM.yyyy" // Формат на датата
                placeholderText="Select Date"
              />
            </Form.Group>







            <Form.Group className="mb-3" controlId="transmision">
              <Form.Label>Transmision</Form.Label>
              <Form.Select value={transmision} onChange={HANDLEtransmision}>
                <option value="">Select</option>
                {transmisionE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="fuelType">
              <Form.Label>Fuel Type</Form.Label>
              <Form.Select value={fuelType} onChange={HANDLEfuelType} >
                <option value="">Select</option>
                {fuelTypeE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="color">
              <Form.Label>Color</Form.Label>
              <Form.Select value={color} onChange={HANDLEcolor} >
                <option value="">Select</option>
                {colorE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="doorCount">
              <Form.Label>Door Count</Form.Label>
              <Form.Select value={doorCount} onChange={HANDdoorCount} >
                <option value="">Select</option>
                {doorCountE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="conditionCar">
              <Form.Label>Condition</Form.Label>
              <Form.Select value={conditionCar} onChange={HANDconditionCar} >
                <option value="">Select</option>
                {conditionCarE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="eurostandard">
              <Form.Label>Eurostandard</Form.Label>
              <Form.Select value={eurostandard} onChange={HANDeurostandard} >
                <option value="">Select</option>
                {eurostandardE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="category">
              <Form.Label>Category</Form.Label>
              <Form.Select value={category} onChange={HANDcategory} >
                <option value="">Select</option>
                {categoryE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="autoStartStop">
              <Form.Label>AutoStartStop</Form.Label>
              <Form.Select value={autoStartStop} onChange={HANDautoStartStop} >
                <option value="">Select</option>
                {autoStartStopE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="metallic">
              <Form.Label>Metallic</Form.Label>
              <Form.Select value={metallic} onChange={HANDmetallic} >
                <option value="">Select</option>
                {metallicE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="alarm">
              <Form.Label>Alarm</Form.Label>
              <Form.Select value={alarm} onChange={HANDalarm} >
                <option value="">Select</option>
                {alarmE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="leatherSalon">
              <Form.Label>Leather Salon</Form.Label>
              <Form.Select value={leatherSalon} onChange={HANDleatherSalon} >
                <option value="">Select</option>
                {leatherSalonE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="halogenHeadlights">
              <Form.Label>Halogen Headlights</Form.Label>
              <Form.Select value={halogenHeadlights} onChange={HANDhalogenHeadlights} >
                <option value="">Select</option>
                {halogenHeadlightsE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="serviceBook">
              <Form.Label>Service Book</Form.Label>
              <Form.Select value={serviceBook} onChange={HANDserviceBook} >
                <option value="">Select</option>
                {serviceBookE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="parktronik">
              <Form.Label>Parktronik</Form.Label>
              <Form.Select value={parktronik} onChange={HANDparktronik} >
                <option value="">Select</option>
                {parktronikE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="airbags">
              <Form.Label>Airbags</Form.Label>
              <Form.Select value={airbags} onChange={HANDairbags} >
                <option value="">Select</option>
                {airbagsE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="elMirrors">
              <Form.Label>ElMirrors</Form.Label>
              <Form.Select value={elMirrors} onChange={HANDelMirrors} >
                <option value="">Select</option>
                {elMirrorsE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="elWindows">
              <Form.Label>ElWindows</Form.Label>
              <Form.Select value={elWindows} onChange={HANDelWindows} >
                <option value="">Select</option>
                {elWindowsE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="climatic">
              <Form.Label>Climatic</Form.Label>
              <Form.Select value={climatic} onChange={HANDclimatic} >
                <option value="">Select</option>
                {climaticE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="navigation">
              <Form.Label>Navigation</Form.Label>
              <Form.Select value={navigation} onChange={HANDnavigation} >
                <option value="">Select</option>
                {navigationE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="statusAvailable">
              <Form.Label>Status Available</Form.Label>
              <Form.Select value={statusAvailable} onChange={HANDstatusAvailable} >
                <option value="">Select</option>
                {statusAvailableE.map((value, index) => (
                  <option key={index} value={value}>
                    {value}
                  </option>
                ))}
              </Form.Select>
            </Form.Group>


            <Form.Group className="mb-3" controlId="comments">
              <Form.Label>Comments</Form.Label>

              <Form.Control
                as="textarea"
                rows={4}
                value={comments}
                onChange={HANDLEcomments}
                placeholder=""
              />

            </Form.Group>



            <div className="d-flex justify-content-end">
              <Button className="mt-2" variant="primary" type="submit">
                Add Car
              </Button>
            </div>
          </Form>
        </div>


      </Container>
    </>
  );
};

export default AddCar;