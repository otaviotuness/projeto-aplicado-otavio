import { GetServerSideProps, GetServerSidePropsContext, GetServerSidePropsResult } from "next";
import { parseCookies } from "nookies";
import { validateUserPermissionsParams } from "./validateUserPermissions";
import decode from 'jwt-decode'

type WithSSRAuthOptions = {
    roles?: string[]
}

export function withSSRAuth<P>(fn: GetServerSideProps<P>, options?:WithSSRAuthOptions) {
    return async (ctx: GetServerSidePropsContext): Promise<GetServerSidePropsResult<P>> => {

        const cookies = parseCookies(ctx);
        const token = cookies['music.token'];

        if (!token) {
            return {
                redirect: {
                    destination: '/',
                    permanent: false,
                }         
            }
        }

        if (options){ 
            const user = decode<{ roles: string[] }>(token); 
            const {roles} = options;

            const userHasValidPermissions = validateUserPermissionsParams({
                user,
                roles
            })

            if(!userHasValidPermissions){
                return {
                    //notFound: true,//usuario recebe 404 caso não tenha nenhuma tela que todos possam acessar
                    redirect: {
                        destination: '/dashboard',
                        permanent: false,
                    }    
                }
            }

        }
        

        return await fn(ctx);
    }
}


