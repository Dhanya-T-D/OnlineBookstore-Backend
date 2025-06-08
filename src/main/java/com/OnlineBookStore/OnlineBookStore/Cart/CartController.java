package com.OnlineBookStore.OnlineBookStore.Cart;

import com.OnlineBookStore.OnlineBookStore.DtoClasses.DisplayCartItemDto;
import com.OnlineBookStore.OnlineBookStore.status.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

//    add to cart

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam Long userid, @RequestParam Long bookId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        try{
            return cartService.addToCart(userid, bookId, quantity);
        }
       catch (Exception e){
            e.printStackTrace();
       }
        return new ResponseEntity<>("Failed to cart Item", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display cart items

    @GetMapping("/items")
    public ResponseEntity<List<DisplayCartItemDto>> getCartItems(@RequestParam Long userid) {

        return cartService.displayCartItems(userid);

    }

//    update cart item quantity
@PutMapping("/updateQuantity")
public ResponseEntity<String> updateCartItemQuantity(
        @RequestParam Long userid,
        @RequestParam Long bookId,
        @RequestParam int quantity) {

    cartService.updateCartItemQuantity(userid, bookId, quantity);
    return ResponseEntity.ok("Cart item quantity updated successfully");
}

// remove cart item

    @DeleteMapping("/removeItem")
    public ResponseEntity<?> removeItemFromCart(
            @RequestParam Long userid,
            @RequestParam Long bookId) {

        cartService.removeCartItem(userid, bookId);
        return ResponseEntity.ok("Item removed from cart successfully");
    }
}
