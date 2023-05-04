import Navbar from "./layouts/Navbar";
import Home from "./pages/Home";
import SignUp from "./pages/SignUp";
import Login from "./pages/Login";
import 'bootstrap/dist/css/bootstrap.min.css'
import "bootstrap/dist/js/bootstrap.bundle.min";

import {
  BrowserRouter as Router,
  Routes,
  Route,
  useParams,
} from "react-router-dom";
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path = "/login" element={<Login/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
