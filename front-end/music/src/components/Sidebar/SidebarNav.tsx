import { Stack, useConst } from "@chakra-ui/react";
import { RiContactsLine, RiDashboardLine, RiGitMergeLine, RiInputMethodLine } from "react-icons/ri";
import { Can } from "../authorization/Can";
import { NavLink } from "./NavLink";
import { NavSection } from "./NavSection";

export function SidebarNav(){
  return (
    <Stack spacing="12" align="flex-start">
      <NavSection title="GERAL">
        <Can roles={['ADMIN']}>
          <NavLink icon={RiDashboardLine} href="/dashboard">Dashboard</NavLink>
        </Can>

        <Can roles={['ADMIN', 'MUSICIAN']}>
          <NavLink icon={RiContactsLine} href="/users">Usuários</NavLink>
        </Can>
      </NavSection>
      <NavSection title="AUTOMAÇÃO">
        <NavLink icon={RiInputMethodLine} href="/users">Formulários</NavLink>
        <NavLink icon={RiGitMergeLine} href="/users">Automação</NavLink>
      </NavSection>
    </Stack>
  );
}