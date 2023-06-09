import React, {useState, useEffect} from 'react'
import Navbar from '../layouts/Navbar'
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'bootstrap/dist/css/bootstrap.css';
import Filter from '../components/Filter';
import SideBar from '../components/SideBar';
import '../style/Home.css';
import Book from '../components/Book';
import axios from 'axios';
import { Link } from 'react-router-dom';
import LoadingSpinner from '../components/LoadingSpinner';
export default function Home() {
  //Loading and error state 
  const [loading, setLoading] = useState(true);
  const [hasError, setError] = useState(false);
  const [errMessage, setErrMessage] = useState("");

  //Books, Format, and Genres
  const [books, setBooks] = useState([]);
  const [formats, setFormats] = useState([]);
  const [genres, setGenres] = useState([]);
  const [year, setYear] = useState();
  const [month, setMonth] = useState();


  //Hold an array of requested formats and genres selected by user
  const [requestedFormats, setRequestedFormats] = useState([]);
  const [requestedGenres, setRequestedGenres] = useState([]);
  
  //At initial mount, get all the books,genres, and formats from the database

  function filterCallback(year, month){
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

  useEffect(()=>{
    const promiseBooks = axios.get("http://localhost:8080/");
    const promiseFormats = axios.get('http://localhost:8080/formats');
    const promiseGenres = axios.get('http://localhost:8080/genres');

    Promise.all([promiseBooks, promiseFormats,promiseGenres]).then((response)=>{
      setBooks(response[0].data);
      setFormats(response[1].data);
      setGenres(response[2].data);
      setLoading(false);
    }).catch((error)=>{
      setLoading(false);
      setError(true);
      setErrMessage("SERVER IS DOWN. TRY AGAIN ANOTHER TIME");
    });

  },[]);

  async function getFormats(){
    
  }
  useEffect(()=>{ 
    if(requestedFormats.length != 0){

    }
  },[requestedFormats,requestedGenres, year,month])
  // useEffect(()=>{
  //     axios.get("http://localhost:8080/").then(response=>{
  //       if(response.data == null){
  //         setLoading(false);
  //         setError(true);
  //         setErrMessage("Server Database is empty");
  //       }
  //       else{
  //         setBooks(response.data);
  //         setLoading(false);
  //       }
  //     }).catch((error) =>{
  //       setLoading(false);
  //       setError(true);
  //       setErrMessage("SERVER IS DOWN. TRY AGAIN ANOTHER TIME");
      
  //     })
  // }, []);

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
  
  return (
    <div>
        <Navbar/>
        <div class = 'filter-container'>
          <Filter className = 'filters' filterCallback={filterCallback}/>
          
        </div>

        <div class = 'content-container'>
        <SideBar formats = {formats} genres = {genres} sidebarCallback={sidebarCallback}/>
          

          <div class = 'table'>
            {hasError && <p>{errMessage}</p>}
          
            {
              loading ? <LoadingSpinner/> : 
              books.map((element, index)=>(
                <Link to = {`/BookDetails/${element.id}`} state={element} className='link' onMouseMove={rotateCard}>
                  <Book name = {element.name} releaseDate={element.releaseDate} id={element.id} key={index}  className = 'flex-items'></Book>
                </Link>
              ))
            }
              
          </div>
        </div>
    </div>
  )
}
