import React from "react";
import "./LoginBanner.css";

const LoginBanner = () => {
  return (
    <div className="loginbanner">
      <h1>Welcome to Trip Planner</h1>
      <p>Join us and start planning your dream trips with others!</p>
      <input
        className="loginbanner-input"
        placeholder="Enter your email addres"
      ></input>
      <div>
        <button>Login</button>
        <button>Sing Up</button>
      </div>
    </div>
  );
};
export default LoginBanner;
