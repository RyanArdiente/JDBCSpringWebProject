package web;

import java.util.List;

public interface JDBCSWPDAO
{
	public List<List<String>> searchDB(String username, String password, String sqlstatement);
	public int updateDB(String username, String password, String sqlstatement);
	
}
