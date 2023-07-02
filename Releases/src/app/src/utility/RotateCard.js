  function rotateCard(e){
    console.log(e);
    //X and Y coordinates of the mouse position
    const x = e.clientX;
    const y = e.clientY;
    const card = e.target;

    //width and height of the cards
    const width = card.offsetWidth;
    const height = card.offsetHeight; 
    console.log("WIDTH: " + width + " HEIGHT: " + height );
    
    const offsetX = (((x - width)) / width ) * 15;
    const offsetY = (((y - height)) / height ) * 15;

    console.log(offsetX, offsetY);
    card.style.setProperty("--rotateX", -1 * offsetY + "deg");
    card.style.setProperty("--rotateY", offsetX + "deg");
    
    //find the middle of the card 
    //get the coordinates of the mouse and find the difference between the midle and the mouse coordinates //get the value and use it to transform the card
    
  }