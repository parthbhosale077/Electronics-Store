import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";
const BasicCard=(props)=>{
    return(
        <Card style={{ 
            width: '20rem',
            position:'relative',
            boxShadow:'0 4px 8px rgba(0, 0, 0, 0.5)',
            backgroundColor:'#C8E4B2' }}>
            <Card.Body>
                <Card.Text  style={{textAlign:'center'}}>
                    <h1>
                        <Link to={`/category/${props.message}`}
                     style={{
                      textDecoration: "none",
                      color: "inherit",
                      cursor: "pointer",
                    }}>
                        {props.message}
                        </Link></h1>
                </Card.Text>
            </Card.Body>
        </Card>
    )
}
export default BasicCard;