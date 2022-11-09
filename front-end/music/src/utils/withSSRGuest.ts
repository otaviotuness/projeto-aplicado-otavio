import { GetServerSideProps, GetServerSidePropsContext, GetServerSidePropsResult } from "next";
import { parseCookies } from "nookies";

type WithSSRAuthOptions = {
    roles?: string[]
}

export function withSSRGuest<P>(fn: GetServerSideProps<P>, options?:WithSSRAuthOptions) {
    return async (ctx: GetServerSidePropsContext): Promise<GetServerSidePropsResult<P>> => {

        const cookies = parseCookies(ctx);

        if (cookies['music.token']) {
            return {
                redirect: {
                    destination: '/dashboard',
                    permanent: false,
                }         
            }
        }  

        return await fn(ctx);
    }
}


