import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Card, Col, Form, Row } from "react-bootstrap";

import { MdDeleteForever } from "react-icons/md";
import config from "../config/serverUrl";
import productServices from "../services/productServices";
import { useNavigate } from "react-router-dom";
import cartservice from "../services/cartService";
import validation from "../services/validation";

const CartItemCard = (props) => {
  useEffect(() => {
    // getImage();
  }, []);

  const [image, setImage] = useState();
  const { item } = props;
  const { product,user,quantity }=item;
  const navigate=useNavigate();
  const styles = {
    h2: {
      marginTop: "5%",
    },
    img: {
      width: "auto",
      height: "140px",
      borderRadius: "15px",
      
    },
    col: { marginTop: "10px", marginBottom: "10px" },
  };

  const getImage = () => {
    axios.get(config.serverUrl+'home/'+'/images');
  };

  const updateQuantity= async (id,newQuantity)=>{
      const response=await productServices.incrementProductQuantity(id,newQuantity);
      const result=response.data;
      if(result.message=="success")
      {
        window.location.reload(false);
      }
  }

  const removeFromCart = async () => {

          try
          {
          
               const response=  await cartservice.deleteProductFromCart(item.id);
               const result=response.data;
               
               if(result.message =="success")
               {
                validation.success("succesfully deleted");
                window.location.reload(false);
                
               }
          }
          catch(E)
          {
              validation.error("exception")     
          }
    

  };

  return (
    <Row>
      <Col style={styles.col} sm={12}>
        <Card style={{ borderRadius: "15px" }}>
          <Card.Body >
            <Row >
              <Col style={{ 
                textAlign: "center" }}
                 md={4}>
                <img 
                src={config.serverUrl+'home/'+product.id+'/images'} 
                style={styles.img} alt="product-image" />
              </Col>
              <Col md={8}>
                <Card.Title>
                  <h4>{product.name}</h4>
                </Card.Title>
                <Card.Text>
                  <h5>
                    <b>₹{product.price} </b>(Inclusive of all taxes)
                  </h5>
                  <h5 style={{ color: "green" }}>In stock.</h5>
                </Card.Text>
                <h5 style={{ display: "inline" }}>Quantity : </h5>
                <Form.Select
                  defaultValue={quantity}
                  onChange={(event) => {
                    updateQuantity(item.id, event.target.value);
                  }}
                  style={{
                    marginBottom: "10px",
                    width: "70px",
                    display: "inline-block",
                  }}
                  aria-label="Default select example"
                >
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                </Form.Select>
                <Button
                  onClick={removeFromCart}
                  className="mb-1"
                  style={{
                    width: "200px",
                    color: "white",
                    backgroundColor: "red",
                    border: "none",
                    display: "block",
                  }}
                  variant="info"
                >
                  <MdDeleteForever style={{ fontSize: "18px" }} /> Remove
                </Button>
              </Col>
            </Row>
          </Card.Body>
        </Card>
      </Col>
    </Row>
  );
};

export default CartItemCard;
