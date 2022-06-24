import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
	
public static void main(String[] args) {
	
	Random aleatorio = new Random();
	Integer numeroSorteado = aleatorio.nextInt(1,11);
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoquiz", "root", "146787Gu@");
		Statement statement = conexao.createStatement();
		
		ResultSet resultSet = statement.executeQuery("Select * from perguntas where id = " + numeroSorteado);
		while(resultSet.next()) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.println(resultSet.getString(2));
			}
		
		resultSet = statement.executeQuery("Select * from respostas where idpergunta =" + numeroSorteado +" order by opcao asc");
		while(resultSet.next()) {
				System.out.print(resultSet.getString(3) +" - ");
				System.out.println(resultSet.getString(2));
			}
		
		Scanner entrada = new Scanner(System.in);
		String opcao = entrada.next();
		resultSet = statement.executeQuery("Select * from respostas where idpergunta =" + numeroSorteado + " and opcao = '" + opcao+ "'");
		entrada.close();
		
		while(resultSet.next()) {
			if(resultSet.getBoolean(4)) {
				System.out.println("RESPOSTA CORRETA!");
			} else {
				System.out.println("RESPOSTA ERRADA!");
				System.out.println("RESPOSTA CORRETA É: ");
			}
		}
		
		resultSet = statement.executeQuery("Select * from respostas where idpergunta =" + numeroSorteado + " and correto =" + true);
		while(resultSet.next()) {
			System.out.print(resultSet.getString(3) + " - ");
			System.out.println(resultSet.getString(2));
		}
			
	} catch (Exception e) {
	System.out.println(e);
	}
	
}
}
