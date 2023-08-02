import React from "react";
import Navbar from "../layouts/Navbar";
import "../style/Profile.css";
import Rating from "../components/Review/Rating";
//Display the user's profile
//Photo, Reviews on Book, Top 3 fav books
export default function Profile() {
  return (
    <div>
      <div className="container">
        <div className="profile">
          <div className="user-info d-flex ">
            <img
              src={require("../assets/blank_profile.png")}
              alt="User"
              className="w-25"
            />
            <h1 className="ml-2">USER NAME</h1>
          </div>

          <div className="user-ratings-container mt-5">
            <h1>User Ratings
              <span> (1)</span>
            </h1>
            <Rating />
          </div>
        </div>
      </div>
    </div>
  );
}
