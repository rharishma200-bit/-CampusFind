

package CampusFind;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lost-items")
@CrossOrigin
public class LostItemController {

    private final LostItemRepository repository;

    public LostItemController(LostItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public LostItem addLostItem(@RequestBody LostItem item) {
        return repository.save(item);
    }

    @GetMapping
    public List<LostItem> getAllLostItems() {
        return repository.findAll();
    }

    @PutMapping("/{id}/returned")
    public LostItem markAsReturned(@PathVariable Long id) {
        LostItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setReturned(true);

        return repository.save(item);
    }
}


