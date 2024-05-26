package ben_aharoni_amit_halaly;

public abstract class ShippingCompany {
	private String name;
	private String contact;
	private double shippingFee;
	private String whatsup;

	public ShippingCompany(String name, String contact, double shippingFee, String whatsup) {
		this.name = name;
		this.contact = contact;
		this.shippingFee = shippingFee;
		this.whatsup = whatsup;

	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getWhatsup() {
		return whatsup;
	}

	public void setWhatsup(String whatsup) {
		this.whatsup = whatsup;
	}

	public abstract double calculateShippingFee(orders order);

	@Override
	public String toString() {
		return "ShippingCompany: " + name + "\ncontact: " + contact + "\nshippingFee: " + shippingFee + "$\nwhatsup: "
				+ whatsup;
	}

}