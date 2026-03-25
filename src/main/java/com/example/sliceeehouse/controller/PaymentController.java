// package com.example.sliceeehouse.controller;

// import com.example.sliceeehouse.model.Cart;
// import com.example.sliceeehouse.model.User;
// import com.example.sliceeehouse.repository.CartRepository;
// import com.example.sliceeehouse.repository.UserRepository;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// import java.util.List;

// @Controller
// public class PaymentController {

//     private final CartRepository cartRepository;
//     private final UserRepository userRepository;

//     public PaymentController(CartRepository cartRepository,
//                              UserRepository userRepository) {
//         this.cartRepository = cartRepository;
//         this.userRepository = userRepository;
//     }

//     // ================= PAYMENT PAGE =================
//     @GetMapping("/payment")
//     public String paymentPage(Model model) {

//         // Get logged-in user
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//         String username = auth.getName();

//         User user = userRepository.findByUsername(username).orElseThrow();

//         // Get cart items
//         List<Cart> cartItems = cartRepository.findByUser(user);

//         model.addAttribute("cartItems", cartItems);

//         return "payment"; // loads payment.html
//     }

// }
