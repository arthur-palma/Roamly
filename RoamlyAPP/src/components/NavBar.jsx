import React from "react";
import "./NavBar.css";

const NavBar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <img src="" alt="logo" className="navbar-logo-image" />
        <h1>Roamly</h1>
      </div>
      <ul className="navbar-links">
        <a href="#home">Home</a>
        <a href="#about">About</a>
        <a href="#services">Services</a>
        <a href="#contact">Contact</a>
      </ul>
    </nav>
  );
};

export default NavBar;
