package com.hexa.main;

import com.hexa.dao.AssetManagementService;
import com.hexa.dao.AssetManagementServiceImpl;
import com.hexa.entity.Asset;
import com.hexa.util.DBConnUtil;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DBConnUtil.getConnection();
            AssetManagementService assetService = new AssetManagementServiceImpl(connection);

            // Add Asset
            Asset asset = new Asset();
            asset.setAssetId(1); // Ensure this ID is unique in DB
            asset.setName("Projector");
            asset.setType("Electronics");
            asset.setStatus("Available");

            boolean added = assetService.addAsset(asset);
            System.out.println("Asset Added: " + added);

            // Update Asset
            asset.setName("HD Projector");
            asset.setType("AV Equipment");
            asset.setStatus("In Use");

            boolean updated = assetService.updateAsset(asset);
            System.out.println("Asset Updated: " + updated);

            // Allocate Asset
            boolean allocated = assetService.allocateAsset(1, 1001, "2025-04-10");
            System.out.println("Asset Allocated: " + allocated);

            // Deallocate Asset
            boolean deallocated = assetService.deallocateAsset(1, 1001, "2025-04-11");
            System.out.println("Asset Deallocated: " + deallocated);

            // Perform Maintenance
            boolean maintained = assetService.performMaintenance(1, "2025-04-12", "Lens cleaned", 250.0);
            System.out.println("Maintenance Recorded: " + maintained);

            // Reserve Asset
            boolean reserved = assetService.reserveAsset(1, 1001, "2025-04-08", "2025-04-13", "2025-04-15");
            System.out.println("Asset Reserved: " + reserved);

            // Withdraw Reservation
            boolean withdrawn = assetService.withdrawReservation(1); // Assuming reservation_id is 1
            System.out.println("Reservation Withdrawn: " + withdrawn);

            // Delete Asset
            boolean deleted = assetService.deleteAsset(1);
            System.out.println("Asset Deleted: " + deleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
