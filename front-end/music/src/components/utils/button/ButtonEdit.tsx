import { Button, Icon, Link } from "@chakra-ui/react";
import { RiAddLine, RiDeleteBinLine, RiPencilLine } from "react-icons/ri";

export function ButtonEdit(){
    return (
        <Link href="/users/create">
            <Button 
                as="a" 
                size="sm" 
                fontSize="sm" 
                colorScheme="pink"
                leftIcon={<Icon as={RiPencilLine}/>}
                bg="orange.600"
                _hover={{bgColor: 'orange.700'}}
                >
                Editar

            </Button>
        </Link>
        
    );
}