import { Flex, Box, Text, Avatar } from "@chakra-ui/react";

interface ProfileProps {
  showProfileData?: boolean;
}

export function Profile({showProfileData = true}: ProfileProps){
  return (
    <Flex align="center"> 

      { showProfileData &&  (
        <Box mr="4" textAlign="right">
          <Text>otavio</Text>
          <Text color="gray.300" fontSize="small">
            email
          </Text>
        </Box>
      )}

      <Avatar size="md" name="email" src="" />
    </Flex>
  );
}