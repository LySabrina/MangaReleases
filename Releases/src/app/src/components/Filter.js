import React from 'react'

export default function Filter() {
    const MONTHS = ['January', 'February','March', 'April', 'May', 'June', 'July', 'August', 'October', 'September', 'November', 'December'];
  return (
    <div id = 'filter'>
        <select id='year'>
            <option>2023</option>
            <option>2022</option>
        </select>

        <select id='month'>
            {
                MONTHS.map((element) =>(
                    <option>{element}</option>
                ))
            }
        </select>
    </div>
  )
}
