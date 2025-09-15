import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import AccommodationDetails from "./ui/components/accommodations/Details/AccommodationDetails.jsx";
import HostsPage from "./ui/pages/HostsPage/HostsPage.jsx";
import CountriesPage from "./ui/pages/CountriesPage/CountriesPage.jsx";
import Register from "./ui/components/auth/Register/Register.jsx";
import Login from "./ui/components/auth/Login/Login.jsx";



const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="accommodations" element={<AccommodationsPage />} />
                    <Route path="accommodations/:id" element={<AccommodationDetails/>}/>
                    <Route path="hosts" element={<HostsPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;
