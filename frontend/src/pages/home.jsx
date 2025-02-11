 import { Container,Row,Col } from "react-bootstrap";
 import HomeCarousel from "../componenets/homecarousel";
import ProductCarousel from '../componenets/trendingproduct';
 import Footer from "../componenets/footer";
import categoryService from "../services/categoryService";
 import { useEffect, useState } from "react";
 import productServices from "../services/productServices";
 import BasicCard from "../componenets/card";

const Home=()=>{

    const [categoires,setCategories]=useState([]);
    const [Products,setTopProducts]=useState([]);

    useEffect(()=>{
        getCategory();
        getProducts();
    },[])

    const getCategory= async ()=>{
    //    debugger
     const response=  await categoryService.getCategories();
      setCategories(response.data.data);
    }

    const getProducts=async()=>{
       const response=await productServices.showAllProducts();
       setTopProducts(response.data.data);
       console.log(response);
    }

    const Style={
        message:{
            textAlign :'center',
            fontWeight:'bold',
            fontSize: 30
        },
        align:{
            padding:20,
            height: 600
        },
        card:{
            display:'inline-block',
            margin:15,
            position:'relative'
        },
    }

    return(
       <Row key="0">
           <Row key="1">
                <Col style={Style.align}>
                     <HomeCarousel/>
                 </Col>
           </Row>
           _<div style={{textAlign:'center'}}>____________
                 <h1 style={{
                    fontFamily:'sans-serif',
                    color:'grey'}} >
                        PRODUCTS!</h1>
            </div>_
           <Row key="2">
                <Col>
                  <ProductCarousel products={Products}/>
                </Col>
           </Row>
           _<div style={{textAlign:'center'}}>____________
                 <h1 style={{
                    fontFamily:'sans-serif',
                    color:'grey'}} >
                        CATEGORY</h1>
            </div>
            <Row key="3">
            <Container>
            {categoires.map((category)=>{
                return(
                    <div style={Style.card}>
                    <BasicCard message={category.name}/>
                    </div>
                )
            })} 
            </Container>
           </Row>           
       </Row>
    )
}

export default Home;