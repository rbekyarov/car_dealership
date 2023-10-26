import logo from "./logo.svg";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Register from "./pages/Register";
import Layout from "./Layouts/Layout";
import Login from "./pages/Login";
import CarList from "./pages/CarList";
import CarAdd from "./pages/CarAdd";
import OfferList from "./pages/OfferList";
import SalesList from "./pages/SalesList";
import ClientsList from "./pages/ClientList";
import VendorsList from "./pages/VendorList";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from "react";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/cars" element={<CarList />} />
          <Route path="/cars/add" element={<CarAdd />} />
          <Route path="/offers" element={<OfferList />} />
          <Route path="/sales" element={<SalesList />} />
          <Route path="/clients" element={<ClientsList />} />
          <Route path="/vendors" element={<VendorsList />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
