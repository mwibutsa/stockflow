package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.common.exception.*;
import com.mwibutsa.stockflow.po.dto.*;
import com.mwibutsa.stockflow.product.ProductRepository;
import com.mwibutsa.stockflow.supplier.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class PoService {
    private final PoRepository poRepository;
    private final PoMapper poMapper;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;


    public PaginatedResponse<PoResponse> getPurchaseOrders(
            String search, Pageable pagination, PoStatus status, UUID supplierId) {

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

    public PoResponse updateItem(UUID poId, UUID poItemId, PoItemRequest payload) {
        // validate modifiability
        var po = poRepository.findById(poId).orElseThrow(PurchaseOrderNotFoundException::new);
        if (po.getStatus() != PoStatus.PENDING) {
            throw new PurchaseOrderNotEditableException();
        }

        // handle product changes
        var updatedItem = po.updateItem(poItemId, payload);
        updateItemProduct(updatedItem, payload);
        // save the item
        poRepository.save(po);
        return poMapper.toDto(po);
    }

    private void updateItemProduct(PoItem updatedItem, PoItemRequest payload) {
        if (!updatedItem.getProduct().getId().equals(payload.getProductId())) {
            var product = productRepository.findById(payload.getProductId())
                    .orElseThrow(ProductNotFoundException::new);
            updatedItem.setProduct(product);
        }
    }

    public PoResponse updatePurchaseOrder(UUID poId, UpdatePoRequest payload) {

        var po = poRepository.findById(poId).orElseThrow(PurchaseOrderNotFoundException::new);


        poMapper.update(payload, po);

        // update supplier if  supplier is changed.
        updateSupplier(payload, po);

        syncItems(po, payload.getItems());

        poRepository.save(po);
        return poMapper.toDto(po);
    }

    private void updateSupplier(UpdatePoRequest payload, Po po) {
        if (!po.getSupplier().getId().equals(payload.getSupplierId())) {
            var supplier = supplierRepository.findById(payload.getSupplierId())
                    .orElseThrow(() -> new BadRequestException("Invalid supplier", "supplierId"));
            po.setSupplier(supplier);
        }
    }

    private void syncItems(Po po, List<UpdatePoItemRequest> payloadItems) {
        // delete removed.

        var incomingIds = payloadItems.stream().map(UpdatePoItemRequest::getId).filter(Objects::nonNull)
                .collect(Collectors.toSet());
        po.getItems().removeIf(existingItem -> !incomingIds.contains(existingItem.getId()));
        // update existing.
        payloadItems.forEach(item -> {
            if (item.getId() != null) {
                var updatedItem = po.updateItem(item.getId(), item);

                // update item product if any changes
                updateItemProduct(updatedItem, item);
            } else {
                var product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new BadRequestException("Product is not found"));
                var newItem = poMapper.toEntity(item);
                newItem.setProduct(product);
                po.addItem(newItem);
            }
        });
    }
}
