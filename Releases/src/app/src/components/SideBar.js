import "../style/Home.css";
import "../style/Sidebar.css";
import React, { useState, useEffect } from "react";

export default function SideBar({ formats, genres }) {
  const [formatFilters, setFormatFilters ] = [];
  const [genresFilters, setGenresFilters] = [];
  
  //make sure it the query is submitted to the database
  function handleFormChange(e){
    console.log("CHANGED");
  }

  return (
    <div id="sideBar">

      
      <form className="filter" onChange={handleFormChange}>
        <div>
          <h3>Format</h3>
          {formats.map((element) => (
            <div className="format-list">
              <input
                type="checkbox"
                name={element}
                className="format"
                id={element}
                
              />
              <label for={element}>{element}</label>
            </div>
          ))}

          <h3>Genres</h3>
          <div className="genres-list">
            {genres.map((element) => (
              <div className="genres-list">
                <input
                  type="checkbox"
                  name={element}
                  className="genre"
                  id={element}
                  
                />
                <label for={element}>{element}</label>
              </div>
            ))}
          </div>
        </div>
      </form>

    </div>
  );
}
