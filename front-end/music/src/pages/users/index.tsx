import { Box, Button, Checkbox, Flex, Heading, Icon, Table, Tbody, Td, Th, Thead, Tr, Text } from "@chakra-ui/react";
import { RiAddLine, RiDeleteBinLine, RiPencilLine } from "react-icons/ri";

import { Header } from "../../components/Header";
import { Pagination } from "../../components/Pagination";
import { Sidebar } from "../../components/Sidebar";

export default function UserList(){
  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box flex="1" borderRadius="8" bg="gray.800" p="8">
            <Flex mb="8" justify="space-between" align="center">
              <Heading size="lg" fontWeight="normal">Usuários</Heading>

              <Button 
                as="a" 
                size="sm" 
                fontSize="sm" 
                colorScheme="pink"
                leftIcon={<Icon as={RiAddLine} fontSize="20"/>}
              >
                Criar novo

              </Button>
            </Flex>

            <Table colorScheme="whiteAlpha">
              <Thead>
                <Tr>
                  <Th px="6" color="gray.300" width="8">
                    <Checkbox colorScheme="pink"/>
                  </Th>
                  <Th>Usuário</Th>
                  <Th>Data de cadastro</Th>
                  <Th width="8"></Th>
                  <Th width="8"></Th>
                </Tr>
              </Thead>
              <Tbody>
                <Tr>
                  <Td px="6">
                    <Checkbox colorScheme="pink"/>
                  </Td>
                  <Td>
                    <Box>
                      <Text fontWeight="bold">Otávio</Text>
                      <Text fontSize="small" color="gray.300">otavio.tuens@email.com</Text>
                    </Box>
                  </Td>
                  <Td>
                    04 de abril de 2022
                  </Td>
                  <Td>
                    <Button 
                      as="a" 
                      size="sm" 
                      fontSize="sm" 
                      colorScheme="pink"
                      leftIcon={<Icon as={RiPencilLine}/>}
                    >
                      Editar

                    </Button>
                  </Td>
                  <Td>
                    <Button 
                      as="a" 
                      size="sm" 
                      fontSize="sm" 
                      colorScheme="pink"
                      leftIcon={<Icon as={RiDeleteBinLine}/>}
                    >
                      Excluir

                    </Button>
                  </Td>
                </Tr>
              </Tbody>
            </Table>

            <Pagination/>
          </Box>

        </Flex>  
    </Box>
  )
}