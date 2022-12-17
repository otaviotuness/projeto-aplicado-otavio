import { Box, Button, Checkbox, Flex, Heading, Icon, Table, Tbody, Td, Th, Thead, Tr, Text, useBreakpointValue } from "@chakra-ui/react";
import { RiPencilLine } from "react-icons/ri";

import decode from 'jwt-decode'

import { Header } from "../../components/Header";
import { Pagination } from "../../components/Pagination";
import { Sidebar } from "../../components/Sidebar";

import Link from 'next/link'
import { useEffect, useState } from "react";
import { api } from "../../services/api";
import { withSSRAuth } from "../../utils/withSSRAuth";
import ConfirmButton from "../../components/confirmButtonDelete/ConfirmButtonDelete";
import { ButtonCreateNew } from "../../components/utils/button/ButtonCreateNew";
import LabelBoolean from "../../components/Labels/LabelBoolean";

interface Checklist {
  id: number;
  description: string;
  value: boolean;
}

export default function ChecklistList(){
  const [checklists, setChecklists] = useState<Checklist[]>([]);
  
  const isWideVersion = useBreakpointValue({
    base: false,
    lg: true,
  })

  async function getChecklists() {
    const response = await api.get('/checklists')
    .catch(error => (error));

    console.log(response.data);
    
    setChecklists(response.data);
  }

  async function deleteChecklist(id:number) {
    await api.delete('/checklist/' + id)
    .catch(error => (error));

    getChecklists();
  }

  useEffect(() => {
    getChecklists(); 
  }, [])

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6" >
          <Sidebar />

          <Box flex="1" borderRadius="8" bg="gray.800" p="8">
            <Flex mb="8" justify="space-between" align="center">
              <Heading size="lg" fontWeight="normal">Checklists</Heading>
              <ButtonCreateNew href={"/checklists/create"} passHref/>
            </Flex>

            <Table colorScheme="whiteAlpha">
              <Thead>
                <Tr>
                  <Th px={["4", "4", "6"]} color="gray.300" width="8">
                    <Checkbox colorScheme="orange"/>
                  </Th>
                  <Th>Descrição</Th>
                  <Th>Valor</Th>
                  <Th width="8"></Th>
                  <Th width="8"></Th>
                </Tr>
              </Thead>
                {checklists.map(checklist => {
                  return(
                    <Tbody>
                      <Tr key={checklist.id}>
                        <Td px={["4", "4", "6"]}>
                          <Checkbox colorScheme="orange"/>
                        </Td>
                        <Td>
                          <Text fontWeight="bold">{checklist.description}</Text>
                        </Td>
                        <Td>
                          <LabelBoolean value={checklist.value}/> 
                        </Td>
                        <Td>
                          <Link href={`/checklists/edit/${encodeURIComponent(checklist.id)}`}>
                            <Button 
                              as="a" 
                              size="sm" 
                              fontSize="sm" 
                              leftIcon={<Icon as={RiPencilLine}/>}
                              bg="orange.600"
                              _hover={{bgColor: 'orange.700'}}
                            >
                              Editar
                            </Button>
                          </Link>
                        </Td>
                        <Td>
                          <ConfirmButton
                            headerText="Deseja deletar esse item do checklist?"
                            bodyText="
                            Você tem certeza que quer deletar este registro? Isto não pode ser desfeito."
                            onSuccessAction={() => {
                              deleteChecklist(checklist.id);
                            }}
                            buttonText="Excluir"
                            isDanger={true}
                          />
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