import { extendTheme } from '@chakra-ui/react'

export const theme = extendTheme({
  fonts:{
    heading: 'Roboto',
    body: 'Roboto'
  },
  styles: {
    global:{
      body:{
        bg: 'purple.900',
        color: 'gray.50'
      }
    }
  }
})