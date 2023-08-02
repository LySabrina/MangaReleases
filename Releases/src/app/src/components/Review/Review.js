import React from "react";

export default function Review() {
  return (
    <div className="d-flex border border-primary p-2  w75">
      <img
        src={require("../../assets/blank_profile.png")}
        alt="Profile"
        className="w-25"
      />

      <div className="">
        <h1>Review Title</h1>
        <p>This is okay</p>
      </div>
    </div>
  );
}
