import { Box, Button, Divider, Flex, Heading, HStack, SimpleGrid, VStack } from "@chakra-ui/react";
import { SubmitHandler, useForm } from 'react-hook-form';
import { Input } from "../../../components/Form/Input";

import { Header } from "../../../components/Header";
import { Sidebar } from "../../../components/Sidebar";

import Link from 'next/link'
import { useRouter } from 'next/router'

import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { api } from "../../../services/api";

import { useEffect, useState } from "react";

interface User {
  id: number;
  email: string;
  name: string;
  telephone?: string;
  id_master: number;
  password: string;
}

type EditUserFormData = {
  name: string;
  email: string;
  password: string;
  password_confirmation: string;
  telephone: string;
}

const editUserSchema = yup.object().shape({
  name: yup.string().required('Nome obrigatório'),
  email: yup.string().required('E-mail obrigatório').email('E-mail inválido'),
  password: yup.string().required('Senha obrigatória').min(6, "Senha no mínimo 6 caracteres"),
  password_confirmation: yup.string().oneOf([
    null, yup.ref('password')
  ], "As senhas precisam ser iguais") 
})

export default function EditUser(){
  const [user, setUser] = useState<User>();

  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(editUserSchema)
  })

  const { errors } = formState

  async function editUser({name, email, password, telephone}: EditUserFormData){
    await api.post("/user", {
      name: name,
      email: email,
      password: password,
      telephone: telephone
    });
  }  

  const handleEditUser: SubmitHandler<EditUserFormData> = async (values) => {
    await editUser(values)      
  }

  const router = useRouter();
  const userId = router.query.edit_user_id;

  async function getUser() {
    const response = await api.get('/user/'+ userId)
    .catch(error => (error));
    
    setUser(response.data);
  }

  useEffect(() => {
    console.log("useeffect")
    getUser(); 
  },[])

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box as="form" flex="1" borderRadius="8" bg="purple.800" p="8" onSubmit={handleSubmit(handleEditUser)}>
            <Heading fontWeight="normal" size="lg">Editar Usuário</Heading>

            <Divider my="6" borderColor="gray.700" />

            <VStack spacing="8"> 
              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input 
                  name="name" 
                  label="Nome completo" {...register('name')}
                  value={user?.name.length > 0 ? user.name : ""} 
                  error={errors.name}/>
                <Input 
                  name="email" 
                  type="email" 
                  label="E-mail" {...register('email')}
                  value={user?.email.length > 0 ? user.email : ""}  
                  error={errors.email}/>
                <Input 
                  name="telephone" 
                  label="Telefone" {...register('telephone')}   
                  value={user?.telephone != null ? user.telephone : "" } 
                  error={errors.name}/>
              </SimpleGrid>

              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input 
                  name="password" 
                  type="password" 
                  label="Senha" {...register('password')}
                  value={user?.password.length > 0 ? user.password : "" }  
                  error={errors.password}/>
                <Input 
                  name="password_confirmation" 
                  type="password" 
                  label="Confirmação da senha" {...register('password_confirmation')}  
                  value={user?.password.length > 0 ? user.password : "" } 
                  error={errors.password_confirmation}/>
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