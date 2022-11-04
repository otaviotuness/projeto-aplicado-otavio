import { Box, Button, Checkbox, Flex, Heading, Icon, Table, Tbody, Td, Th, Thead, Tr, Text, useBreakpointValue } from "@chakra-ui/react";
import { RiAddLine, RiDeleteBinLine, RiPencilLine } from "react-icons/ri";

import { Header } from "../../components/Header";
import { Pagination } from "../../components/Pagination";
import { Sidebar } from "../../components/Sidebar";

import Link from 'next/link'
import { useEffect, useState } from "react";
import { api } from "../../services/api";
import internal from "stream";

interface User {
  id: number;
  email: string;
  name: string;
  telephone: string;
  id_master: number;
}

export default function UserList(){
  const [users, setUsers] = useState<User[]>([]);
  
  const isWideVersion = useBreakpointValue({
    base: false,
    lg: true,
  })

  async function getUsers() {
    const response = await api.get('/users');
    
    setUsers(response.data);
  }

  useEffect(() => {
    getUsers(); 
  }, [])

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box flex="1" borderRadius="8" bg="gray.800" p="8">
            <Flex mb="8" justify="space-between" align="center">
              <Heading size="lg" fontWeight="normal">Usuários</Heading>

              <Link href="/users/create" passHref>
                <Button 
                  as="a" 
                  size="sm" 
                  fontSize="sm" 
                  colorScheme="pink"
                  leftIcon={<Icon as={RiAddLine} fontSize="20"/>}
                >
                  Criar novo

                </Button>
              </Link>              
              
            </Flex>

            <Table colorScheme="whiteAlpha">
              <Thead>
                <Tr>
                  <Th px={["4", "4", "6"]} color="gray.300" width="8">
                    <Checkbox colorScheme="pink"/>
                  </Th>
                  <Th>Usuário</Th>
                  <Th>Data de cadastro</Th>
                  <Th width="8"></Th>
                  <Th width="8"></Th>
                </Tr>
              </Thead>

                {users.map(user => {
                  return(
                    <Tbody>
                      <Tr key={user.id}>
                        <Td px={["4", "4", "6"]}>
                          <Checkbox colorScheme="pink"/>
                        </Td>
                        <Td>
                          <Box>
                            <Text fontWeight="bold">{user.name}</Text>
                            <Text fontSize="small" color="gray.300">{user.email}</Text>
                          </Box>
                        </Td>
                        <Td>
                          {user.telephone}
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
                  )
                })}
            </Table>

            <Pagination/>
          </Box>

        </Flex>  
    </Box>
  )
}