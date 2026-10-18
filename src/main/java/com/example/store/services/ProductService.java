package com.example.store.services;

import com.example.store.dtos.ProductRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Category;
import com.example.store.models.Color;
import com.example.store.models.Product;
import com.example.store.models.Size;
import com.example.store.repository.CategoryRepository;
import com.example.store.repository.ColorRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          ColorRepository colorRepository,
                          SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));
    }

    public Product createProduct(ProductRequest newProduct) {
        Category category = categoryRepository.findById(newProduct.categoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoría con el id " + newProduct.categoryId() + " no encontrada")
        );

        Product product = new Product();
        product.setProductName(newProduct.productName());
        product.setPrice(newProduct.price());
        product.setActivity(newProduct.activity());
        product.setLevel(newProduct.level());
        product.setCategory(category);

        if (newProduct.colorIds() != null && !newProduct.colorIds().isEmpty()) {
            List<Color> colors = colorRepository.findAllById(newProduct.colorIds());
            product.setColors(colors);
        }

        if (newProduct.sizeIds() != null && !newProduct.sizeIds().isEmpty()) {
            List<Size> sizes = sizeRepository.findAllById(newProduct.sizeIds());
            product.setSizes(sizes);
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest updateProduct) {
        Product oldProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));
        if (updateProduct.productName() != null)
            oldProduct.setProductName(updateProduct.productName());
        if (updateProduct.price() != null)
            oldProduct.setPrice(updateProduct.price());
        if (updateProduct.activity() != null)
            oldProduct.setActivity(updateProduct.activity());
        if (updateProduct.level() != null)
            oldProduct.setLevel(updateProduct.level());
        if (updateProduct.categoryId() != null) {
            Category category = categoryRepository.findById(updateProduct.categoryId()).orElseThrow(
                    () -> new ResourceNotFoundException("Categoría con el id " + updateProduct.categoryId() + " no encontrada")
            );
            oldProduct.setCategory(category);
        }
        if (updateProduct.colorIds() != null) {
            List<Color> colors = colorRepository.findAllById(updateProduct.colorIds());
            oldProduct.setColors(colors);
        }
        if (updateProduct.sizeIds() != null) {
            List<Size> sizes = sizeRepository.findAllById(updateProduct.sizeIds());
            oldProduct.setSizes(sizes);
        }
        return productRepository.save(oldProduct);
    }

    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));
        productRepository.delete(product);
        return product;
    }
}