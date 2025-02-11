import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import config from "../config/serverUrl";
import log from "../services/logService";

const Checkout = () => {
  useEffect(() => {
    getAddresses();
  }, []);

  const location = useLocation();
  const navigate = useNavigate();
  console.log(location);
  const { products, productItems } = location.state;
  console.log("product Items:: ")
  console.log( productItems);
  const userId = sessionStorage["id"];
  const [addressId, setAddressId] = useState();
  const [codStatus, setCodStatus] = useState(false);

  const [address, setAddress] = useState([]);

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

  const getAddresses = () => {
    axios
      .get(config.serverUrl+ "user/getAddress/" + sessionStorage["id"], {
        headers: {
        Authorization: `Bearer ${sessionStorage["jwt"]}`},
    
    })
      .then((response) => {
        const result = response.data;
        console.log(result);
        if (result.message === "success") {
          setAddress(result.data);
        } else {
          toast.error("Something went wrong :(");
        }
      })
      .catch((error) => {
        console.log("error");
        console.log(error);
      });
  };

  log(products);
  log(codStatus);
  log(userId);
  log(addressId);
  const checkout = () => {
    axios
      .post(config.serverUrl+ "user/checkout", {
        products:products,
        codStatus:codStatus,
        addressId:addressId,
        userId:userId
      },{
          headers: {
          Authorization: `Bearer ${sessionStorage["jwt"]}`},
      
      })
      .then((response) => {
        const result = response.data;
        if (result.message === "success") {
          log(result);
          let orderDetail = result.data;
          toast.success("Ordered Successfully");
          navigate("/invoice", {state : {orderDetail : orderDetail, productItems : productItems}});
        }
      });
  };



  return (
    <div>
      <Container>
        <Row>
          <Col sm={12} md={8}>
            <h2 style={styles.h2}>Select Delivery Address</h2>
            <hr />
            {address.map((add) => {
              return (
                <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                  <Card style={{ borderRadius: "15px" }}>
                    <Card.Body>
                      <Row>
                        <Col sm={1}>
                          <Form.Check
                            onChange={(e) => {
                              console.log(e.target.value);
                              setAddressId(e.target.value);
                            }}
                            value={add.id}
                            type="radio"
                            name="address"
                            aria-label="radio 1"
                          />
                        </Col>
                        <Col sm={11}>
                          <Card.Title></Card.Title>
                          <Card.Text>
                            <h5>{add.addressLine1}</h5>
                            <h5>{add.addressLine2}</h5>
                            <h5>
                              {add.city} - {add.pincode}
                            </h5>
                            <h5>
                              {add.state}, {add.country}
                            </h5>
                          </Card.Text>
                        </Col>
                      </Row>
                    </Card.Body>
                  </Card>
                </div>
              );
            })}
          </Col>
          <Col sm={12} md={4}>
            <div style={{ marginTop: "10%" }}>
              <h2 style={styles.h2}>Payment Method</h2>
              <hr />
              <Form.Check
                onChange={(e) => {
                  console.log(e.target.value);
                  setCodStatus(e.target.value);
                }}
                value={false}
                defaultChecked="yes"
                type="radio"
                name="payment"
                id={`default-radio`}
                label={`Online Payment`}
              />
              <Form.Check
                onChange={(e) => {
                  console.log(e.target.value);
                  setCodStatus(e.target.value);
                }}
                value={true}
                type="radio"
                name="payment"
                id={`default-radio`}
                label={`Cash On Delivery`}
              />
            </div>
          </Col>
        </Row>
        <Row>
          <Col>
            <Button
              onClick={checkout}
              className="mb-1"
              style={{ width: "100%", color: "white" }}
              variant="info"
            >
              Confirm Order
            </Button>
          </Col>
          <Col></Col>
          <Col></Col>
        </Row>
      </Container>


      

    </div>
  );
};

export default Checkout;
