import { Flex, Button, Stack, } from '@chakra-ui/react'
import { useState } from 'react'
import { Input } from '../components/Form/Input'

export default function Sigin() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  function handleSubmit(){
    const data = {
      email,
      password,
    }

    console.log(data);
  }

  return (
    <Flex w="100vw" h="100vh" align="center" justify="center">
      <Flex as="form" width="100%" maxWidth={360} bg="gray.800" p="8" borderRadius={8} flexDir="column">
        <Stack spacing={4}>

          <Input type="email" name="email" label="E-mail" onChange={e => setEmail(e.target.value)}></Input>
          <Input type="password" name="password" label="Senha"></Input>

        </Stack>  

        <Button type="submit" mt="6" colorScheme={'orange'} size="lg">Entrar</Button>

      </Flex>   
    </Flex>
  )
}
