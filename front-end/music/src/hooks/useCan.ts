import { useContext } from "react";
import { AuthContext } from "../contex/AuthContext";

type UseCanParams = {
  roles?: string[];
};

export function useCan({roles}: UseCanParams) {
  const {user, isAuthenticated} = useContext(AuthContext)

  console.log(user, isAuthenticated);

  if (!isAuthenticated){
    console.log(1)
    return false;
  }

  if (roles?.length > 0){
    const hasAllRoles = roles.some(role => {
      console.log(2)
      return user.roles.includes(role)
    });

    if (!hasAllRoles){
      console.log(3)
      return false;
    }
  }

  console.log(4)

  return true;
}