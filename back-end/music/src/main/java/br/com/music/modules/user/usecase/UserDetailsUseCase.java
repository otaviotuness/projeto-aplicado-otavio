package br.com.music.modules.user.usecase;

import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.music.modules.user.enumeration.RoleEnum.ADMIN;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsUseCase implements UserDetailsService {

    private final UserUseCase userUseCase;
    private final UserInfo userInfo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final var user = userUseCase.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        } else {
            final var role = user.getRoles().stream().map(RoleDomain::getRoleName).findAny().orElse("");

            userInfo.setName(user.getName());
            userInfo.setEmail(user.getEmail());
            userInfo.setRoles(user.getRoles());
            userInfo.setUserId(user.getId());
            userInfo.setUserIdMaster(user.getId_master() == null ? 0 : user.getId_master());
            userInfo.setRole(role);
            userInfo.setAdmin(role.equals(ADMIN.getRoleName()));
        }

        return user;
    }
}
