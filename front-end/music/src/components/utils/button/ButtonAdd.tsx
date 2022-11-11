import { Button, Icon } from "@chakra-ui/react";
import { RiAddLine } from "react-icons/ri";

export function ButtonAdd(){
    return (
        <Button 
            as="a" 
            size="sm" 
            fontSize="sm" 
            colorScheme="pink"
            leftIcon={<Icon as={RiAddLine} fontSize="20"/>}
            bg="orange.600"
            _hover={{bgColor: 'orange.700'}}
        >
            Criar novo

        </Button>
    );
}