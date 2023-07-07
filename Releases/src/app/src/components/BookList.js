import React, { useEffect, useState } from "react";
import axios from "axios";
import LoadingSpinner from "./LoadingSpinner";
import { Link } from "react-router-dom";
import Book from "./Book";
import Pagination from "./Pagination";

export default function BookList({ searchQuery }) {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [hasError, setHasError] = useState(false);
  const [errMessage, setErrMessage] = useState("");

  const [isSearch, setIsSearch] = useState(false);
  const numPageElements = [10, 20, 25];

  const [page, setPage] = useState({
    currentPageNo: 0,
    currentPageSize: 10,
    currentTotalPageSize: 1,
  });

  useEffect(() => {
    const promiseBooks = axios
      .get("http://localhost:8080/")
      .then((response) => {
        setLoading(false);
        setBooks(response.data.content);
        setPage((prevPage) => ({
          ...prevPage,
          currentTotalPageSize: response.data.totalPageNo,
        }));
      })
      .catch((error) => {
        setLoading(false);
        setHasError(true);
        setErrMessage("SERVER DOWN - TRY AGAIN");
      });
  }, []);

  useEffect(() => {
    const api = `http://localhost:8080/search?query=${searchQuery}&pageNo=${page.currentPageNo}&pageSize=${page.currentPageSize}`;
    setIsSearch((prevVal) => {
      return !prevVal;
    });
    const delay = setTimeout(() => {
      if (searchQuery === "") {
        axios
          .get(
            `http://localhost:8080/?pageNo=${page.currentPageNo}&pageSize=${page.currentPageSize}`
          )
          .then((response) => {
            setBooks(response.data.content);
            setPage((prevPage) => {
              return {
                ...prevPage,
                currentPageNo: 0,
                currentTotalPageSize: response.data.totalPageNo,
              };
            });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        axios
          .get(api)
          .then((response) => {
            setBooks(response.data.content);
            setPage((prevPage) => ({
              ...prevPage,
              currentTotalPageSize: response.data.totalPageNo,
              currentPageNo: 0,
            }));
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }, 500);
    return () => clearTimeout(delay);
  }, [searchQuery]);

  function handleSelectChange(e) {
    
    const perPageVal = e.target.value;
    setPage((prevPage) => ({
      ...prevPage,
      currentPageSize: perPageVal,
      currentPageNo: 0,
    }));
    if(searchQuery === ""){
      const getNewBooks = axios
      .get(`http://localhost:8080/?pageNo=${0}&pageSize=${perPageVal}`)
      .then((response) => {
        
        setPage((prevPage) => ({
          ...prevPage,
          currentTotalPageSize: response.data.totalPageNo,
        }));
        setBooks(response.data.content);
      })
      .catch((error) => {
        console.log("ERROR IN GETTING SPECIFIC PAGE NO AND PAGE SIZE");
      });
    }
    else{
      const getNewBooks = axios.get(`http://localhost:8080/search?query=${searchQuery}&pageNo=${0}&pageSize=${perPageVal}`).then((response)=>{
        setPage((prevPage)=>(
          {
            ...prevPage,
            currentTotalPageSize: response.data.totalPageNo
          }
        ))
        setBooks(response.data.content);
      }).catch((error)=>{
        console.log(error);
      })
    }
   
  }

  function getNextPrevPage(pageNo) {
    if (searchQuery === "") {
      const getNewBooks = axios
        .get(
          `http://localhost:8080/?pageNo=${pageNo}&pageSize=${page.currentPageSize}`
        )
        .then((response) => {
          console.log(response);
          setPage((prevPage) => ({
            ...prevPage,
            currentTotalPageSize: response.data.totalPageNo,
            currentPageNo: pageNo,
          }));

          setBooks(response.data.content);
        })
        .catch((error) => {
          console.log(error);
          console.log("ERROR IN GETTING SPECIFIC PAGE NO AND PAGE SIZE");
        });
    } else {
      const getNewBooks = axios
        .get(
          `http://localhost:8080/search?query=${searchQuery}&pageNo=${pageNo}&pageSize=${page.currentPageSize}`
        )
        .then((response) => {
          console.log(response);
          setPage((prevPage) => ({
            ...prevPage,
            currentTotalPageSize: response.data.totalPageNo,
            currentPageNo: pageNo,
          }));
          setBooks(response.data.content);
        });
    }
  }

  return (
    <div className="container">
      {!hasError && books.length !== 0 && (
        <div>
          <span>Items per page:</span>
          <select
            onChange={(e) => {
              handleSelectChange(e);
            }}
          >
            {numPageElements.map((element) => (
              <option value={element}>{element}</option>
            ))}
          </select>
        </div>
      )}

      <div className="error-container">
        {books.length === 0 && (
          <h1>NO BOOKS FOUND WITH SEARCH RESULT: {searchQuery}</h1>
        )}
      </div>
      <div className="table">
        {loading ? (
          <LoadingSpinner />
        ) : (
          books.map((element, index) => (
            <Link
              to={`/BookDetails/${element.id}`}
              state={element}
              className="link"
            >
              <Book
                name={element.name}
                releaseDate={element.releaseDate}
                id={element.id}
                price={element.price}
                key={index}
                className="flex-items"
              ></Book>
            </Link>
          ))
        )}
      </div>

      {!hasError && books.length !== 0 && (
        <div className="page-numbers">
          <Pagination
            currentPageNo={page.currentPageNo}
            currentTotalPageSize={page.currentTotalPageSize}
            getNextPrevPage={getNextPrevPage}
          />
        </div>
      )}
    </div>
  );
}
