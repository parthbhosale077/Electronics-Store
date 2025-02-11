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
   console.log("he");
  const [categories,setCategories]=useState([]);
  useEffect(()=>{
    loadCategories();
  },[])

  const loadCategories= async ()=>{
    try{
      const response= await categoryService.getCategories();
      console.log(response);
      const result=response.data;
      setCategories(result.data);
    }catch(ex){
      validation.error("something went wrong");
    }
      
  }

  const [name, setName] = useState('');
  const [price,setPrice]=useState();
  const [File,setFile]=useState([]);
  const [description,setDescription]=useState('');
  const [brand,setBrand]=useState('');
  const [category,setCategory]=useState('');
  const [image,setImage]=useState();

  const handleSubmit = async(event) => {
    console.log(name)
    const formData = new FormData();
    formData.append("name",name);
    formData.append("price",price);
    formData.append("image",image);
    formData.append("description",description);
    formData.append("brand",brand);
    formData.append("category",category);
    event.preventDefault();
    const head = {"Authorization":"Bearer " + sessionStorage.getItem("token")};
    axios.post(config.serverUrl+"admin/addProduct",
                formData,{headers:{"Content-Type":"multipart/form-data","Authorization":"Bearer " + sessionStorage.getItem("token")}})
                .then(response=>{console.log(response)})
                validation.success("Product Added!!!");
                
                
  };

  return (
    <div style={{marginTop: 20 + 'em'}} className="add-product-form mt-5">
      <h3>Add New Product</h3>
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

        <Form.Group controlId="price">
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            step="0.01"
            name="price"
            onChange={(event)=>{setPrice(event.target.value)}}
            required
          />
        </Form.Group>

        <Form.Group controlId="imageFile">
          <Form.Label>Image</Form.Label>
          <Form.Control
            type="file"
            name="imageFile"
            accept="image/*"
            onChange={(event)=>{ setFile(event.target.files)
                                 setImage(event.target.files[0])}}
          />
        </Form.Group>

        <Form.Group controlId="description">
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            name="description"
            onChange={(event)=>{setDescription(event.target.value)}}
            required
          />
        </Form.Group>

        <Form.Group controlId="brand">
          <Form.Label>Brand</Form.Label>
          <Form.Control
            type="text"
            name="brand"
            onChange={(event)=>{setBrand(event.target.value)}}
            required
          />
        </Form.Group>

        <Form.Group controlId="category">
          <Form.Label>Category</Form.Label>
          <Form.Control
            as="select"
            name="category"
            onChange={(event)=>{setCategory(event.target.value)}}
            required
          >
            <option value={0}>Select a Category</option>

           {categories.map((category)=>{
            return(
              <option id={category.id} value={category.id}>{category.name}</option>
            )
           })}
          </Form.Control>
        </Form.Group>

        <div className="button-group">
          <Button variant="primary" type="submit">
            Add Product
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
