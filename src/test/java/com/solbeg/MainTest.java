package com.solbeg;


import com.solbeg.exception.EntityNotFoundException;
import com.solbeg.model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest {

    private static final List<User> users = Arrays.asList(
            User.builder().id(1L).firstName("Justin").lastName("Butler").email("justin.butler@gmail.com").balance(BigDecimal.valueOf(172966)).birthDay(LocalDate.parse("2003-04-17")).createdOn(LocalDate.parse("2016-06-13")).build(),
            User.builder().id(2L).firstName("Olivia").lastName("Cardenas").email("cardenas@mail.com").balance(BigDecimal.valueOf(38029)).birthDay(LocalDate.parse("1930-01-19")).createdOn(LocalDate.parse("2014-06-21")).build(),
            User.builder().id(3L).firstName("Nolan").lastName("Donovan").email("nolandonovan@gmail.com").balance(BigDecimal.valueOf(13889)).birthDay(LocalDate.parse("1925-04-19")).createdOn(LocalDate.parse("2011-03-10")).build(),
            User.builder().id(4L).firstName("Lucas").lastName("Lynn").email("lucas.lynn@yahoo.com").balance(BigDecimal.valueOf(16980)).birthDay(LocalDate.parse("1987-05-25")).createdOn(LocalDate.parse("2009-03-05")).build());
    private final Main streams = new Main(users);

    @Test
    @Order(1)
    void findRichestUser() {
        var expectedPerson = Optional.of(users.get(0));
        var actualRichestPerson = streams.findRichestUser();

        assertEquals(expectedPerson, actualRichestPerson);
    }

    @Test
    @Order(2)
    void findUsersByBirthdayMonth() {
        var expectedList = getExpectedList();
        var aprilUsers = streams.findUsersByBirthdayMonth(Month.APRIL);

        assertEquals(expectedList, aprilUsers);
    }


    private List<User> getExpectedList() {
        return Arrays.asList(users.get(0), users.get(2));
    }

    @Test
    @Order(3)
    void groupUsersByEmailDomain() {
        var expectedEmailMap = getExpectedEmailMap();
        var emailDomainToUsersMap = streams.groupUsersByEmailDomain();

        assertEquals(expectedEmailMap, emailDomainToUsersMap);
    }

    private Map<String, List<User>> getExpectedEmailMap() {
        var expectedEmailMap = new HashMap<String, List<User>>();
        expectedEmailMap.put("gmail.com", Arrays.asList(users.get(0), users.get(2)));
        expectedEmailMap.put("mail.com", Arrays.asList(users.get(1)));
        expectedEmailMap.put("yahoo.com", Arrays.asList(users.get(3)));

        return expectedEmailMap;
    }


    @Test
    @Order(4)
    void calculateTotalBalance() {
        var totalBalance = streams.calculateTotalBalance();

        assertEquals(BigDecimal.valueOf(241864), totalBalance);
    }


    @Test
    @Order(5)
    void sortByFirstAndLastNames() {
        var sortedList = streams.sortByFirstAndLastNames();

        assertEquals(1L, sortedList.get(0).getId().longValue());
        assertEquals(4L, sortedList.get(1).getId().longValue());
        assertEquals(3L, sortedList.get(2).getId().longValue());
        assertEquals(2L, sortedList.get(3).getId().longValue());
    }

    @Test
    @Order(6)
    void containsUsersWithEmailDomain() {
        assertTrue(streams.containsUserWithEmailDomain("gmail.com"));
        assertTrue(streams.containsUserWithEmailDomain("yahoo.com"));
        assertFalse(streams.containsUserWithEmailDomain("blr.by"));
    }

    @Test
    @Order(7)
    void getBalanceByEmail() {
        var user = users.get(1);
        var balance = streams.getBalanceByEmail(user.getEmail());

        assertEquals(user.getBalance(), balance);
    }

    @Test
    @Order(8)
    void getBalanceByEmailThrowsException() {
        var fakeEmail = "fake@mail.com";
        var exception = assertThrows(EntityNotFoundException.class, () -> streams.getBalanceByEmail(fakeEmail));
        assertEquals(String.format("Cannot find User by email=%s", fakeEmail), exception.getMessage());
    }

    @Test
    @Order(9)
    void collectUsersById() {
        var idToUserMap = streams.collectUsersById();

        assertEquals(users.get(0), idToUserMap.get(1L));
        assertEquals(users.get(1), idToUserMap.get(2L));
        assertEquals(users.get(2), idToUserMap.get(3L));
        assertEquals(users.get(3), idToUserMap.get(4L));
    }


    @Test
    @Order(10)
    void groupFirstNamesByLastNames() {
        var lastToFirstNamesMap = streams.groupFirstNamesByLastNames();

        assertEquals(4, lastToFirstNamesMap.size());
        assertEquals(Set.of("Justin"), lastToFirstNamesMap.get("Butler"));
        assertEquals(Set.of("Olivia"), lastToFirstNamesMap.get("Cardenas"));
        assertEquals(Set.of("Nolan"), lastToFirstNamesMap.get("Donovan"));
        assertEquals(Set.of("Lucas"), lastToFirstNamesMap.get("Lynn"));
    }
}

