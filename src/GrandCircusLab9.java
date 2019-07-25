import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;

final class Item {
	public Item(final String _name, final double _price) {
		name = _name;
		price = _price;
		quantity = 1;
	}
	@Override
	public String toString() {
		return name + ": $" + price;
	}
	public String toString(final String extra) {
		String ret = name + extra + "$" + price;
		if (quantity > 1) {
			ret += (" x"+quantity);
		}
		return ret;
	}
	private String name;
	private double price;
	private int quantity;
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public void incQuantity() {
		++quantity;
	}
	public int getQuantity() {
		return quantity;
	}
}

final class ItemSorter implements Comparator<Item> {
	// sorts lowest to highest price
	public int compare(final Item a, final Item b) {
		return (int) (a.getPrice() - b.getPrice());
	}
}

public class GrandCircusLab9 {
	
	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		final ArrayList<Item> items = new ArrayList<Item>();
		System.out.println("Welcome to the hardware store:\n");
		items.add(new Item("Pro Stand", 999.99));
		items.add(new Item("Mouse", 30.49));
		items.add(new Item("Keyboard", 24.99));
		items.add(new Item("Laptop", 499.99));
		items.add(new Item("Grand Circus Mug", 14.99));
		items.add(new Item("Smart Phone", 299.99));
		items.add(new Item("22\" PC monitor", 149.99));
		items.add(new Item("30\" PC monitor", 199.99));
		items.add(new Item("Water-proof laptop bag", 69.99));
		items.add(new Item("Tablet", 249.99));
		items.sort(new ItemSorter());
		
		System.out.println("Item	" + "				" + "Price" + "\n===============================================");
		for (int i = 0; i < items.size(); ++i) {
			final int spacing = 40 - items.get(i).getName().length();
			String spacing2 = "";
			for (int j = 0; j < spacing; ++j) {
				spacing2 += " ";
			}
			System.out.println((i+1) + ". " + items.get(i).toString(spacing2));
		}
		
		/*System.out.println("Index of lowest value: " + lowestIndex(items));
		System.out.println("Index of highest value: " + highestIndex(items));
		System.out.println("Average ArrayList value: " + avgCost(items));*/
		
		// selection:
		boolean cont = true;
		final Scanner sc = new Scanner(System.in);
		final ArrayList<Item> order = new ArrayList<Item>();
		int iter = 0;
		while (cont) {
			if (++iter == 1) {
				System.out.println("Select an item or item number from the list. Type \"Q\" to quit.");
			} else {
				System.out.println("Select an item or item number another item from the list. Type \"Q\" to quit.");
			}
			String tmp = sc.nextLine();
			try {
				final int tmp2 = Integer.parseInt(tmp);
				if (tmp2 > 0 && tmp2 <= items.size()) {
					tmp = items.get(tmp2-1).getName();
					System.out.println("You selected " + tmp + ".");
				}
			} catch (NumberFormatException e) {}
			if (tmp.toLowerCase().trim().equals("q")) {
				cont = false;
			} else {
				boolean test = false;
				for (final Item i : items) {
					if (i.getName().equalsIgnoreCase(tmp)) {
						boolean inced = false;
						for (final Item i2 : order) {
							if (i2.getName().equalsIgnoreCase(tmp)) {
								i2.incQuantity();
								inced = true;
							}
						}
						if (!inced) {
							order.add(i);
						}
						test = true;
						break;
					}
				}
				if (!test) {
					System.out.println("The item you entered was not in the list; please try again, or type \"Q\" to quit.");
				}
			}
		}
		sc.close();
		System.out.println("Your Order:\n");
		System.out.println("Item	" + "				" + "Price" + "\n===============================================");
		
		double total = 0;
		Item cheapest = null;
		Item expensivest = null;
		for (final Item i : order) {
			if (cheapest == null || i.getPrice() < cheapest.getPrice()) {
				cheapest = i;
			}
			if (expensivest == null || i.getPrice() > expensivest.getPrice()) {
				expensivest = i;
			}
			final int spacing = 40 - i.getName().length();
			String spacing2 = "";
			for (int j = 0; j < spacing; ++j) {
				spacing2 += " ";
			}
			System.out.println(i.toString(spacing2));
			total += i.getPrice()*i.getQuantity();
		}
		
		System.out.println("\nTotal Cost: $" + total);
		System.out.println("Average Cost: $" + avgCost(order));
		System.out.println("Most Expensive Item: " + expensivest.toString());
		System.out.println("Least Expensive Item: " + cheapest.toString());
	}
	
	public static int highestIndex(final ArrayList<Item> items) {
		double max = -1;
		int ret = -1;
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).getPrice() > max) {
				max = items.get(i).getPrice();
				ret = i;
			}
		}
		return ret;
	}

	public static int lowestIndex(final ArrayList<Item> items) {
		double min = (1000*1000);
		int ret = -1;
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).getPrice() < min) {
				min = items.get(i).getPrice();
				ret = i;
			}
		}
		return ret;
	}
	
	public static double avgCost(final ArrayList<Item> items) {
		double ret = 0;
		for (final Item i: items) {
			ret += i.getPrice();
		}
		return ret/items.size();
	}
}