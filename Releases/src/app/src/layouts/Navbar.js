import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faBookOpen} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { Button, Form, Row, Col } from "react-bootstrap";

export default function Navbar() {
  return (
    <div className="container">
        <nav className="navbar navbar-light bg-light">
      <a className="navbar-brand" href="/">
      <FontAwesomeIcon icon= {faBookOpen} />
      <span> MR</span>
      </a>

      <div>
        <Link to={"/signup"} style={{ textDecoration: "none", color: "white" }} >
          <Button style = {{marginRight: '10px'}}>Sign Up</Button>
        </Link>

        <Link to={"/login"} style={{ textDecoration: "none", color: "white" }}>
          <Button  style = {{marginRight: '5px'}}>Login</Button>
        </Link>

        <a href="#">
          <FontAwesomeIcon icon={faUser} />
        </a>
        
      </div>
    </nav>  
    </div>

  );
}
