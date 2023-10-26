import logo from "./logo.svg";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Register from "./pages/Register";
import Layout from "./Layouts/Layout";
import Login from "./pages/Login";
import CarList from "./pages/CarList";
import CarAdd from "./pages/CarAdd";
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
          <Route path="/api/cars" element={<CarList />} />
          <Route path="/api/cars/add" element={<CarAdd />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
