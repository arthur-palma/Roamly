import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Login from "../pages/Login";
import NotFound from "../pages/NotFound";
import ProtectedRoute from "./ProtectedRoute";

const Router = createBrowserRouter([
  { path: "/", element: <Login /> },
  {
    path: "/home",
    element: <ProtectedRoute />,
    children: [{ path: "", element: <Home /> }],
  },
  { path: "*", element: <NotFound /> },
]);

export default Router;
