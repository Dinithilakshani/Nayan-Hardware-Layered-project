package ik.ijse.HardwareSystem.bo.impl;

import ik.ijse.HardwareSystem.dao.DAO.ItemDAO;
import ik.ijse.HardwareSystem.dao.DAOFactory;
import ik.ijse.HardwareSystem.dao.impl.ItemDAOimpl;
import ik.ijse.HardwareSystem.dto.ItemDto;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ik.ijse.HardwareSystem.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOimpl {
   ItemDAO itemDAO = (ItemDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);


    public ItemDto searchBy(String code) throws SQLException, ClassNotFoundException {

        Item i = itemDAO.searchBy(code);
        ItemDto itemDto =  new ItemDto(i.getCode(),i.getDesctription(),i.getQtyOnHeand(),i.getPrice());
        return itemDto;

    }

    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {

        return itemDAO.getDataToBarChart();
    }



    public boolean save(Item entity) throws SQLException, ClassNotFoundException {

        return itemDAO.save(entity);
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

        return itemDAO.update(entity);
    }

    public ArrayList<ItemDto> table() {
        ArrayList<Item> items = itemDAO.table();
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (Item i : items) {
            ItemDto itemDto = new ItemDto(i.getCode(),i.getDesctription(),i.getQtyOnHeand(),i.getPrice());
        }
        return itemDtos;
    }


    public ItemDto search(String code) throws SQLException, ClassNotFoundException {

        Item i = itemDAO.search(code);
        ItemDto item = new ItemDto(i.getCode(),i.getDesctription(),i.getQtyOnHeand(),i.getPrice());
        return item;

    }


    public ArrayList<ItemDto> getall() {

        ArrayList<Item> items = itemDAO.table();
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (Item i : items) {
            ItemDto itemDto = new ItemDto(i.getCode(),i.getDesctription(),i.getQtyOnHeand(),i.getPrice());
        }
        return itemDtos;
    }
}
