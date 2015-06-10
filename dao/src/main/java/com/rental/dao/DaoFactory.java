package com.rental.dao;

/**
 * Created by Dasha on 09.06.15.
 */
public interface DaoFactory {
    IPersonDao createPersonDao();
    IPropertyDao createPropertyDao();
}
