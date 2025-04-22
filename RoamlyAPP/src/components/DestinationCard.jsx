import React from "react";
import "./DestinationCard.css";

const DestinationCard = ({ image, name, days, price }) => {
  return (
    <div className="destination-card">
      <div className="image-container">
        <img src={image} alt={name} className="destination-image" />
        <span className="price-label">A partir de ${price}</span>
      </div>
      <div className="destination-info">
        <h3 className="destination-name">{name}</h3>
        <p className="destination-days">{days} dias</p>
      </div>
    </div>
  );
};

export default DestinationCard;
