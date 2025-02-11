import React from "react";
import { Button, Card, Col, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const OrderHistoryCard = (props) => {
  const { id, status, codStatus, statusUpdatedAt, createdAt, address } = props;
  const text = codStatus ? "Cash On Delivery" : "Paid Online";

  //console.log(address)

  const orderState = {
    id,
    status,
    codStatus,
    statusUpdatedAt,
    createdAt,
    address,
  };
  const navigate = useNavigate();

  let imgSrc;

  switch (status) {
    case "ORDERED":
      imgSrc = "/images/ordered.avif";
      break;

    case "INTRANSIT":
      imgSrc = "/images/intransit.avif";
      break;

    case "DELIVERED":
      imgSrc = "/images/delivered.avif"; //deliveredSrc
      break;

    case "CANCELLED":
      imgSrc = "/images/cancelled.avif";
      break;
    default:
      break;
  }

  const styles = {
    h2: {
      marginTop: "5%",
    },
    img: {
      width: "150px",
      height: "200px",
      borderRadius: "15px",
    },
    col: { marginTop: "10px", marginBottom: "10px" },
  };

  return (
    <Card style={{ borderRadius: "15px" }}>
      <Card.Body>
        <Row>
          <Col style={{ textAlign: "center" }} md={4}>
            <img src={imgSrc} style={styles.img} alt="product-status" />
          </Col>
          <Col md={1}></Col>
          <Col md={7} style={{}}>
            <Card.Title>
              <h4>
                <b>{status}</b>
              </h4>
            </Card.Title>
            <Card.Text>on {statusUpdatedAt}</Card.Text>
            <Card.Text>
              Order Id: {id}
              <br />
              Payment : {text}
            </Card.Text>
            <Button
              className="mb-1"
              style={{ width: "100%", color: "white" }}
              variant="info"
              onClick={() => {
                navigate("/viewOrderDetails", {
                  state: { orderState },
                });
                /*
const createdAt = '2022-05-05'
  const id = 2
  const orderTotal = 550
  const codStatus = false
  const paymentText = codStatus ? 'Cash On Delivery' : 'Paid Online'
  const status = 'ORDERED'
  */
              }}
            >
              View Order Details
            </Button>
          </Col>
        </Row>
      </Card.Body>
    </Card>
  );
};

export default OrderHistoryCard;
