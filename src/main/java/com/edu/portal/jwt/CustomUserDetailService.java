package com.edu.portal.jwt;

import com.edu.portal.user.UserDTO;
import com.edu.portal.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("mbrId", username);
        UserDTO user = userMapper.getLoginUser(map);

        return new User(user.getMbrId(), passwordEncoder.encode(user.getMbrPswd()), new ArrayList<>());
    }

}
