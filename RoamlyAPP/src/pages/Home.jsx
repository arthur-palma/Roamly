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
      console.log(response);
    } catch (error) {
      const statusCode =
        error.response?.status || error.request?.status || null;
      console.log(error);
      unauthorizedUser(statusCode);
      console.error(error.response?.data?.message || error.message);
    }
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.get("http://localhost:8080/users", {
        withCredentials: true,
      });
      console.log(response);
    } catch (error) {
      const statusCode =
        error.response?.status || error.request?.status || null;
      console.log(error);
      unauthorizedUser(statusCode);
      console.error(
        "Erro no login:",
        error.response?.data?.message || error.message
      );
    }
  };

  return (
    <div>
      <h2>Testando API</h2>
      {error && <p style={{ color: "red" }}>Erro: {error}</p>}
      {data ? <pre>{JSON.stringify(data, null, 2)}</pre> : <p>Carregando...</p>}
    </div>
  );
}
