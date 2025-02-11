import httpClient from '../http-common'

const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};
const getCategories=()=>{
    return  httpClient.get('home/getCategories');
   
 };

const addCategory=(CategoryDto)=>{
    const headers = {"Authorization":"Bearer " + sessionStorage.getItem("token")};
    return httpClient.post('admin/addCategory',
    CategoryDto,{ headers}
    );
    
};

export default { getCategories,addCategory };
// import httpClient from '../http-common';

// const getAuthHeaders = () => ({
//     headers: { "Authorization": "Bearer " + sessionStorage.getItem("token") }
// });

// const getCategories = () => {
//     return httpClient.get('home/getCategories', getAuthHeaders());
// };

// const addCategory = (CategoryDto) => {
//     return httpClient.post('admin/addCategory', CategoryDto, getAuthHeaders());
// };

//export default { getCategories, addCategory };
