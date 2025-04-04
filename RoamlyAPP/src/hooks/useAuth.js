import { useGlobalAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

export function useAuth() {
  const [, setToken] = useGlobalAuth();
  const navigate = useNavigate();

  function unauthorizedUser(status) {
    if (status === 403) {
      localStorage.removeItem("authToken");
      setToken(null);
      navigate("/");
    }
  }

  return { unauthorizedUser };
}
