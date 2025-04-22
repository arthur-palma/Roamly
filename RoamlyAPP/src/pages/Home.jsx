import { useState, useEffect } from "react";
import { useAuth } from "../hooks/useAuth";
import axios from "axios";
import { useGlobalAuth } from "../context/AuthContext";

export default function Home() {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const { unauthorizedUser } = useAuth();
  const [token, setToken] = useGlobalAuth();

  useEffect(() => {
    teste();
  }, []);

  async function teste() {
    try {
      const response = await axios.get("http://localhost:8080/users", {
        headers: {
          Authorization: token,
        },
        withCredentials: true,
      });
    } catch (error) {
      const statusCode =
        error.response?.status || error.request?.status || null;
      unauthorizedUser(statusCode);
    }
  }

  return (
    <div>
      <h2>Testando API</h2>
      {error && <p style={{ color: "red" }}>Erro: {error}</p>}
      {data ? <pre>{JSON.stringify(data, null, 2)}</pre> : <p>Carregando...</p>}
    </div>
  );
}
