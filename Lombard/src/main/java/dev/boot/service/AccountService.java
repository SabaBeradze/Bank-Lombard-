package dev.boot.service;

import dev.boot.domain.Account;
import dev.boot.exception.NotFoundWithIdException;
import dev.boot.repository.AccountRepository;
import dev.boot.repository.PawnItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final PawnItemRepository itemRepository;

    public Account save(Account entity) {
        return repository.save(entity);
    }

    public Optional<Account> findById(Long aLong) {
        return repository.findById(aLong);
    }
    public List<Account> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void update(long accountId, double money) {
        Optional<Account> account = findById(accountId);
        if (account.isPresent()) {
            Account acc = account.get();
            acc.setCurrentBalance(acc.getCurrentBalance() + money);
            save(acc);

            if (acc.getTotalDebt() <= acc.getCurrentBalance()) {
                if (itemRepository.findById(accountId).isEmpty()) {
                    throwException.accept(String.format("No item such id=%s exist ", accountId));
                }
                var item = itemRepository.findById(accountId).get();
                item.setTaken(true);
                item.setDateOfEvent(LocalDate.now());
                itemRepository.save(item);
            }
        } else {
            throwException.accept(String.format("No Account such  id=%s exist ", accountId));
        }
    }

    private static final Consumer<String> throwException = (text) -> {
        throw new NotFoundWithIdException(text, new Throwable());
    };


}
