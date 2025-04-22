import "./style.css";
import LoginBanner from "../components/LoginBanner.jsx";
import { useEffect, useState } from "react";
import DestinationCard from "../components/DestinationCard";
export default function LandingPage() {
  const [destinations, setDestinations] = useState([]);
  useEffect(() => {
    async function fetchDestinations() {
      try {
        const response = await axios.get(
          "http://localhost:8080/trip/trending",
          userData,
          {
            withCredentials: true,
          }
        );
        const data = await response.json();
        setDestinations(data);
      } catch (error) {
        console.error("Error fetching destinations:", error);
      }
    }

    fetchDestinations();
  }, []);
  return (
    <>
      <LoginBanner />
      <div className="landingpage">
        <h1>Popular Destinations</h1>
        <h5>Discover trending destinations loved by many</h5>
        <button>Explore destinations</button>
        <div className="destination-cards">
          {destinations.map((destination) => (
            <DestinationCard
              key={destination.id}
              name={destination.name}
              image={destination.image}
              days={destination.days}
              price={destination.price}
            />
          ))}
        </div>
      </div>
    </>
  );
}
