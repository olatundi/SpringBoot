package com.manchester.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manchester.entities.Trainee;
import com.manchester.repository.TraineeRepository;


@RestController
//@RequestMapping("/trainee")
@CrossOrigin(origins = "http://localhost:3000")
public class DbController {
	
	private TraineeRepository repo;

	public DbController(TraineeRepository repo) {
		super();
		this.repo = repo;
	}
	
//	@RequestMapping(value = "/show/{id}", method = {RequestMethod.GET} )
//	public String showRecord(@RequestParam(value = "/show/{id}", required = false) int id) {
//		Optional<Trainee> t = repo.findById(id);
//		if (t.isPresent()) {
//			Trainee member = t.get();
//			return "Trainee record name"+member.getName();
//		}
//		else {
//		
//		return "This trainee does not exist";
//		}
//	}
	
	@RequestMapping(value = "/show/{id}", method = {RequestMethod.GET} )
	public String showRecord(@PathVariable(value = "id") int id) {
		Optional<Trainee> t = repo.findById(id);
		if (t.isPresent()) {
			Trainee member = t.get();
			return "Trainee record name: "+member.getName() + " city: " + member.getCity();
		}
		else {
			return "This trainee does not exist";
		}
	}
	
	@RequestMapping(value = "/find/{id}", method = {RequestMethod.GET} )
	public Trainee findRecord(@PathVariable(value = "id") int id) {
		Optional<Trainee> t = repo.findById(id);
		if (t.isPresent()) {
			Trainee member = t.get();
			return member;
		}
		return null;
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE} )
	public String deleteRecord(@PathVariable(value = "id") int id) {
		Optional<Trainee> t = repo.findById(id);
		if (t.isPresent()) {
			Trainee member = t.get();
			repo.delete(member);
			return "Trainee is gone";
		}
		else {
		return "This trainee does not exist";
		}
	}
	
	@RequestMapping(value = "/create/{name}/{city}", method = {RequestMethod.POST} )
	public String createRecord(@PathVariable(value = "name") String name, @PathVariable(value = "city") String city) {
		Trainee t = new Trainee();
		t.setName(name);
		t.setCity(city);
		repo.save(t);
		
		return "Trainee created";
	}
	
	@RequestMapping(value = "/create2/", method = {RequestMethod.POST} )
	public String createRecord2(@RequestBody Trainee T) {

		repo.save(T);
		return "Trainee created";
	}
	
	@RequestMapping(value = "/update/{id}/{name}/{city}", method = {RequestMethod.POST} )
	public String updateRecord1(@PathVariable(value = "id") int id, @PathVariable(value = "name") String name, @PathVariable(value = "city") String city) {
		Optional<Trainee> t = repo.findById(id);
		if (t.isPresent()) {
			Trainee member = t.get();
			member.setName(name);
			member.setCity(city);
			repo.save(member);
			return "Trainee is updated";
		}
		else {
		return "Trainee does not exist";}
	}
	
	@RequestMapping(value = "/update2/{id}", method = {RequestMethod.POST} )
	public String updateRecord2(@PathVariable(value = "id") int id, @RequestBody Trainee T) {
		Optional<Trainee> t = repo.findById(id);
		if (t.isPresent()) {
			Trainee member = t.get();
			member.setName(T.getName());
			member.setCity(T.getCity());
			repo.save(member);
			return "Trainee is updated";
		}
		else {
		return "Trainee does not exist";}
	}
	
	
	@RequestMapping(value = "/showAll", method = {RequestMethod.GET} )
	public List<Trainee> showAllRecord() {
		List<Trainee> t = repo.findAll();
		return t;
	}

}
	
