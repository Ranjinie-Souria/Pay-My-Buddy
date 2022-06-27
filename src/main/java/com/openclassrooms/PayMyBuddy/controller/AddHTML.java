package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.ui.ModelMap;

public class AddHTML {
	
	public static void addFooterHeader(ModelMap model) {
		model.addAttribute("header", "header");
		model.addAttribute("footer", "footer");
	}

}
