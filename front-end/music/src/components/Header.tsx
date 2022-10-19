import { Flex, Text, Input, Icon } from "@chakra-ui/react"
import { IncomingMessage } from "http"
import { RiSearchLine } from 'react-icons/ri'

export function Header() {
  return(
    <Flex 
      w="100%" 
      as="header" 
      maxWidth={1480}
      h="20"
      mx="auto"
      mt="4"
      px="6"
      align="center"
    >
      <Text 
        fontSize="3xl" 
        fontWeight="bold" 
        letterSpacing="tight" 
        w="64"
      >
        Music
      </Text>  

      <Flex
        as="label"
        flex="1"
        py="4"
        px="8"
        ml="6"
        maxWidth={400}
        alignSelf="center"
        color="gray.200"
        position="relative"
        bg="gray.800"
        borderRadius="full"
      >

        <Input 
          color="gray.50"
          variant="unstyled"
          placeholder="Buscar"
          _placeholder={{color : 'gray.400'}}
        />

        <Icon as={RiSearchLine} fontSize="20"/>

      </Flex>  

    </Flex>
  )
}