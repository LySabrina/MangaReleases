import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";
import axios from 'axios';
import '../style/Book.css';

//TODO: Fix the issue where Springboot has an issue with the {id} being a String from the path and it didnt work
export default function Book({name, releaseDate, id}) {
  const [imageSrc, setImageSrc] = useState("");
  
  function arrayToBase64(array){
    const binary = "";
    const bytes = new Uint8Array(array);
    const len = bytes.byteLength;
    for(let i = 0 ; i < len ;i++){
      binary += String.fromCharCode(bytes[i]);
    }
    return window.btoa(binary);
  }
  useEffect(() =>{
    
    axios.get(`http://localhost:8080/1/image`).then((response)=>{
      if(response.data == null){
        console.log("NO IMAGE AVAILABLE");
      }
      else{
        console.log('SUCCESSFULLY RECEIVED IMAGE');
        console.log(response.data);
        setImageSrc(response.data);
      }

    }).catch((error)=>{
      console.log('ERROR IN GETTING IMAGE');
    })
  }, []);

  return (
      <div class="book">
        <div class="imageContainer">
          <img src = {`data:image/jpg;base64,${imageSrc}`}/>
        </div>

        <div class="book-info">
          <h3>{name}</h3>
          <h4>Release Date: {releaseDate}</h4>
        </div>
      </div>
    
  );
}
