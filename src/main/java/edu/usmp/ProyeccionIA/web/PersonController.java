package edu.usmp.ProyeccionIA.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.usmp.ProyeccionIA.model.Person;
import edu.usmp.ProyeccionIA.repository.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/person/new")
	public String initCreationForm(Model model) {
		model.addAttribute("person", new Person());
		return "personForm";
	}
	
	@PostMapping
	public String submitForm(@Valid Person person, BindingResult bindingResult) {
		if(bindingResult.hasFieldErrors()) {
			return "personForm";
		}
		
		person.setResulDni(String.format("%08d", person.getDni()));
		
		person.setIngAnual(person.getIngInd()*12+person.getIngDep()*14);
		
		 if (person.getIngAnual()<7*4050) {
             person.setRentGravable(person.getIngAnual());}
             else {                 
            	 person.setRentGravable(person.getIngAnual() - 7 * 4050);
             }
		
		 if(person.getRentGravable()<=20250){
			 person.setImpTotal((double)Math.round(person.getRentGravable()*0.08));
			 person.setIngTotal(person.getIngAnual()-person.getImpTotal());
		 }
         
		 if(person.getRentGravable()<=81000 && person.getRentGravable()>20250){
			 person.setImpTotal((double)Math.round(20250*0.08+((person.getRentGravable()-20250)*0.14)));
			 person.setIngTotal(person.getIngAnual()-person.getImpTotal());
		 }
		 
		 if(person.getRentGravable()<=141750 && person.getRentGravable()>81000){
			 person.setImpTotal((double)Math.round(20250*0.08+60750*0.14+
					 ((person.getRentGravable()-81000)*0.17)));
			 person.setIngTotal(person.getIngAnual()-person.getImpTotal());
		 }
		 
		 if(person.getRentGravable()<=182250 && person.getRentGravable()>141750){
			 person.setImpTotal((double)Math.round(20250*0.08+60750*0.14+60750*0.17+
					 ((person.getRentGravable()-141750)*0.2)));
			 person.setIngTotal(person.getIngAnual()-person.getImpTotal());
		 }
		 
		 if(person.getRentGravable()>182250){
			 person.setImpTotal((double)Math.round(20250*0.08+60750*0.14+60750*0.17+40500*0.2+
					 ((person.getRentGravable()-182250)*0.3)));
			 person.setIngTotal(person.getIngAnual()-person.getImpTotal());
		 }
		 		 		
		personRepository.save(person);
		return "redirect:/person/list";
	}
	
	@GetMapping("/person/list")
	public String list(Map<String, Object> model) {
		List<Person> persons = personRepository.findAll();
		model.put("persons", persons);
		return "listPerson";
	}
	
	
	
}
