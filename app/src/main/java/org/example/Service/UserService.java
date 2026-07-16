package org.example.Service;

import org.example.Dto.CreateUserRepositoryDto;
import org.example.Entity.User;
import org.example.ErrorHandling.DuplicateResourceException;
import org.example.ErrorHandling.UserIdMissingException;
import org.example.ErrorHandling.UserNotFoundException;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("No user found with this id"));
    }

    public User findByUserName(String userName){
        if(userName == null){
            throw new UserIdMissingException("Value given is null");
        }
        User user = userRepository.findByUserName(userName);
        if(user == null){
            throw new UserNotFoundException("No user found with this username");
        }
        return user;
    }

    public CreateUserRepositoryDto createUser(CreateUserRepositoryDto dto){
        if(userRepository.existsByUserName(dto.getUsername())){
            throw new DuplicateResourceException("Username already exists");
        }
        User saved = userRepository.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    public CreateUserRepositoryDto updateUser(Long id, CreateUserRepositoryDto dto){
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user found with this id"));
        existing.setUserName(dto.getUsername());
        existing.setName(dto.getName());
        existing.setIssuedBook(dto.isIssuedBook());
        User saved = userRepository.save(existing);
        return mapToDto(saved);
    }

    public void deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else throw new UserNotFoundException("No user found with this id");
    }

    private CreateUserRepositoryDto mapToDto(User user) {
        return new CreateUserRepositoryDto(user.getId(), user.getUserName(), user.getName(), user.isIssuedBook());
    }

    private User mapToEntity(CreateUserRepositoryDto dto) {
        User user = new User();
        user.setUserName(dto.getUsername());
        user.setName(dto.getName());
        user.setIssuedBook(dto.isIssuedBook());
        return user;
    }
}
