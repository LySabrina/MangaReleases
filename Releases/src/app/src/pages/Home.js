import React, {useState, useEffect} from 'react'
import Navbar from '../layouts/Navbar'
import Calendar from '../layouts/Calendar';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import startOfWeek from 'date-fns/startOfWeek';
import getDay from 'date-fns/getDay';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'bootstrap/dist/css/bootstrap.css';
import Filter from '../components/Filter';
import SideBar from '../components/SideBar';
import '../style/Home.css';
import Book from '../components/Book';
import axios from 'axios';
export default function Home() {
  const [books, setBooks] = useState([]);

  //At initial mount, get all the books from the database
  useEffect(()=>{
      axios.get("http://localhost:8080/").then(response=>{
        if(response.data == null){
          console.log('Data is null');
        }
        else{
          console.log('DATA SUCCESSFULLY RETRIEVED');
          console.log(books);
          setBooks(response.data);
        }
      }).catch((error) =>{
        console.log('ERROR IN SENDING HTTP REQUEST');
      })
  }, []);

  
  return (
    <div>
        <Navbar/>
        <div class = 'filter-container'>
          <Filter/>
        </div>

        <div class = 'table-container'>
          <SideBar/>
          
          <div class = 'table'>
            {
              books.map((element, index)=>(
                <Book name = {element.name} releaseDate={element.releaseDate} imagePath={element.imagePath} key={index}></Book>
              ))
            }
              
          </div>
        </div>
    </div>
  )
}
