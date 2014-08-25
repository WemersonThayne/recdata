package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Datashow;
import web.recdata.model.Entidade;
import web.recdata.model.Item;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class DatashowDAO implements DAO{

	// a conex�o com o banco de dados
	public Connection connection;

	private ItemDAO itemDAO;

	public DatashowDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
		itemDAO = new ItemDAO(banco);
	}

	public ArrayList<Entidade> listarTodos() throws SQLException{
		ArrayList<Entidade> itens = new ArrayList<Entidade>();
		// e passado por parrametro um objeto generico para facilitar a nossa vida ;)
		String sql = String
		.format("%s",
				"SELECT idItem, statusItem,Descri��o_Datashow FROM `item` I,`datashow` D " +
				"WHERE I.idItem =D.idItem_Datashow");
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setStatusItem(rs.getBoolean("statusItem"));
			Datashow datashow = new Datashow();
			datashow.setDescricaoDatashow("Descri��o_Datashow");
			itens.add(item);
			itens.add(datashow);//add o item datashow
		}
		
		return itens;
	}
	
	@Override
	public void creat(Entidade entidade) {

		if (entidade instanceof Datashow) {

			Datashow datashowItem = (Datashow) entidade;

			int chave = itemDAO.creat(datashowItem);

			if (chave != 0) {

				try {
					
					/*
					 *`idUsuario_Professor` INT NOT NULL AUTO_INCREMENT,
					 * `Forma��o_Professor` VARCHAR(40) NOT NULL,
					 */

					String sql = "INSERT INTO `datashow` (`idItem_Datashow`,`Descri��o_Datashow`)"
							+ " VALUES (?, ?)";

					// prepared statement para inser��o
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, datashowItem.getDescricaoDatashow());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("N�o foi poss�vel inserir Datashow!");

		} 

	}

	@Override
	public void readById(Entidade entidade) {
		if (entidade instanceof Datashow) {

			Datashow datashow = (Datashow) entidade;

			try {
				/*
				 * ` `idItem_Chave` INT NOT NULL AUTO_INCREMENT,
				 * `Local_Chave`VARCHAR(30) NOT NULL, `Descri��oChave`
				 * VARCHAR(40) NOT NULL,
				 */
				String sql = "SELECT * FROM `datashow` D "
							 + "INNER JOIN `item` I ON D.`idItem_Datashow` = I.`idItem` "
							 + " WHERE C.`idItem_Datashow` =" + datashow.getIdItem();

				// prepared statement para inser��o
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					datashow.setIdItem(rs.getInt("idItem"));
					datashow.setStatusItem(rs.getInt("statusItem") == 1 ? true: false  );
					datashow.setDescricaoDatashow(rs.getString("Descri��o_Datashow"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} 
	}

	@Override
	public void update(Entidade entidade)  {
		if (entidade instanceof Datashow) {

			Datashow datashow = (Datashow) entidade;

			itemDAO.update(datashow);

			try {

				String sql = "UPDATE `datashow` SET `Descri��o_Datashow`=?"
						+ " WHERE `idItem_Datashow`=?";

				// prepared statement para inser��o
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, datashow.getDescricaoDatashow());

				// envia para o Banco e fecha o objeto
				stmt.execute();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} 
	}

	@Override
	public void delete(Entidade entidade){
		if (entidade instanceof Datashow) {

			Datashow datashow = (Datashow) entidade;

			try {

				// Deleta uma tupla setando o atributo de identifica��o com
				// valor representado por ?
				String sql = "DELETE FROM `datashow` WHERE `idItem_Datashow`=?";

				// prepared statement para inser��o
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, datashow.getIdItem());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				itemDAO.delete(datashow);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}

}
