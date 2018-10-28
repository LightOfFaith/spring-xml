package com.share.lifetime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.share.lifetime.entity.Owner;

@Controller
@RequestMapping("owners")
public class OwnersController {

	@GetMapping("/{ownerId}")
	public String findOwner(@PathVariable String ownerId, Model model) {
//		Owner owner = ownerService.findOwner(ownerId);
//		model.addAttribute("owner", owner);
		model.addAttribute("owner", "owner");
		return "displayOwner";
	}

}
