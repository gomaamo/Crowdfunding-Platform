package de.unidue.inf.is.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.unidue.inf.is.utils.DBUtil;


public class DBFacade{
	private static DBFacade instance;
	private DBFacade() {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}
		return instance;
	}
	
	public ArrayList<Benutzer> listUsers(){
		
		ArrayList<Benutzer> benutzer= new ArrayList<Benutzer>();
		String sqlSelect = "SELECT * FROM dbp004.benutzer";
		
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
					
							benutzer.add(new Benutzer(rs.getString(1), rs.getString(2),rs.getString(3),getKonto(rs.getString(1)))); 
						
					}
					return benutzer;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public int createP(String title, double fundingLimit, int category, int vorg, String desc, String user) {
		int id=0;
		String sqlInsert = "INSERT INTO dbp004.projekt (kennung, titel, beschreibung, finanzierungslimit, ersteller, vorgaenger, kategorie) VALUES (?,?,?,?,?,?,?)";
		String sqlSelectCount = "SELECT kennung FROM dbp004.projekt ORDER BY kennung DESC FETCH FIRST 1 ROW ONLY";
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert);
					PreparedStatement psC = connection.prepareStatement(sqlSelectCount)) {
				
				try (ResultSet rsC = psC.executeQuery()) {
					if (rsC.next()) {
						
						id+=rsC.getInt(1)+1;
					
						ps.setInt(1, id);
						ps.setString(2, title);
						ps.setString(3, desc);
						ps.setDouble(4, fundingLimit);
						ps.setString(5, user);
						if(vorg==0)
							ps.setNull(6, java.sql.Types.SMALLINT);
						else
							ps.setInt(6, vorg);
						ps.setInt(7, category);
						ps.executeUpdate();
					}
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;

	}
	
	public ArrayList<Project> getUserProjects(String email){
		
		ArrayList<Project> result = new ArrayList<Project>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM dbp004.projekt WHERE ersteller = ?";

		try (Connection connection = DBUtil.getExternalConnection()) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				
				ps.setString(1, email);
				
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
												
						Project temp = new Project(rs.getInt(1), rs.getString(2),rs.getString(3),
								rs.getString(4), rs.getDouble(5),getBenutzer(rs.getString(6)),
								rs.getInt(7),getKategorie(rs.getInt(8)));
						temp.setSpende(getSpende(rs.getInt(1)));
						temp.setKommentar(getKommentar(rs.getInt(1)));
						result.add(temp);
							
						}
						
					}
				}
		} catch (Exception e) {
				e.printStackTrace();
			}
		

		return result;
		
	}
	
	
	public ArrayList<Project> viewOffen() {

		ArrayList<Project> resultOffen = new ArrayList<Project>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM dbp004.projekt WHERE status = ?";

		try (Connection connection = DBUtil.getExternalConnection()) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				
				ps.setString(1, "offen");
				
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
												
						Project temp = new Project(rs.getInt(1), rs.getString(2),rs.getString(3),
								rs.getString(4), rs.getDouble(5),getBenutzer(rs.getString(6)),
								rs.getInt(7),getKategorie(rs.getInt(8)));
						temp.setSpende(getSpende(rs.getInt(1)));
						temp.setKommentar(getKommentar(rs.getInt(1)));
						resultOffen.add(temp);
							
						}
						
					}
				}
		} catch (Exception e) {
				e.printStackTrace();
			}
		

		return resultOffen;
	}
	
	public ArrayList<Project> viewGeschlossen() {

		ArrayList<Project> resultGeschlossen = new ArrayList<Project>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM dbp004.projekt WHERE status = ?";

		try (Connection connection = DBUtil.getExternalConnection()) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				
				ps.setString(1, "geschlossen");
				
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
												
						Project temp = new Project(rs.getInt(1), rs.getString(2),rs.getString(3),
								rs.getString(4), rs.getDouble(5),getBenutzer(rs.getString(6)),
								rs.getInt(7),getKategorie(rs.getInt(8)));
						temp.setSpende(getSpende(rs.getInt(1)));
						temp.setKommentar(getKommentar(rs.getInt(1)));
						resultGeschlossen.add(temp);
							
						}
						
					}
				}
		} catch (Exception e) {
				e.printStackTrace();
			}
		

		return resultGeschlossen;
	}
	
	public ArrayList<Project> view(int id) {

		ArrayList<Project> result =new ArrayList<Project>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM dbp004.projekt WHERE kennung = ? ";

		try (Connection connection = DBUtil.getExternalConnection()) {
			

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {

				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
												
						Project temp = new Project(rs.getInt(1), rs.getString(2),rs.getString(3),
								rs.getString(4), rs.getDouble(5),getBenutzer(rs.getString(6)),
								rs.getInt(7),getKategorie(rs.getInt(8)));
						temp.setSpende(getSpende(rs.getInt(1)));
						temp.setKommentar(getKommentar(rs.getInt(1)));
						result.add(temp);
													
						}
						
					}
				}
		} catch (Exception e) {
				e.printStackTrace();
			}
		

		return result;
	}
	
	public String vorgangerTitel(int id) {
		String sqlSelect = "SELECT titel FROM dbp004.projekt WHERE kennung = ?";
		
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				ps.setInt(1, id);
				
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
					
							return rs.getString(1);
						
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	public Benutzer getBenutzer(String email) {
		
		String sqlSelectB = "SELECT * FROM dbp004.benutzer WHERE email = ?";
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psB = connection.prepareStatement(sqlSelectB)) {
				psB.setString(1, email);
				
				try (ResultSet rsB = psB.executeQuery()) {
					if (rsB.next()) {
					
							return new Benutzer(rsB.getString(1), rsB.getString(2),rsB.getString(3),getKonto(email));
						
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	public Konto getKonto(String email) {
		
		String sqlSelectB = "SELECT * FROM dbp004.konto WHERE inhaber = ?";
		
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psB = connection.prepareStatement(sqlSelectB)) {
				psB.setString(1, email);
				
				try (ResultSet rsB = psB.executeQuery()) {
					if (rsB.next()) {
					
							return new Konto(rsB.getString(1), rsB.getDouble(2),rsB.getString(3));
						
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public Kategorie getKategorie(int id) {
		
	String sqlSelectK = "SELECT * FROM dbp004.kategorie WHERE id = ?";
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psK = connection.prepareStatement(sqlSelectK)) {
				psK.setInt(1, id);
				
				try (ResultSet rsK = psK.executeQuery()) {
					if (rsK.next()) {
					
							return new Kategorie(rsK.getInt(1),rsK.getString(2),rsK.getString(3));
						
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	public ArrayList<Spende> getSpende(int projekt) {
	
	ArrayList<Spende> spende = new ArrayList<Spende>();
	String sqlSelectS = "SELECT * FROM dbp004.spenden WHERE projekt=? ORDER BY spendenbetrag DESC";
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psS = connection.prepareStatement(sqlSelectS)) {
				psS.setInt(1, projekt);				
				try (ResultSet rsS = psS.executeQuery()) {
					while (rsS.next()) {
						
						spende.add(new Spende(rsS.getDouble(3),rsS.getString(4),getBenutzer(rsS.getString(1))));
						
					}
					return spende;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	public ArrayList<Kommentar> getKommentar(int projekt) {
	
	ArrayList<Kommentar> kommentar= new ArrayList<Kommentar>();
	
	String sqlSelectKId = "SELECT * FROM dbp004.schreibt WHERE projekt = ? ORDER BY kommentar DESC";
	String sqlSelectK = "SELECT * FROM dbp004.kommentar WHERE id = ? ";

	try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psKId = connection.prepareStatement(sqlSelectKId);
					PreparedStatement psK = connection.prepareStatement(sqlSelectK)) {
				psKId.setInt(1, projekt);				
				try (ResultSet rsKId = psKId.executeQuery()) {
					while (rsKId.next()) {
						
						psK.setInt(1, rsKId.getInt(3));
						try (ResultSet rsK = psK.executeQuery()) {
							if (rsK.next()) {
								
								kommentar.add(new Kommentar(rsK.getInt(1),rsK.getString(2),rsK.getTimestamp(3),rsK.getString(4),getBenutzer(rsKId.getString(1))));
																
							}
						}
						
					}
					return kommentar;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	public ArrayList<Project> getUserSpende(String email) {
	
	ArrayList<Project> spende = new ArrayList<Project>();
	String sqlSelectS = "SELECT * FROM dbp004.spenden WHERE spender=? ";
		try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psS = connection.prepareStatement(sqlSelectS)) {
				psS.setString(1, email);				
				try (ResultSet rsS = psS.executeQuery()) {
					while (rsS.next()) {
						
						spende.addAll(view(rsS.getInt(2)));
						
					}
					return spende;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	public void editP(String title, double fundingLimit, int category, int vorg, String desc, String user,int id) {
	
	String sqlInsert = "UPDATE dbp004.projekt SET ( titel, beschreibung, finanzierungslimit, ersteller, vorgaenger, kategorie)= (?,?,?,?,?,?) WHERE kennung = ?";

	try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
			
					
					ps.setString(1, title);
					ps.setString(2, desc);
					ps.setDouble(3, fundingLimit);
					ps.setString(4, user);
					if(vorg==0)
					ps.setNull(5, java.sql.Types.SMALLINT);
					else
					ps.setInt(5, vorg);
					ps.setInt(6, category);	
					ps.setInt(7, id);
					ps.executeUpdate();
			}catch (SQLException e) {
					e.printStackTrace();
			}
		}catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void donate(String spender,  int project, double fund, String checkBox) {
	
	String sqlInsert = "INSERT INTO dbp004.spenden (spender, projekt, spendenbetrag, sichtbarkeit) VALUES (?,?,?,?)";

	try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setString(1, spender);
				ps.setInt(2, project);
				ps.setDouble(3, fund);
				ps.setString(4, checkBox);
				ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		}catch (Exception e) {
			e.printStackTrace();
	}

}

	public void updatekonto(String spender, double fund) {
	
	
	String sqlupdate = "UPDATE dbp004.konto SET guthaben=? WHERE inhaber = ?";

	try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement psC = connection.prepareStatement(sqlupdate)) {
				
					psC.setDouble(1, fund);
					psC.setString(2, spender);
					psC.executeUpdate();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}catch (SQLException e) {
			e.printStackTrace();}
}

	public void deleteSpende(int id) {
	String sqlupdate = "DELETE FROM dbp004.spenden  WHERE projekt = ?";
	
	try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlupdate)) {
						ps.setInt(1, id);
						ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (SQLException e) {
			e.printStackTrace();}
}

	public void deleteKommentar(ArrayList<Kommentar> kommentar) {
		
		String sqlDeleteS = "DELETE FROM dbp004.schreibt  WHERE kommentar = ?";
		String sqlDeleteK = "DELETE FROM dbp004.kommentar  WHERE id = ?";
		
		try (Connection connection = DBUtil.getExternalConnection()){
			
			try (PreparedStatement psS = connection.prepareStatement(sqlDeleteS);
					PreparedStatement psK = connection.prepareStatement(sqlDeleteK)) {
				
				for(Kommentar k: kommentar) {
					psS.setInt(1, k.getId());
					psS.executeUpdate();
					psK.setInt(1, k.getId());
					psK.executeUpdate();
				}
			}catch (SQLException e){
				e.printStackTrace();
			}	
		}catch (SQLException e) {
			e.printStackTrace();	
		}

}

	public void deleteProject(int id) {
		
		String sqlDelete = "DELETE FROM dbp004.projekt WHERE kennung = ?";
		String sqlUpdate = "Update dbp004.projekt SET vorgaenger = ? WHERE vorgaenger= ?";

		try (Connection connection = DBUtil.getExternalConnection()){
			
			try (PreparedStatement psD = connection.prepareStatement(sqlDelete);
					PreparedStatement psU = connection.prepareStatement(sqlUpdate)) {
				psU.setNull(1, java.sql.Types.SMALLINT);
				psU.setInt(2, id);
				psU.executeUpdate();
				psD.setInt(1, id);
				psD.executeUpdate();
				connection.commit();
			}catch (SQLException e){
				try {
					connection.rollback();
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();}

}

	public void returnfund(ArrayList<Spende> result) {
	String sqlupdate = "UPDATE dbp004.konto SET guthaben=? WHERE inhaber = ?";

	try (Connection connection = DBUtil.getExternalConnection()){

			try (PreparedStatement ps = connection.prepareStatement(sqlupdate)) {
					
				for(int i=0;i< result.size();i++)
				{
				ps.setDouble(1, result.get(i).getBenutzer().getKonto().getGuthaben());
				ps.setString(2, result.get(i).getBenutzer().getEmail());
				ps.executeUpdate();
				}
			}catch (SQLException e) {
					e.printStackTrace();		
			}
		}catch (SQLException e) {
			e.printStackTrace();}

}

	public void comment(String user, int pd, String comment, String checkBox) {
		// TODO Auto-generated method stub
		int id = 1;
		String sqlInsert1 = "INSERT INTO dbp004.kommentar (id, text, sichtbarkeit) VALUES (?,?,?)";
		String sqlSelectCount = "SELECT id FROM dbp004.kommentar ORDER BY id DESC FETCH FIRST 1 ROW ONLY";
		String sqlInsert2 = "INSERT INTO dbp004.schreibt (benutzer, projekt, kommentar) VALUES (?,?,?)";

		try (Connection connection = DBUtil.getExternalConnection()){

				try (PreparedStatement ps1 = connection.prepareStatement(sqlInsert1);
						PreparedStatement psC = connection.prepareStatement(sqlSelectCount);
						PreparedStatement ps2 = connection.prepareStatement(sqlInsert2)) {
					
					try (ResultSet rsC = psC.executeQuery()) {
						if (rsC.next()) {
							
							id+=rsC.getInt(1);
						
							ps1.setInt(1, id);
							ps1.setString(2, comment);
							ps1.setString(3, checkBox);
							ps1.executeUpdate();
							
							ps2.setString(1, user);
							ps2.setInt(2, pd);
							ps2.setInt(3, id);
							ps2.executeUpdate();
						}
					}catch (SQLException e) {
						e.printStackTrace();
					}
			}
				}catch (Exception e) {
					e.printStackTrace();
				}
		}
	
	public ArrayList<Project> search(String s){
		ArrayList<Project> result = new ArrayList<Project>();
		String sqlSelect = "SELECT kennung FROM dbp004.projekt WHERE LOCATE_IN_STRING(LCASE(titel),?,1)=1";
		
		try (Connection connection = DBUtil.getExternalConnection()){
			
			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				
				ps.setString(1, s);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						
						result.addAll(view(rs.getInt(1)));
						
					}
					return result;
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (SQLException e) {
			e.printStackTrace();	
		}
		
		return null;
		
	}
	
}