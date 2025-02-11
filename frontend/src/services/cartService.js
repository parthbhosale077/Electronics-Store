import httpClient from '../http-common'

const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};

const addToCart=  (CartDto)=>{
    return httpClient.post('user/addToCart',CartDto, {headers});
};

const viewCart= async (userId)=>{
    return await httpClient.get(`user/viewCart/${userId}`);
};

const deleteProductFromCart= (cartId)=>{
    return httpClient.delete(`user/deleteProductFromCart/${cartId}`, {headers});
}; 

const checkout=(CheckoutDto)=>{
    return httpClient.post('user/checkout',CheckoutDto);
};

export default { addToCart,viewCart,deleteProductFromCart,checkout };
