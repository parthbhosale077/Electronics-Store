import React, { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import NAVBAR from "../componenets/navbar";
import axios from "axios";
import config from "../config/serverUrl";
import { toast } from "react-toastify";
import CartItemCard from "../componenets/CartItemCard";
import { useNavigate } from "react-router-dom";
import log from "../services/logService";

const ShoppingCart = () => 
{
  useEffect(() => {
    getCartItems();
  }, []);

  const [products,setProducts]=useState([]);

  const navigate = useNavigate();
  const [cartItems, setCartItems] = useState([]);
  const [total, setTotal] = useState(0);
  const [ productItems , setProductItems ] = useState([]);

  const getItems=(cartItems)=>{
    let products =[];
    let items = [];
    console.log("Cart items ::")
    console.log(cartItems);
    for(let i=0;i<cartItems.length;i++)
    {
      let product={};
      let item = cartItems[i].product;
      item.quantity = cartItems[i].quantity;
      product.productId=cartItems[i].product.id;
      product.quantity=cartItems[i].quantity;
      products.push(product);
      items.push(item);
    }
    console.log("products");
    console.log(products);
    setProducts(products);
    setProductItems(items);
  }
 

  const styles = {
    h2: {
      marginTop: "5%",
    },
    img: {
      width: "auto",
      height: "200px",
      borderRadius: "15px",
    },
    col: { marginTop: "10px", marginBottom: "10px" },
  };

  const calculateTotal = (items) => {
    console.log(items);
    let total = 0;
    for (let i = 0; i < items.length; i++) {
      let amount = items[i].product.price * items[i].quantity;
      total += amount;
    }
    setTotal(total);
  };
  const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};
  const getCartItems = () => {
    axios.get(config.serverUrl + "user/viewCart/" + sessionStorage["id"], {
       headers
      })
      .then((response) => {
        const result = response.data;
        console.log(result); 
        if (result.message === "success") {
          setCartItems(result.data);
          calculateTotal(result.data);
          getItems(result.data);
        } else {
          toast.error("Something went wrong :(");
        }
      })
      .catch((error) => {
        console.log("error");
        console.log(error);
      });
  };

  return (
    <div>
      <Container>
        <Row>
          <Col sm={12} md={8}>
            <h2 style={styles.h2}>Shopping Cart</h2>
            <hr />
            {cartItems.map((item) => {
              return <CartItemCard item={item} />;
            })}
          </Col>
          <Col sm={12} md={4}>
            <div style={{ marginTop: "10%" }}>
              <Card style={{ width: "18rem", borderRadius: "15px" }}>
                <Card.Body>
                  <Card.Title>
                    <h4>
                      <b>Total : </b>₹{total}
                    </h4>
                  </Card.Title>
                  {cartItems.length === 0 ? (
                    <Button
                      onClick={() => navigate("/checkout")}
                      className="mb-1"
                      style={{ width: "100%", color: "white" }}
                      variant="secondary"
                      disabled
                    >
                      Proceed To Checkout
                    </Button>
                  ) : (
                    <Button
                      onClick={() => navigate("/checkout",{state:{products:products, productItems : productItems}})}
                      className="mb-1"
                      style={{ width: "100%", color: "white" }}
                      variant="info"
                    >
                      Proceed To Checkout
                    </Button>
                  )}
                </Card.Body>
              </Card>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default ShoppingCart;
