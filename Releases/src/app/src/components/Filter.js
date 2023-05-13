import React from "react";
import "../style/Filter.css";
export default function Filter() {
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
  const [year, setYear] = [currentDate.getFullYear()];
  const [month, setMonth] = [currentDate.getMonth()];

  const listYears = [];

  //Generate years
  function generateYears(){
    for(let i = START_YEAR; i <= END_YEAR; i++){
        listYears.push(i);
    }
  }
  generateYears();

  return (
    <div id="filter-form">
      <form method="GET">
        <span>Select Year and Month: </span>
        <select id="year" name = "year">
          {
            [...listYears].reverse().map((element)=>(
                <option value = {element}>{element}</option>
            ))
          }
        </select>

        <select id="month" name = "month">
          {MONTHS.map((element) => (
            <option value={element}>{element}</option>
          ))}
        </select>
      </form>
    </div>
  );
}
