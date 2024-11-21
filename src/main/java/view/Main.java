package view;

import model.Sale;

public class Main {
    public static void main(String[] args) {
        Sale sale = new Sale();

        System.out.println("Iniciando venda...");

        if (sale.createSaleItem("at", 2)) 
            System.out.println("Item adicionado com sucesso!");
        if (sale.createSaleItem("ff", 6)) 
            System.out.println("Item adicionado com sucesso!"); // Deve falhar (estoque insuficiente)
        if (sale.createSaleItem("msa", 2)) 
            System.out.println("Item adicionado com sucesso!");

        sale.setPaymentMethod("c");

        System.out.println("\nItens na venda:");
        for (String item : sale.getItems())
            System.out.println(item);

        System.out.println(String.format("Total: %.2f", sale.getTotal()));
        System.out.println(String.format("Forma de pagamento: '%s'", sale.getPaymentMethod()));
        System.out.println(String.format("Valor a ser pago: R$%.2f", sale.getFinalPrice()));

        System.out.println("\nEstoque atualizado:");
        System.out.println("Arroz Tatiana: " + model.DataBase.getStock("at"));
        System.out.println("Feijão Fejó: " + model.DataBase.getStock("ff"));
        System.out.println("Macarrão Santa Amália: " + model.DataBase.getStock("msa"));
    }
}