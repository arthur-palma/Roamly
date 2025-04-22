import React from "react";
import "./DestinationCard.css";

const DestinationCard = ({ image, name, days, price }) => {
  return (
    <div className="destination-card">
      <div className="image-container">
        <img
          src={
            "https://viagemeturismo.abril.com.br/wp-content/uploads/2016/11/thinkstockphotos-4549879531.jpeg"
          }
          alt={name}
          className="destination-image"
        />
        <span className="price-label">Starting from R${price}</span>
      </div>
      <div className="destination-info">
        <p className="destination-name">{name}</p>
        <h3 className="destination-days">{days} Days</h3>
      </div>
    </div>
  );
};

export default DestinationCard;
