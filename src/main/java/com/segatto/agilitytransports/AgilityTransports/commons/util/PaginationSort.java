package com.segatto.agilitytransports.AgilityTransports.commons.util;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*Standard: sort="column, direction"*/
@Component
public class PaginationSort {

    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    private static final String EMPTY = "";

    public String getOrderByConvertedToString(List<String> sortList){
        if (sortList == null)
                return EMPTY;

        List<Sort.Order> orders = getOrderList(sortList);
        return getOrdersConvertedToString(orders);
    }

    private List<Sort.Order> getOrderList(List<String> sortList) {
        List<Sort.Order> orders = new ArrayList<>();
        if (isMultipleOrders(sortList)) {
            for (String sortOrder : sortList) {
                String[] _sort = sortOrder.split(",");
                String direction = (_sort.length > 1) ? _sort[1].toUpperCase() : ASC;
                orders.add(new Sort.Order(getSortDirection(direction), _sort[0]));
            }
        } else {
            String direction = (sortList.size() > 1) ? sortList.get(1).toUpperCase() : ASC;
            orders.add(new Sort.Order(getSortDirection(direction), sortList.get(0)));
        }

        return orders;
    }

    private Boolean isMultipleOrders(List<String> sortList) {
        int sortListSize = sortList.size();

        if (sortList.get(0).contains(","))
            return true;

        if (sortListSize > 1 &&
                (!sortList.get(1).equalsIgnoreCase(ASC) && !sortList.get(1).equalsIgnoreCase(DESC)))
            return true;

        return false;
    }

    private String getOrdersConvertedToString(List<Sort.Order> orders) {
        String orderString = null;
        if (!orders.isEmpty()) {
            orderString = "order by ";

            for (Sort.Order order : orders) {
                orderString = orderString.concat(order.getProperty()).concat(" ").concat(order.getDirection().name()).concat(",");
            }

            orderString = orderString.substring(0, orderString.length() - 1);
        }

        return orderString;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals(ASC)) {
            return Sort.Direction.ASC;
        } else if (direction.equals(DESC)) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

}
