import React, { useEffect, useState } from 'react'
import { Button,Table,Container } from 'react-bootstrap';
import validation from '../services/validation';
import log from '../services/logService';
import discountService from '../services/discountService';

export const AdminCoupons = () => {
    const[coupons,setCoupons]=useState([]);

    useEffect(()=>{
        showCoupons();
    },[])
    const showCoupons= async ()=>{
        try{
            const response = await discountService.fetchAllDiscountDetails();
            log(response);
            const result=response.data;
            setCoupons(result.data);
        }catch(ex){
            validation.error("something went wrong");
        }
    }
    const removeCoupon= async (id)=>{
      const response = await discountService.deleteDiscountDetails(id);
      log(response);
      validation.success("coupon removed ");
      window.location.reload(false);
    }
    
  return (
    <Container> 
    <div className='p-5'>  
  <Table striped bordered hover >  
  <thead>  
    <tr>  
      <th>#</th>  
      <th>Coupon Code</th>  
      <th>Created At</th>   
      <th>Percentage</th> 
      <th>Valid Date</th>
      <th>Action</th>
    </tr>  
  </thead>  
  <tbody>  
    {coupons.map((coupon)=>{
        return(
         <tr>  
         <td>{coupon.id}</td>  
         <td>{coupon.couponCode}</td>  
         <td>{coupon.createdAt}</td> 
         <td>{coupon.percentage}</td>
         <td>{coupon.validDate}</td>  
         <td>
            <Button 
            onClick={()=>removeCoupon(coupon.id)}
            className="btn btn-danger">
            remove
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
