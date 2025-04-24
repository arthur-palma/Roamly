import axios from "axios";
import LoginBanner from "../components/LoginBanner.jsx";
import { useEffect, useState } from "react";
import DestinationCard from "../components/DestinationCard";
import NavBar from "../components/NavBar";
import "./LandingPage.css";
export default function LandingPage() {
  const [destinations, setDestinations] = useState([]);
  useEffect(() => {
    async function fetchDestinations() {
      try {
        const response = await axios.get(
          "http://localhost:8080/trip/trending",
          {
            withCredentials: true,
          }
        );
        const data = await response.data;
        setDestinations(data);
      } catch (error) {
        console.error("Error fetching destinations:", error);
      }
    }

    fetchDestinations();
  }, []);
  return (
    <div className="container">
      <NavBar />
      <LoginBanner />
      <div className="landingpage">
        <div className="landingpage-destiantions">
          <h1>Popular Destinations</h1>
          <h4>Discover trending destinations loved by many</h4>
          <button className="button">Explore destinations</button>
        </div>
        <div className="destination-cards">
          {destinations.map((destination) => (
            <DestinationCard
              key={destination.id}
              name={destination.destination}
              image={destination.image_url}
              days={destination.days}
              price={destination.budget}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
