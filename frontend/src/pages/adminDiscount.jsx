import { useState } from "react";
import { Button, Row, Col, Container, Form } from "react-bootstrap";
import discountService from "../services/discountService";
import log from "../services/logService";
import validation from "../services/validation";
import { useNavigate } from "react-router-dom";

const AdminDiscount = () => {
    const [couponCode, setCouponCode] = useState('');
    const [percentage, setPercentage] = useState('');
    const [validDate, setValidDate] = useState('');

    const navigate = useNavigate();

    //id	coupon_code	status 	valid_date	created_at	percentage
    const addDiscount = async () => {
        const discountDetails = {
            couponCode: couponCode,
            percentage: percentage,
            validDate: validDate,
            status: 'true'
        }
        try {
            const response = await discountService.addDiscount(discountDetails);
            log(response);
            validation.success("coupon created");
        } catch (ex) {
            validation.error("discount coupon not created");
        }

    }

    const showCoupons = () => {
        navigate('/AdminCoupons');
    }
    return (
        <Container>
            <h1> Manage Discounts</h1>
            <Row className="mb-5">
                <Col>
                    <Button onClick={() => showCoupons()} className="btn btn primary">
                        View Exsisting Coupons
                    </Button>
                </Col>
            </Row>
            <Row className="mb-5">
                <Form.Group className="mb-3">
                    <Form.Label>Discount Percentage</Form.Label>
                    <Form.Control
                        onChange={(event) => { setPercentage(event.target.value) }}
                        type="number"
                        placeholder="%"
                        name="percentage"
                        required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Discount Code</Form.Label>
                    <Form.Control
                        onChange={(event) => { setCouponCode(event.target.value) }}
                        type="text"
                        placeholder="Coupon"
                        name="couponCode"
                        required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Valid Till</Form.Label>
                    <Form.Control
                        onChange={(event) => { setValidDate(event.target.value) }}
                        type="date"
                        placeholder="YYYY-MM-DD"
                        name="validDate"
                        required />
                </Form.Group>
                <Button onClick={() => addDiscount()} className='btn btn-primary'> Add New Coupon</Button>
            </Row>
        </Container>
    )
}
export default AdminDiscount;