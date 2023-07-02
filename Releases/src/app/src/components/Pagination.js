import React from "react";

export default function Pagination({
  currentPageNo,
  currentTotalPageSize,
  getNextPrevPage,
}) {
  const generatedPagination = new Array(currentTotalPageSize).fill(true);

  return (
    <nav>
      <ul className="pagination justify-content-center">
        <li className={`page-item ${currentPageNo === 0 && "disabled"}`}>
          <button className="page-link" aria-label="Previous" onClick={()=>{getNextPrevPage(currentPageNo-1)}}>
            &laquo;
          </button>
        </li>

        <li className="page-item">
          {generatedPagination.map((element, index) => (
            <button
              className={`page-link ${currentPageNo === index && "active"}`}
              style={{ display: "inline" }}
              onClick={() => {
                getNextPrevPage(index);
              }}
            >
              {index + 1}
            </button>
          ))}
        </li>

        <li className={`page-item ${currentPageNo +1 === currentTotalPageSize && "disabled"}`}>
          <button className="page-link" aria-label="Next" onClick={()=>{getNextPrevPage(currentPageNo+1)}} >
            &raquo;
          </button>
        </li>
      </ul>
    </nav>
  );
}
