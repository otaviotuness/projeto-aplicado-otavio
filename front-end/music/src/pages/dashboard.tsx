import { Flex, SimpleGrid, Box, Text, theme } from "@chakra-ui/react"
import dynamic from 'next/dynamic'
import { useContext, useEffect } from "react"
import { Header } from "../components/Header"
import { Sidebar } from "../components/Sidebar"
import { AuthContext } from "../contex/AuthContext"
import { api } from "../services/api"

const Chart = dynamic(() => import('react-apexcharts'), {
  ssr: false,
})

const options={
  chart:{
    toolbar:{
      show: false,
    },
    zoom:{
      enabled: false,
    },
    foreColor: theme.colors.gray[500]
  },
  grid: {
    show: false,
  },
  dataLabels: {
    enabled: false,
  },
  tooltip: {
    enabled: false,
  },
  xaxis:{
    type: 'datetime',
    axisBorder: {
      color: theme.colors.gray[600]
    },
    axisTicks: {
      color: theme.colors.gray[600]
    },
    categories: [
      '2021-03-18T00:00:00.000Z',
      '2021-03-19T00:00:00.000Z',
      '2021-03-20T00:00:00.000Z',
      '2021-03-21T00:00:00.000Z',
      '2021-03-22T00:00:00.000Z',
      '2021-03-23T00:00:00.000Z',
      '2021-03-24T00:00:00.000Z',
    ]
  },
  fill: {
    opacity: 0.3,
    type: 'gradient',
    gradient: {
      shade: 'dark',
      opacityFrom: 0.7,
      opacityTo: 0.3
    }
  }
};

const series=[
  { name: 'series1', data: [23,32,5454,33,123,456,7] }
];

export default function Dashboard() {

  const { user } = useContext(AuthContext)

  useEffect(() => {
    api.get('/me').then(response => console.log(response))
  })

  return(
    <Flex
      direction="column"//uma ao lado da outra
      h="100vh" //altura toda da tela
    >
      <h1>{user?.email}</h1>
      <Header/>

      <Flex width="100%" my="6" maxWidth={1480} mx="auto" px="6">
        <Sidebar />

        <SimpleGrid flex="1" gap="4" minChildWidth="320px" alignItems="flex-start">
          <Box
            p={["6", "8"]}
            bg="gray.800"
            borderRadius={8}
            pb="4"
          >
            <Text fontSize="lg" mb="4">Inscritos da semana</Text>
            <Chart options={options} series={series}  type="area" height={160} />
          </Box>
          <Box
            p={["6", "8"]}
            bg="gray.800"
            borderRadius={8}
            pb="4"
          >
            <Text fontSize="lg" mb="4">Taxa de abertura</Text>
            <Chart options={options} series={series}  type="area" height={160} />
          </Box>
        </SimpleGrid>
      </Flex>
    </Flex>
  )
}