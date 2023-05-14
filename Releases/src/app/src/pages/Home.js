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

  //At initial mount, get all the books,genres, and formats from the database

  function filterCallback(data){
    setBooks(data);
    
  }

  function sidebarCallback(data){
    
  }

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

  
  return (
    <div>
        <Navbar/>
        <div class = 'filter-container'>
          <Filter className = 'filters' filterCallback={filterCallback}/>
        </div>

        <div class = 'content-container'>
          <div class='sidebar-container'>
          <SideBar formats = {formats} genres = {genres}/>
          </div>

          <div class = 'table'>
            {hasError && <p>{errMessage}</p>}
          
            {
              loading ? <LoadingSpinner/> : 
              books.map((element, index)=>(
                <Link to = {`/BookDetails/${element.id}`} state={element}className='link'>
                  <Book name = {element.name} releaseDate={element.releaseDate} id={element.id} key={index} class = 'flex-items'></Book>
                </Link>
                
              ))
            }
              
          </div>
        </div>
    </div>
  )
}
