import React from "react";
import { useState } from "react";
import { Card, Button, Container } from "react-bootstrap";
import { FaHeart } from "react-icons/fa";
import "../style/ProductCard.css";
import cartService from "../services/cartService"
import validation from "../services/validation";
import config from '../config/serverUrl';

const ProductCard=(props)=> {

  const { product }=props
  const [isFavorite, setIsFavorite] = useState(product.isFavorite);
  const toggleFavorite = () => {
    setIsFavorite((prevFavorite) => !prevFavorite);
  };
  const Style={

    card:{
        width:'18rem',
        backgroundColor: '#C8E4B2'
    },
    image:{
      
       borderRadius:10,
       width:'100%',
       height:300,
       Color: '#C8E4B2'
    }
   }

  const addProductToCart= async (productId)=>{
    
    const CartDto={
      productId:productId,
      userId:sessionStorage.getItem("id"),
      quantity:1

    }
    try{
      const response=await cartService.addToCart(CartDto);
      validation.success("product added to cart");
    }catch(ex)
    {
      validation.error("something went wrong");
    }
  };

  return (
  //   <Card className="product-card">
    // <div
    //   className={`wishlist-icon ${isFavorite ? "favorite" : ""}`}
    //   onClick={toggleFavorite}
    // >
    //   <FaHeart />
    // </div>
  //   <Card.Img
  //     variant="top"
  //     src={config.serverUrl+product.imagePath}
  //     alt={product.name}
  //     style={{ height: "200px" }}
  //   />
  //   <Card.Body>
  //     <Card.Title>{product.name}</Card.Title>
  //     <Card.Text>Price: ${product.price}</Card.Text>
  //     <Button onClick={()=>addProductToCart(product.id)} variant="primary" className="btn-add-to-cart">
  //       Add to Cart
  //     </Button>
  //   </Card.Body>
  // </Card> 
  <Card style={Style.card}>
    <div
      className={`wishlist-icon ${isFavorite ? "favorite" : ""}`}
      onClick={toggleFavorite}
    >
      <FaHeart />
    </div>
    <Card.Img style={Style.image} variant="top" src={config.serverUrl+'home/'+product.id+'/images'} />
    <Card.Body>
    <Card.Title>{product.name}</Card.Title>
    <Card.Text>
    ₹{product.price}
    </Card.Text>
    <Card.Text>
      {product.brand}
    </Card.Text>
    <Button onClick={()=>addProductToCart(product.id)}  variant="dark">Add to cart</Button>
    </Card.Body>
</Card>
  )  
   

}

export default ProductCard;
