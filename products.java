package ben_aharoni_amit_halaly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class products implements Comparable<products>, Cloneable {
	protected String product_name;
	protected String serialnum;
	protected int cost_price;
	protected int selling_price;
	protected int stock;
	protected double weight;
	protected ArrayList<orders> order_list = new ArrayList<>();

	public products(String product_name, int cost_price, int selling_price, int stock, String serialnum,
			double weight) {
		this.setProduct_name(product_name);
		this.setCost_price(cost_price);
		this.setSelling_price(selling_price);
		this.setStock(stock);
		this.setSerialnum(serialnum);
		this.setWeight(weight);
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public ArrayList<orders> getOrderList() {
		return order_list;
	}

	public Iterator<orders> getOrderIterator() {
		return order_list.iterator();
	}

	void addOrder(customer customer, int quantity, String serial, eShipmentType type) {
		if (quantity > this.stock) {
			System.out.println("Not enough stock available for this product.");
			return;
		}
		for (orders order : getOrderList()) {
			if (order.getSerial().equals(serial)) {
				System.out.println("an order with that serial already exists");
				return;
			}
		}
		this.stock -= quantity;
		order_list.add(new orders(customer, this, quantity, serial, type));
	}

	boolean addOrderWithoutShipment(customer customer, int quantity, String serial) {
		if (quantity > this.stock) {
			System.out.println("Not enough stock available for this product.");
			return false;
		}
		for (orders order : getOrderList()) {
			if (order.getSerial().equals(serial)) {
				System.out.println("an order with that serial already exists");
				return false;
			}
		}
		this.stock -= quantity;
		order_list.add(new orders(customer, this, quantity, serial));
		return true;
	}

	public boolean undoOrder() {
		if (order_list.isEmpty()) {
			return false;
		}
		Iterator<orders> iterator = order_list.iterator();
		orders recentOrder = null;

		while (iterator.hasNext()) {
			recentOrder = iterator.next();
		}

		if (recentOrder != null) {
			iterator.remove();
			System.out.println("Order " + recentOrder.getSerial() + " has been cancelled by the store");
			stock += recentOrder.getQuantity();
			return true;
		}
		return false;
	}

	public products pclone() {
		try {
			products cloned = (products) super.clone();
			cloned.order_list = new ArrayList<orders>();
			for (orders order : this.order_list) {
				cloned.order_list.add(order);
			}
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(serialnum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		products other = (products) obj;
		return Objects.equals(serialnum, other.serialnum);
	}

	@Override
	public int compareTo(products p) {
		return this.getSerialnum().compareTo(p.getSerialnum());
	}

	@Override
	public String toString() {

		return "Product Name: " + product_name + "\nProduct serial: " + serialnum + "\nCurrent Stock: " + stock
				+ "\nweight: " + weight + "\nProduct Type: " + this.getClass().getSimpleName();
	}

}
