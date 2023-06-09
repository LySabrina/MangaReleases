import "../style/Home.css";
import "../style/Sidebar.css";
import React, { useState, useEffect } from "react";

export default function SideBar({ formats, genres, sidebarCallback }) {
  const [formatFilters, setFormatFilters ] = useState([]);
  const [genresFilters, setGenresFilters] = useState([]);
  
  //make sure it the query is submitted to the database
  function handleGenresChange(e){
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
    sidebarCallback(formatFilters,genresFilters);
  }

  function handleFormatsChange(e){
    const formatName = e.target.value;
    const isChecked = e.target.checked;
    if(isChecked){
      setFormatFilters([...formatFilters,formatName]);
    }
    else if(!isChecked){
      const formatIndex = formatFilters.indexOf(formatName);
      if(formatIndex!== -1){
        formatFilters.splice(formatIndex,1);
      }
    }
    sidebarCallback(formatFilters,genresFilters);
  }

  //Printing state purpose
  // useEffect(()=>{
  //   console.log(genresFilters);
  //   console.log("FORMATS: " + formatFilters);
  // }, [genresFilters, formatFilters]);

  return (
    <div id="sideBar" className = "p-2">
      <form className="filter" >
        <div>
          <h3>Format</h3>
          {formats.map((element, index) => (
            <div className="format-list">
              <input
                type="checkbox"
                name={element}
                value={element}
                className="format"
                id={element}
                key = {index}
                onChange={(e)=>handleFormatsChange(e)}
              />
              <label htmlFor={element} className="p-1">{element}</label>
            </div>
          ))}

          <h3>Genres</h3>
          <div className="genres-list">
            {genres.map((element, index) => (
              <div className="genres-list">
                <input
                  type="checkbox"
                  value = {element}
                  className="genre"
                  id={element}
                  onChange={handleGenresChange}
                />
                <label htmlFor={element} className = "p-1">{element}</label>
              </div>
            ))}
          </div>
        </div>
      </form>

    </div>
  );
}
