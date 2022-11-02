import axios, { AxiosError } from 'axios'
import { parseCookies } from 'nookies'

let cookies = parseCookies();

export const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    Authorization: `Bearer ${cookies['music.token']}`
  }
});