import React from "react";
import { Link } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import "../style/AdminDashboard.css";

function AdminDashboard() 
{
  return (
    <Container>
      <Row className="admin-dashboard">
        <Col xs={12} className="text-center">
          <h2 className="admin-title">Welcome Admin!</h2>
        </Col>
        <Col xs={12} className="text-center">
          <div className="admin-options">
            <Row className="options-row">
              <Col xs={12} md={6} lg={3} className="admin-option">
                <Link to="/admin/add-product" className="admin-link">
                  Add Product
                </Link>
              </Col>
              <Col xs={12} md={6} lg={3} className="admin-option">
                <Link to="/admin/manage-discount" className="admin-link">
                  Manage Discounts
                </Link>
              </Col>
            </Row>
            <Row className="options-row">
              <Col xs={12} md={6} lg={3} className="admin-option">
                <Link to="/admin/view-inventory" className="admin-link">
                  View Inventory
                </Link>
              </Col>
              <Col xs={12} md={6} lg={3} className="admin-option">
                <Link to="/admin/view-transaction" className="admin-link">
                  View Transactions
                </Link>
              </Col>
            </Row>
          </div>
        </Col>
      </Row>
    </Container>
  );
}

export default AdminDashboard;
