import React, { useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom'
import productServices from '../services/productServices';
import validation from '../services/validation';
import { Button,Table,Container } from 'react-bootstrap';
import log from '../services/logService';

export const AdminCategoryProduct = () => {
    const location=useLocation()
    const { productCategory }=location.state;
    const [products,setProducts]=useState([]);

    useEffect(()=>{
        getProducts();
    },[])

    const getProducts= async ()=>{
        try{
            log(productCategory);
            const response = 
                await productServices.getProductByCategory(productCategory);
            const result=response.data;
            log(response.data);
            setProducts(result.data);
        }catch(ex){
            validation.error("something went wrong");
        }
    }

    const removeProduct = async (id)=>{
        try{
            const response= await productServices.removeProduct(id); // only changes status from 0 to 1  make it unavailable for  displaying  
           //const response= await productServices.deleteProduct(id);  //permently deleted from database
            validation.success("product removed successfully");
            window.location.reload(false);
        }catch(ex){
            validation.error("something went wrong")
            console.log(ex);
        }
    }

  return (
    <Container> 
    <div className='p-5'>  
  <Table striped bordered hover >  
  <thead>  
    <tr>  
      <th>#</th>  
      <th> Name</th>  
      <th> Price</th>
      <th> Brand</th>   
    </tr>  
  </thead>  
  <tbody>  
    {products.map((product)=>{
        return(
         <tr>  
         <td>{product.id}</td>  
         <td>{product.name}</td>
         <td>₹{product.price}</td>   
         <td>{product.brand}</td>
         <td>
            <Button 
            onClick={()=>removeProduct(product.id)} 
            className="btn btn-danger">
            Remove
            </Button>
        </td>   
       </tr>  
        )})}    
  </tbody>  
</Table>  
</div>   
    </Container>
  )
}
