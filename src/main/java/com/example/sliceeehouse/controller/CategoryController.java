package com.example.sliceeehouse.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sliceeehouse.model.Category;
import com.example.sliceeehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private Cloudinary cloudinary; // ✅ ADD THIS

    // Dashboard Page
    @GetMapping
    public String viewPage(Model model) {
        model.addAttribute("categories", repo.findByStatusTrue());
        model.addAttribute("category", new Category());
        return "category";
    }

    // ✅ SAVE CATEGORY WITH IMAGE UPLOAD
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category,
                               @RequestParam("file") MultipartFile file) throws IOException {

        // 🔥 Upload to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploadResult.get("url").toString();

        // Save URL in DB
        category.setImage(imageUrl);

        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        category.setStatus(true);

        repo.save(category);

        return "redirect:/category";
    }

    // Edit Category
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        Category cat = repo.findById(id).orElseThrow();
        model.addAttribute("category", cat);
        model.addAttribute("categories", repo.findByStatusTrue());
        return "category";
    }

    // ✅ UPDATE CATEGORY WITH IMAGE (optional change)
    @PostMapping("/update")
    public String updateCategory(@ModelAttribute Category category,
                                 @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        Category cat = repo.findById(category.getCategoryId()).orElseThrow();

        cat.setCategoryName(category.getCategoryName());
        cat.setDescription(category.getDescription());

        // 🔥 If new image uploaded → update
        if (file != null && !file.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("url").toString();
            cat.setImage(imageUrl);
        }

        cat.setUpdatedAt(LocalDateTime.now());

        repo.save(cat);

        return "redirect:/category";
    }

    // Soft Delete
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        Category cat = repo.findById(id).orElseThrow();
        cat.setStatus(false);
        repo.save(cat);
        return "redirect:/category";
    }
}