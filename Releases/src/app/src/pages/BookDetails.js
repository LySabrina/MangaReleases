import React, { useEffect, useState } from "react";
import { useLocation } from "react-router";
import axios from "axios";
import "../style/BookDetails.css";
import Navbar from "../layouts/Navbar";
import Rating from "../components/Review/Rating";
import Review from "../components/Review/Review";
export default function BookDetails() {
  const state = useLocation();
  const bookData = state.state;
  const [image, setImageSrc] = useState("");
  const [genres, setGenres] = useState([]);
  const [hasError, setHasError] = useState(false);

  useEffect(() => {
    const promiseImage = axios.get(
      `http://localhost:8080/${bookData.id}/image`
    );
    const promiseGenres = axios.get(
      `http://localhost:8080/${bookData.id}/genres`
    );

    Promise.all([promiseImage, promiseGenres])
      .then((response) => {
        setImageSrc(response[0].data);
        setGenres(response[1].data);
      })
      .catch((error) => {
        setHasError(true);
        console.log("error");
      });
  }, []);

  return (
    <div>
      <div className="book-details_container container">
        <div className="image-container">
          {hasError && <h1>COULD NOT FETCH IMAGE FROM DATABASE</h1>}
          <img src={`data:image/jpg;base64,${image}`} className="image" />
        </div>

        <div className="description-container">
          <h1 className="display-4">{bookData.name}</h1>
          <h4 className="h4">Series: {bookData.series}</h4>

          <p>{bookData.description}</p>
          <dl className="row">
            <dt className="col-sm-3">Author & Artist:</dt>
            <dd className="col-sm-9">
              {bookData.author}, {bookData.artist}
            </dd>

            <dt className="col-sm-3">Genres:</dt>
            <dd className="col-sm-9">
              <ul>
                {genres.map((element, index) => (
                  <li key={index}>{element}</li>
                ))}
              </ul>
            </dd>

            <dt className="col-sm-3">FORMAT:</dt>
            <dd className="col-sm-9">{bookData.type}</dd>

            <dt className="col-sm-3">ISBN:</dt>
            <dd className="col-sm-9">{bookData.isbn}</dd>
          </dl>
        </div>
      </div>

      <div
        className="book-reviews w-100 container mt-5"
        style={{ backgroundColor: "", width: "100px" }}
      >
        <h1>REVIEWS PLACEHOLDER</h1>
        <div className="book-review">
          <Review />
        </div>
      </div>
    </div>
  );
}
