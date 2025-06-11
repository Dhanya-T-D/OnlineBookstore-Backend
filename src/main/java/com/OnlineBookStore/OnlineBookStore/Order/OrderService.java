package com.OnlineBookStore.OnlineBookStore.Order;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.OrderDetailsDto;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubRepo;
import com.OnlineBookStore.OnlineBookStore.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private PubRepo pubRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    public ResponseEntity<?> placeOrder(Long userid, Long bookId, int quantity,
                                        String name, Long phone, String address) {

        if (name == null || name.trim().isEmpty() ||
                phone == null || String.valueOf(phone).length() != 10 ||
                address == null || address.trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid name, phone, or address");
        }

        BookModel bookModel = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (bookModel.getAvailableCopies() < quantity) {
            throw new RuntimeException("Sold Out or Not enough copies available");
        }

        double bookPrice = bookModel.getPrice();
        double shippingCharge = 50;
        double basePrice = bookPrice * quantity;
        double totalPrice = basePrice + shippingCharge;

        double adminShare = basePrice * 0.10;
        double publisherShare = basePrice * 0.90;

        OrderModel orderModel = new OrderModel();
        orderModel.setUserid(userid);
        orderModel.setBookId(bookId);
        orderModel.setPubId(bookModel.getPubId());
        orderModel.setQuantity(quantity);
        orderModel.setBookPrice(bookPrice);
        orderModel.setTotalPrice(totalPrice);
        orderModel.setAdminShare(adminShare);
        orderModel.setPublisherShare(publisherShare);
        orderModel.setOrderDate(LocalDate.now());
        orderModel.setExpectedDeliveryDate(LocalDate.now().plusDays(7));
        orderModel.setName(name);
        orderModel.setPhone(phone);
        orderModel.setAddress(address);

        // Reduce stock
        bookModel.setAvailableCopies(bookModel.getAvailableCopies() - quantity);
        bookRepo.save(bookModel);

        // Save order
        orderRepo.save(orderModel);



        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }


//    display order details

    public ResponseEntity<List<OrderDetailsDto>> displayOrderDetails(Long bookId) {
        Optional<BookModel> bookModelOptional = bookRepo.findById(bookId);
        if (bookModelOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BookModel bookModel = bookModelOptional.get();
        double price = bookModel.getPrice();
        int shippingCharge = 50;
        int quantity = 1;
        double totalPrice = price * quantity + shippingCharge;

        LocalDate expectedDeliveryDate = LocalDate.now().plusDays(7);

        OrderDetailsDto dto = new OrderDetailsDto(
                bookModel.getBookName(),
                price,
                shippingCharge,
                totalPrice,
                expectedDeliveryDate,
                "Cash on Delivery"
        );

        return ResponseEntity.ok(List.of(dto));  // ✅ wrap the list in ResponseEntity
    }

}
