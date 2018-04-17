package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculate() {
        return new Calculate().invoke();
    }

    private class Calculate {
        public BigDecimal invoke() {
            BigDecimal subTotal = new BigDecimal(0);

            // Total up line items
            subTotal = getTotalUp(subTotal);

            // Subtract discounts
            subTotal = subtractDiscounts(subTotal);

            // calculate tax
            BigDecimal tax = calculateTax(subTotal);

            // calculate GrandTotal
            BigDecimal grandTotal = subTotal.add(tax);

            return grandTotal;
        }

        private BigDecimal calculateTax(BigDecimal subTotal) {
            return subTotal.multiply(Order.this.tax);
        }

        private BigDecimal subtractDiscounts(BigDecimal subTotal) {
            for (BigDecimal discount : discounts) {
                subTotal = subTotal.subtract(discount);
            }
            return subTotal;
        }

        private BigDecimal getTotalUp(BigDecimal subTotal) {
            for (OrderLineItem lineItem : orderLineItemList) {
                subTotal = subTotal.add(lineItem.getPrice());
            }
            return subTotal;
        }
    }
}
