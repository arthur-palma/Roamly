package api.Roamly.Service.Interface.Trip;

import org.springframework.http.ResponseEntity;

public interface ITripActivityService {
    ResponseEntity<Void> addActivity();

    ResponseEntity<Void> removeActivity();

}
