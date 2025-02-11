import { useEffect, useState } from "react";
import { Button, Col, Row ,Image, Container} from "react-bootstrap";
import productService from '../services/productServices';
import cartService from '../services/cartService';
import ProductCarousel from "../componenets/trendingproduct";
import { Await, useNavigate, useParams,useSearchParams } from "react-router-dom";
import validation from "../services/validation"
import config from '../config/serverUrl';
import Productcard from "../componenets/ProductCard"
import buttonstyle from "../style/Button.css"



const Product=()=>{

    const [parameter,setparm] = useSearchParams();
    var a=parseInt(parameter.get("id"));
    const [product,setProduct]=useState([]);
    const navigate=useNavigate();
    useEffect( ()=>{
       
        console.log("useeffect");
        getproductbyid();
       
       

    },[])

   const getproductbyid=  async ()=>{
    console.log("in method")
 
        try{
            const response=  await productService.getProductbyid(a);
            
           
           console.log(response.data.data);
           
            setProduct(response.data.data);
            console.log("product "+product);
          }catch(ex)
          {
            validation.error("something went wrong");
          }

    }
    const addReview=()=>{
        
      navigate("/review/"+product.id);
    }
    const showReview=()=>{
        navigate("/showReview/"+product.id);
    }

    const Style={
        image:{
            height: 500,
            width: 400,
            borderWidth: '2px',
            borderColor:'lightgrey',
            borderStyle: 'solid'
        },
        borderClass:{
            borderWidth: '2px',
            borderColor:'lightgrey',
            borderStyle: 'solid'
        }
    }

    const addToCart = async (product)=>
    {
        console.log(product);
        const cartDTO = {
            quantity : 1,
            userId : sessionStorage.getItem("id"),
            productId : product.id
        }
        const response =  await cartService.addToCart(cartDTO);
    
        validation.success(response.data.message);
        
    }

return(
    
    //  <Productcard product ={product}/>
  
    <Row>
        <Row style={Style.borderClass}>
            <Col style={Style.borderClass}>
            <Container>
            <Image style={Style.image} src={config.serverUrl+'home/'+product.id+'/images'}></Image>
            </Container>
            {/* <ProductCarousel  product={product}/> */}
           
            </Col>
            <Col style={Style.borderClass}>
            <Row>
                <Col className="mb-5"><h1><strong>Product details</strong></h1></Col>
            </Row>
            <Row><Col style={Style.borderClass} className="mb-5"><h1><strong>Product Name :</strong> {product.name}</h1></Col></Row>
            <Row><Col style={Style.borderClass} className="mb-5"><h1><strong>Price :</strong> {product.price}</h1></Col></Row>
            <Row><Col style={Style.borderClass} className="mb-5"><h3><strong>Product description :</strong> {product.description}</h3></Col></Row>
            <Row><Col><Button style={{width:150,borderRadius:"18px"}} className="btn btn-dark" onClick={()=>addToCart(product)}>add to cart</Button></Col>
            <Col><Button className="btn btn-success"  style={{width:150,borderRadius:"18px"}} onClick={()=>addReview(product)}> Add Review</Button></Col><Col><Button className="btn btn-primary" style={{width:150,borderRadius:"18px"}} onClick={()=>showReview(product)} >Show Reviews</Button></Col>
            </Row>
            </Col>
        </Row>
    </Row>
   
   
)
}

export default Product;