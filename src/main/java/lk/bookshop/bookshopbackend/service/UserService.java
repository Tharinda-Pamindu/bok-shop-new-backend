package lk.bookshop.bookshopbackend.service;

import org.springframework.stereotype.Service;
import lk.bookshop.bookshopbackend.dto.UserProfileDto;
import lk.bookshop.bookshopbackend.entity.User;

@Service
public interface UserService {
    
    User getUserById(Long id);

    User updateUser(Long id, UserProfileDto user);
}
