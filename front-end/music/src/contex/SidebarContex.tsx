import { useDisclosure, UseDisclosureReturn } from "@chakra-ui/react";
import { useRouter } from "next/router";
import  { createContext, ReactNode, useContext, useEffect } from "react"

interface SiderbarDrawerProviderProps {
  children: ReactNode;
}

type SidebarDrawerContexData = UseDisclosureReturn

const SiderbarDrawerContex = createContext({} as SidebarDrawerContexData);

export function SidebarDrawerProvider({children}: SiderbarDrawerProviderProps){
  const disclosure = useDisclosure()
  const router = useRouter()

  useEffect(() => {
    disclosure.onClose()
  }, [router.asPath])

  return (
    <SiderbarDrawerContex.Provider value={disclosure}>
      {children}
    </SiderbarDrawerContex.Provider>
  )
}

export const useSidebarDrawer = () => useContext(SiderbarDrawerContex)