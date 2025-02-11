import { useEffect, useState } from "react"
import categoryService from "../services/categoryService"
import validation from "../services/validation"
import { Container, Table,Row,Col,Button } from "react-bootstrap";
import log from "../services/logService";
import { useNavigate } from "react-router-dom";
const AdminCategory=()=>{
    const [categories,setCategories]=useState([]);
    const navigate=useNavigate();
    useEffect(()=>{
        getAllCategory();
    },[])

    const getAllCategory= async ()=>{
        try{
            const response= await categoryService.getCategories();
            if(response.data.message==='success')
            {
                setCategories(response.data.data);
                console.log(response.data.data)
            }
        }catch(ex)
        {
            validation.error("something went wrong");
        }
        
    }

    const showProducts= async (category)=>{
        navigate('/adminShowProducts',{state:{productCategory:category}});
    }

    const addCategory = ()=>{
        navigate("/admin/add-category");
    }

return(
    <Container> 
    <div className='p-5'>  
  <Table striped bordered hover >  
  <thead>  
    <tr>  
      <th>#</th>  
      <th>First Name</th>  
      <th>Last Name</th>   
    </tr>  
  </thead>  
  <tbody>  
    {categories.map((category)=>{
        return(
         <tr>  
         <td>{category.id}</td>  
         <td>{category.name}</td>  
         <td>
            <Button 
            onClick={()=>showProducts(category.name)} 
            className="btn btn primary">
            view
            </Button>
        </td>   
       </tr>  
        )})}    
  </tbody>  
</Table>  
<Button 
            onClick={()=>addCategory()} 
            className="btn btn primary">
            Add Category
            </Button>
</div>   
    </Container>
)
}

export default AdminCategory