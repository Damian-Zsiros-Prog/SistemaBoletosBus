package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nuevo.springboot.reservas.app.models.dao.IBoletoDao;
import com.nuevo.springboot.reservas.app.models.entity.Boleto;



@Controller
public class BoletoController {

	@Autowired
	private IBoletoDao boletoDao;
	
	@GetMapping("/boleto/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE BOLETOS");
	    model.addAttribute("boletos", boletoDao.findAll());
	    return "listarBoleto";
	}

	@GetMapping("/boleto/form")
	public String crear(Model model) {
	    Boleto boleto = new Boleto();
	    model.addAttribute("boleto", boleto);
	    model.addAttribute("titulo", "Ingrese el boleto");
	    return "formBoleto";
	}
	
	@GetMapping("/boleto/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Boleto boleto = null;
		if(id > 0) {
			boleto = boletoDao.findOne(id);
		}else {
		return "redirect:listar";
		}
		model.addAttribute("boleto", boleto);
	    model.addAttribute("titulo", "Editar boleto");
		return "formBoleto";
	}
	
	@GetMapping("/boleto/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			boletoDao.delete(id);
		}
		return "redirect:/boleto/listar";
	}

	@PostMapping("/boleto/form")
	public String guardar(Boleto boleto) {
	    boletoDao.save(boleto);
	    return "redirect:listar";
	}
	
}