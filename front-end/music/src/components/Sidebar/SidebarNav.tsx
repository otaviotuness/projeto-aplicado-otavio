import { Stack, useConst } from "@chakra-ui/react";
import { useContext } from "react";
import { RiContactsLine, RiDashboardLine, RiGitMergeLine, RiInputMethodLine } from "react-icons/ri";
import { AuthContext } from "../../contex/AuthContext";
import { useCan } from "../../hooks/useCan";
import { NavLink } from "./NavLink";
import { NavSection } from "./NavSection";

export function SidebarNav(){
  const useRole = useCan({
    roles : ['ADMIN']
  })

  return (
    <Stack spacing="12" align="flex-start">
      <NavSection title="GERAL">
        <NavLink icon={RiDashboardLine} href="/dashboard">Dashboard</NavLink>
        { useRole && <NavLink icon={RiContactsLine} href="/users">Usuários</NavLink>}
      </NavSection>
      <NavSection title="AUTOMAÇÃO">
        <NavLink icon={RiInputMethodLine} href="/users">Formulários</NavLink>
        <NavLink icon={RiGitMergeLine} href="/users">Automação</NavLink>
      </NavSection>
    </Stack>
  );
}