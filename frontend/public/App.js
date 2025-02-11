import './App.css';
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import Home from './pages/home';
import NAVBAR from './componenets/navbar';
import Review from './pages/review';
import Product from './pages/product';
import SignupPage from './pages/signup';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.min.css';  
import LoginPage from './pages/login';
import AdminCategory from './pages/adminCategory';
import AdminDiscount from './pages/adminDiscount';
import AdminDashboard from './pages/AdminDashboard';
import AddProduct from './pages/AddNewProduct';
import CategoryPage from './pages/CategoryPage';
import ShoppingCart from './pages/ShoppingCartPage';
import { AdminCategoryProduct } from './pages/AdminCategoryProduct';
import { AdminCoupons } from './pages/AdminCoupons';
import { Container } from 'react-bootstrap';
import Footer from './componenets/footer';
import ViewOrderHistory from './pages/viewOrderHistory';
import ViewOrderDetails from './pages/viewOrderDetails';
import Checkout from './pages/Checkout';
import AddNewAddresses from './pages/AddNewAddress';

function App() {
  return (
    <BrowserRouter>
    <div style={{backgroundColor:'#F3FDE8'}}>
      <NAVBAR/>
      <div>
        <Routes>
          <Route path='/' element={<Home/>}/>
          {/* <Route path='/signin' element={<LoginPage/>}></Route> */}
          {/* <Route path='/signup' element={<SignupPage/>}></Route> */}
          {/* <Route path='/review' element={<Review/>}></Route> */}
          {/* <Route path='/product' element={<Product/>}></Route> */}
          {/* <Route path='/admin/category' element={<AdminCategory/>}></Route> */}
          {/* <Route path='/admin/discount' element={<AdminDiscount/>}></Route> */}
          {/* <Route path='/admin/add-product' element={<AddProduct/>}></Route> */}
          {/* <Route path='/admin' element={<AdminDashboard/>}></Route> */}
          {/* <Route path='/category/:categoryName' element={<CategoryPage/>}></Route> */}
          {/* <Route path='/cart' element={<ShoppingCart/>}></Route> */}
          {/* <Route path='/admin/view-inventory' element={<AdminCategory/>}></Route> */}
          {/* <Route path='/admin/manage-discount' element={<AdminDiscount/>}></Route> */}
          {/* <Route path='/adminShowProducts' element={<AdminCategoryProduct/>}></Route> */}
          {/* <Route path='/AdminCoupons' element={<AdminCoupons/>}></Route> */}
          {/* <Route path='/viewOrderHistory' element={<ViewOrderHistory/>}></Route> */}
          {/* <Route path='/viewOrderDetails' element={<ViewOrderDetails/>}></Route> */}
          {/* <Route path='checkout' element={<Checkout/>}></Route> */}
          {/* <Route path='addAddress' element={<AddNewAddresses/>}></Route> */}
        </Routes>
      </div>
      {/* <ToastContainer/> */}
      {/* <Footer/> */}
      </div>
    </BrowserRouter>

  );
}

export default App;
