import React, { useState } from "react";

import "../../node_modules/bootstrap/dist/css/bootstrap.min.css"
import { Container, Form, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { FaFacebook, FaGoogle } from "react-icons/fa";
import "../style/LoginPage.css";
import userService from "../services/userService";
import validation from "../services/validation";
import log from '../services/logService';
import { toast } from 'react-hot-toast'

function Signin() {
  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setLoginData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const navigate = useNavigate();

  const handleSubmit = async (event) => {

    event.preventDefault();
    try {
      const response = await userService.validateUserCreateToken(loginData);
      console.log(response.data)
      if (response.data.message === 'Auth successful!') {
        validation.success("welcome");
        const result = response.data;
        log(result)



        sessionStorage.setItem("token", result.jwt);
        sessionStorage.setItem("jwt", result.jwt);
        if (result.data.roles[0].roleName === 'USER') {

          sessionStorage.setItem("id", result.data.userId);
          navigate('/');
        }
        else
          navigate('/admin')
      }
      else {
        // toast.error("Something went wrong");
        validation.error("Invalid Credentials!");
      }
    }
    catch (ex) {
      validation.error("something went wrong");
    }

  };

  return (
    <Container className="login-container">
      <div className="login-box">
        <h2>Welcome To Electronic Store</h2>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="email">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              name="email"
              value={loginData.email}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              value={loginData.password}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Button variant="primary" type="submit" size="lg" className="mt-3">
            Log In
          </Button>

          <div className="login-icons">
            <Link to="#" className="login-icon">
              <FaFacebook size={30} />
            </Link>
            <Link to="#" className="login-icon">
              <FaGoogle size={30} />
            </Link>
          </div>
        </Form>
        <p className="no-account">
          Don't have an account? <Link to="/signup">Sign up</Link>
        </p>
      </div>
    </Container>
  );
}

export default Signin;

// import React, { useState } from "react";
// import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
// import { Container, Form, Button } from "react-bootstrap";
// import { Link, useNavigate } from "react-router-dom";
// import { FaFacebook, FaGoogle } from "react-icons/fa";
// import "../style/LoginPage.css";
// import userService from "../services/userService";
// import validation from "../services/validation";
// import log from "../services/logService";
// import { toast } from "react-hot-toast";
// import ReCAPTCHA from "react-google-recaptcha";

// function Signin() {
//   const [loginData, setLoginData] = useState({
//     email: "",
//     password: "",
//   });

//   const [captchaValue, setCaptchaValue] = useState(null);

//   const handleChange = (event) => {
//     const { name, value } = event.target;
//     setLoginData((prevData) => ({
//       ...prevData,
//       [name]: value,
//     }));
//   };

//   const handleCaptchaChange = (value) => {
//     setCaptchaValue(value);
//   };

//   const navigate = useNavigate();

//   const handleSubmit = async (event) => {
//     event.preventDefault();

//     if (!captchaValue) {
//       toast.error("Please verify the CAPTCHA!");
//       return;
//     }

//     try {
//       const response = await userService.validateUserCreateToken(loginData);
//       console.log(response.data);
//       if (response.data.message === "Auth successful!") {
//         validation.success("Welcome");
//         const result = response.data;
//         log(result);

//         sessionStorage.setItem("token", result.jwt);
//         sessionStorage.setItem("jwt", result.jwt);
//         if (result.data.roles[0].roleName === "USER") {
//           sessionStorage.setItem("id", result.data.userId);
//           navigate("/");
//         } else {
//           navigate("/admin");
//         }
//       } else {
//         validation.error("Invalid Credentials!");
//       }
//     } catch (ex) {
//       validation.error("Something went wrong");
//     }
//   };

//   return (
//     <Container className="login-container">
//       <div className="login-box">
//         <h2>Welcome To Electronic Store</h2>
//         <Form onSubmit={handleSubmit}>
//           <Form.Group controlId="email">
//             <Form.Label>Email</Form.Label>
//             <Form.Control
//               type="email"
//               name="email"
//               value={loginData.email}
//               onChange={handleChange}
//               required
//             />
//           </Form.Group>

//           <Form.Group controlId="password">
//             <Form.Label>Password</Form.Label>
//             <Form.Control
//               type="password"
//               name="password"
//               value={loginData.password}
//               onChange={handleChange}
//               required
//             />
//           </Form.Group>

//           <div className="mt-3">
//             <ReCAPTCHA
//               sitekey="YOUR_RECAPTCHA_SITE_KEY" // Replace with your actual site key
//               onChange={handleCaptchaChange}
//             />
//           </div>

//           <Button variant="primary" type="submit" size="lg" className="mt-3">
//             Log In
//           </Button>

//           <div className="login-icons">
//             <Link to="#" className="login-icon">
//               <FaFacebook size={30} />
//             </Link>
//             <Link to="#" className="login-icon">
//               <FaGoogle size={30} />
//             </Link>
//           </div>
//         </Form>
//         <p className="no-account">
//           Don't have an account? <Link to="/signup">Sign up</Link>
//         </p>
//       </div>
//     </Container>
//   );
// }

// export default Signin;
