import React, { useState, useEffect } from "react";

export default function BookFilters({
  bookFiltersCallback,
  yearMonthCallback,
  genreYearCallback,
  formats,
  genres,
}) {
  const MONTHS = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "October",
    "September",
    "November",
    "December",
  ];

  const currentDate = new Date();
  const START_YEAR = 2000;
  const END_YEAR = currentDate.getFullYear();
  const listYears = [];

  //Generate years
  function generateYears() {
    for (let i = START_YEAR; i <= END_YEAR; i++) {
      listYears.push(i);
    }
  }
  generateYears();

  //state to hold the selected filters BY THE USER
  const [filters, setFilters] = useState({
    year: null,
    month: null,
    formats: [],
    genres: [],
  });

  function handleGenresChange(e) {
    const genreName = e.target.value;
    const isChecked = e.target.checked;
    if (isChecked) {
      setFilters((prevVal) => ({
        ...prevVal,
        genres: [...prevVal.genres, genreName],
      }));
    } else if (!isChecked) {
      const genreIndex = filters.genres.indexOf(genreName);
      if (genreIndex !== -1) {
        filters.genres.splice(genreIndex, 1);
      }
    }
    bookFiltersCallback(filters);
  }

  function handleFormatsChange(e) {
    const formatName = e.target.value;
    const isChecked = e.target.checked;
    if (isChecked) {
      setFilters((prevFilters) => ({
        ...prevFilters,
        formats: [...prevFilters.formats, formatName],
      }));
    } else if (!isChecked) {
      const formatIndex = filters.formats.indexOf(formatName);
      if (formatIndex !== -1) {
        filters.formats.splice(formatIndex, 1);
      }
    }
    bookFiltersCallback(filters);
    
  }

  //!!!!!!!!!! FIX THE CALLBACK FOR MONTH AND YEAR
  useEffect(() => {
    if (
      filters.year !== undefined &&
      filters.year !== "-" &&
      filters.month !== undefined
    ) {
        bookFiltersCallback(filters);
    }
  }, [filters.year, filters.month]);
  
  console.log(filters);
  return (
    <div>
      <form id="filters" method="GET">
        
        <div className="date-container">
            <h3>Date</h3>
            <p>Select Year:</p>
            <select id = 'year' name ='year'>
                <option selected>-</option>
                {
                    [...listYears].reverse().map((element)=>(
                        <option value = {element}>{element}</option>
                    ))
                }
            </select>
            
            <p>Select Month:</p>
            <select id ='month' name ='month'>
                <option selected>-</option>
                {MONTHS.map((element, index)=>(
                    <option value ={element} key ={index}>{element}</option>
                ))}
            </select>
        </div>

        <div className="format-container">
          <h3>Format</h3>
          {formats.map((element, index) => (
            <div className="format-list">
              <input
                type="checkbox"
                name={element}
                value={element}
                className="format"
                id={element}
                key={index}
                onChange={(e) => handleFormatsChange(e)}
              />
              <label htmlFor={element} className="p-1">
                {element}
              </label>
            </div>
          ))}
        </div>

        <div className="genres-container">
          <h3>Genres</h3>
          {genres.map((element, index) => (
            <div className="genres-list">
              <input
                type="checkbox"
                value={element}
                className="genre"
                id={element}
                onChange={handleGenresChange}
              />
              <label htmlFor={element} className="p-1">
                {element}
              </label>
            </div>
          ))}
        </div>

      </form>
    </div>
  );
}
