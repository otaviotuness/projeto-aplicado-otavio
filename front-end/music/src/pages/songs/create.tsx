import { Box, Button, Divider, Flex, Heading, HStack, SimpleGrid, VStack } from "@chakra-ui/react";
import { SubmitHandler, useForm } from 'react-hook-form';
import { Input } from "../../components/Form/Input";

import { Header } from "../../components/Header";
import { Sidebar } from "../../components/Sidebar";

import Link from 'next/link'

import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { api } from "../../services/api";
import { AuthContext } from "../../contex/AuthContext";
import { useContext } from "react";
import { ButtonCancel } from "../../components/utils/button/ButtonCancel";

type CreateSongFormData = {
  description: string;
  link: string;
}

const createSongSchema = yup.object().shape({
  description: yup.string().required('Descrição obrigatório'),
  link: yup.string().required('Link obrigatório'),
})

export default function CreateSong(){
  const {user} = useContext(AuthContext)

  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(createSongSchema)
  })

  const { errors } = formState

  async function createSong({description, link}: CreateSongFormData){
    await api.post("/song", {
      description: description,
      link: link,
      id_user: user.id
    });
  }  

  const handleCreateSong: SubmitHandler<CreateSongFormData> = async (values) => {
    await createSong(values)      
  }

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box as="form" flex="1" borderRadius="8" bg="purple.800" p="8" onSubmit={handleSubmit(handleCreateSong)}>
            <Heading fontWeight="normal" size="lg">Criar Música</Heading>

            <Divider my="6" borderColor="gray.700" />

            <VStack spacing="8"> 
              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input name="description" label="Descrição" {...register('description')} error={errors.description}/>
                <Input name="link" label="Link" {...register('link')} error={errors.link}/>
              </SimpleGrid>
            </VStack>

            <Flex
              mt="8"
              justify="flex-end"
            >
              <HStack spacing="4">
                <Link href="/songs" passHref>
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