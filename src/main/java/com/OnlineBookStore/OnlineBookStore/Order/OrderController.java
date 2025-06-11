package com.OnlineBookStore.OnlineBookStore.Order;

import com.OnlineBookStore.OnlineBookStore.DtoClasses.OrderDetailsDto;
import com.OnlineBookStore.OnlineBookStore.status.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired OrderService orderService;

//    place order
    @PostMapping("/buy-now")
    public ResponseEntity<?> placeOrder(
            @RequestParam Long userid,
            @RequestParam Long bookId,
            @RequestParam int quantity,
            @RequestParam String name,
            @RequestParam Long phone,
            @RequestParam String address) {
        try {
            return  orderService.placeOrder(userid, bookId, quantity,name,phone,address);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>("Order not placed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//     display order details
@GetMapping(path = "/display/orderDetails")
public ResponseEntity<List<OrderDetailsDto>>displayOrderDetails(@RequestParam Long  bookId)
{
    return orderService.displayOrderDetails(bookId);

}
}
