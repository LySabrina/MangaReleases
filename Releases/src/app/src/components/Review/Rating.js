import React, { useState } from "react";

export default function Rating() {
  

  const [isHover, setIsHover] = useState(false);
  const bookCoverStyle = { width: "100%", height: "100%" , opacity : isHover ? '0.75' : '1' };
  console.log(isHover);

  const show = {
    display : 'block'
  }
  const hide = {
    display: 'none'
  }
  return (
    <div className="book-rating d-flex border border-dark p-3">
      <div
        className="book-cover-container w-25 border border-dark position-relative"
        onMouseEnter={() => {
          setIsHover(true);
        }}
        onMouseLeave={() => {
          setIsHover(false);
        }}
      >
        <img
          src={require("../../assets/10 Beautiful Assassins Vol. 1.jpg")}
          alt="Book Cover"
          className=""
          style={bookCoverStyle}
        />


        <div className="book-info-hover border border-primary position-absolute bottom-0 start-0 w-100 h-50 bg-light p-2"
            style = {isHover ? show : hide}
        >
          <h4>10 Beautiful Assassins</h4>
          <h6>John Doe</h6>
          <h6>John Doe</h6>
        </div>
      </div>

      <div className="user-review w-75 p-3">
        <h4>User Review Title</h4>
        <span>STAR RATING 5/5</span>
        <p>Blah blah</p>
      </div>
    </div>
  );
}
