import { createContext, ReactNode } from "react";
import { api } from "../services/api";


type SingInCredential = {
  email: string;
  password: string;
}

type AuthContextData = {
  singIn(credential: SingInCredential): Promise<void>;
  isAuthenticated: boolean;
};

type AuthProviderProps = {
  children: ReactNode;
}

export const AuthContext = createContext({} as AuthContextData)

export function AuthProvider({ children }: AuthProviderProps) {
  const isAuthenticated = false;

  async function singIn({email, password}: SingInCredential){
    const form = new FormData();

    form.append('username',email);
    form.append('password',email);
    form.append('grant_type','password');
    
    const response = await api.post("oauth/token", {}, {
      auth: {
        username: "myappname",
        password: "mysecret"
      },
      headers : {
        "Content-Type" : "application/x-www-form-urlencoded"
      }, 
      form: {
        "username": email,
        "password": password,
        "grant_type": "password"
      }
    })

    console.log(response.data)
  }

  return (
    <AuthContext.Provider value={{singIn, isAuthenticated}}>
      {children}
    </AuthContext.Provider>
  )
}