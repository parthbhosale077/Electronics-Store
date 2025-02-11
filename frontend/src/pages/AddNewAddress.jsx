import axios from "axios";
import React, { useState, useEffect } from "react";
import { Form, Row, Col, Container, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import config from "../config/serverUrl";

const AddNewAddresses = () => {
  const [addressLine1, setaddressLine1] = useState("");
  const [addressLine2, setaddressLine2] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");
  const [pincode, setPincode] = useState("");

  const navigate = useNavigate();

  const [addresses, setAddresses] = useState([]);

  const addNewAddress = () => {
    if (addressLine1.length === 0) {
      toast.error("Please enter addressLine1!");
    } else if (addressLine2.length === 0) {
      toast.error("Please enter addressLine2!");
    } else if (city.length === 0) {
      toast.error("Please enter city!");
    } else if (state.length === 0) {
      toast.error("Please enter state");
    } else if (country.length === 0) {
      toast.error("Please enter country");
    } else if (pincode.length === 0) {
      toast.error("Please enter pincode");
    } else {
      axios
        .post(
          config.serverUrl+ "user/addNewAddress",
          {
            addressLine1: addressLine1,
            addressLine2: addressLine2,
            city: city,
            state: state,
            country: country,
            pincode: pincode,
            userId: sessionStorage["id"],
          }
        )
        .then((response) => {
          const result = response.data;

          if (result.message === "success") {
            toast.success("Address added successfully :)");
            navigate("/home");
          } else {
            toast.error("Something went wrong :( Please try again.");
            console.log(result.error);
          }
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
    }
  };

  return (
    <>
      <Container>
        <h3 style={{ marginTop: "20px" }}>Add New Address</h3>
        <hr></hr>
        <Row>
          <Col md={4}>
            <Form.Group className="mb-3" controlId="formBasicFname">
              <Form.Label>AddressLine1</Form.Label>
              <Form.Control
                type="text"
                onChange={(event) => {
                  setaddressLine1(event.target.value);
                }}
              />
            </Form.Group>
          </Col>
          <Col md={4}>
            <Form.Group className="mb-3" controlId="formBasicFname">
              <Form.Label>AddressLine2</Form.Label>
              <Form.Control
                type="text"
                onChange={(event) => {
                  setaddressLine2(event.target.value);
                }}
              />
            </Form.Group>
          </Col>
          <Col md={4}>
            <Form.Group className="mb-3" controlId="formBasicFname">
              <Form.Label>City</Form.Label>
              <Form.Control
                type="text"
                onChange={(event) => {
                  setCity(event.target.value);
                }}
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col md={4}>
            <Form.Group className="mb-3" controlId="formBasicGender">
              <Form.Label>State</Form.Label>
              <Form.Select
                onChange={(event) => {
                  setState(event.target.value);
                }}
                aria-label="Default select example"
              >
                <option></option>
                <option value="Maharashtra">Maharashtra</option>
                <option value="Andhra Pradesh">Andhra Pradesh</option>
                <option value="Tamilnadu">Tamilnadu</option>
                <option value="West Bengal">West Bengal</option>
                <option value="Chandigarh">Chandigarh</option>
                <option value="Rajasthan">Rajasthan</option>
                <option value="Chattisgarh">Chattisgarh</option>
                <option value="Uttarakhand">Uttarakhand</option>
                <option value="Kerala">Kerala</option>
                <option value="Uttar Pradesh">Uttar Pradesh</option>
              </Form.Select>
            </Form.Group>
          </Col>

          <Col md={4}>
            <Form.Group className="mb-3" controlId="formBasicFname">
              <Form.Label>Country</Form.Label>

              <Form.Select
                onChange={(event) => {
                  setCountry(event.target.value);
                }}
                aria-label="Default select example"
              >
                <option></option>
                <option value="India">India</option>
                <option value="Canada">Canada</option>
                <option value="England">England</option>
                <option value="Germany">Germany</option>
                <option value="Switzerland">Switzerland</option>
                <option value="Turkey">Turkey</option>
                <option value="Australia">Australia</option>
                <option value="Thailand">Thailand</option>
                <option value="France">France</option>
                <option value="Norway">Norway</option>
                <option value="South Korea">South Korea</option>
                <option value="Iceland">Iceland</option>
                <option value="Bolivia">Bolivia</option>
              </Form.Select>
            </Form.Group>
          </Col>

          <Col md={4}>
            <Form.Group className="mb-3" controlId="formBasicFname">
              <Form.Label>Pincode</Form.Label>
              <Form.Control
                type="text"
                onChange={(event) => {
                  setPincode(event.target.value);
                }}
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col>
            <Button
              onClick={addNewAddress}
              className="mb-3"
              style={{ color: "white" }}
              variant="info"
            >
              Save
            </Button>
          </Col>
          <Col></Col>
          <Col></Col>
        </Row>
      </Container>
    </>
  );
};

export default AddNewAddresses;
