package lk.bookshop.bookshopbackend.service;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lk.bookshop.bookshopbackend.dto.UserProfileDto;
import lk.bookshop.bookshopbackend.entity.User;
import lk.bookshop.bookshopbackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${user.upload.directory}")
    private String uploadDirectory;

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return user;
        }

        return null;
    }

    @Override
    public User updateUser(Long id, UserProfileDto user) {
        User existsUser = userRepository.findById(id).orElse(null);

        if (existsUser != null) {
            MultipartFile file = user.getProfileImage();
            String fileName = file.getOriginalFilename();
            String filePath = uploadDirectory + File.separator + fileName;

            try {
                file.transferTo(new File(filePath));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            existsUser.setProfileImage(fileName);
            return userRepository.save(existsUser);
        }

        return null;

    }

}
