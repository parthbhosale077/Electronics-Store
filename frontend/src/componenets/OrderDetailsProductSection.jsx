import { Row, Col } from "react-bootstrap";
import { useEffect, useState } from "react";
import axios from "axios";
import ReviewModal from "./ReviewModal";
import { Link } from "react-router-dom";
import config from "../config/serverUrl";

const OrderDetailsProduct = (props) => {
  const { ProductDetails, reviewBtnVariant, reviewBtnStatus } = props;

  useEffect(() => {
    getImage();
  }, []);

  const [image, setImage] = useState();

  const getImage = () => {    axios
    .get(config.serverUrl+`home/${ProductDetails.product.id}/images`, {responseType: 'blob',
    }).then((response) => {
      //console.log(response.data)
      const imageBlob = response.data   
      const reader = new FileReader()
      reader.readAsDataURL(imageBlob)        
      reader.onloadend = () => {
      const base64data = reader.result          
      setImage(base64data)
      setImage(response.data);        }
    })  };

  return (
    <Row md={10} key={ProductDetails.product.id}>
      <Col md={4}>
        <img
          src={image}
          alt="product-image"
          style={{
            width: "250px",
            height: "320px",
            padding: "10%",
            borderRadius: "60px",
          }}
        />
      </Col>
      <Col md={4}>
        <br />
        <br />
        <Row>
          <Link
            style={{ color: "black", textDecoration: "none" }}
            to="/product-details"
            state={{ details: ProductDetails.product, image: image }}
          >
            <h4>{ProductDetails.product.name}</h4>
          </Link>
        </Row>
        <br />
        <Row>
          <h5>
            <b>{ProductDetails.product.price}</b> Rs
          </h5>
        </Row>
        <br />
        <Row>
          <h5>Qty : {ProductDetails.quantity}</h5>
        </Row>
        <Row>
          <ReviewModal
            disabled={!reviewBtnStatus}
            variant={reviewBtnVariant}
            productId={ProductDetails.product.id}
          />
        </Row>
      </Col>
    </Row>
  );
};

export default OrderDetailsProduct;
