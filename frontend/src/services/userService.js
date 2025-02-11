 import httpClient from '../http-common'

const fetchAllUserDetails=()=>{
    return httpClient.get('admin/viewUsers');
};

const addNewAddress=(UserAddressDto)=>{
    return httpClient.post('user/addNewAddress',UserAddressDto);
};

const getAddress=(userId)=>{
    return httpClient.get(`user/getAddress/${userId}`);
};

const updateAccountDetails=(userId,UserEntity)=>{
    return httpClient.put(`user/updateAccountDetails/${userId}`,UserEntity);
};

const editAddress=(addressId,UserAddressDto)=>{
    return httpClient.put(`user/editAddress/${addressId}`,UserAddressDto);
};

const validateUserCreateToken=(AuthRequest)=>{
    return httpClient.post('auth/signin',AuthRequest);
};

const userRegistration= async (UserDto)=>{
    return await httpClient.post('auth/signup',UserDto);
};
export default { fetchAllUserDetails,addNewAddress,getAddress,
                 updateAccountDetails,editAddress,validateUserCreateToken,
                 userRegistration };