package com.hexa.AssetManage;

import com.hexa.dao.AssetManagementService;
import com.hexa.dao.AssetManagementServiceImpl;
import com.hexa.entity.Asset;
import com.hexa.util.DBConnUtil;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AssetManagementServiceImplTest {

    private static Connection connection;
    private static AssetManagementService service;

    @BeforeClass
    public static void init() {
        try {
            // Initialize database connection
            connection = DBConnUtil.getConnection();

            // Initialize service with the connection
            service = new AssetManagementServiceImpl(connection);

        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
            fail("Failed to establish database connection: " + e.getMessage());
        } 
    }

    @Test
    public void testAddAsset() {
        Asset asset = new Asset();
        asset.setAssetId(1001);
        asset.setName("Test Printer");
        asset.setType("Electronics");
        asset.setStatus("Available");

        boolean result = service.addAsset(asset);
        assertTrue(result);
    }

    @Test
    public void testUpdateAsset() {
        Asset asset = new Asset();
        asset.setAssetId(1001);
        asset.setName("Updated Printer");
        asset.setType("Electronics");
        asset.setStatus("In Use");

        boolean result = service.updateAsset(asset);
        assertTrue(result);
    }

    @Test
    public void testAllocateAsset() {
        boolean result = service.allocateAsset(1001, 2001, "2025-04-10");
        assertTrue(result);
    }

    @Test
    public void testDeallocateAsset() {
        boolean result = service.deallocateAsset(1001, 2001, "2025-04-11");
        assertTrue(result);
    }

    @Test
    public void testPerformMaintenance() {
        boolean result = service.performMaintenance(1001, "2025-04-09", "Checked cables", 150);
        assertTrue(result);
    }

    @Test
    public void testReserveAsset() {
        boolean result = service.reserveAsset(1001, 2001, "2025-04-08", "2025-04-12", "2025-04-15");
        assertTrue(result);
    }

    @Test
    public void testWithdrawReservation() {
        boolean result = service.withdrawReservation(1); // Ensure reservation_id 1 exists
        assertTrue(result);
    }

    @Test
    public void testDeleteAsset() {
        boolean result = service.deleteAsset(1001);
        assertTrue(result);
    }

    @AfterClass
    public static void cleanup() throws Exception {
        if (connection != null) connection.close();
    }
}
