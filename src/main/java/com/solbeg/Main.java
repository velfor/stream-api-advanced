package com.solbeg;


import com.solbeg.exception.EntityNotFoundException;
import com.solbeg.exception.ExerciseNotCompletedException;
import com.solbeg.model.User;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class Main {
    private final Collection<User> users;

    /**
     * Find the richest user. If there are several users with the same balance, return any of them.
     *
     * @return user with max balance wrapped with optional
     */
    public Optional<User> findRichestUser() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Find Users that have a birthday in a particular month.
     *
     * @param birthdayMonth a month of birth
     * @return a list of Users
     */
    public List<User> findUsersByBirthdayMonth(Month birthdayMonth) {
        throw new ExerciseNotCompletedException();
    }


    /**
     * Returns a Map that stores users grouped by its email domain. A map key is String which is an
     * email domain like "gmail.com". And the value is a List<User> that contains all users with such email domain.
     *
     * @return Map<String, List < User>> where key is email domain and value is a list of users
     */
    public Map<String, List<User>> groupUsersByEmailDomain() {
        throw new ExerciseNotCompletedException();
    }


    /**
     * @return total balance of all users
     */
    public BigDecimal calculateTotalBalance() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * @return list of users sorted by first and last names
     */
    public List<User> sortByFirstAndLastNames() {
        throw new ExerciseNotCompletedException();
    }

    /**
     * @param emailDomain - for example "gmail.com"
     * @return true if there is a user that has an email with provided domain
     */
    public boolean containsUserWithEmailDomain(String emailDomain) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Returns user balance by its email. Throws {@link EntityNotFoundException}
     * with message "Cannot find User by email={email}" if user is not found.
     *
     * @param email user email
     * @return user balance
     */
    public BigDecimal getBalanceByEmail(String email) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Collects all existing users into a {@link Map} where a key is user id, and the value is {@link User} instance
     *
     * @return map of users by its ids
     */
    public Map<Long, User> collectUsersById() {
        throw new ExerciseNotCompletedException();
    }


    /**
     * Returns a {@link Map} where key is {@link User#getLastName()} and values is a {@link Set} that contains first names
     * of all users with a specific last name.
     *
     * @return a map where key is a last name and value is a set of first names
     */
    public Map<String, Set<String>> groupFirstNamesByLastNames() {
        throw new ExerciseNotCompletedException();
    }
}

