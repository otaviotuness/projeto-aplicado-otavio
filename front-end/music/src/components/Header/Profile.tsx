import { Flex, Box, Text, Avatar } from "@chakra-ui/react";

interface ProfileProps {
  showProfileData?: boolean;
}

export function Profile({showProfileData = true}: ProfileProps){
  return (
    <Flex align="center"> 

      { showProfileData &&  (
        <Box mr="4" textAlign="right">
          <Text>Otavio</Text>
          <Text color="gray.300" fontSize="small">
            otavio.tunes@email.com</Text>
        </Box>
      )}

      <Avatar size="md" name="Otavio" src="" />
    </Flex>
  );
}