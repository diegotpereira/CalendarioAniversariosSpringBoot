package br.com.java.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.java.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findByUser(String user);
	
	 @Query(value = "SELECT * FROM TODO WHERE first_name = ?1", nativeQuery = true)
	 public List<Todo> findByFirstName(String firstName);
	 
	 @Query(value = "SELECT * FROM TODO WHERE place = ?1", nativeQuery = true)
	 public List<Todo> findByPlace(String place);

}