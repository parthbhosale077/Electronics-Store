import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col } from "react-bootstrap";
import ProductCard from "../componenets/ProductCard";
import { useParams } from "react-router-dom";
import productServices from "../services/productServices";
import validation from "../services/validation";
import axios from "axios";
import config from '../config/serverUrl';
import wishListService from "../services/wishListService";


function Wishlist() {

  const [products,setProducts]=useState([]);

  useEffect(()=>{
    loadProducts();
  },[])


  const Style={

    column:{
       height: 500,
       width: 500,
    },
    img:{
      padding:20
    },
    card:{
        boxShadow:'0px 5px 20px rgba(0,0,0,0.5)',
        display:'inline-block',
        margin:15,
        position:'relative'
    },
    container:{
        overflow:'hidden'
    }
}
  const loadProducts= ()=>{
   
    const wishlistDTO = {
      userId: sessionStorage.getItem("id"),
      productId: null
    }
      wishListService.viewWishlist(sessionStorage.getItem("id"))
      .then(response=>{console.log(response)
        console.log(response);
        console.log(response.data);
        console.log(response.data.data);
        setProducts(response.data.data)
      });
      // axios.get(config.serverUrl+"home/view/"+categoryName)
      // .then(response=>{setProducts(response.data.data)
      // console.log(response)});
    
  }
  
  return (
    <Container>
      <h2>My Wishlist</h2>
      <Row>
        
          <Col >
              {products.map((item)=>{
                return(
                  <div style={Style.card}>
                  <ProductCard product={item}/>
                  </div>
                )
              })}
          </Col>
      </Row>
    </Container>
  );
}

export default Wishlist;
