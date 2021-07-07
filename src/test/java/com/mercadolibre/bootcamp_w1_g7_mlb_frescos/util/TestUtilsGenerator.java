package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TestUtilsGenerator {

    public static CreateInboundOrderDTO getInboundOrderDto (){
        CreateInboundOrderDTO createInboundOrderDTO = new CreateInboundOrderDTO();
        InboundOrderWithoutOrderNumberDTO inboundOrderWithoutOrderNumberDTO = new InboundOrderWithoutOrderNumberDTO();

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setSectionCode("OSAF001");
        sectionDTO.setWarehouseCode("OSAF");

        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");

        List<BatchWithoutNumberDTO> batches = new ArrayList<>();
        BatchWithoutNumberDTO batchDTO1 = new BatchWithoutNumberDTO();
        batchDTO1.setProductId(product.getId());
        batchDTO1.setCurrentTemperature(10.0);
        batchDTO1.setMinimumTemperature(5.0);
        batchDTO1.setInitialQuantity(500);
        batchDTO1.setCurrentQuantity(500);
        batchDTO1.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batchDTO1.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batchDTO1.setDueDate(LocalDate.of(2021, 8, 15));

        batches.add(batchDTO1);

        inboundOrderWithoutOrderNumberDTO.setOrderDate(LocalDate.of(2021, 07, 01));
        inboundOrderWithoutOrderNumberDTO.setSection(sectionDTO);
        inboundOrderWithoutOrderNumberDTO.setBatchStock(batches);
        createInboundOrderDTO.setInboundOrder(inboundOrderWithoutOrderNumberDTO);

        return createInboundOrderDTO;
    }

    public static InboundOrderDTO createInboundOrderDTO(){
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO();

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setSectionCode("OSAF001");
        sectionDTO.setWarehouseCode("OSAF");

        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");

        List<BatchDTO> batches = new ArrayList<>();
        BatchDTO batchDTO1 = new BatchDTO();
        batchDTO1.setProductId(product.getId());
        batchDTO1.setCurrentTemperature(10.0);
        batchDTO1.setMinimumTemperature(5.0);
        batchDTO1.setInitialQuantity(500);
        batchDTO1.setCurrentQuantity(500);
        batchDTO1.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batchDTO1.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batchDTO1.setDueDate(LocalDate.of(2021, 8, 15));

        batches.add(batchDTO1);

        inboundOrderDTO.setOrderNumber(1);
        inboundOrderDTO.setOrderDate(LocalDate.of(2021, 07, 01));
        inboundOrderDTO.setSection(sectionDTO);
        inboundOrderDTO.setBatchStock(batches);

        return inboundOrderDTO;
    }

    public static Section createSection (){
        Batch batch = new Batch();
        Set<Batch> batches = new HashSet<>();
        batches.add(batch);
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setBatchStock(batches);
        Set<InboundOrder> inboundOrderSet = new HashSet<>();
        inboundOrderSet.add(inboundOrder);

        Section section = new Section();
        section.setCode("OSAF001");
        section.setCategory("FS");
        section.setCapacity(500);
        section.setWarehouse(new Warehouse("OSAF", "Fullfillment Osasco"));
        section.setInboundOrder(inboundOrderSet);

        return section;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");

        return product;
    }

    public static List<Product> createListProducts(){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");
        products.add(product);

        return products;
    }

    public static Supervisor createSupervisor(){
        Supervisor supervisor = new Supervisor();
        Warehouse warehouse = new Warehouse();
        warehouse.setCode("OSAF");
        warehouse.setName("Fullfillment Osasco");
        supervisor.setId(UUID.fromString("cdd7bfff-1eeb-4fe8-b3ed-7fb2c0304020"));
        supervisor.setName("Maria");
        supervisor.setWarehouse(warehouse);

        return supervisor;
    }

    //TODO MUDAR SUPERVISOR ID
    public static Supervisor createOtherSupervisor(){
        Supervisor supervisor = new Supervisor();
        Warehouse warehouse = new Warehouse();
        warehouse.setCode("CAJF");
        warehouse.setName("Fullfillment Cajamar");
        supervisor.setId(UUID.fromString("b20c0c2d-f378-4d7c-b965-e8a6a128c948"));
        supervisor.setName("Barbara");
        supervisor.setWarehouse(warehouse);
        return supervisor;
    }

    public static InboundOrder createInboundOrder(){
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setOrderNumber(1);
        inboundOrder.setSection(createSection());
        inboundOrder.setSupervisor(createSupervisor());
        inboundOrder.setOrderDate(LocalDate.of(2021, 07, 05));
        Set<Batch> batchStock = createBatchStock();
        batchStock.forEach(batch -> batch.setInboundOrder(inboundOrder));
        inboundOrder.setBatchStock(batchStock);

        return inboundOrder;
    }

    public static InboundOrder createInboundOrderWithTwoBatches(){
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setOrderNumber(1);
        inboundOrder.setSection(createSection());
        inboundOrder.setSupervisor(createSupervisor());
        inboundOrder.setOrderDate(LocalDate.of(2021, 07, 05));
        Set<Batch> batchStock = createBatchStockWithTwoProducts();
        batchStock.forEach(batch -> batch.setInboundOrder(inboundOrder));
        inboundOrder.setBatchStock(batchStock);

        return inboundOrder;
    }

    public static Set<Batch> createBatchStock(){
        Set<Batch> batches = new HashSet<>();
        Batch batch = new Batch();
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        batch.setProduct(product);
        batch.setCurrentTemperature(10.0);
        batch.setMinimumTemperature(5.0);
        batch.setInitialQuantity(500);
        batch.setCurrentQuantity(500);
        batch.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batch.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batch.setDueDate(LocalDate.of(2021, 8, 15));
        batch.setBatchNumber(1);

        batches.add(batch);

        return batches;
    }

    public static Set<Batch> createBatchStockWithTwoProducts(){
        Set<Batch> batches = new HashSet<>();
        Batch batch = new Batch();
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        batch.setProduct(product);
        batch.setCurrentTemperature(10.0);
        batch.setMinimumTemperature(5.0);
        batch.setInitialQuantity(500);
        batch.setCurrentQuantity(500);
        batch.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batch.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batch.setDueDate(LocalDate.of(2021, 8, 15));
        batch.setBatchNumber(1);

        Batch batch1 = new Batch();
        Product product1 = new Product();
        product1.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        batch1.setProduct(product);
        batch1.setCurrentTemperature(10.0);
        batch1.setMinimumTemperature(5.0);
        batch1.setInitialQuantity(500);
        batch1.setCurrentQuantity(500);
        batch1.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batch1.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batch1.setDueDate(LocalDate.of(2021, 8, 15));
        batch1.setBatchNumber(1);

        batches.add(batch);
        batches.add(batch1);


        return batches;
    }
    public static List<Batch> createBatchStockList(){
        List<Batch> batches = new ArrayList<>();
        Batch batch = new Batch();
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        batch.setProduct(product);
        batch.setCurrentTemperature(10.0);
        batch.setMinimumTemperature(5.0);
        batch.setInitialQuantity(500);
        batch.setCurrentQuantity(500);
        batch.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batch.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batch.setDueDate(LocalDate.of(2021, 8, 15));
        batch.setBatchNumber(1);
        batch.setInboundOrder(createInboundOrder());

        batches.add(batch);

        return batches;
    }

    public static ModelMapper createModelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.addMappings(new PropertyMap<SectionDTO, Section>() {
            @Override
            protected void configure() {
                map().setCode(source.getSectionCode());
            }
        });

        modelMapper.addMappings(new PropertyMap<BatchWithoutNumberDTO, Batch>() {
            @Override
            protected void configure() {
                map().getInboundOrder().setOrderDate(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<BatchDTO, Batch>() {
            @Override
            protected void configure() {
                map().getInboundOrder().setOrderDate(null);
            }
        });

        return modelMapper;
    }

    public static UpdateInboundOrderDTO createUpdateInboundOrderDTO(){
        UpdateInboundOrderDTO updateInboundOrderDTO = new UpdateInboundOrderDTO();
        InboundOrderDTO inboundOrderDTO = createInboundOrderDTO();
        inboundOrderDTO.getBatchStock().get(0).setBatchNumber(1);
        updateInboundOrderDTO.setInboundOrder(inboundOrderDTO);
        return updateInboundOrderDTO;
    }

    public static String createRequestOneBatch(){
        String request = "{\"inboundOrder\": {\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"OSAF001\" , \"warehouseCode\": \"OSAF\"}, \"batchStock\": [" +
                getBatchStock("51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-10 20:00:00", "2021-08-15") + "]}}";

        return request;
    }

    public static String createRequestTwoBatches(){
        String request = "{\"inboundOrder\": {\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"OSAF001\" , \"warehouseCode\": \"OSAF\"}, \"batchStock\": [" +
                getBatchStock("51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock("51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15")  + "]}}";
        return request;
    }

    private static String getBatchStock(String productId, Double currentTemperature, Double minimumTemperature, Integer initialQuantity, Integer currentQuantity, String manufacturingDate, String manufacturingTime, String dueDate) {
        return "{\"productId\": \""+productId+"\", \"currentTemperature\": "+currentTemperature+", " +
                "\"minimumTemperature\": "+minimumTemperature+", \"initialQuantity\":"+initialQuantity+", \"currentQuantity\": "+currentQuantity+", " +
                "\"manufacturingDate\": \""+manufacturingDate+"\", \"manufacturingTime\": \""+manufacturingTime+"\", \"dueDate\": \""+dueDate+"\"}";
    }

    private static String getUpdateBatchStock(String productId, Double currentTemperature, Double minimumTemperature, Integer initialQuantity, Integer currentQuantity, String manufacturingDate, String manufacturingTime, String dueDate, Integer batchNumber) {
        return "{\"productId\": \""+productId+"\", \"currentTemperature\": "+currentTemperature+", " +
                "\"minimumTemperature\": "+minimumTemperature+", \"initialQuantity\":"+initialQuantity+", \"currentQuantity\": "+currentQuantity+", " +
                "\"manufacturingDate\": \""+manufacturingDate+"\", \"manufacturingTime\": \""+manufacturingTime+"\", \"dueDate\": \""+dueDate+"\", \"batchNumber\": \""+batchNumber+"\"}";
    }

    public static String updateRequestOneBatch(){
        String request = "{\"inboundOrder\": {\"orderNumber\": 1, \"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"OSAF001\" , \"warehouseCode\": \"OSAF\"}, \"batchStock\": [" +
                getUpdateBatchStock("51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-10 20:00:00", "2021-08-15", 1) + "]}}";

        return request;
    }

    public static String updateRequestTwoBatches(){
        String request = "{\"inboundOrder\": {\"orderNumber\": 1, \"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"OSAF001\" , \"warehouseCode\": \"OSAF\"}, \"batchStock\": [" +
                getUpdateBatchStock("51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15", 1) + "," +
                getUpdateBatchStock("51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15", 1)  + "]}}";
        return request;
    }

    public static List<BatchInfoDTO> createBatchInfoList() {
        return List.of(
                new BatchInfoDTO(1, 500, LocalDate.of(2021, 8, 15))
        );
    }
}