package DataStorage;

import java.util.Hashtable;

/**
 *
 * Created by David Meade on 8/5/2016.
 *
 */
public class Database {

	// Hashtable of Table objects
	// Identified by the unique name of each table

	private Hashtable tableHolder;

	private int tableCount = 0;


	public Database() {

		// Initialize hashtable holding all tables for database
		this.tableHolder = new Hashtable();
	}

	public void addTable() {

	}

	private void display() {

	}

	public static void main(String[] args) {

		Database db1 = new Database();

		db1.display();

	}
}
