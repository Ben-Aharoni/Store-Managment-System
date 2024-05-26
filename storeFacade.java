package ben_aharoni_amit_halaly;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

public class storeFacade {
	private final int DOLLAR_RATE = 4;
	private static storeFacade instance = null;
	private Caretaker caretaker = new Caretaker();
	private TreeSet<products> product_list = new TreeSet<products>();
	private ArrayList<ShippingCompany> ShippingCompany_list = new ArrayList<>();

	private storeFacade() {

	}
	
	public static storeFacade getInstance() {
		if (instance == null) {
			instance = new storeFacade();
		}
		return instance;
	}

	public TreeSet<products> getProduct_list() {
		return product_list;
	}

	boolean addProduct(products product) {
		boolean exist = product_list.add(product);
		if (!exist) {
			System.out.println("Product with serial " + product.getSerialnum() + " already exists.");
			return false;
		}
		System.out.println("product " + product.getProduct_name() +" has been added");
		return exist;
	}

	public void addShippingCompany(ShippingCompany shippingCompany) {
		ShippingCompany_list.add(shippingCompany);
	}

	public ArrayList<ShippingCompany> getShippingCompaniesList() {
		return ShippingCompany_list;
	}

	public void deleteProduct(products foundObject) {
		if (foundObject != null) {
			product_list.remove(foundObject);
			System.out.println("the product has been deleted");
		} else
			System.out.println("product not found");
	}

	public void updateStock(products foundObject, int amount) {

		if (foundObject != null) {
			product_list.remove(foundObject);
			foundObject.setStock(amount);
			product_list.add(foundObject);
			System.out.println("the amount of " + foundObject.product_name + " has been changed to " + amount);
		} else
			System.out.println("product not found");
	}

	public Iterator<products> getProductIterator() {
		return product_list.iterator();
	}

	public products findProductBySerial(String serialId) {
		for (products product : product_list) {
			if (product.getSerialnum().equals(serialId)) {
				return product;
			}
		}
		return null;
	}

	public StoreMemento saveMem() throws CloneNotSupportedException {
		TreeSet<products> clonedProducts = new TreeSet<>();
		for (products product : this.product_list) {
			clonedProducts.add(product.pclone());
		}
		return new StoreMemento(clonedProducts);
	}

	public void resetMem(StoreMemento memento) {
		this.product_list = new TreeSet<>(memento.getState());
	}

	public ShippingCompany findCheapestShippingOption(orders order) {
		ShippingCompany cheapest = null;
		double lowestFee = Double.MAX_VALUE;
		for (ShippingCompany company : ShippingCompany_list) {
			double fee = company.calculateShippingFee(order);
			if (fee < lowestFee) {
				lowestFee = fee;
				cheapest = company;
			}
		}
		cheapest.setShippingFee(lowestFee);
		return cheapest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product_list);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		storeFacade other = (storeFacade) obj;
		return Objects.equals(product_list, other.product_list);
	}

}
