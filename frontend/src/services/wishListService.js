import httpClient from '../http-common'

const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};

const addToWishlist=(WishListDto)=>{
    return httpClient.post('user/addToWishlist',WishListDto, {headers});   
};  

const viewWishlist=(userId)=>{
    return httpClient.get(`user/viewWishlist/${userId}`, {headers});
}

// check if wishlist spelling is right in UserController
const removeFromWishList=(userId,productId)=>{
    return httpClient.delete(`user/wishlist/remove/${userId}/${productId}`, {headers});
};


export default { addToWishlist,viewWishlist,removeFromWishList };