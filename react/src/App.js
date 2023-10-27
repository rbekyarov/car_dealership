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
import ClientAdd from "./pages/ClientAdd";
import VendorsList from "./pages/VendorList";
import VendorAdd from "./pages/VendorAdd";
import CompaniesList from "./pages/CompaniesList";
import InvoicesList from "./pages/InvoicesList";
import CostsList from "./pages/CostsList";
import CostAdd from "./pages/CostAdd";
import BankAccountList from "./pages/BankAccountList";
import CurrenciesList from "./pages/CurrenciesList";
import UsersList from "./pages/UsersList";
import SellersList from "./pages/SellersList";
import PrincingsList from "./pages/PrincingsList";
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
          <Route path="/clients/add" element={<ClientAdd />} />
          <Route path="/vendors" element={<VendorsList />} />
          <Route path="/vendors/add" element={<VendorAdd />} />
          <Route path="/companies" element={<CompaniesList />} />
          <Route path="/invoices" element={<InvoicesList />} />
          <Route path="/costs" element={<CostsList />} />
          <Route path="/costs/add" element={<CostAdd />} />
          <Route path="/bankAccounts" element={<BankAccountList />} />
          <Route path="/currencies" element={<CurrenciesList />} />
          <Route path="/users" element={<UsersList />} />
          <Route path="/sellers" element={<SellersList />} />
          <Route path="/princings" element={<PrincingsList />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
