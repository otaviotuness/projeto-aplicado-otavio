import { useContext } from "react";
import { AuthContext } from "../contex/AuthContext";

type UseCanParams = {
  roles?: string[];
};

export function useCan({roles}: UseCanParams) {
  const {user, isAuthenticated} = useContext(AuthContext)

  console.log(user, isAuthenticated);

  if (!isAuthenticated){
    return false;
  }

  if (roles?.length > 0){
    const hasAllRoles = roles.some(role => {
      return user.roles.includes(role)
    });

    if (!hasAllRoles){
      return false;
    }
  }

  return true;
}