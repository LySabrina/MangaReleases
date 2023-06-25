import React, {useState, useEffect} from 'react'
import Navbar from '../layouts/Navbar'
import 'bootstrap/dist/css/bootstrap.css';
import Filter from '../components/Filter';
import SideBar from '../components/SideBar';
import '../style/Home.css';
import Book from '../components/Book';
import axios from 'axios';
import { Link } from 'react-router-dom';
import LoadingSpinner from '../components/LoadingSpinner';
import Searchbar from '../components/Searchbar';
import BookFilters from '../components/BookFilters';

export default function Home() {
  //Loading and error state 
  const [loading, setLoading] = useState(true);
  const [hasError, setError] = useState(false);
  const [errMessage, setErrMessage] = useState("");

  //Books, Format, and Genres
  const [books, setBooks] = useState([]);


  //Holds the current available formats and genres provided
  const [formats, setFormats] = useState([]);
  const [genres, setGenres] = useState([]);

  //Discard these states later
  const [year, setYear] = useState();
  const [month, setMonth] = useState();

  //holds the requested user's filters
  const [filters, setFilters] = useState({
    year: null,
    month: null,
    formats: [],
    genres: [],
  })

  //Hold an array of requested formats and genres selected by user
  const [requestedFormats, setRequestedFormats] = useState([]);
  const [requestedGenres, setRequestedGenres] = useState([]);
  
  //At initial mount, get all the books,genres, and formats from the database


  //filters is an object
  function bookFiltersCallback(userSelectedFilters){
    setFilters(userSelectedFilters);
  }

  function filterCallback(year, month){
    // setBooks(data);
    console.log(year, month);
    setYear(year);
    //do some check if month == "-"
    //we can allow users to search manga in a given year
    setMonth(month);
    
  }

  function yearMonthCallback(year, month){
    // setBooks(data);
    console.log(year, month);
    setYear(year);
    //do some check if month == "-"
    //we can allow users to search manga in a given year
    setMonth(month);
  }
  
  function sidebarCallback(formats,genres){
    setRequestedFormats(formats);
    setRequestedGenres(genres);
  }

  //Now i got the genresm, formats, and year and month. Combine them in someway such that i get books of thes

  function tempShuffle(array){
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      const temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
    return array;
  }
  useEffect(()=>{
    const promiseBooks = axios.get("http://localhost:8080/");
    const promiseFormats = axios.get('http://localhost:8080/formats');
    const promiseGenres = axios.get('http://localhost:8080/genres');

    Promise.all([promiseBooks, promiseFormats,promiseGenres]).then((response)=>{
      const arr = tempShuffle(response[0].data);
      setBooks(arr);

      tempShuffle(response[0].data);
      setFormats(response[1].data);
      setGenres(response[2].data);
      setLoading(false);

    }).catch((error)=>{
      setLoading(false);
      setError(true);
      setErrMessage("SERVER IS DOWN. TRY AGAIN ANOTHER TIME");
    });

  },[]);

  
  //when user's filter choice is updated, call the backend function to get all the filtered books
  //after getting all the filtered books , set the books to all those books 
  useEffect(()=>{
     axios.get("http://localhost:8080/filters").then((response)=>{
      console.log(response);
     }).catch((error)=>{
      console.log(error);
     })

  }, [filters])


  useEffect(()=>{ 
    if(requestedFormats.length != 0){

    }
  },[requestedFormats,requestedGenres, year,month])

  function rotateCard(e){
    console.log(e);
    //X and Y coordinates of the mouse position
    const x = e.clientX;
    const y = e.clientY;
    const card = e.target;

    //width and height of the cards
    const width = card.offsetWidth;
    const height = card.offsetHeight; 
    console.log("WIDTH: " + width + " HEIGHT: " + height );
    
    const offsetX = (((x - width)) / width ) * 15;
    const offsetY = (((y - height)) / height ) * 15;

    console.log(offsetX, offsetY);
    card.style.setProperty("--rotateX", -1 * offsetY + "deg");
    card.style.setProperty("--rotateY", offsetX + "deg");
    
    //find the middle of the card 
    //get the coordinates of the mouse and find the difference between the midle and the mouse coordinates //get the value and use it to transform the card
    
  }

  function getSearchedBook(){

  }
  
  return (
    <div>
        <Navbar/>

        {/* className should be called searchbar-container*/}
        <div class = 'filter-container '>
          <Searchbar getSearchedBook={getSearchedBook}/> 
          {/* <Filter className = 'filters' filterCallback={filterCallback}/>
          <Searchbar getSearchedBook={getSearchedBook}/> */}
        </div>

        <div class = 'content-container'>
        {/* <SideBar formats = {formats} genres = {genres} sidebarCallback={sidebarCallback}/> */}
        <BookFilters yearMonthCallback={yearMonthCallback} bookFiltersCallback={bookFiltersCallback} formats={formats} genres = {genres}/>

          <div class = 'table'>
            {hasError && <p>{errMessage}</p>}
          
            {
              loading ? <LoadingSpinner/> : 
              books.map((element, index)=>(
                <Link to = {`/BookDetails/${element.id}`} state={element} className='link'>
                  <Book name = {element.name} releaseDate={element.releaseDate} id={element.id} key={index}  className = 'flex-items'></Book>
                </Link>
                
              ))
            }

             {/* 
                             <Link to = {`/BookDetails/${element.id}`} state={element} className='link' onMouseMove={rotateCard}>

             */} 
          </div>
        </div>
    </div>
  )
}
