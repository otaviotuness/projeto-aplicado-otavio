import { Button, color, Icon } from "@chakra-ui/react";
import { RiAddLine, RiDeleteBinLine } from "react-icons/ri";

export function ButtonDelete(){
    return (
        <Button 
            as="a" 
            size="sm" 
            fontSize="sm" 
            colorScheme="pink"
            leftIcon={<Icon as={RiDeleteBinLine}/>}
            bg="orange.600"
            _hover={{bgColor: 'orange.700'}}
            >
            Excluir

        </Button>
    );
}