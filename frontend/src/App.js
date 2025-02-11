
import './App.css';
import { BrowserRouter,Route,Routes } from 'react-router-dom';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"

import Footer from "./componenets/footer"
import NAVBAR  from "./componenets/navbar"
 import { Toaster } from 'react-hot-toast';


import Home from './pages/home';
import LoginPage from "./pages/signin"
import SignupPage from "./pages/signup.jsx"
import AdminDashboard from './pages/AdminDashboard.jsx';
import AddProduct from './pages/AddNewProduct.jsx';
import AdminCategory from './pages/adminCategory.jsx';
import AddCategory from './pages/AddCategory.jsx';
import AdminDiscount from './pages/adminDiscount.jsx';
import { AdminCoupons } from './pages/AdminCoupons.jsx';
import { AdminCategoryProduct } from './pages/AdminCategoryProduct.jsx';
import AddNewAddresses from './pages/AddNewAddress.jsx';
import Product from './pages/product.jsx';
import CategoryPage from './componenets/CategoryPage.jsx';
import ProductCard from './componenets/ProductCard.jsx';
import ShoppingCart from './pages/ShoppingCartPage.jsx';
import Review from './pages/review.jsx';
import Showreview from './pages/showreview.jsx';
import Checkout from './pages/Checkout.jsx';
import Wishlist from './pages/WishlistPage.jsx';
import Invoice from './pages/Invoice.jsx';


function App() {
  return (
    <div >

<BrowserRouter>
    <div><Toaster/></div>
    <div style={{backgroundColor:'#F3FDE8'}}>
      <NAVBAR/>
      <div>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/signin' element={<LoginPage/>}></Route>
          <Route path='/signup' element={<SignupPage/>}></Route>
          <Route path='/admin' element={<AdminDashboard/>}></Route>
          <Route path='/admin/add-product' element={<AddProduct/>}></Route>
          <Route path='/admin/view-inventory' element={<AdminCategory/>}></Route>
          <Route path='/admin/category' element={<AdminCategory/>}></Route>
          <Route path='/admin/add-category' element={<AddCategory/>}></Route>
          <Route path='/admin/manage-discount' element={<AdminDiscount/>}></Route>
          <Route path='/admin/discount' element={<AdminDiscount/>}></Route>
          <Route path='/AdminCoupons' element={<AdminCoupons/>}></Route>
          <Route path='/adminShowProducts' element={<AdminCategoryProduct/>}></Route>
          <Route path='addAddress' element={<AddNewAddresses/>}></Route>
          <Route path='/product' element={<Product/>}></Route>
          <Route path='/category/:categoryName' element={<CategoryPage/>}></Route>
           <Route path='/cart' element={<ShoppingCart/>}></Route> 
           <Route path='/review/:u_id' element={<Review/>}></Route>
           <Route path='/showReview/:u_id' element={<Showreview/>}></Route>
           <Route path='/checkout' element={<Checkout/>}></Route>
           <Route path='/wishlist' element={<Wishlist/>}></Route>
           <Route path='/invoice' element={<Invoice/>}></Route>
          {/*
         
          
          <Route path='/viewOrderHistory' element={<ViewOrderHistory/>}></Route>
          <Route path='/viewOrderDetails' element={<ViewOrderDetails/>}></Route>
         
           */}
        </Routes>
      </div>
      {/* <ToastContainer/> */}
      <Footer/>
      </div>
    </BrowserRouter>
      
    </div>
  );
}

export default App;
