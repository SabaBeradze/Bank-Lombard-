package dev.boot.service;

import dev.boot.domain.Car;
import dev.boot.domain.Jewelry;
import dev.boot.domain.PawnItem;
import dev.boot.dto.CarDTO;
import dev.boot.dto.JewelryDTO;
import dev.boot.dto.PawnItemDTO;
import dev.boot.dto.TechnologyDTO;
import dev.boot.repository.PawnItemRepository;
import dev.omedia.boot.domain.*;
import dev.omedia.boot.dto.*;
import dev.omedia.boot.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ItemService {
    private final PawnItemRepository repository;
    private final ModelMapper modelMapper;
    private final AccountService accountService;


    public PawnItem save(PawnItem pawnItem) {
        return repository.save(pawnItem);
    }

    public List<PawnItemDTO> findAll(long branch_id) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(pawnItem -> pawnItem.getBranch().getId() == branch_id)
                .map(pawnItem -> {
                    if (pawnItem instanceof Car) {
                        return modelMapper.map(pawnItem, CarDTO.class);
                    } else if (pawnItem instanceof Jewelry) {
                        return modelMapper.map(pawnItem, JewelryDTO.class);
                    } else {
                        return modelMapper.map(pawnItem, TechnologyDTO.class);
                    }
                }).collect(Collectors.toList());
    }

    private List<PawnItemDTO> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(pawnItem -> modelMapper.map(pawnItem, PawnItemDTO.class))
                .collect(Collectors.toList());
    }

    //testingistvis davwere es cron
    @Scheduled(cron = "0 * * * * ?")
    public void checkAllItems() {
        accountService.findAll().forEach(account -> {

            Optional<PawnItem> itemOptional = repository.findById(account.getId());
            itemOptional.orElseThrow(NoSuchElementException::new);
            PawnItem item = itemOptional.get();

            if (!(item.isConfiscated() || item.isTaken())) {
                double amountPaid = account.getCurrentBalance();
                if (amountPaid < (LocalDate.now().getMonthValue() - item.getDate().getMonthValue()) * item.getAmountInMonth()) {
                    item.setConfiscated(true);
                    item.setDateOfEvent(LocalDate.now());
                    repository.save(item);
                }
            }
        });
    }


}
