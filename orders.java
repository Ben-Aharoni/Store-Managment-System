package ben_aharoni_amit_halaly;

public class orders {
	private customer customer;
	private products product;
	private int quantity;
	private String serial;
	private eShipmentType type;

	public orders(customer customer, products product, int quantity, String serial, eShipmentType type) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.serial = serial;
		this.type = type;
	}

	public orders(customer customer, products product, int quantity, String serial) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.serial = serial;
	}

	@Override
	public String toString() {
		return "customer: " + customer + "\nquantity: " + quantity + "\nserial: " + serial + "\nshipment type: " + type
				+ "\n";
	}

	public String toStringNoShipment() {
		return "customer: " + customer + "\nquantity: " + quantity + "\nserial: " + serial + "\n";
	}

	public eShipmentType getType() {
		return type;
	}

	public customer getCustomer() {
		return customer;
	}

	public products getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public orders oclone() {
		try {
			return (orders) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
