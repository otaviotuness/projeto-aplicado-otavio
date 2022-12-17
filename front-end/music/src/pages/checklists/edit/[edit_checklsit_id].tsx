import { Box, Button, Checkbox, Divider, Flex, Heading, HStack, SimpleGrid, VStack } from "@chakra-ui/react";
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

import { useEffect, useState } from "react";
import { ButtonCancel } from "../../../components/utils/button/ButtonCancel";

type EditChecklistFormData = {
  id: number;
  description: string;
  value: boolean;
}

const editChecklistSchema = yup.object().shape({
  description: yup.string().required('Descrição obrigatório')
})

export default function EditChecklist(){
  const [idChecklist, setIdChecklist] = useState('');
  const [checkValue, setCheckValue] = useState(false);
  const [description, setDescription] = useState('');


  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(editChecklistSchema)
  })

  const { errors } = formState

  const handleEditChecklist: SubmitHandler<EditChecklistFormData> = async () => {
    await editChecklist();      

    Router.push('/checklists');
  }

  const router = useRouter();
  const checklistId = router.query.edit_checklist_id;

  async function getChecklist() {
    const response = await api.get('/checklist/'+ checklistId)
    .catch(error => (error));
    
    const checklistResponse = response.data;

    setIdChecklist(checklistResponse.id);
    setCheckValue(checklistResponse.value);
    setDescription(checklistResponse.description);
  }

  async function editChecklist(){
    await api.post("/checklist", {
      id: idChecklist,
      description: description,
      value: checkValue,
    });
  } 

  useEffect(() => {
    getChecklist(); 
  },[])

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box as="form" flex="1" borderRadius="8" bg="purple.800" p="8" onSubmit={handleSubmit(handleEditChecklist)}>
            <Heading fontWeight="normal" size="lg">Editar Usuário</Heading>

            <Divider my="6" borderColor="gray.700" />

            <VStack spacing="8"> 
              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input 
                  name="description" 
                  label="Descrição" 
                  {...register('name')}
                  error={errors.description}
                  value={description}
                  onChange={e => setDescription(e.target.value)}
                />
                <Input name="description" label="Evento vinculado" {...register('name')} error={errors.description}/>
              </SimpleGrid>

              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Checkbox 
                  colorScheme='green' 
                  defaultChecked
                  isChecked={checkValue}
                  onChange={(e) => setCheckValue(e.target.checked)}
                >
                  Realizado?
                </Checkbox>
              </SimpleGrid>
            </VStack>

            <Flex
              mt="8"
              justify="flex-end"
            >

            <HStack spacing="4">
              <ButtonCancel href={"/checklists"} passHref /> 
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