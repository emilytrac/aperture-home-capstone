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

	@Autowired
	private ConsumerService consumerService;
	
	// showing login page 
	@RequestMapping("/")
	public ModelAndView getLoginController() {
		return new ModelAndView("index");
	}
	
	// navigating to home page if login is successful - otherwise prompted to enter details again
	
	@RequestMapping("/login")
	public ModelAndView getHomeController(@RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		User user = consumerService.loginCheck(userEmail, userPassword);
			
		// successful login
		if (user != null) {
			modelAndView.addObject("user", user);
			session.setAttribute("user", user);
			
			ProductList products = consumerService.showAllProducts();
			List<Product> listOfProducts = products.getProducts();
			modelAndView.addObject("products", listOfProducts);
			
			modelAndView.setViewName("homepage");
		// login fails
		} else {
			modelAndView.addObject("message", "Invalid user credentials, please try again");
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	
	// navigating to homepage once in the app - from any page within the app
	
	@RequestMapping("/homepage")
	public ModelAndView getHomepageController() {
		ModelAndView modelAndView=new ModelAndView();
		
		ProductList products = consumerService.showAllProducts();
		List<Product> listOfProducts = products.getProducts();
		modelAndView.addObject("products", listOfProducts);
		
		modelAndView.setViewName("homepage");
		return modelAndView;
	}
	
	// controllers for adding a new item
	
	@RequestMapping("/addItem") 
	public ModelAndView addProductController() {
		return new ModelAndView("addnew");
	}
	
	@RequestMapping("/added")
	public ModelAndView processAddController(@RequestParam("productName") String productName, @RequestParam("productCategory") String productCategory, @RequestParam("quantityAvailable") int quantityAvailable, 
			@RequestParam("pricePerItem") double pricePerItem, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		Product product = consumerService.addNewProduct(productName, productCategory, quantityAvailable, pricePerItem, 0);
		String message;
		
		if(product != null) 
			message = "Product added.";
		else
			message = "Something went wrong, this product may already exist.";
		
		modelAndView.addObject("message", message);
		modelAndView.setViewName("addnew");
		return modelAndView;
	}
	
	// controllers for updating an new item
	
	@RequestMapping("/updateItem") 
	public ModelAndView updateProductController() {
		return new ModelAndView("update");
	}
	
	@RequestMapping("/updated")
	public ModelAndView processUpdateController(@RequestParam("productName") String productName, @RequestParam("quantity") int quantity) {
		ModelAndView modelAndView=new ModelAndView();
		String message;
		
		if (quantity <= 0) {
			message = "Please enter a positive amount";
		} else {
			if (consumerService.updateProduct(productName, quantity) != null) {
				message = "Inventory stock of " + productName + " increased by " + quantity;
			} else {
				message = "Something went wrong. This product may not exist";
			}
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("update");
		return modelAndView;
	}
	
	// controllers for deleting an item
	
	@RequestMapping("/deleteItem") 
	public ModelAndView deleteProductController() {
		return new ModelAndView("delete");
	}
	
	@RequestMapping("/deleted")
	public ModelAndView processDeleteController(@RequestParam("productName") String productName) {
		ModelAndView modelAndView=new ModelAndView();
		
		consumerService.deleteProduct(productName);
		
		String message = "Product deleted";
			
		modelAndView.addObject("message", message);
		modelAndView.setViewName("delete");
		return modelAndView;
	}
	
	// controller to search inventory by either product name or category
	
	@RequestMapping("/search")
	public ModelAndView searchInventory(@RequestParam("keyword") String keyword) {
		ModelAndView modelAndView=new ModelAndView();
		ProductList products = new ProductList();
		
		try {
			if (keyword != null) 
				products = consumerService.searchByKeyword(keyword);
				List<Product> listOfProducts = products.getProducts();
				modelAndView.addObject("products", listOfProducts);
				
				modelAndView.setViewName("homepage");
				return modelAndView;
		} catch (Exception e) {
			products = consumerService.showAllProducts();
			List<Product> listOfProducts = products.getProducts();
			modelAndView.addObject("products", listOfProducts);
			
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
