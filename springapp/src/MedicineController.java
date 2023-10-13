package com.example.pharmacy.controller;

import com.example.pharmacy.model.Medicine;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/medicines")
public class MedicineController {
    private Map<Integer, Medicine> medicineMap = new ConcurrentHashMap<>();
    private int nextMedicineId = 1;

    @PostMapping
    public boolean addMedicine(@RequestBody Medicine medicine) {
        medicine.setMedicineId(nextMedicineId);
        medicineMap.put(nextMedicineId, medicine);
        nextMedicineId++;
        return true; // Successfully added medicine
    }

    @PutMapping("/{medicineId}")
    public Medicine updateMedicine(@PathVariable int medicineId, @RequestBody Medicine updatedMedicine) {
        if (medicineMap.containsKey(medicineId)) {
            updatedMedicine.setMedicineId(medicineId);
            medicineMap.put(medicineId, updatedMedicine);
            return updatedMedicine;
        }
        return null; // Medicine not found
    }
}
