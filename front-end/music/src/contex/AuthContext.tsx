import { createContext, ReactNode, useEffect, useState } from "react";
import { setCookie, parseCookies, destroyCookie } from "nookies";
import { api } from "../services/api";

import Router from 'next/router';

type User = {
  id: number,
  name: string;
  email: string;
  roles: string[];
  token: string;
}

type SingInCredential = {
  email: string;
  password: string;
}

type AuthContextData = {
  singIn(credential: SingInCredential): Promise<void>;
  user: User;
  isAuthenticated: boolean;
};

type AuthProviderProps = {
  children: ReactNode;
}

export const AuthContext = createContext({} as AuthContextData)

export function signOut(){
  destroyCookie(undefined, 'music.token')

  Router.push('/')
}

export function AuthProvider({ children }: AuthProviderProps) {
  const [user, setUser] = useState<User>()
  const isAuthenticated = !!user;

  useEffect(() => {
    const { 'music.token': token } = parseCookies()

    if(token){
      api.get('/me')
        .then(response => {
          const { id, name, email, roles } = response.data;

          setUser({
            id,
            name, 
            email, 
            roles, 
            token})
        })
        .catch(() => {
          signOut();
        })
    }
  }, [])

  async function singIn({email, password}: SingInCredential){

    try{ 
      const response = await api.get("/token", {
        auth: {
          username: email,
          password: password
        }
      })

      const { id, name, roles, token } = response.data;

      setCookie(undefined, 'music.token', token, {
        maxAge: 60 * 60 * 24 * 30, // 30 days
        path: '/'
      })

      setUser({
        id,
        name,
        email,
        roles,
        token
      })

      api.defaults.headers['Authorization'] = `Bearer ${token}`;

      Router.push('/dashboard')
    } catch (err){
      console.log(err);
    }  
  }

  return (
    <AuthContext.Provider value={{singIn, isAuthenticated, user}}>
      {children}
    </AuthContext.Provider>
  )
}