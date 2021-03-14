package fr.diginamic.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.Driver;

public class TestConnection {

	public static void main(String[] args) {
		// Etape 1 : chargement du driver de la bdd
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver non trouvé");

		}
		ResourceBundle database = ResourceBundle.getBundle("database");
		String url = database.getString("url");
		String user = database.getString("user");
		String password = database.getString("password");
		try {
			// Etape 2 : je demande au DriverManager de me fournir une connexion à une base
			// de type MariaDB
			Connection connection = DriverManager.getConnection(url, user, password);

			// Etape 3 : création du statement
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into abonne (nom, prenom) values ('MAX', 'MOD')");

			ResultSet curseur = statement.executeQuery("SELECT prenom, nom FROM abonne ORDER BY nom, prenom");
			while (curseur.next()) {
				String prenom = curseur.getString("prenom");
				String nom = curseur.getString("nom");

				System.out.println("Abonne :" + prenom + "," + nom);
			}
			curseur.close();
			statement.close();
			connection.close();

			System.out.println(connection.isClosed());

		} catch (SQLException e) {
			if (e.getErrorCode() == 1045) {
				System.out.println("Erreur bdd" + e.getMessage());
			} else {
				System.out.println("Problème requête" + e.getMessage());
			}
			System.out.println("Erreur" + e.getErrorCode());
		}

	}

}
