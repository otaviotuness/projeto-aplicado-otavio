import { Flex, Button, Stack, } from '@chakra-ui/react'
import { SubmitHandler, useForm } from 'react-hook-form';
import { Input } from '../components/form/Input';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { AuthContext } from '../contex/AuthContext';

import { useContext } from 'react'
import { withSSRAuth } from '../utils/withSSRAuth';
import { withSSRGuest } from '../utils/withSSRGuest';


export default function Sigin() {

  const { singIn } = useContext(AuthContext) 

  const signInFormSchema = yup.object().shape({
    email: yup.string().required('E-mail obrigatório').email('E-mail inválido'),
    password: yup.string().required('Senha obrigatória')
  })

  const { register, handleSubmit, formState } = useForm({
    resolver: yupResolver(signInFormSchema)
  })
  
  const { errors } = formState

  type SignInFormData = {
    email: string;
    password: string;
  }

  const handleSigIn: SubmitHandler<SignInFormData> = async (values) =>  {
    await singIn(values)
  }

  return (
    <Flex w="100vw" h="100vh" align="center" justify="center">
      <Flex 
        as="form" 
        width="100%" 
        maxWidth={360} 
        bg="purple.800" 
        p="8" 
        borderRadius={8} 
        flexDir="column"
        onSubmit={handleSubmit(handleSigIn)}
      >
        <Stack spacing="4">
        <Input
            name="email"
            type="email"
            label="E-mail"
            error={errors.email}
            {...register('email')}
          />
          <Input
            name="password"
            type="password"
            label="Senha"
            error={errors.password}
            {...register('password')}
          />
        </Stack>  

        <Button 
          type="submit" 
          mt="6" 
          bg="orange.500" 
          _hover={{bgColor: 'orange.700'}}
          size="lg" 
          isLoading={formState.isSubmitting}
        >
          Entrar
        </Button>

      </Flex>   
    </Flex>
  )
}

export const getServerSideProps = withSSRGuest(async (ctx) => {
  return {
    props:{}
  }
});