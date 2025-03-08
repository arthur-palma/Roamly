package api.Roamly.Service.Interface.Trip;

import org.springframework.http.ResponseEntity;

public interface ITripExpenseService {

    ResponseEntity<Void> addExpense();

    ResponseEntity<Void> removeExpense();

}
