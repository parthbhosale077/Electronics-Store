import React, { useEffect, useState } from 'react';
import '../style/ReviewTableComponent.css'; // Import CSS file for styling
import httpClient from '../http-common'
import axios from 'axios';
import { useParams } from "react-router-dom";


const ReviewTableComponent = () => 
{
    const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};
    const[reviews,setreview]=useState([]);
    const product=parseInt( useParams().u_id);
    
    useEffect(()=>{
       
        getreviewofproduct();
    },[])

    const  getreviewofproduct = async()=>{
           const response= await httpClient.get("user/getReviews/"+product,{headers});
           console.log(response.data);
           setreview(response.data);
    }
  return (
    <div className="review-table-container">
      <h2>Reviews</h2>
      <table className="review-table">
        <thead>
          <tr>
          <th>id</th>
            <th>Title</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>User Name</th>
          </tr>
        </thead>
        <tbody>
          {reviews.map((review) => (
            <tr key={review.id}>
                
                <td>{review.id}</td>
              <td>{review.title}</td>
              <td>{review.rating}</td>
              <td>{review.description}</td>
              <td>{review.user.firstName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ReviewTableComponent;
