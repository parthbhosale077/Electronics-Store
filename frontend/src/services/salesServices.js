import httpClient from '../http-common'

//map salesDto obj with com.app.dto.salesDto
const getSalesDetailsByProduct=(SalesDto)=>{
    return httpClient.post('admin/getSalesDetailsByProduct',SalesDto);
};

const getSalesDetailsByCategory=(SalesDto)=>{
    return httpClient.post('admin/getSalesDetailsByCategory',SalesDto);
};

export default { getSalesDetailsByProduct,getSalesDetailsByCategory };