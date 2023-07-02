import React, { useState, useEffect } from "react";
import Navbar from "../layouts/Navbar";
import "bootstrap/dist/css/bootstrap.css";
import Filter from "../components/Filter";
import SideBar from "../components/SideBar";
import "../style/Home.css";
import Book from "../components/Book";
import axios from "axios";
import { Link } from "react-router-dom";
import LoadingSpinner from "../components/LoadingSpinner";
import Searchbar from "../components/Searchbar";
import BookFilters from "../components/BookFilters";
import BookList from "../components/BookList";

export default function Home() {
  //Loading and error state
  const [loading, setLoading] = useState(true);
  const [hasError, setError] = useState(false);
  const [errMessage, setErrMessage] = useState("");

  //Discard these states later
  const [year, setYear] = useState();
  const [month, setMonth] = useState();

  //holds the requested user's filters
  const [filters, setFilters] = useState({
    year: null,
    month: null,
    formats: [],
    genres: [],
  });

  const [pageSize, setPageSize] = useState(0);
  const numPageElements = [10, 20, 25];

  //At initial mount, get all the books,genres, and formats from the database

  //filters is an object
  function bookFiltersCallback(userSelectedFilters) {
    setFilters(userSelectedFilters);
  }

  function yearMonthCallback(year, month) {
    // setBooks(data);
    console.log(year, month);
    setYear(year);
    //do some check if month == "-"
    //we can allow users to search manga in a given year
    setMonth(month);
  }

  // useEffect(() => {
  //   const promiseBooks = axios.get("http://localhost:8080/");
  //   const promiseFormats = axios.get("http://localhost:8080/formats");
  //   const promiseGenres = axios.get("http://localhost:8080/genres");

  //   Promise.all([promiseBooks, promiseFormats, promiseGenres])
  //     .then((response) => {
  //       // const arr = tempShuffle(response[0].data);

  //       // setBooks(response[0].data.content);
  //       // tempShuffle(response[0].data);

  //       setLoading(false);
  //     })
  //     .catch((error) => {
  //       setLoading(false);
  //       setError(true);
  //       setErrMessage("SERVER IS DOWN. TRY AGAIN ANOTHER TIME");
  //     });
  // }, []);

  //when user's filter choice is updated, call the backend function to get all the filtered books
  //after getting all the filtered books , set the books to all those books
  // useEffect(() => {
  //   axios
  //     .get("http://localhost:8080/filters")
  //     .then((response) => {
  //       console.log(response);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }, [filters]);

  return (
    <div>
      <Navbar />

      {/* className should be called searchbar-container*/}
      <div className="filter-container ">
        <Searchbar />
        {/* <Filter className = 'filters' filterCallback={filterCallback}/>
          <Searchbar getSearchedBook={getSearchedBook}/> */}
      </div>

      <div className="content-container">
        <BookFilters
          yearMonthCallback={yearMonthCallback}
          bookFiltersCallback={bookFiltersCallback}
          setLoading={setLoading}
          setError={setError}
          setErrMessage={setErrMessage}
        />

        {hasError && <p>{errMessage}</p>}
        <BookList pageSize={pageSize} />
      </div>
    </div>
  );
}
