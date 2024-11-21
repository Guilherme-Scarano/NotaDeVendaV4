package model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<SaleItem> items = new ArrayList<>();
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(String payment) {
        paymentMethod = DataBase.selectPayment(payment);
    }

    public String getPaymentMethod() {
        return paymentMethod.toString();
    }

    public boolean createSaleItem(String code, int quantity) {
        if (!DataBase.checkStock(code, quantity)) {
            System.out.println(String.format("Estoque insuficiente para o produto '%s'. Disponível: %d",
                    DataBase.selectProduct(code)[0], DataBase.getStock(code)));
            return false;
        }

        SaleItem item = new SaleItem();
        item.createProduct(code, quantity);
        items.add(item);

        DataBase.updateStock(code, quantity);
        return true;
    }

    public double getTotal() {
        double total = 0;
        for (SaleItem saleItem : items) 
            total += saleItem.getSubtotal();
        return total;
    }

    public List<String> getItems() {
        List<String> itemsStr = new ArrayList<>();
        for (SaleItem saleItem : items)
            itemsStr.add(saleItem.toString());
        return itemsStr;
    }

    public double getFinalPrice() {
        return paymentMethod.calculate(getTotal());
    }
}
