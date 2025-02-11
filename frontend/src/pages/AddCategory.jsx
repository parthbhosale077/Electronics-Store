import React, { useEffect, useState } from "react";
import { Form, Button } from "react-bootstrap";
import "../style/AddNewProduct.css"
import productServices from "../services/productServices";
import axios from "axios";
import config from "../config/serverUrl";
import categoryService from "../services/categoryService";
import validation from "../services/validation"
function AddProduct() 
{

  useEffect(()=>{

  },[])

  const [name, setName] = useState('');

  const handleSubmit = async(event) => {
    console.log(name)
    const formData = new FormData();
    formData.append("name",name);
    event.preventDefault();
    
    // axios.post(config.serverUrl+"admin/addCategory",
    //             formData,{headers:{"Content-Type":"multipart/form-data"}})
    //             .then(response=>{console.log(response)})
    //             validation.success("Category Added!!!");


    try{
      const response= await categoryService.addCategory(formData);
      console.log(response);
      const result=response.data;
      validation.success("Category Added!!!");
    }catch(ex){
      validation.error("something went wrong");
    }
                
                
  };

  return (
    <div className="add-product-form">
      <h3>Add New Category</h3>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="name">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            name="name"
            onChange={(event)=>{setName(event.target.value)}}
            required
          />
        </Form.Group>

        <div className="button-group">
          <Button variant="primary" type="submit">
            Add Category
          </Button>
          <Button
            variant="primary"
            type="reset"
            className="btn btn-danger"
          >
            Reset Fields
          </Button>
        </div>
      </Form>
    </div>
  );
}

export default AddProduct;
