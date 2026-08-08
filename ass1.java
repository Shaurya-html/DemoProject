package Assignments;

import java.sql.*;
import java.util.*;

public class ass1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Gugyftft@1800");
			int choice;

			do {
				System.out.println("\n1.Register \n2.View \n3.Search \n4.Update \n5.Delete \n6.Exit\n");
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1: {

					System.out.println("Enter Your Name: ");
					String cname = sc.nextLine();

					System.out.println(
							"Enter Your Complaint Type (Water Supply • Garbage • Street Light • Road Damage • Drainage): ");
					String comp_type = sc.nextLine();

					System.out.println("Enter Location of Complaint: ");
					String comp_loc = sc.nextLine();

					PreparedStatement pt = con.prepareStatement(
							"insert into complaints(citizen_name, complaint_type, location) values(?,?,?)");

					pt.setString(1, cname);
					pt.setString(2, comp_type);
					pt.setString(3, comp_loc);
					pt.executeUpdate();
					System.out.println("\nData Entered Successfully.");
					break;
				}

				case 2:

				{
					PreparedStatement pt = con.prepareStatement("select * from complaints");
					ResultSet rs = pt.executeQuery();

					while (rs.next()) {

						int comp_id = rs.getInt("complaint_id");
						String cname = rs.getString("citizen_name");
						String comp_type = rs.getString("complaint_type");
						String comp_loc = rs.getString("location");
						String status = rs.getString("status");

						System.out.println(
								comp_id + "	" + cname + "	" + comp_type + "	" + comp_loc + "		" + status);

					}
					break;
				}

				case 3: {
					System.out.println("Enter the Complaint ID: ");
					int searchid = sc.nextInt();
					sc.nextLine();

					PreparedStatement pt = con.prepareStatement("select * from complaints where complaint_id = ?");
					pt.setInt(1, searchid);
					ResultSet rs = pt.executeQuery();

					if (rs.next()) {
						System.out.println("\nComplaint found!");

						int comp_id = rs.getInt("complaint_id");
						String cname = rs.getString("citizen_name");
						String comp_type = rs.getString("complaint_type");
						String comp_loc = rs.getString("location");
						String status = rs.getString("status");

						System.out.println(
								comp_id + " || " + cname + " || " + comp_type + " || " + comp_loc + " || " + status);
						break;

					} else {
						System.out.println("\nComplaint NOT found!");
						break;
					}

				}

				case 4: {
					System.out.println("Enter the Complaint ID: ");
					int searchid = sc.nextInt();
					sc.nextLine();

					System.out.println("Enter the Updated Status: ");
					String newstatus = sc.nextLine();

					PreparedStatement pt = con
							.prepareStatement("UPDATE complaints SET status = ? WHERE complaint_id = ?");
					pt.setString(1, newstatus);
					pt.setInt(2, searchid);

					pt.executeUpdate();
					System.out.println("Status Updated!");
					break;

				}

				case 5: {
					System.out.println("Enter the Complaint ID: ");
					int searchid = sc.nextInt();
					sc.nextLine();

					PreparedStatement pt = con.prepareStatement("DELETE FROM complaints WHERE complaint_id = ?");
					pt.setInt(1, searchid);
					pt.executeUpdate();

					System.out.println("Record Deleted!");
					break;
				}

				case 6: {
					System.out.println("Exiting the program...");
					break;
				}

				}

			} while (choice != 6);

			con.close();
			sc.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
