import httpClient from '../http-common'
// discount details is an entity so map its body according to its attribute
const headers = { "Authorization": "Bearer " + sessionStorage.getItem("token") };
const addDiscount = async (DiscountDetails) => {

    return await httpClient.post('admin/addDiscount', DiscountDetails, { headers });
};

//check line number 93 for admin controller getmapping for view to return admin page

const fetchAllDiscountDetails = () => {
    return httpClient.get('admin/viewDiscounts', { headers });
};

// discount details is an entity so map its body according to its attribute
const updateDiscount = (DiscountDetails) => {
    return httpClient.put('admin/updateDiscount', DiscountDetails, { headers });
};

const deleteDiscountDetails = (DiscountDetailsId) => {
    return httpClient.delete(`admin/deleteDiscount/${DiscountDetailsId}`, { headers });
};



export default { addDiscount, fetchAllDiscountDetails, updateDiscount, deleteDiscountDetails }