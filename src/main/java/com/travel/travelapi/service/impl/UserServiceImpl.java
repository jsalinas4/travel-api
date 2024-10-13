package com.travel.travelapi.service.impl;

import com.travel.travelapi.dto.AuthResponseDTO;
import com.travel.travelapi.dto.LoginDTO;
import com.travel.travelapi.dto.UserProfileDTO;
import com.travel.travelapi.dto.UserRegistrationDTO;
import com.travel.travelapi.entity.Role;
import com.travel.travelapi.entity.User;
import com.travel.travelapi.enums.ERole;
import com.travel.travelapi.mapper.UserMapper;
import com.travel.travelapi.repository.RoleRepository;
import com.travel.travelapi.repository.UserRepository;
import com.travel.travelapi.security.TokenProvider;
import com.travel.travelapi.service.UserService;
import lombok.RequiredArgsConstructor;
import com.travel.travelapi.exception.BadRequestException;
import com.travel.travelapi.exception.InvalidCredentialsException;
import com.travel.travelapi.exception.ResourceNotFoundException;
import com.travel.travelapi.exception.RoleNotFoundException ;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager; // Necesario para la autenticación
    private final TokenProvider tokenProvider; // Necesario para la creación de tokens JWT

    public UserProfileDTO register(UserRegistrationDTO registrationDTO){
        return registerUserWithRole(registrationDTO, ERole.USER);
    }

    public UserProfileDTO registerUserWithRole(UserRegistrationDTO registrationDTO, ERole roleEnum) {

    // Verificar si el email ya está registrado o si ya existe un usuario con el mismo nombre y apellido
    boolean emailExists = userRepository.existsByEmail(registrationDTO.getEmail());
    boolean existsAsUser = userRepository.existsByFirstNameAndLastName(registrationDTO.getFirstName(), registrationDTO.getLastName());
    if (emailExists) {
        throw new UsernameNotFoundException("El email ya está registrado");
    }

    if (existsAsUser) {
        throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
    }


    // Asignar el rol del usuario
    Role role = roleRepository.findByName(roleEnum)
        .orElseThrow(() -> new RoleNotFoundException("Rol no encontrado"));

    // Cifrar la contraseña
    registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

    // Convertir el DTO a una entidad User
    User user = userMapper.toUserEntity(registrationDTO);
    user.setRole(role); // Asignar el rol al usuario

    // Asignar la entidad específica basada en el rol
    if (roleEnum == ERole.USER) {
        // Crear un cliente
            
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setRegistrationDate(registrationDTO.getRegistrationDate());
        user.setNationality(registrationDTO.getNationality());
        user.setBirthdate(registrationDTO.getBirthdate()); // Enlazar el cliente con el usuario
        
    }
    // Guardar el usuario en la base de datos
    User savedUser = userRepository.save(user);

    // Convertir el usuario registrado a UserProfileDTO para la respuesta
    return userMapper.toUserProfileDTO(savedUser);
        
    
    }

    @Transactional
    @Override
    public UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO) {
        // Buscar el usuario por su ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // Verificar si ya existe un cliente o autor con el mismo nombre y apellido (excepto el usuario actual)
        // La verificación se realiza excluyendo el usuario actual para permitir que actualice su propio perfil
        // sin que se genere un conflicto de duplicidad en los nombres y apellidos.
        boolean existsAsUser = userRepository.existsByFirstNameAndLastNameAndIdNot(userProfileDTO.getFirstName(), userProfileDTO.getLastName(), id);

        if (existsAsUser) {
            throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
        }


            user.setFirstName(userProfileDTO.getFirstName());
            user.setLastName(userProfileDTO.getLastName());
        
        // Guardar los cambios en la base de datos
        User updatedUser = userRepository.save(user);

        // Convertir el usuario actualizado a UserProfileDTO para la respuesta
        return userMapper.toUserProfileDTO(updatedUser);
    }



    @Transactional
    @Override
    public UserProfileDTO getUserProfileById(Integer id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // Convertir a UserProfileDTO para la respuesta
        return userMapper.toUserProfileDTO(user);
    }

    @Transactional
    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        // Buscar el usuario por email
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + loginDTO.getEmail()));

        // Verificar si la contraseña es correcta
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Credenciales incorrectas");
        }

        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        // Generar el token JWT usando el TokenProvider
        String token = tokenProvider.createAccessToken(authentication);

        // Generar la respuesta de autenticación, con el rol correspondiente
        AuthResponseDTO response = userMapper.toAuthResponseDTO(user, token);

        // Retornar la respuesta
        return response;
    }

}
