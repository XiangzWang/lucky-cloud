package org.bamboo.oracle.test;

import java.sql.SQLException;
import java.text.ParseException;

import org.bamboo.oracle.tools.PartitionUtils;

public class PartitionTest {
	
	public static void main(String[] args) {
		String tablespace = "SEA";
		String tableName = "E_MP_DAY_READ";
		String fromDateStr = "20171008";
		int days = 20;
		String[] areaCodeArray = {"01", "02", "03", "04", "05"};
		try {
			PartitionUtils.addPartition(tablespace, tableName, fromDateStr, days, areaCodeArray);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
