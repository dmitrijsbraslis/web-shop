package com.app.dao;

import com.app.model.CatalogItem;
import com.app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CatalogItem> getItems() {
        RowMapper<CatalogItem> rowMapper = (rs, rowNumber) -> mapCatalogItem(rs);
        return jdbcTemplate.query("SELECT catalog.id AS cat_id, sub_types.type_id AS type_id, " +
                "catalog.sub_type_id AS subtype_id, types.name AS type_name, " +
                "sub_types.name AS subtype_name, catalog.name AS name, " +
                "catalog.description, catalog.price FROM catalog " +
                "INNER JOIN sub_types ON catalog.sub_type_id = sub_types.id " +
                "INNER JOIN types ON sub_types.type_id = types.id", rowMapper);
    }

    public List<Category> getCategories() {
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategoryItem(rs);
        return jdbcTemplate.query("SELECT * from types", rowMapper);
    }

    public List<Category> getSubCategories(int id) {
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategoryItem(rs);
        return jdbcTemplate.query("SELECT * from sub_types WHERE type_id = ?", rowMapper, id);
    }

    private CatalogItem mapCatalogItem(ResultSet rs) throws SQLException {
        CatalogItem item = new CatalogItem();

        item.setId(rs.getInt("cat_id"));
        item.setTypeId(rs.getInt("type_id"));
        item.setSubTypeId(rs.getInt("subtype_id"));
        item.setTypeName(rs.getString("type_name"));
        item.setSubTypeName(rs.getString("subtype_name"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getDouble("price"));

        return item;
    }

    private Category mapCategoryItem(ResultSet rs) throws SQLException {
        return new Category(rs.getInt("id"), rs.getString("name"));
    }
}
