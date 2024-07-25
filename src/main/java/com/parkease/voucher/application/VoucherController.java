package com.parkease.voucher.application;

import com.parkease.voucher.domain.VoucherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @Operation(summary = """
            Accepts sorting parameters passing them in the URL, such as '?page=2&size=5&sort=arrivedAt,desc' 
            to fetch the third page (page count starts at 0), with 5 records per page, 
            sorted by arrivedAt in descending order.
            """)
    @GetMapping("/list-all")
    public ResponseEntity<Page<VoucherDTO>> listAll(@Nullable @PageableDefault(size = 20, sort = "arrivedAt", direction = DESC) Pageable pageable) {
        return ResponseEntity.ok(voucherService.listAllVouchers(pageable));
    }

    @GetMapping("/list-by-driver/{driverId}")
    public ResponseEntity<Page<VoucherDTO>> listAllByDriver(@Nullable @PageableDefault(size = 20, sort = "arrivedAt", direction = DESC) Pageable pageable,
                                                            @PathVariable(value = "driverId") String driverId) {
        return ResponseEntity.ok(voucherService.listAllVouchersByDriverId(pageable, driverId));
    }

    @GetMapping("/list-by-arrived-at-between")
    public ResponseEntity<Page<VoucherDTO>> listAllByArrivedAtBetween(@Nullable @PageableDefault(size = 20, sort = "arrivedAt", direction = DESC) Pageable pageable,
                                                                      @RequestBody LocalDateTime start,
                                                                      @RequestBody LocalDateTime end) {
        return ResponseEntity.ok(voucherService.listAllVouchersByArrivedAtBetween(pageable, start, end));
    }
}
