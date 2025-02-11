import { Carousel,Image,Container } from "react-bootstrap";
const Style={
  height:500,
  width:'100%'
}
const HomeCarousel=()=>{
    return(
      <Container style={{boxShadow:'0 4px 8px rgba(0, 0, 0, 0.5)'}} fluid>
        <Carousel>
      <Carousel.Item interval={5000}>
        <Image style={Style} src="carouselimage1.jpg"/>
      </Carousel.Item>
      <Carousel.Item interval={5000}>
        <Image style={Style}  src="carouselimage2.webp" />
      </Carousel.Item>
      <Carousel.Item interval={5000}>
        <Image style={Style}  src="carouselimage3.avif" />
      </Carousel.Item>
    </Carousel>
    </Container>
    )
}

export default HomeCarousel;