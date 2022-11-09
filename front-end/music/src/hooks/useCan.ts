import { useContext } from "react";
import { AuthContext } from "../contex/AuthContext";
import { validateUserPermissionsParams } from "../utils/validateUserPermissions";

type UseCanParams = {
  roles?: string[];
};

export function useCan({roles}: UseCanParams) {
  const {user, isAuthenticated} = useContext(AuthContext)

  if (!isAuthenticated){
    return false;
  }

  const userHasValidPermissions = validateUserPermissionsParams({
    user,
    roles
  })

  return userHasValidPermissions;
}