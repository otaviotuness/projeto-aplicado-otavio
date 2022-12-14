import { Box } from "@chakra-ui/layout";
import {
  Button,
  Icon,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  useDisclosure,
} from "@chakra-ui/react";
import React from "react";
import { RiDeleteBinLine } from "react-icons/ri";

interface ConfirmButtonProps {
  onSuccessAction: () => void;
  headerText: string;
  bodyText: string;
  buttonText: string;
  isDanger?: boolean;
}

const ConfirmButton = ({
  onSuccessAction,
  buttonText,
  headerText,
  bodyText,
  isDanger,
}: ConfirmButtonProps) => {
  const { isOpen, onOpen, onClose } = useDisclosure();

  const onSubmit = () => {
    onSuccessAction();
    onClose();
  };

  return (
    <>
      <Button 
        onClick={onOpen} 
        colorScheme={isDanger ? "red" : ""}
        as="a" 
        size="sm" 
        fontSize="sm"
        leftIcon={<Icon as={RiDeleteBinLine}/>}
      >
        {buttonText}
      </Button>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent bg={"purple.700"}>
          <ModalHeader>{headerText}</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <Box>{bodyText}</Box>
          </ModalBody>

          <ModalFooter>
            <Button 
              variant="ghost" 
              onClick={onClose} mr={3}
              _hover={{bgColor: 'purple.800'}}
            >
              Cancelar
            </Button>
            <Button 
              onClick={onSubmit}
              as="a" 
              size="sm" 
              fontSize="sm" 
              leftIcon={<Icon as={RiDeleteBinLine}/>}
              bg="orange.600"
              _hover={{bgColor: 'orange.700'}}
              colorScheme={isDanger ? "red" : ""}
            >
              {buttonText}
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};
export default ConfirmButton;