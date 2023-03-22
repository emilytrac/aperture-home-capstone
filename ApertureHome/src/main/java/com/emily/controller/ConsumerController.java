package com.emily.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.entity.User;
import com.emily.model.service.ConsumerService;
import com.emily.entity.PdfExport;
import com.lowagie.text.DocumentException;

@Controller
public class ConsumerController {
	
	// Creating the view for the user to interact with!

	// Will automatically allow access to ConsumerServiceImpl methods
	@Autowired
	private ConsumerService consumerService;
	
	// requestmapping creates actions for href in HTML pages to show new view
	@RequestMapping("/")
	public ModelAndView getLoginController() {
		// returns index.html and all associated formatting on localhost:8086
		return new ModelAndView("index");
	}
	
	// navigating to home page if login is successful - otherwise prompted to enter details again
	@RequestMapping("/login")
	public ModelAndView getHomeController(@RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		User user = consumerService.loginCheck(userEmail, userPassword);
			
		// successful login
		if (user != null) {
			// creating user session and setting attribute
			modelAndView.addObject("user", user);
			session.setAttribute("user", user);
			
			// using service to get all products to be shown on the homepage of app
			ProductList products = consumerService.showAllProducts();
			List<Product> listOfProducts = products.getProducts();
			// adding products to the view - formatted in html
			modelAndView.addObject("products", listOfProducts);
			
			// sets view to show as homepage.html
			modelAndView.setViewName("homepage");
		// login fails
		} else {
			// setting view to be login page again if login fails with message
			modelAndView.addObject("message", "Invalid user credentials, please try again");
			modelAndView.setViewName("index");
		}
		// return either homepage.html or index.html
		return modelAndView;
	}
	
	// navigating to homepage once in the app - from any page within the app
	@RequestMapping("/homepage")
	public ModelAndView getHomepageController() {
		ModelAndView modelAndView=new ModelAndView();
		
		// using service to get all products to be shown on the homepage of app
		ProductList products = consumerService.showAllProducts();
		List<Product> listOfProducts = products.getProducts();
		modelAndView.addObject("products", listOfProducts);
		
		// sets view to show as homepage.html and returns it
		modelAndView.setViewName("homepage");
		return modelAndView;
	}
	
	// controllers for adding a new item
	
	@RequestMapping("/addItem") 
	public ModelAndView addProductController() {
		// sets view to show as addnew.html and returns it
		return new ModelAndView("addnew");
	}
	
	// user is prompted to enter parameters for the creation of a new product object
	@RequestMapping("/added")
	public ModelAndView processAddController(@RequestParam("productName") String productName, @RequestParam("productCategory") String productCategory, @RequestParam("quantityAvailable") int quantityAvailable, 
			@RequestParam("pricePerItem") double pricePerItem, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		// use service to add a new product
		Product product = consumerService.addNewProduct(productName, productCategory, quantityAvailable, pricePerItem, 0);
		String message;
		
		// return relevant message dependent on whether product object is returned or null
		if(product != null) 
			message = "Product added.";
		else
			message = "Something went wrong, this product may already exist.";
		
		// add message to the view
		modelAndView.addObject("message", message);
		// sets view to show as addnew.html and returns it
		modelAndView.setViewName("addnew");
		return modelAndView;
	}
	
	// controllers for updating an new item
	
	@RequestMapping("/updateItem") 
	public ModelAndView updateProductController() {
		// sets view to show as update.html and returns it
		return new ModelAndView("update");
	}
	
	// user is prompted to enter a product and amount to update by
	@RequestMapping("/updated")
	public ModelAndView processUpdateController(@RequestParam("productName") String productName, @RequestParam("quantity") int quantity) {
		ModelAndView modelAndView=new ModelAndView();
		String message;
		
		// checks for a positive amount of additional stock
		if (quantity <= 0) {
			message = "Please enter a positive amount";
		} else {
			// return relevant message dependent on whether product object is returned or null
			// uses service method
			if (consumerService.updateProduct(productName, quantity) != null) {
				message = "Inventory stock of " + productName + " increased by " + quantity;
			} else {
				message = "Something went wrong. This product may not exist";
			}
		}
		// add message to the view
		modelAndView.addObject("message", message);
		// sets view to show as update.html and returns it
		modelAndView.setViewName("update");
		return modelAndView;
	}
	
	// controllers for deleting an item
	
	@RequestMapping("/deleteItem") 
	public ModelAndView deleteProductController() {
		// sets view to show as delete.html and returns it
		return new ModelAndView("delete");
	}
	
	// user is prompted to enter a product to delete
	@RequestMapping("/deleted")
	public ModelAndView processDeleteController(@RequestParam("productName") String productName) {
		ModelAndView modelAndView=new ModelAndView();
		
		// using service method to delete the product
		consumerService.deleteProduct(productName);
		
		String message = "Product deleted";
			
		// add message to the view
		modelAndView.addObject("message", message);
		// sets view to show as delete.html and returns it
		modelAndView.setViewName("delete");
		return modelAndView;
	}
	
	// controller to search inventory by either product name or category
	
	@RequestMapping("/search")
	public ModelAndView searchInventory(@RequestParam("keyword") String keyword) {
		ModelAndView modelAndView=new ModelAndView();
		// instantiating new ProductList
		ProductList products = new ProductList();
		
		try {
			// if keyword is entered, use service method to return list with relevant products
			if (keyword != null) 
				products = consumerService.searchByKeyword(keyword);
				List<Product> listOfProducts = products.getProducts();
				modelAndView.addObject("products", listOfProducts);
				
				// sets view to show as homepage.html and returns it
				modelAndView.setViewName("homepage");
				return modelAndView;
		// if exception occurs i.e. nothing entered in the search bar, all products returned		
		} catch (Exception e) {
			// returning a list of all products
			products = consumerService.showAllProducts();
			List<Product> listOfProducts = products.getProducts();
			modelAndView.addObject("products", listOfProducts);
			
			// sets view to show as homepage.html and returns it
			modelAndView.setViewName("homepage");
			return modelAndView;
		}
		
	}
	
	// controller to get product report
	
	@GetMapping(path = "/reports", produces = MediaType.APPLICATION_PDF_VALUE)
	public void reportResource(HttpServletResponse response) throws DocumentException, IOException {
		
		// getting list of products to add to the report
		ProductList products = consumerService.generateProductReport();
		List<Product> listOfProducts = products.getProducts();
		
		// setting the name of the pdf file to the current date-time for version control
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=products_" +currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		// calling pdf entity class to export the pdf
		PdfExport exporter = new PdfExport(listOfProducts);
		exporter.export(response);
		
	}
	
}
