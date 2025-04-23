import React from "react";
import "./LoginBanner.css";

const LoginBanner = () => {
  return (
    <div className="loginbanner">
      <h1>Welcome to Trip Planner</h1>
      <h4>Join us and start planning your dream trips with others!</h4>
      <input
        className="loginbanner-input"
        placeholder="Enter your email addres"
      ></input>
      <div>
        <button className="button">Login</button>
        <button className="button">Sing Up</button>
      </div>
    </div>
  );
};
export default LoginBanner;
