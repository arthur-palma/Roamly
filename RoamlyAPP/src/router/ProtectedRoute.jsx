import { Navigate, Outlet } from "react-router-dom";
import { useGlobalAuth } from "../context/AuthContext";

const ProtectedRoute = () => {
  const [token] = useGlobalAuth();
  return token ? <Outlet /> : <Navigate to="/" />;
};

export default ProtectedRoute;
