import React, { useState } from "react";
import "../style/Home.css";

/**
 * Finds the searched book based on user input
 * Should find the user raequired book based on continuous typing
 * If the input box is empty, then just go back to showing /all or the filtered books they have requested
 */
export default function Searchbar({ getSearchedBook, searchQuery, setSearchQuery }) {
  //getSearchedBooks is a callback function that should go to the parent component to return the found result
  


  return (
    <form id="search" method="GET" onSubmit={(e)=>{e.preventDefault();}}>
      <input
        type="text"
        placeholder="Enter Book Name"
        value={searchQuery}
        onChange={(e)=>{setSearchQuery(e.target.value)}}
      ></input>
    </form>
  );
}
