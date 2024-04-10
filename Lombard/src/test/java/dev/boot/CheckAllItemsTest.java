package dev.boot;

import dev.boot.domain.Account;
import dev.boot.domain.Branch;
import dev.boot.domain.Technology;
import dev.boot.dto.BranchDTO;
import dev.boot.dto.TechnologyDTO;
import dev.boot.service.BranchService;
import dev.boot.service.ItemService;
import dev.boot.repository.BranchRepository;
import dev.boot.repository.PawnItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringJUnitConfig
@SpringBootTest
class CheckAllItemsTest {

    @Spy
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchService branchService;
    @InjectMocks
    private BranchService accountService;
    @InjectMocks

    private ItemService itemService;
    @Test
    public void testCheckAll_2Months100total10Paid() {
        ItemService itemService = mock(ItemService.class);
        PawnItemRepository itemRepository = mock(PawnItemRepository.class);
        BranchService branchService = mock(BranchService.class);


        BranchDTO branchDTO = mock(BranchDTO.class);
        when(branchService.save(any(BranchDTO.class))).thenReturn(branchDTO);
        when(branchDTO.getId()).thenReturn(1L);
        ModelMapper modelMapper =new ModelMapper();

        long id = 1L;
        double amountRecieved = 200.;
        double monthlyPayment = 10.;
        TechnologyDTO techDTO = mock(TechnologyDTO.class);
        techDTO.setAmountReceived(200.);
        techDTO.setAmountInMonth(10.);
        techDTO.setDate(LocalDate.now().minusMonths(2));
        when(techDTO.getAmountReceived()).thenReturn(amountRecieved);
        when(techDTO.getAmountInMonth()).thenReturn(monthlyPayment);
        when(techDTO.getDate()).thenReturn(LocalDate.now().minusMonths(2));
        when(techDTO.getId()).thenReturn(id);
        techDTO.setBranch(modelMapper.map(branchDTO, Branch.class));
        Technology tech = mock(Technology.class);
        tech.setAmountReceived(200.);
        tech.setAmountInMonth(10.);
        tech.setDate(LocalDate.now().minusMonths(2));
        when(tech.getId()).thenReturn(id);
        when(tech.getAmountReceived()).thenReturn(200.);
        when(tech.getAmountInMonth()).thenReturn(10.);
        when(tech.getDate()).thenReturn(LocalDate.now().minusMonths(2));


        Account account = mock(Account.class);
        when(account.getTotalDebt()).thenReturn(200.0);
        when(account.getCurrentBalance()).thenReturn(10.0);
        when(account.getId()).thenReturn(id);
        when(account.getId()).thenReturn(id);


        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        when(itemRepository.findById(idCaptor.capture())).thenReturn(Optional.of(tech));


        itemService.checkAllItems();

        Assertions.assertTrue(tech.isConfiscated());

    }
}
