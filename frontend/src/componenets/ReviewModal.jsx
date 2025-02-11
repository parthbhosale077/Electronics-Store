import { Rating } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { toast } from "react-toastify";
import config from "../config/serverUrl";


const ReviewModal = (props) => {
  const { productId } = props;
  const [show, setShow] = useState(false);
  const [value, setValue] = useState(1);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const addReview = () => {
    console.log("adding product review for product " + productId);
    console.log(title.length);
    console.log(title);
    if (title.length === 0) {
      toast.error("Please enter title");
    } else if (description.length === 0) {
      toast.error("Please enter description");
    } else {
      axios
        .post(
          config.serverUrl + "/user/addProductReview",
          {
            title,
            description,
            rating: value,
            user: sessionStorage["userId"],
            product: productId,
          },
          {
            headers: {
              Authorization: `Bearer ${sessionStorage["jwt"]}`,
            },
          }
        )
        .then(() => {
          setTitle("");
          setDescription("");
          handleClose();
          toast.success("Thanks for your valuable review :)");
        })
        .catch((error) => {
          console.error(error);
          toast.error("An error occurred while adding the review.");
        });
    }
  };

  const { disabled, variant } = props;

  return (
    <div>
      <Button
        style={{ color: "white" }}
        variant={variant}
        onClick={handleShow}
        disabled={disabled}
      >
        Review This Product
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add product review</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h5>Give Overall Rating</h5>
          <Rating
            style={{ color: "#0091E6" }}
            name="simple-controlled"
            value={value}
            onChange={(event, newValue) => {
              console.log(newValue);
              setValue(newValue);
            }}
          />
          <Form.Group className="mb-3" controlId="formBasicFname">
            <Form.Label>Title*</Form.Label>
            <Form.Control
              type="text"
              onChange={(event) => {
                console.log(event.target.value);
                setTitle(event.target.value);
              }}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
            <Form.Label>Description*</Form.Label>
            <Form.Control
              style={{ resize: "none" }}
              as="textarea"
              maxLength={1000}
              rows={5}
              onChange={(event) => {
                console.log(event.target.value);
                setDescription(event.target.value);
              }}
            />
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button
            style={{ color: "white" }}
            variant="info"
            onClick={() => {
              addReview();
            }}
          >
            Add Review
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default ReviewModal;
