import { Button } from "@chakra-ui/react";
import Link from "next/link";

export function ButtonCancel({...rest}){
    return (
        <Link href={rest.href}>
            <Button 
                variant="ghost" 
                _hover={{bgColor: 'purple.900'}}
            >
                Cancelar
            </Button>
        </Link>
    );
}