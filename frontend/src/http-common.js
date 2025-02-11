import axios from 'axios';
import config from './config/serverUrl';

export default axios.create({
    baseURL:config.serverUrl,
    headers:{
        'Content-Type':'application/json',
    }
   
});