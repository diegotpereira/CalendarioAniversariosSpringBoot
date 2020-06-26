package br.com.java.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.java.model.Todo;
import br.com.java.service.TodoRepository;



@Controller
public class TodoController {

	@Autowired
	private TodoRepository repository;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodosList(ModelMap model) {
		String user = getLoggedInUserName();
		model.addAttribute("todos", repository.findByUser(user));
		return "list-todos";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(ModelMap model) {
		return "search";
	}
	
	@RequestMapping(value = "/searchbyname", method = RequestMethod.GET)
	public String showTodosListByName(ModelMap model,@RequestParam String firstname) {
			model.addAttribute("todos", repository.findByFirstName(firstname));
		return "list-todos";
	}
	
	@RequestMapping(value = "/searchbyplace", method = RequestMethod.GET)
	public String showTodosListByPlace(ModelMap model,@RequestParam String place) {
			model.addAttribute("todos", repository.findByPlace(place));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUser(getLoggedInUserName());
		repository.save(todo);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-todos";
	}

	private String getLoggedInUserName() {
		return "aadhu";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
		model.addAttribute("todo", repository.findById(id));
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUser(getLoggedInUserName());
		repository.save(todo);

		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		repository.deleteById(id);
		// service.deleteTodo(id);

		return "redirect:/list-todos";
	}

}
