public class Payment {
    private String paymentId;
    private String customerId;
    private double amount;
    private String paymentMethod;
    private String date;

    public Payment(String paymentId, String customerId, double amount, String paymentMethod, String date) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public void processPayment() {
        System.out.println("Processing payment of " + amount + " via " + paymentMethod);
    }
}

