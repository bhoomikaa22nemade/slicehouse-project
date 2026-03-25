
package com.example.sliceeehouse.controller;

import com.example.sliceeehouse.model.Order;
import com.example.sliceeehouse.repository.OrderRepository;
import com.example.sliceeehouse.repository.PizzaRepository;
import com.example.sliceeehouse.repository.UserRepository;
import com.example.sliceeehouse.repository.AddressRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository,
                           PizzaRepository pizzaRepository,
                           AddressRepository addressRepository,
                           UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    // ================= VIEW ORDERS =================

    @GetMapping
    public String viewOrders(Model model) {

        model.addAttribute("orders", orderRepository.findAll());

        return "admin/order/view_order";
    }


    // ================= ADD ORDER FORM =================

    @GetMapping("/add")
    public String addOrderForm(Model model) {

        model.addAttribute("order", new Order());
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("addresses", addressRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        return "admin/order/add_order";
    }


    // ================= SAVE ORDER =================

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {

        orderRepository.save(order);

        return "redirect:/admin/orders";
    }


    // ================= UPDATE ORDER FORM =================

    @GetMapping("/update/{id}")
    public String updateOrderForm(@PathVariable Long id, Model model) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        model.addAttribute("order", order);
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("addresses", addressRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        return "admin/order/update_order";
    }


    // ================= UPDATE ORDER =================

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order) {

        orderRepository.save(order);

        return "redirect:/admin/orders";
    }


    // ================= DELETE ORDER =================

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderRepository.deleteById(id);

        return "redirect:/admin/orders";
    }
    

}
