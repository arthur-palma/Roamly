import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Login from "../pages/Login";
import NotFound from "../pages/NotFound";
import ProtectedRoute from "./ProtectedRoute";
import LandingPage from "../pages/LandingPage";

const Router = createBrowserRouter([
  { path: "/", element: <LandingPage /> },
  { path: "/login", element: <Login /> },
  {
    path: "/home",
    element: <ProtectedRoute />,
    children: [{ path: "", element: <Home /> }],
  },
  { path: "*", element: <NotFound /> },
]);

export default Router;
