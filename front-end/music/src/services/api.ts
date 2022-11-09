import axios, { AxiosError } from 'axios'
import { parseCookies, setCookie } from 'nookies'
import { signOut } from '../contex/AuthContext';

let cookies = parseCookies();
let isRefreshing = false;
let failedRquestQueue = [];

export const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    Authorization: `Bearer ${cookies['music.token']}`
  }
});

api.interceptors.response.use(response => {
  return response;
}, (error: AxiosError) => {
  if (error.response.status == 401){
    cookies = parseCookies();

    const originalConfig = error.config;

    if(!isRefreshing){
      isRefreshing = true;

      api.post('/refreshToken').then(response => {
        const { token } = response.data;
  
        setCookie(undefined, 'music.token', token, {
          maxAge: 60 * 60 * 24 * 30, // 30 days
          path: '/'
        })
  
        api.defaults.headers['Authorization'] = `Bearer ${token}`;

        failedRquestQueue.forEach(request => request.onSuccess(token))
        failedRquestQueue = [];
      }).catch(err => {
        failedRquestQueue.forEach(request => request.onFailure(err)) 
        failedRquestQueue = [];
      }).finally(() => {
        isRefreshing = false;
      })
    }

    return new Promise((resolve, reject) => {
      failedRquestQueue.push({
        onSuccess: (token: string) => {
          originalConfig.headers['Authorization'] = `Bearer ${token}`

          resolve(api(originalConfig))
        },
        onFailure: (err: AxiosError) => {
          reject(err)
        }
      })   
    });
  }else {
    signOut();
  }

  return Promise.reject(error);
});