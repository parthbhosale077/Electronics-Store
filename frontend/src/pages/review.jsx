import { useState } from "react";
import { useParams } from "react-router-dom";

import "../style/review.css"
import axios from "axios";
import httpClient from '../http-common'
import validation from "../services/validation";


const Review=()=>
{
    const [title, setTitle] = useState('');
  const [rating, setRating] = useState(0);
  const [description, setComment] = useState('');
  const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};
  
  const product=parseInt( useParams().u_id);
  const user=sessionStorage.getItem("id");


  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  }

  const handleRatingChange = (event) => {
    setRating(event.target.value);
  }

  const handleCommentChange = (event) => {
    setComment(event.target.value);
  }

  const handleSubmit = async (event) => {
    const reviewdto ={title,description,rating,product,user};
    event.preventDefault();
   const response = await httpClient.post('user/addProductReview',
    reviewdto,{ headers}
    );

     if(response.data.message =="Review Added Successfully!")
     {
        validation.success("Review Added Successfully!");
     }
     else{
        validation.error("something went wrong");
     }
    console.log(response);

    




    // You can perform any action here with the review data like sending it to a server.
    console.log('Title:', title);
    console.log('Rating:', rating);
    console.log('Comment:', description);
    // Clear the form after submission
    setTitle('');
    setRating(0);
    setComment('');
  }

  return (
    <div style={{width:"500px",margin:"auto"}}>
      <h2>Leave a Review</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Title:
            <input type="text" value={title} onChange={handleTitleChange} />
          </label>
        </div>
        <div>
          <label>
            Rating:
            <select value={rating} onChange={handleRatingChange}>
              <option value="0">Select Rating</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </label>
        </div>
        <div>
          <label>
            Comment:
            <textarea value={description} onChange={handleCommentChange} />
          </label>
        </div>
        <button type="submit" >Submit</button>
      </form>
    </div>
  );
}

export default Review;