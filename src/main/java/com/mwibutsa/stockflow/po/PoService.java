package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.common.exception.BadRequestException;
import com.mwibutsa.stockflow.common.exception.ConflictException;
import com.mwibutsa.stockflow.common.exception.PurchaseOrderNotEditableException;
import com.mwibutsa.stockflow.common.exception.PurchaseOrderNotFoundException;
import com.mwibutsa.stockflow.po.dto.PoItemRequest;
import com.mwibutsa.stockflow.po.dto.PoRequest;
import com.mwibutsa.stockflow.po.dto.PoResponse;
import com.mwibutsa.stockflow.product.ProductRepository;
import com.mwibutsa.stockflow.supplier.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@AllArgsConstructor
@Service
public class PoService {
    private final PoRepository poRepository;
    private final PoMapper poMapper;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;


    public PaginatedResponse<PoResponse> getPurchaseOrders(String search, Pageable pagination, PoStatus status, UUID supplierId) {
        var spec = Specification
                .where(PoSpecification.searchPurchaseOrders(search))
                .and(PoSpecification.filterByStatus(status))
                .and(PoSpecification.filterBySupplier(supplierId));

        return poMapper.toPageResponse(poRepository.findAll(spec, pagination));
    }

    public PoResponse createPurchaseOrder(PoRequest payload) {
        var supplier = supplierRepository.findById(payload.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found", "supplierId"));

        var po = poMapper.toEntity(payload);
        po.setSupplier(supplier);
        po.setReference(this.generatePoReference());

        poRepository.save(po);
        return poMapper.toDto(po);
    }

    private String generatePoReference() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "PO-" + datePart + "-" + uniqueSuffix;
    }

    public PoResponse getPurchaseOrder(UUID poId) {
        var purchaseOrder = poRepository.findWithItems(poId)
                .orElseThrow(PurchaseOrderNotFoundException::new);

        return poMapper.toDto(purchaseOrder);
    }

    public PoResponse addPoItem(UUID poId, PoItemRequest payload) {
        var po = poRepository.findById(poId).orElseThrow(PurchaseOrderNotFoundException::new);
        var product = productRepository.findById(payload.getProductId())
                .orElseThrow(() -> new BadRequestException("Product not found", "productId"));

        if (po.getStatus() != PoStatus.PENDING) {
            throw new PurchaseOrderNotEditableException();
        }

        var poItemExists = poRepository.existsByIdAndItemsProductId(poId, payload.getProductId());


        if (poItemExists) {
            throw new ConflictException("Item is already in the purchase order");
        }

        var poItem = poMapper.toEntity(payload);

        poItem.setProduct(product);
        po.addItem(poItem);

        poRepository.save(po);
        return poMapper.toDto(po);
    }

    public void removeItem(UUID poId, UUID itemId) {

        var po = poRepository.findById(poId).orElseThrow(PurchaseOrderNotFoundException::new);
        po.removeItem(itemId);
        poRepository.save(po);
    }

    public PoResponse updateItem(UUID poId, PoItemRequest payload) {
        return null;
    }
}
