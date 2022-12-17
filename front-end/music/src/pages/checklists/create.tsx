import { Box, Button, Checkbox, Divider, Flex, Heading, HStack, SimpleGrid, VStack } from "@chakra-ui/react";
import { SubmitHandler, useForm } from 'react-hook-form';
import { Input } from "../../components/Form/Input";

import { Header } from "../../components/Header";
import { Sidebar } from "../../components/Sidebar";

import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { api } from "../../services/api";
import { ButtonCancel } from "../../components/utils/button/ButtonCancel";
import { useState } from "react";

type CreateChecklistFormData = {
  description: string;
  value: boolean;
}

const createChecklistSchema = yup.object().shape({
  description: yup.string().required('Descrição obrigatório')
})

export default function CreateChecklist(){
  const [checkValue, setCheckValue] = useState(false);

  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(createChecklistSchema)
  })

  const { errors } = formState

  async function createChecklist({description}: CreateChecklistFormData){
    await api.post("/checklist", {
      description: description,
      value: checkValue,
    });
  }  

  const handleCreateChecklist: SubmitHandler<CreateChecklistFormData> = async (values) => {
    await createChecklist(values)      
  }

  return(
    <Box>
      <Header/>
        <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
          <Sidebar />

          <Box as="form" flex="1" borderRadius="8" bg="purple.800" p="8" onSubmit={handleSubmit(handleCreateChecklist)}>
            <Heading fontWeight="normal" size="lg">Criar Checklist</Heading>

            <Divider my="6" borderColor="gray.700" />

            <VStack spacing="8"> 
              <SimpleGrid minChildWidth="240px" spacing="8" w="100%">
                <Input name="description" label="Descrição" {...register('name')} error={errors.description}/>
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