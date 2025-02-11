import httpClient from '../http-common'

const getAllProductsFromInventory=()=>{
    return httpClient.get('admin/inventory');    
};

const updateQuantity=(ProductInventoryDto)=>{
    return httpClient.put('admin/inventory/updateQuantity',ProductInventoryDto);
};

export default { getAllProductsFromInventory,updateQuantity };