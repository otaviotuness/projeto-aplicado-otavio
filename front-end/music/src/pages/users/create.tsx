import { Box, Button, Divider, Flex, Heading, HStack, SimpleGrid, VStack } from "@chakra-ui/react";
import { SubmitHandler, useForm } from 'react-hook-form';
import { Input } from "../../components/Form/Input";

import { Header } from "../../components/Header";
import { Sidebar } from "../../components/Sidebar";

import Link from 'next/link'

import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { api } from "../../services/api";

type CreateUserFormData = {
  name: string;
  email: string;
  password: string;
  password_confirmation: string;
  telephone: string;
}

const createUserSchema = yup.object().shape({
  name: yup.string().required('Nome obrigatório'),
  email: yup.string().required('E-mail obrigatório').email('E-mail inválido'),
  password: yup.string().required('Senha obrigatória').min(6, "Senha no mínimo 6 caracteres"),
  password_confirmation: yup.string().oneOf([
    null, yup.ref('password')
  ], "As senhas precisam ser iguais") 
})

export default function CreateUser(){

  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(createUserSchema)
  })

  const { errors } = formState

  async function createUser({name, email, password, telephone}: CreateUserFormData){
    await api.post("/user", {
      name: name,
      email: email,
      password: password,
      telephone: telephone
    });
  }  

  const handleCreateUser: SubmitHandler<CreateUserFormData> = async (values) => {
    await createUser(values)      
  }

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box as="form" flex="1" borderRadius="8" bg="purple.800" p="8" onSubmit={handleSubmit(handleCreateUser)}>
            <Heading fontWeight="normal" size="lg">Criar Usuário</Heading>

            <Divider my="6" borderColor="gray.700" />

            <VStack spacing="8"> 
              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input name="name" label="Nome completo" {...register('name')} error={errors.name}/>
                <Input name="email" type="email" label="E-mail" {...register('email')} error={errors.email}/>
                <Input name="telephone" label="Telefone" {...register('telephone')} error={errors.name}/>
              </SimpleGrid>

              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input name="password" type="password" label="Senha" {...register('password')} error={errors.password}/>
                <Input name="password_confirmation" type="password" label="Confirmação da senha" {...register('password_confirmation')}error={errors.password_confirmation}/>
              </SimpleGrid>
            </VStack>

            <Flex
              mt="8"
              justify="flex-end"
            >
              <HStack spacing="4">
                <Link href="/users" passHref>
                  <Button as="a" colorScheme="red">Cancelar</Button>
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