package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.ItemDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.dto.ItemDto;
import ik.ijse.HardwareSystem.entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOimpl implements ItemDAO {

    public Item searchBy(String description) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLunit.execute("SELECT * FROM item WHERE  description=?",description);



        Item itemDto = null;
        if (resultSet.next()) {
            String code = resultSet.getString(1);
            String descriptions = resultSet.getString(2);
            int qty = resultSet.getInt(4);
            double price = resultSet.getDouble(3);

            itemDto = new Item(code, descriptions, qty, price);
        }
        return itemDto;
    }

    public  ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {

        ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = SQLunit.execute("select description , qty from order_detail");

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        while (resultSet.next()) {
            String description = resultSet.getString("description");
            Integer qty = resultSet.getInt("qty");
            series.getData().add(new XYChart.Data<>(description, qty));
        }
        datalist.add(series);
        return datalist;
    }

    public int delete(String code) throws SQLException, ClassNotFoundException {

        return SQLunit.execute("DELETE FROM item WHERE code = ?",code);

    }

    public boolean save(Item entity) throws SQLException, ClassNotFoundException {

            return SQLunit.execute("INSERT INTO item VALUES(?, ?, ?, ?)",entity.getCode(),entity.getDesctription(),entity.getQtyOnHeand(),entity.getPrice());

    }

    @Override
    public ArrayList<String> getalls() {
        return null;
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

            System.out.println(entity.getCode());
            return SQLunit.execute("UPDATE item SET description = ?, unitPrice= ?, qtyOnHand = ? WHERE code = ?",entity.getCode(),entity.getPrice(),entity.getQtyOnHeand(),entity.getDesctription());

        }

    public ArrayList<Item> table() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            ResultSet resultSet = SQLunit.execute("SELECT * from Item");
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("code"),
                        resultSet.getString("description"),
                        resultSet.getInt("qtyOnHand"),
                        resultSet.getDouble("unitPrice")

                        );
                items.add(item);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return items;
    }






    public Item search(String code) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM item WHERE  code=?",code);
        Item T = null;

        if (resultSet.next()) {
            String codes = resultSet.getString(2);
            String descrption = resultSet.getString(1);
            int qtyOnHenad = resultSet.getInt(4);
            double price = resultSet.getDouble(3);


            T  = new Item( codes, descrption, qtyOnHenad,price);
        }
        return T;
    }




    public ArrayList<Item> getall() {
        ArrayList<Item> allData = new ArrayList<Item>();

        try {
            ResultSet resultSet = SQLunit.execute("SELECT * FROM item");

            while (resultSet.next()) {
                new ItemDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                         resultSet.getDouble(4)


                );

            }
            return allData;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }










}
