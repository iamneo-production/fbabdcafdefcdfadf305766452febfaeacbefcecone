package controller;

import model.Medicine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private Map<Integer, Medicine> medicineMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine) {
        medicineMap.put(medicine.getMedicineId(), medicine);
        return new ResponseEntity<>("Medicine added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<String> updateMedicine(@PathVariable int medicineId, @RequestBody Medicine updatedMedicine) {
        if (medicineMap.containsKey(medicineId)) {
            Medicine medicine = medicineMap.get(medicineId);
            medicine.setMedicineName(updatedMedicine.getMedicineName());
            medicine.setPrice(updatedMedicine.getPrice());
            medicine.setQuantity(updatedMedicine.getQuantity());
            medicine.setDescription(updatedMedicine.getDescription());
            return new ResponseEntity<>("Medicine updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Medicine not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<Medicine> getMedicine(@PathVariable int medicineId) {
        Medicine medicine = medicineMap.get(medicineId);
        if (medicine != null) {
            return new ResponseEntity<>(medicine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>(medicineMap.values());
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }
}