package com.nighthawk.spring_portfolio.mvc.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonRoleJpaRepository;

import java.util.Date;



public class AssignmentDetailsService {
    // Encapsulate many object into a single Bean (Person, Roles, and Scrum)
    @Autowired  // Inject PersonJpaRepository
    private AssignmentJpaRepository assignmentJpaRepository;

    /* UserDetailsService Overrides and maps Person & Roles POJO into Spring Security */
    // @Override
    // public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //     Person person = personJpaRepository.findByEmail(email); // setting variable user equal to the method finding the username in the database
    //     if(person==null) {
	// 		throw new UsernameNotFoundException("User not found with username: " + email);
    //     }
    //     Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    //     person.getRoles().forEach(role -> { //loop through roles
    //         authorities.add(new SimpleGrantedAuthority(role.getName())); //create a SimpleGrantedAuthority by passed in role, adding it all to the authorities list, list of roles gets past in for spring security
    //     });
    //     // train spring security to User and Authorities
    //     return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), authorities);
    // }

    /* Person Section */

    public  List<Assignment>listAll() {
        return assignmentJpaRepository.findAllByOrderByNameAsc();
    }

    // custom query to find match to name or email
    /*
    public  List<Person>list(String name, arraylist classes) {
        return assignmentJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name, classes);
    }
    */

    // custom query to find anything containing term in name or email ignoring case
    /*
    public  List<Person>list(Date dateCreated, Date dateDue) {
        return assignmentJpaRepository.findByDateCreatedAndDateDue(dateCreated, dateDue);
    }
    */

    // custom query to find anything containing term in name or email ignoring case
    public  List<Assignment>listLikeNative(String term) {
        String like_term = String.format("%%%s%%",term);  // Like required % rappers
        return assignmentJpaRepository.findByLikeTermNative(like_term);
    }

    public Assignment get(long id) {
        return (assignmentJpaRepository.findById(id).isPresent())
                ? assignmentJpaRepository.findById(id).get()
                : null;
    }

    public List<Assignment> findByName(String name) {
        return (assignmentJpaRepository.findByName(name));
    }

    public List<Assignment> findByCreatedBy(Person createdBy) {
        return (assignmentJpaRepository.findByCreatedBy(createdBy));
    }

    public void delete(long id) {
        assignmentJpaRepository.deleteById(id);
    }
}
