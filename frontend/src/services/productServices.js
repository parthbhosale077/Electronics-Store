import httpClient from '../http-common'

const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};

const getProductByCategory=(productCategory)=>{
    return httpClient.get(`home/view/${productCategory}`);
};

const getProductByName=(productName)=>{
    return httpClient.get(`home/search/${productName}`);
};

const getProductByBrand=(productBrand)=>{
    return httpClient.get(`home/search/${productBrand}`);
};

const getReviews=(productId)=>{
    return httpClient.get(`home/getReviews/${productId}`);
};
// download image or getting images
const downloadImage=(productId)=>{
    return httpClient.get(`home/${productId}/images`);
};

const showAllProducts=()=>{
    return httpClient.get('admin/viewAll');
};

const deleteProduct=(productId)=>{
    return httpClient.delete(`admin/deleteProduct/${productId}`,{ headers});
};

// need to change this to productDto
const addProductDetails= async (product)=>{
    return await httpClient.post('admin/addProduct',product,{ headers});
}

const removeProduct=(productId)=>{
    console.log(headers);
    return httpClient.put(`admin/removeProduct/${productId}`,{headers});
};

const getProductDetailsById=(productId)=>{
    return httpClient.get(`admin/getProductDetails/${productId}`,{ headers});
};
const getProductbyid=(productId)=>
{
    return httpClient.get(`home/getProductDetails/${productId}`);
};
//we are sending productDto so map the attributes with productDto from com.app.dto
const editProductDetails=(productId,ProductDto)=>{
    return httpClient.put(`admin/editProduct/${productId}`,ProductDto,{ headers});
};

const getAllRemovedProducts=()=>{
    return httpClient.get('admin/getRemovedProducts',{ headers});
};

const makeProductAvailable=(productId)=>{
    return httpClient.put(`admin/makeProductAvailable/${productId}`,{ headers});
};

const incrementProductQuantity=(cartId,quantity)=>{
    return httpClient.put(`user/cart/incrementProductQuantity/${cartId}/${quantity}`,null,{ headers});
};

const addProductReview=(ReviewDto)=>{
    return httpClient.post('user/addProductReview',ReviewDto,{ headers});
};

const getProductByRating=()=>{
    return httpClient.get('home/getProduct/rating',{ headers});
};

export default { showAllProducts,deleteProduct,
                 getProductByBrand,getProductByCategory
                ,getProductByName,getReviews,downloadImage,
                 addProductDetails,removeProduct,getProductDetailsById, 
                 editProductDetails,getAllRemovedProducts,makeProductAvailable,
                 incrementProductQuantity,addProductReview,getProductByRating,getProductbyid };