import React, {useState} from 'react'
import Navbar from '../layouts/Navbar'
import Calendar from '../layouts/Calendar';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import startOfWeek from 'date-fns/startOfWeek';
import getDay from 'date-fns/getDay';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'bootstrap/dist/css/bootstrap.css';

export default function Home() {


  return (
    <div>
        <Navbar/>
    </div>
  )
}
