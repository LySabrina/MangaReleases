import React from "react";
import { Link } from "react-router-dom";

export default function Book() {
  return (
    <Link to='BookDetails/'>
      <div class="book">
        
        <div class="imageContainer">

        </div>

        <div class="book-info">
          <h3>TITLE</h3>
          <h4>Release Date: May 4</h4>
        </div>
      </div>
    </Link>
  );
}
