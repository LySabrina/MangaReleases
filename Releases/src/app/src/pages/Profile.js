import React from "react";
import Navbar from '../layouts/Navbar'

//Display the user's profile
//Photo, Reviews on Book, Top 3 fav books
export default function Profile() {
  return (
    <div>
      <Navbar/>
    <div className="container">
      <div className="profile">
        <div className="user-info">
          <img src="" alt="User Image" />
          <h1>USER NAME</h1>

        </div>

        <div className="user-ratings-container">
          
          <div className ='user-rating'>
            <img src ="" alt="Book Cover"/>
            <p>Blah blah</p>
          </div>

        </div>
      </div>
    </div>
    </div>

  );
}
