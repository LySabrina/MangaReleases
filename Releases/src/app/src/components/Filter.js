import React, { useEffect, useState } from "react";
import axios from "axios";
import "../style/Filter.css";
export default function Filter({ filterCallback }) {
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

  // send the selected year and month selected
  // By default, it will be the current month and current year
  const [year, setYear] = useState();
  const [month, setMonth] = useState();

  const listYears = [];

  //Generate years
  function generateYears() {
    for (let i = START_YEAR; i <= END_YEAR; i++) {
      listYears.push(i);
    }
  }
  generateYears();

  //once the state has changed, we want to send that new query to the database
  //this needs to be fixed
  useEffect(() => {
    year !== "-" &&
      month !== "-" &&
      axios
        .get(`http://localhost:8080/date?year=${year}&month=${month}`)
        .then((response) => {
          filterCallback(response.data);
        })
        .catch((error) => {
          console.log("error");
        });
  }, [year, month]);

  return (
    <div id="filter-container">
      <div id="filter-form">
        <form method="GET">
          <span>Select Year and Month: </span>
          <select
            id="year"
            name="year"
            onChange={(selectedYear) => setYear(selectedYear.target.value)}
          >
            <option selected>-</option>
            {[...listYears].reverse().map((element) => (
              <option value={element}>{element}</option>
            ))}
          </select>

          <select
            id="month"
            name="month"
            onChange={(selectedMonth) => setMonth(selectedMonth.target.value)}
          >
            <option selected>-</option>
            {MONTHS.map((element) => (
              <option value={element}>{element}</option>
            ))}
          </select>
        </form>
      </div>

      <div id = "searchbar">
          <form method = "GET">
            <input type='text' placeholder='Enter Book Name'></input>
          </form>
      </div>
    </div>
  );
}
