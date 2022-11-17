import { Box, Button, Checkbox, Flex, Heading, Icon, Table, Tbody, Td, Th, Thead, Tr, Text, useBreakpointValue } from "@chakra-ui/react";
import { RiAddLine, RiDeleteBinLine, RiPencilLine } from "react-icons/ri";
import decode from 'jwt-decode'

import { Header } from "../../components/Header";
import { Pagination } from "../../components/Pagination";
import { Sidebar } from "../../components/Sidebar";

import Link from 'next/link'
import { useEffect, useState } from "react";
import { api } from "../../services/api";
import { withSSRAuth } from "../../utils/withSSRAuth";
import { ButtonAdd } from "../../components/utils/button/ButtonAdd";
import { ButtonDelete } from "../../components/utils/button/ButtonDelete";
import { ButtonEdit } from "../../components/utils/button/ButtonEdit";

interface Song {
  id: number;
  description: string;
  link: string;
}

export default function SongList(){
  const [songs, setSongs] = useState<Song[]>([]);
  
  const isWideVersion = useBreakpointValue({
    base: false,
    lg: true,
  })

  async function getSongs() {
    const response = await api.get('/songs')
    .catch(error => (error));
  

    setSongs(response.data);
  }

  useEffect(() => {
    getSongs(); 
  }, [])

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6" >
          <Sidebar />

          <Box flex="1" borderRadius="8" bg="gray.800" p="8">
            <Flex mb="8" justify="space-between" align="center">
              <Heading size="lg" fontWeight="normal">Músicas</Heading>

              <Link href="/songs/create" passHref>
                <Button 
                    as="a" 
                    size="sm" 
                    fontSize="sm" 
                    colorScheme="orange"
                    leftIcon={<Icon as={RiAddLine} fontSize="20"/>}
                    bg="orange.600"
                    _hover={{bgColor: 'orange.700'}}
                >
                    Criar novo

                </Button>
              </Link>              
              
            </Flex>

            <Table colorScheme="whiteAlpha">
              <Thead>
                <Tr>
                  <Th px={["4", "4", "6"]} color="gray.300" width="8">
                    <Checkbox colorScheme="orange"/>
                  </Th>
                  <Th>Descrição</Th>
                  <Th>Link</Th>
                  <Th width="8"></Th>
                  <Th width="8"></Th>
                </Tr>
              </Thead>

                {songs.map(song => {
                  return(
                    <Tbody>
                      <Tr key={song.id}>
                        <Td px={["4", "4", "6"]}>
                          <Checkbox colorScheme="orange"/>
                        </Td>
                        <Td>
                          <Text fontWeight="bold">{song.description}</Text>
                        </Td>
                        <Td>
                          {song.link}
                        </Td>
                        <Td>
                          <ButtonEdit />
                        </Td>
                        <Td>
                          <ButtonDelete />
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