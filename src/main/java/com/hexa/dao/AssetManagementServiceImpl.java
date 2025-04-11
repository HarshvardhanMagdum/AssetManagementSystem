package com.hexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hexa.entity.Asset;
import com.hexa.exception.AssetNotFoundException;
import com.hexa.exception.AssetNotMaintainException;

public class AssetManagementServiceImpl implements AssetManagementService {

    private Connection connection;

    public AssetManagementServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addAsset(Asset asset) {
        String query = "INSERT INTO assets (asset_id, asset_name, asset_type, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, asset.getAssetId());
            ps.setString(2, asset.getName());
            ps.setString(3, asset.getType());
            ps.setString(4, asset.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAsset(Asset asset) {
        String query = "UPDATE assets SET asset_name=?, asset_type=?, status=? WHERE asset_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, asset.getName());
            ps.setString(2, asset.getType());
            ps.setString(3, asset.getStatus());
            ps.setInt(4, asset.getAssetId());
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
            throw new AssetNotFoundException("Asset not found with ID: " + asset.getAssetId());
        } catch (SQLException | AssetNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAsset(int assetId) {
        String query = "DELETE FROM assets WHERE asset_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, assetId);
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
            throw new AssetNotFoundException("Asset not found with ID: " + assetId);
        } catch (SQLException | AssetNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
        String query = "INSERT INTO asset_allocation (asset_id, employee_id, allocation_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, allocationDate);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
        String query = "UPDATE asset_allocation SET return_date=? WHERE asset_id=? AND employee_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, returnDate);
            ps.setInt(2, assetId);
            ps.setInt(3, employeeId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
        String query = "INSERT INTO asset_maintenance (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setString(2, maintenanceDate);
            ps.setString(3, description);
            ps.setDouble(4, cost);
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
            throw new AssetNotMaintainException("Maintenance failed for asset ID: " + assetId);
        } catch (SQLException | AssetNotMaintainException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
        String query = "INSERT INTO asset_reservation (asset_id, employee_id, reservation_date, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, reservationDate);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean withdrawReservation(int reservationId) {
        String query = "DELETE FROM asset_reservation WHERE reservation_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, reservationId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
