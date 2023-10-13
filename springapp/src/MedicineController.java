
package controller;

import model.Medicine;
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
    public boolean addMedicine(@RequestBody Medicine medicine) {
        medicineMap.put(medicine.getMedicineId(), medicine);
        return true;
    }

    @PutMapping("/{medicineId}")
    public boolean updateMedicine(@PathVariable int medicineId, @RequestBody Medicine updatedMedicine) {
        if (medicineMap.containsKey(medicineId)) {
            Medicine medicine = medicineMap.get(medicineId);
            medicine.setMedicineName(updatedMedicine.getMedicineName());
            medicine.setPrice(updatedMedicine.getPrice());
            medicine.setQuantity(updatedMedicine.getQuantity());
            medicine.setDescription(updatedMedicine.getDescription());
            return true;
        }
        return false;
    }

    @GetMapping("/{medicineId}")
    public Medicine getMedicine(@PathVariable int medicineId) {
        return medicineMap.get(medicineId);
    }

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return new ArrayList<>(medicineMap.values());
    }
}