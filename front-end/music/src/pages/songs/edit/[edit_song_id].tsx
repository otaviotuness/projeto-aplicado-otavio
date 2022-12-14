import { Box, Button, Divider, Flex, Heading, HStack, SimpleGrid, VStack } from "@chakra-ui/react";
import { SubmitHandler, useForm } from 'react-hook-form';
import { Input } from "../../../components/Form/Input";

import { Header } from "../../../components/Header";
import { Sidebar } from "../../../components/Sidebar";

import Link from 'next/link'
import { useRouter } from 'next/router'
import Router from 'next/router';

import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { api } from "../../../services/api";
import { AuthContext } from "../../../contex/AuthContext";
import { useEffect, useState, useContext } from "react";
import { ButtonCancel } from "../../../components/utils/button/ButtonCancel";

type EditSongFormData = {
  id: number;
  description: string;
  link: string;
}

const editSongSchema = yup.object().shape({
  description: yup.string().required('Descrição obrigatório'),
  link: yup.string().required('Link obrigatório'),
})

export default function EditSong(){
  const [idSong, setIdSong] = useState('');
  const [description, setDescription] = useState('');
  const [link, setLink] = useState('');

  const handleChangeDescription = (text) => { setDescription(text); }
  const handleChangeLink = (text) => { setLink(text); }


  const {user} = useContext(AuthContext)

  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(editSongSchema)
  })

  const { errors } = formState

  async function editSong({id, description, link}: EditSongFormData){
    await api.post("/song", {
      id: id,  
      description: description,
      link: link,
      id_user: user.id
    });
  }  

  const songId = useRouter().query.edit_song_id;

  async function getSong() {
    const response = await api.get('/song/'+ songId)
    .catch(error => (error));
    
    const userResponse = response.data;

    setIdSong(userResponse.id);
    setDescription(userResponse.description);
    setLink(userResponse.link);
  }

  useEffect(() => {
    getSong(); 
  },[])

  const handleEditSong: SubmitHandler<EditSongFormData> = async (values) => {
    await editSong(values)  
    
    Router.push('/songs');
  }

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box as="form" flex="1" borderRadius="8" bg="purple.800" p="8" onSubmit={handleSubmit(handleEditSong)}>
            <Heading fontWeight="normal" size="lg">Criar Música</Heading>

            <Divider my="6" borderColor="gray.700" />

            <VStack spacing="8"> 
              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input 
                  name="description" 
                  label="Descrição" 
                  {...register('description')} 
                  error={errors.description}
                  value={description}
                  onChange={e => handleChangeDescription(e.target.value)}
                />
                <Input 
                  name="link" 
                  label="Link" 
                  {...register('link')} 
                  error={errors.link}
                  value={link}
                  onChange={e => handleChangeLink(e.target.value)}
                />
              </SimpleGrid>
            </VStack>

            <Flex
              mt="8"
              justify="flex-end"
            >

            <HStack spacing="4">
              <Link href="/users" passHref>
                <ButtonCancel />
              </Link>  
              <Button colorScheme="orange" type="submit" isLoading={formState.isSubmitting}>
                Salvar
              </Button>
            </HStack>       
            </Flex>
          </Box>
        </Flex>  
    </Box>
  )
}