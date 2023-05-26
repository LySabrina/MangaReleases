import "../style/Home.css";
import "../style/Sidebar.css";
import React, { useState, useEffect } from "react";

export default function SideBar({ formats, genres }) {
  const [formatFilters, setFormatFilters ] = useState([]);
  const [genresFilters, setGenresFilters] = useState([]);
  
  //make sure it the query is submitted to the database
  function handleFormChange(e){
    console.log(e);
    const genreName = e.target.value;
    const isChecked = e.target.checked;
    if(isChecked){
      setGenresFilters([...genresFilters, genreName]);
    }
    else if(!isChecked){
      const genreIndex = genresFilters.indexOf(genreName);
      if(genreIndex !== -1){
        genresFilters.splice(genreIndex, 1);
      }
    }
  }
  useEffect(()=>{
    console.log(genresFilters)
  }, [genresFilters]);

  return (
    <div id="sideBar">

      
      <form className="filter" >
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
                  value = {element}
                  className="genre"
                  id={element}
                  onChange={handleFormChange}
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
