import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;

final class Item {
	public Item(final String _name, final double _price) {
		name = _name;
		price = _price;
	}
	public String toString(final String extra) {
		return name + extra + "$" + price;
	}
	private String name;
	private double price;
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
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
		for (final Item i : items) {
			final int spacing = 40 - i.getName().length();
			String spacing2 = "";
			for (int j = 0; j < spacing; ++j) {
				spacing2 += " ";
			}
			System.out.println(i.toString(spacing2));
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
				System.out.println("Select an item from the list. Type \"Q\" to quit.");
			} else {
				System.out.println("Select another item from the list. Type \"Q\" to quit.");
			}
			final String tmp = sc.nextLine();
			if (tmp.toLowerCase().trim().equals("q")) {
				cont = false;
			} else {
				boolean test = false;
				for (final Item i : items) {
					if (i.getName().equalsIgnoreCase(tmp)) {
						order.add(i);
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
		for (final Item i : order) {
			final int spacing = 40 - i.getName().length();
			String spacing2 = "";
			for (int j = 0; j < spacing; ++j) {
				spacing2 += " ";
			}
			System.out.println(i.toString(spacing2));
			total += i.getPrice();
		}
		
		System.out.println("\nTotal Cost: $" + total);
		System.out.print("Average Cost: $" + avgCost(order));
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