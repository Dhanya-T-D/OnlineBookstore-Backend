package com.OnlineBookStore.OnlineBookStore.Cart;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.DisplayCartItemDto;
import com.OnlineBookStore.OnlineBookStore.User.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private BookRepo bookRepo;

    public ResponseEntity<?> addToCart(Long userid, Long bookId, Integer quantity) {
        // Step 1: Find the book by its ID
        BookModel bookModel = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Step 2: Find or create a cart for the user
        Cart cart = cartRepo.findByUserModel_Userid(userid).orElseGet(() -> {
            Cart newCart = new Cart();
            UserModel userModel = new UserModel();
            userModel.setUserid(userid);  // Make sure this matches your UserModel field name
            newCart.setUserModel(userModel);
            return cartRepo.save(newCart);
        });

        // Step 3: Check if the book is already in the cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getBookModel().getBookId().equals(bookId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Book already in cart: increase quantity
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Book not in cart: create new CartItem
            CartItem newItem = new CartItem();
            newItem.setBookModel(bookModel);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        // Step 4: Save cart and return response
        Cart updatedCart = cartRepo.save(cart);
        return ResponseEntity.ok(updatedCart);
    }

//    display cart items

    public ResponseEntity<List<DisplayCartItemDto>> displayCartItems(Long userid) {
        Cart cart = cartRepo.findByUserModel_Userid(userid)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<DisplayCartItemDto> items = cart.getItems().stream()
                .map(item -> new DisplayCartItemDto(
                        item.getBookModel().getBookId(),
                        item.getBookModel().getBookName(),
                        item.getBookModel().getPrice(),
                        item.getQuantity(),
                        item.getBookModel().getCoverImage()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(items);
    }

    public void updateCartItemQuantity(Long userid, Long bookId, int quantity) {
        Cart cart = cartRepo.findByUserModel_Userid(userid)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getBookModel().getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setQuantity(quantity);
        cartItemRepo.save(cartItem);
    }

    public void removeCartItem(Long userid, Long bookId) {
        Cart cart = cartRepo.findByUserModel_Userid(userid)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getBookModel().getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cart.getItems().remove(cartItem);
        cartItemRepo.delete(cartItem);
        cartRepo.save(cart);
    }
}
