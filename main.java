package ben_aharoni_amit_halaly;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;

public class main {
	private static StoreMemento backup;

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner input = new Scanner(System.in);
		storeFacade s = storeFacade.getInstance();
		String choose = "0";
		do {
			System.out.println("press:\n 1) automatic Create  \n " + "2) to add a new product  \n"
					+ " 3) to remove a product \n " + "4) to update a product's amount \n"
					+ " 5) to add an order for a product \n" + " 6) to remove the last order \n"
					+ " 7) to review a prodact's details \n" + " 8) to review all of the products \n"
					+ " 9) to print a product's orders \n" + " 10) restore last backup \n" + " e\\E) to exit  \n ");
			choose = input.nextLine();
			switch (choose) {
			case "1":
				automaticCreate(s);

				break;

			case "2":
				addNewProduct(s);

				break;

			case "3":
				removeproduct(s);

				break;

			case "4":
				updateProductAmount(s);

				break;

			case "5":
				addOrderToproduct(s);

				break;

			case "6":
				undo(s);

				break;

			case "7":
				printAllDetailsForSingleProduct(s);

				break;

			case "8":
				printAllProducts(s);

				break;

			case "9":
				printSingleProduct(s);

				break;

			case "10":
				int choice = 0;
				System.out.println("press:\n 1) to backup the program  \n " + "2) to reset the last state   \n");
				choice = input.nextInt();
				switch (choice) {
				case 1:
					backupProgram(s);
					input.nextLine();

					break;

				case 2:
					resetProgram(s);
					input.nextLine();

					break;

				default:
					System.out.println("invalid choice try again");
					input.nextLine();
				}
				break;

			default:
				if (!choose.equalsIgnoreCase("e") && !choose.equalsIgnoreCase("E")) {
					System.out.println("invalid choice try again");
				}
			}
		} while (!choose.equalsIgnoreCase("e") && !choose.equalsIgnoreCase("E"));
		System.out.println("bye");
	}

	public static void automaticCreate(storeFacade s) {
		soldthroughwebsiteFactory stwfactory = new soldthroughwebsiteFactory();
		soldinStoreFactory sisfactory = new soldinStoreFactory();
		SoldToWholesellersFactory stfactory = new SoldToWholesellersFactory();
		customer c1 = new customer("amit", "0528569874");
		customer c2 = new customer("ben", "0548965234");
		customer c3 = new customer("daniel", "0542387954");
		products p1 = stwfactory.createsoldthroughwebsite("pants", 20, 50, 10, "israel", "aaa", 10);
		products p2 = stwfactory.createsoldthroughwebsite("gloves", 10, 20, 50, "yeman", "bbb", 15.5);
		products p3 = stwfactory.createsoldthroughwebsite("hats", 20, 40, 30, "usa", "ccc", 20.2);
		products p4 = sisfactory.createsoldinstore("shirt", 50, 150, 20, "ddd", 35);
		products p5 = sisfactory.createsoldinstore("socks", 5, 20, 30, "eee", 50.3);
		products p6 = sisfactory.createsoldinstore("shoes", 100, 200, 10, "fff", 60.5);
		products p7 = stfactory.createSoldToWholesellers("polo", 20, 50, 10, "ggg", 100);
		products p8 = stfactory.createSoldToWholesellers("scarf", 10, 20, 30, "hhh", 6.9);
		products p9 = stfactory.createSoldToWholesellers("underwear", 40, 80, 20, "zzz", 98.99);

		s.addProduct(p1);
		p1.addOrder(c1, 1, "aa", eShipmentType.EXPRESS);
		p1.addOrder(c2, 1, "ab", eShipmentType.STANDARD);
		p1.addOrder(c3, 1, "ac", eShipmentType.EXPRESS);

		s.addProduct(p2);
		p2.addOrder(c1, 1, "ba", eShipmentType.EXPRESS);
		p2.addOrder(c2, 1, "bb", eShipmentType.STANDARD);
		p2.addOrder(c3, 1, "bc", eShipmentType.EXPRESS);

		s.addProduct(p3);
		p3.addOrder(c1, 1, "ca", eShipmentType.EXPRESS);
		p3.addOrder(c2, 1, "cb", eShipmentType.STANDARD);
		p3.addOrder(c3, 1, "cc", eShipmentType.EXPRESS);

		s.addProduct(p4);
		p4.addOrder(c1, 1, "da", eShipmentType.EXPRESS);
		p4.addOrder(c2, 1, "db", eShipmentType.STANDARD);
		p4.addOrder(c3, 1, "dc", eShipmentType.EXPRESS);

		s.addProduct(p5);
		p5.addOrder(c1, 1, "ea", eShipmentType.EXPRESS);
		p5.addOrder(c2, 1, "eb", eShipmentType.STANDARD);
		p5.addOrder(c3, 1, "ec", eShipmentType.EXPRESS);

		s.addProduct(p6);
		p6.addOrder(c1, 1, "fa", eShipmentType.EXPRESS);
		p6.addOrder(c2, 1, "fb", eShipmentType.STANDARD);
		p6.addOrder(c3, 1, "fc", eShipmentType.EXPRESS);

		s.addProduct(p7);
		p7.addOrder(c1, 1, "ga", eShipmentType.EXPRESS);
		p7.addOrder(c2, 1, "gb", eShipmentType.STANDARD);
		p7.addOrder(c3, 1, "gc", eShipmentType.EXPRESS);

		s.addProduct(p8);
		p8.addOrder(c1, 1, "ha", eShipmentType.EXPRESS);
		p8.addOrder(c2, 1, "hb", eShipmentType.STANDARD);
		p8.addOrder(c3, 1, "hc", eShipmentType.EXPRESS);

		s.addProduct(p9);
		p9.addOrder(c1, 1, "ia", eShipmentType.EXPRESS);
		p9.addOrder(c2, 1, "ib", eShipmentType.STANDARD);
		p9.addOrder(c3, 1, "ic", eShipmentType.EXPRESS);
		ShippingCompany sc1 = new DHLcomp("DHL", "ben", 0, "052-4444444");
		s.addShippingCompany(sc1);
		ShippingCompany sc2 = new FDEXcomp("FDEX", "amit", 0, "054-2222222");
		s.addShippingCompany(sc2);
		System.out.println("auto creation completed");
	}

	public static void addNewProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		int choose = 0;
		String product_name;
		String serialnum;
		int cost_price;
		int selling_price;
		int stock;
		double weight;
		do {
			System.out.println("choose a product type\n");
			System.out.println("press:\n 1)for sold in Store  \n " + "2) for sold through website \n"
					+ " 3)for sold to wholesellers\n ");
			choose = input.nextInt();
			if (choose < 1 || choose > 3) {
				System.out.println("invalid option");
				return;
			}
		} while (choose == 0);
		System.out.println("please enter product name : ");
		product_name = input.next();
		System.out.println("please enter serial code : ");
		serialnum = input.next();
		System.out.println("please enter product cost price : ");
		cost_price = input.nextInt();
		System.out.println("please enter product selling price : ");
		selling_price = input.nextInt();
		System.out.println("please enter product weight : ");
		weight = input.nextDouble();
		System.out.println("please enter the amount of products : ");
		stock = input.nextInt();
		switch (choose) {
		case 1:
			soldinStoreFactory sisfactory = new soldinStoreFactory();
			s.addProduct(
					sisfactory.createsoldinstore(product_name, cost_price, selling_price, stock, serialnum, weight));
			break;

		case 2:
			System.out.println("please enter the dest country : ");
			String dest_country = input.next();
			soldthroughwebsiteFactory stwfactory = new soldthroughwebsiteFactory();
			s.addProduct(stwfactory.createsoldthroughwebsite(product_name, cost_price, selling_price, stock,
					dest_country, serialnum, weight));
			break;

		case 3:
			SoldToWholesellersFactory stfactory = new SoldToWholesellersFactory();
			s.addProduct(stfactory.createSoldToWholesellers(product_name, cost_price, selling_price, stock, serialnum,
					weight));
			break;

		}
	}
	public static void removeproduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to remove");
			return;
		}
		print(s);
		System.out.println("\nEnter the serial code of the product you want to remove :\n");
		products foundObject = null;
		String serial = input.next();
		for (products element : s.getProduct_list()) {
			if (element.getSerialnum().equals(serial)) {
				foundObject = element;
				break;
			}
		}
		s.deleteProduct(foundObject);
	}

	public static void updateProductAmount(storeFacade s) {
		Scanner input = new Scanner(System.in);
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to update");
			return;
		}
		print(s);
		System.out.println("\nEnter the serial code of the product you want to change :\n");
		products foundObject = null;
		String serial = input.next();
		for (products element : s.getProduct_list()) {
			if (element.getSerialnum().equals(serial)) {
				foundObject = element;
				break;
			}
		}
		System.out.println("\nenter the new amount :\n");
		s.updateStock(foundObject, input.nextInt());
	}

	public static void addOrderToproduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to add an order to");
			return;
		}
		int choose = 0;
		System.out.println("choose the product type\n");
		System.out.println("press:\n 1)for sold in Store  \n " + "2) for sold through website \n"
				+ " 3)for sold to wholesellers\n ");
		choose = input.nextInt();
		if (choose < 1 || choose > 3) {
			System.out.println("invalid option");
			return;
		} else if (choose == 2) {
			Iterator<products> it = s.getProductIterator();
			while (it.hasNext()) {
				products element = it.next();
				if (element instanceof soldThroughWebsite) {
					System.out.println(element);
					System.out.println("-----------------------");
				}
			}
			System.out.println("\nEnter the serial code of the product you want to order:\n");
			products foundObject = null;
			int amount, i = 1;
			String oserial, name, mobile;
			String serial = input.next();
			for (products element : s.getProduct_list()) {
				if (element.getSerialnum().equals(serial)) {
					foundObject = element;
					System.out.println("please enter the customer's name:");
					name = input.next();
					System.out.println("please enter the mobile:");
					mobile = input.next();
					System.out.println("please enter the order serial number:");
					oserial = input.next();
					System.out.println("please enter the amount:");
					amount = input.nextInt();
					System.out.println("press: ");
					for (eShipmentType type : eShipmentType.values()) {
						System.out.println("(" + (i++) + ") for " + type);
					}
					i = input.nextInt();
					switch (i) {

					case 1:
						foundObject.addOrder(new customer(name, mobile), amount, oserial, eShipmentType.EXPRESS);
						System.out.println("order has been created");
						break;

					case 2:
						foundObject.addOrder(new customer(name, mobile), amount, oserial, eShipmentType.STANDARD);
						System.out.println("order has been created");
						break;

					default:
						System.out.println("invalid try again");
					}
					break;
				} else {
					System.out.println("no such product with that serial in that type");
					return;
				}
			}
		} else if (choose == 1) {
			Iterator<products> it = s.getProductIterator();
			while (it.hasNext()) {
				products element = it.next();
				if (element instanceof soldinStore) {
					System.out.println(element);
					System.out.println("-----------------------");
				}
			}
		} else if (choose == 3) {
			Iterator<products> it = s.getProductIterator();
			while (it.hasNext()) {
				products element = it.next();
				if (element instanceof SoldToWholesellers) {
					System.out.println(element);
					System.out.println("-----------------------");
				}
			}
		}
		if (choose == 1 || choose == 3) {
			System.out.println("\nEnter the serial code of the product you want to order:\n");
			products foundObject = null;
			int amount;
			String oserial, name, mobile;
			String serial = input.next();
			for (products element : s.getProduct_list()) {
				if (element.getSerialnum().equals(serial)) {
					foundObject = element;
					System.out.println("please enter the customer's name:");
					name = input.next();
					System.out.println("please enter the mobile:");
					mobile = input.next();
					System.out.println("please enter the order serial code:");
					oserial = input.next();
					System.out.println("please enter the amount:");
					amount = input.nextInt();
					if (foundObject.addOrderWithoutShipment(new customer(name, mobile), amount, oserial))
						System.out.println("order has been created");
				}

			}
		}
		input.nextLine();
	}

	public static void undo(storeFacade s) {
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to undo ");
			return;
		}
		Scanner input = new Scanner(System.in);
		print(s);
		System.out.println("\nEnter the serial code of the product you want to undo the last order for: \n");
		String serial = input.next();
		products product = s.findProductBySerial(serial);
		if (product == null) {
			System.out.println("No product found with the serial : " + serial);
			return;
		}
		if (product.undoOrder()) {
			return;
		} else {
			System.out.println("There are no orders to undo for this product.");
		}
	}

	public static void printAllDetailsForSingleProduct(storeFacade s) {

		Scanner input = new Scanner(System.in);
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to print");
			return;
		}
		print(s);
		System.out.println("\nEnter the serial code of the product you want to view :\n");
		products foundObject = null;
		String serial = input.next();
		for (products element : s.getProduct_list()) {
			if (element.getSerialnum().equals(serial)) {
				foundObject = element;
				break;
			}
		}
		if (foundObject == null) {
			System.out.println("invalid input");
			return;
		}
		System.out.println(foundObject);
		int i = 1, profit = 0, profit1 = 0;
		System.out.println("\nthe orders for " + foundObject.getProduct_name() + ":\n");
		for (orders order : foundObject.getOrderList()) {
			if (foundObject instanceof soldThroughWebsite) {
				profit1 = foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
				System.out.println("\n" + (i++) + ")" + order + "the profit for this order :" + profit1 + "$\n");
				System.out.println(s.findCheapestShippingOption(order));
				profit += foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
			} else {
				profit1 = foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
				System.out.println("\n" + (i++) + ")" + order + "the profit for this order: " + profit1 * 4 + "₪\n");
				profit += foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
				if(foundObject instanceof soldinStore) {
					((soldinStore) foundObject).invoiceFormatToAcountant(foundObject, order.getQuantity());
					((soldinStore) foundObject).invoiceFormatToCustomer(foundObject, order.getCustomer(), order.getQuantity());
				}else
					 ((SoldToWholesellers)foundObject).invoiceFormatToAcountant(foundObject, order.getQuantity());
			}
		}
		if (foundObject instanceof soldThroughWebsite) {
			System.out.println("the total profit from this product is: " + profit + "$");
			System.out.println("\n--------------------\n");
		} else {
			System.out.println("the total profit from this product is: " + profit * 4 + "₪");
		}
		
		
	}

	public static void printAllProducts(storeFacade s) {
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to print");
			return;
		}
		Iterator<products> it = s.getProductIterator();
		while (it.hasNext()) {
			int i = 1, profit = 0;
			products element = it.next();
			System.out.println(element);
			System.out.println("\nthe orders for " + element.getProduct_name() + ":\n");
			for (orders order : element.getOrderList()) {
				if (element instanceof soldThroughWebsite) {
					System.out.println((i++) + ")" + order);
					profit += element.selling_price * order.getQuantity() - element.cost_price * order.getQuantity();
				} else {
					System.out.println((i++) + ")" + order.toStringNoShipment());
					profit += element.selling_price * order.getQuantity() - element.cost_price * order.getQuantity();
				}
			}
			if (element instanceof soldThroughWebsite) {
				System.out.println("the profit from this product is: " + profit + "$");
				System.out.println("\n--------------------\n");
			} else {
				System.out.println("the profit from this product is: " + profit * 4 + "nis");
				System.out.println("\n--------------------\n");
			}
		}
	}

	public static void printSingleProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		if (s.getProduct_list().isEmpty()) {
			System.out.println("there aren't any products to print");
			return;
		}
		print(s);
		System.out.println("\nEnter the serial code of the product you want to view :\n");
		products foundObject = null;
		String serial = input.next();
		for (products element : s.getProduct_list()) {
			if (element.getSerialnum().equals(serial)) {
				foundObject = element;
				break;
			}
		}
		if (foundObject == null) {
			System.out.println("invalid input");
			return;
		}
		System.out.println(foundObject);
		int i = 1, profit = 0, profit1 = 0;
		System.out.println("\nthe orders for " + foundObject.getProduct_name() + ":\n");
		for (orders order : foundObject.getOrderList()) {
			if (foundObject instanceof soldThroughWebsite) {
				profit1 = foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
				System.out.println((i++) + ")" + order + "the profit for this order :" + profit1 + "$\n");
				profit += foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
			} else {
				profit1 = foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
				System.out.println((i++) + ")" + order + "the profit for this order: " + profit1 * 4 + "nis\n");
				profit += foundObject.selling_price * order.getQuantity()
						- foundObject.cost_price * order.getQuantity();
			}
		}
		if (foundObject instanceof soldThroughWebsite) {
			System.out.println("the total profit from this product is: " + profit + "$");
			System.out.println("\n--------------------\n");
		} else {
			System.out.println("the total profit from this product is: " + profit * 4 + "nis");
		}
	}

	public static void backupProgram(storeFacade s) throws CloneNotSupportedException {
		backup = s.saveMem();
		System.out.println("the current state has been backed up");
	}

	public static void resetProgram(storeFacade s) {
		if (backup != null) {
			s.resetMem(backup);
			System.out.println("the previous state has been restored");
		} else {
			System.out.println("No backup has been made , please ensure to backup the program before the reset.");
		}
	}

	public static void print(storeFacade s) {

		Iterator<products> it = s.getProductIterator();
		while (it.hasNext()) {
			products element = it.next();
			System.out.println(element);
			System.out.println("-----------------------");

		}
	}
}
