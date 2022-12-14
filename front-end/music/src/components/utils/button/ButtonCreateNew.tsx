import { Button, Icon } from "@chakra-ui/react";
import Link from "next/link";
import { RiAddLine } from "react-icons/ri";

export function ButtonCreateNew({...rest}){
    return (
        <Link href={rest.href}>
            <Button 
                as="a" 
                size="sm" 
                fontSize="sm" 
                leftIcon={<Icon as={RiAddLine} fontSize="20"/>}
                bg="green.600"
                _hover={{bgColor: 'green.700'}}
                colorScheme={"green"}
            >
                Criar novo
            </Button>
        </Link>
    );
}
