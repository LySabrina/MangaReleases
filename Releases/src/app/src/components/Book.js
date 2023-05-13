import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";
import axios from 'axios';
import '../style/Book.css';
import '../style/Home.css';
//TODO: Fix the issue where Springboot has an issue with the {id} being a String from the path and it didnt work
export default function Book({name, releaseDate, id}) {
  const [imageSrc, setImageSrc] = useState("");
  
  useEffect(() =>{
    
    axios.get(`http://localhost:8080/${id}/image`).then((response)=>{
      if(response.data == null){
        console.log("NO IMAGE AVAILABLE");
      }
      else{
        console.log('SUCCESSFULLY RECEIVED IMAGE');
        setImageSrc(response.data);
      }

    }).catch((error)=>{
      console.log('ERROR IN GETTING IMAGE');
    })
  }, []);

  return (
      <div class="book">
        <div class="imageContainer">
          <img src = {`data:image/jpg;base64,${imageSrc}`} alt={`${name} Book`}/>
        </div>

        <div class="book-info">
          <h3>{name}</h3>
          <h5>Release Date: {releaseDate}</h5>
        </div>
      </div>
    
  );
}
