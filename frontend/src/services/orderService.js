import httpClient from '../http-common'

//here status is string check for admin controller method getOrderDetailsByStatus
const getOrderDetailsByStatus=(status)=>{
    return httpClient.get(`admin/getOrderDetails/${status}`);
};

const updateOrderStatus=(OrderDetailsDto)=>{
    return httpClient.put('admin/orders/updateStatus',OrderDetailsDto);
};

const cancelOrder=(OrderDetailsDto)=>{
    return httpClient.put('user/orders/updateStatus',OrderDetailsDto);
};

const viewOrderHistory=(userId)=>{
    return httpClient.get(`user/viewOrderHistory/${userId}`);
};

const getOrderDetails=(orderDetailsId)=>{
    return httpClient.get(`user/getOrderDetails/${orderDetailsId}`);
};

export default { getOrderDetailsByStatus,updateOrderStatus,
                 cancelOrder,viewOrderHistory,getOrderDetails };