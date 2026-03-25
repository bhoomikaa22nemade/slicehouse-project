package com.example.sliceeehouse.controller;

import com.example.sliceeehouse.model.Cart;
import com.example.sliceeehouse.repository.CartRepository;
import com.example.sliceeehouse.repository.PizzaRepository;
import com.example.sliceeehouse.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final PizzaRepository pizzaRepository;

    public CartController(CartRepository cartRepository,
                          UserRepository userRepository,
                          PizzaRepository pizzaRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.pizzaRepository = pizzaRepository;
    }

    // ================= VIEW CART ITEMS =================
    @GetMapping
    public String viewCartItems(Model model) {

        model.addAttribute("cartItems", cartRepository.findAll());

        return "admin/cart/view_cart";
    }

    // ================= ADD CART FORM =================
    @GetMapping("/add")
    public String addCartForm(Model model) {

        model.addAttribute("cart", new Cart());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("pizzas", pizzaRepository.findAll());

        return "admin/cart/add_cart";
    }

    // ================= SAVE CART ITEM =================
    @PostMapping("/add")
    public String saveCartItem(@ModelAttribute Cart cart) {

        cartRepository.save(cart);

        return "redirect:/admin/cart";
    }

    // ================= DELETE CART ITEM =================
    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable Long id) {

        cartRepository.deleteById(id);

        return "redirect:/admin/cart";
    }

}

