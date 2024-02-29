package com.uktamjon.sodikov.services;

import com.uktamjon.sodikov.domains.User;
import com.uktamjon.sodikov.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordGeneratorService passwordGenerator;

    @Transactional
    public User createUser(User user) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Creating user: {}", user);
        user.setUsername(generateUserName(user.getFirstName(), user.getLastName()));
        String generatedPassword = passwordGenerator.generateRandomPassword(10);
        user.setPassword(passwordGenerator.encryptPassword(generatedPassword));
        log.info("User created: {}", user);
        User savedUser = userRepository.save(user);
        savedUser.setPassword(generatedPassword);
        MDC.clear();
        return savedUser;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        MDC.put("transactionId", generateTransactionId());
        log.info("Getting all users");
        List<User> users = userRepository.findAll();
        MDC.clear();
        return users;
    }

    @Transactional(readOnly = true)
    public User getUserById(int userId) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Getting user by id: {}", userId);
        User user = userRepository.findById(userId).orElse(null);
        MDC.clear();
        return user;
    }

    @Transactional
    public void updateUser(User user) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Updating user: {}", user);
        User userById = getUserById(user.getId());

        if (user.getPassword() != null) {
            user.setPassword(passwordGenerator.encryptPassword(user.getPassword()));
        }
        user.setUsername(userById.getUsername());
        userRepository.save(user);
        log.info("User updated: {}", user);
        MDC.clear();
    }

    @Transactional
    public void deleteUserById(int userId) {
        MDC.put("transactionId", generateTransactionId());
        userRepository.deleteById(userId);
        log.info("User deleted: {}", userId);
        MDC.clear();
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Getting user by username: {}", username);
        User user = userRepository.findByUsername(username);
        MDC.clear();
        return user;
    }

    @Transactional
    public boolean changePassword(String password, String username) {
        MDC.put("transactionId", generateTransactionId());
        User user = getUserByUsername(username);
        if (checkingUserAndPasswordMatching(password, user)) {
            MDC.clear();
            return false;
        }
        user.setPassword(password);
        updateUser(user);
        MDC.clear();
        return true;
    }

    @Transactional
    public boolean changePassword(String password, int id) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Changing password for user with id: {}", id);
        User user = getUserById(id);
        if (checkingUserAndPasswordMatching(password, user)) {
            MDC.clear();
            return false;
        }
        user.setPassword(passwordGenerator.encryptPassword(password));
        updateUser(user);
        log.info("Password changed for user with id: {}", id);
        MDC.clear();
        return true;
    }

    @Transactional(readOnly = true)
    public List<User> findByCriteria(String username) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Getting users by criteria: {}", username);
        List<User> users = userRepository.findAllByUsernameContains(username);
        MDC.clear();
        return users;
    }

    @Transactional
    public void deleteByUsername(String username) {
        MDC.put("transactionId", generateTransactionId());
        if (getUserByUsername(username) == null) {
            log.error("User not found by username: {}", username);
            MDC.clear();
            return;
        }
        log.info("Deleting user by username: {}", username);
        userRepository.deleteByUsername(username);
        MDC.clear();
    }

    @Transactional
    public boolean activateAndDeactivate(String username) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Changing active status for user: {}", username);
        User user = getUserByUsername(username);
        if (user == null) {
            log.error("User not found by username: {}", username);
            MDC.clear();
            return false;
        }
        user.setActive(!user.isActive());
        updateUser(user);
        log.info("Active status changed for user: {}", username);
        MDC.clear();
        return true;
    }

    @Transactional
    public boolean activateAndDeactivate(int id) {
        MDC.put("transactionId", generateTransactionId());
        log.info("Changing active status for user: {}", id);
        User user = getUserById(id);
        if (user == null) {
            log.error("User not found by user id: {}", id);
            MDC.clear();
            return false;
        }
        user.setActive(!user.isActive());
        updateUser(user);
        log.info("Active status changed for user: {}", id);
        MDC.clear();
        return true;
    }

    private static boolean checkingUserAndPasswordMatching(String password, User user) {
        if (user == null) {
            log.error("User not found");
            return true;
        }
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password)) {
            log.error("Password is not valid");
            return true;
        }
        return false;
    }

    private String generateTransactionId() {
        return "TX-" + System.currentTimeMillis();  // Replace with your actual transaction ID generation logic
    }

    private String generateUserName(String firstName, String lastName) {
        MDC.put("transactionId", generateTransactionId());
        String baseUsername = firstName + "." + lastName;

        boolean usernameExists = userRepository.existsByUsername(baseUsername);

        String generatedUsername = usernameExists ? baseUsername + getUserNameSuffix() : baseUsername;

        log.info("Generated username: {}", generatedUsername);
        MDC.clear();
        return generatedUsername;
    }

    private static Long userNameSuffix = 0L;

    private static Long getUserNameSuffix() {
        userNameSuffix++;
        log.debug("Generated User Name Suffix: {}", userNameSuffix);
        return userNameSuffix;
    }
}
